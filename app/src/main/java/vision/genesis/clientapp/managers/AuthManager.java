package vision.genesis.clientapp.managers;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.RecoveryCodesViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.RegisterManagerViewModel;
import io.swagger.client.model.TwoFactorAuthenticator;
import io.swagger.client.model.TwoFactorAuthenticatorConfirm;
import io.swagger.client.model.TwoFactorCodeModel;
import io.swagger.client.model.TwoFactorStatus;
import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import timber.log.Timber;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.OnUnauthorizedResponseGetEvent;
import vision.genesis.clientapp.utils.SharedPreferencesUtil;
import vision.genesis.clientapp.utils.fingerprint.FingerprintHandler;
import vision.genesis.clientapp.utils.fingerprint.GenerateKeyCipher;

import static android.content.Context.KEYGUARD_SERVICE;

/**
 * GenesisVision
 * Created by Vitaly on 1/22/18.
 */

public class AuthManager
{
	public static BehaviorSubject<String> token = BehaviorSubject.create();

	public BehaviorSubject<User> userSubject = BehaviorSubject.create();

	private FingerprintManager fingerprintManager;

	private KeyguardManager keyguardManager;

	private BehaviorSubject<String> getTokenResponseSubject = BehaviorSubject.create();

	private Subscription getTokenSubscription;

	private InvestorApi investorApi;

	private ManagerApi managerApi;

	private SharedPreferencesUtil sharedPreferencesUtil;

	private SettingsManager settingsManager;

	private Subscription getTwoFactorStatusSubscription;

	public AuthManager(InvestorApi investorApi, ManagerApi managerApi, SharedPreferencesUtil sharedPreferencesUtil, SettingsManager settingsManager) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;
		this.sharedPreferencesUtil = sharedPreferencesUtil;
		this.settingsManager = settingsManager;

		keyguardManager = (KeyguardManager) GenesisVisionApplication.INSTANCE.getSystemService(KEYGUARD_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			this.fingerprintManager = (FingerprintManager) GenesisVisionApplication.INSTANCE.getSystemService(Context.FINGERPRINT_SERVICE);
		}

		EventBus.getDefault().register(this);

		userSubject.onNext(null);
		tryGetSavedToken();
	}

	private void tryGetSavedToken() {
		String token = sharedPreferencesUtil.getToken();
		if (token != null) {
			Timber.d("TOKEN: %s", token);
			AuthManager.token.onNext(token);
			getTwoFactorStatus();
		}
	}

	public Observable<String> login(String email, String password, String tfaCode, boolean useRecoveryCode) {
		LoginViewModel model = new LoginViewModel();
		model.setEmail(email);
		model.setPassword(password);
		if (useRecoveryCode)
			model.setRecoveryCode(tfaCode);
		else
			model.setTwoFactorCode(tfaCode);
		model.rememberMe(true);

		getToken(getLoginApiObservable(model));
		return getTokenResponseSubject;
	}

	private void getToken(Observable<String> apiMethodObservable) {
		getTokenResponseSubject = BehaviorSubject.create();
		getTokenSubscription = apiMethodObservable
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.io())
				.subscribe(this::handleGetTokenResponse,
						error -> {
							getTokenSubscription.unsubscribe();
							logout();
							getTokenResponseSubject.onError(error);
						});
	}

	private void handleGetTokenResponse(String token) {
		String newToken = "Bearer " + token;
		sharedPreferencesUtil.saveToken(newToken);
		AuthManager.token.onNext(newToken);
		Timber.d("TOKEN: %s", newToken);
		getTokenResponseSubject.onNext(newToken);
		getTwoFactorStatus();
	}

	private void getTwoFactorStatus() {
		getTwoFactorStatusSubscription = twoFactorStatus()
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.io())
				.subscribe(response -> {
							getTwoFactorStatusSubscription.unsubscribe();
							User user = new User();
							user.setTwoFactorStatus(response.isTwoFactorEnabled());
							settingsManager.setTwoFactorEnabled(response.isTwoFactorEnabled());
							userSubject.onNext(user);
						},
						error -> {
							getTwoFactorStatusSubscription.unsubscribe();
							logout();
						});
	}

	public void setTwoFactorStatus(boolean enabled) {
		User user = userSubject.getValue();
		settingsManager.setTwoFactorEnabled(enabled);
		if (user != null) {
			user.setTwoFactorStatus(enabled);
			userSubject.onNext(user);
		}
	}

	private Observable<TwoFactorStatus> twoFactorStatus() {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorAuth2faGet(AuthManager.token.getValue())
				: managerApi.apiManagerAuth2faGet(AuthManager.token.getValue());
	}

	public Observable<TwoFactorAuthenticator> createTfaKey() {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorAuth2faCreatePost(AuthManager.token.getValue())
				: managerApi.apiManagerAuth2faCreatePost(AuthManager.token.getValue());
	}

	public Observable<RecoveryCodesViewModel> confirmTfa(String sharedKey, String password, String code) {
		TwoFactorAuthenticatorConfirm model = new TwoFactorAuthenticatorConfirm();
		model.setSharedKey(sharedKey);
		model.setPassword(password);
		model.setCode(code);
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorAuth2faConfirmPost(AuthManager.token.getValue(), model)
				: managerApi.apiManagerAuth2faConfirmPost(AuthManager.token.getValue(), model);
	}

	public Observable<Void> disableTfa(String password, String code) {
		TwoFactorCodeModel model = new TwoFactorCodeModel();
		model.setPassword(password);
		model.setTwoFactorCode(code);
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorAuth2faDisablePost(AuthManager.token.getValue(), model)
				: managerApi.apiManagerAuth2faDisablePost(AuthManager.token.getValue(), model);
	}

	public Observable<Void> register(String email, String password, String confirmPassword) {
		return BuildConfig.FLAVOR.equals("investor")
				? registerInvestor(email, password, confirmPassword)
				: registerManager(email, password, confirmPassword);
	}

	private Observable<Void> registerInvestor(String email, String password, String confirmPassword) {
		RegisterInvestorViewModel model = new RegisterInvestorViewModel();
		model.setEmail(email);
		model.setPassword(password);
		model.setConfirmPassword(confirmPassword);
		return investorApi.apiInvestorAuthSignUpPost(model);
	}

	private Observable<Void> registerManager(String email, String password, String confirmPassword) {
		RegisterManagerViewModel model = new RegisterManagerViewModel();
		model.setEmail(email);
		model.setPassword(password);
		model.setConfirmPassword(confirmPassword);
		return managerApi.apiManagerAuthSignUpPost(model);
	}

	public Observable<Void> sendForgotPassword(String email) {
		ForgotPasswordViewModel model = new ForgotPasswordViewModel();
		model.setEmail(email);
		return investorApi.apiInvestorAuthForgotPasswordPost(model);
	}

	public Observable<Void> sendChangePassword(String oldPassword, String newPassword, String confirmPassword) {
		ChangePasswordViewModel model = new ChangePasswordViewModel();
		model.setOldPassword(oldPassword);
		model.setPassword(newPassword);
		model.setConfirmPassword(confirmPassword);
		return investorApi.apiInvestorAuthChangePasswordPost(AuthManager.token.getValue(), model);
	}

	private Observable<String> getUpdateTokenObservable() {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorAuthUpdateTokenGet(AuthManager.token.getValue())
				: managerApi.apiManagerAuthUpdateTokenGet(AuthManager.token.getValue());
	}

	private Observable<String> getLoginApiObservable(LoginViewModel model) {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorAuthSignInPost(model)
				: managerApi.apiManagerAuthSignInPost(model);
	}

	public void logout() {
		sharedPreferencesUtil.saveToken(null);
		AuthManager.token.onNext(null);
		settingsManager.logout();
		userSubject.onNext(null);
	}

	@Subscribe
	public void onEventMainThread(OnUnauthorizedResponseGetEvent event) {
		logout();
	}

	public boolean haveUpdate(int lastVersion) {
		return (BuildConfig.VERSION_CODE < lastVersion && lastVersion != getIgnoredVersionUpdate());
	}

	private int getIgnoredVersionUpdate() {
		return sharedPreferencesUtil.getIgnoredVersionUpdate();
	}

	public void setIgnoredVersionUpdate(int versionCode) {
		sharedPreferencesUtil.saveIgnoredVersionUpdate(versionCode);
	}

	public boolean isNeedShowEnableTwoFactor() {
		return !userSubject.getValue().getTwoFactorStatus() && !sharedPreferencesUtil.isEnableTwoFactorAlreadyShown();
	}

	public void setEnableTwoFactorAlreadyShown(boolean shown) {
		sharedPreferencesUtil.setEnableTwoFactorAlreadyShown(shown);
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	public boolean hasFingerprintFeature() {
		return fingerprintManager != null && fingerprintManager.isHardwareDetected()
				&& (ActivityCompat.checkSelfPermission(GenesisVisionApplication.INSTANCE, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED);
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	public boolean hasEnrolledFingerprints() {
		return fingerprintManager.hasEnrolledFingerprints();
	}

	public boolean isKeyguardSecure() {
		return keyguardManager.isKeyguardSecure();
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	public boolean generateFingerprintKey() {
		GenerateKeyCipher generateKeyCipher = new GenerateKeyCipher();
		return generateKeyCipher.generateKey();
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	public CancellationSignal startFingerprintAuth(FingerprintHandler fingerprintHandler) {
		GenerateKeyCipher generateKeyCipher = new GenerateKeyCipher();
		if (generateKeyCipher.cipherInit()) {
			FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(generateKeyCipher.getCipher());
			return fingerprintHandler.startAuth(fingerprintManager, cryptoObject);
		}
		else {
			return null;
		}
	}
}
