package vision.genesis.clientapp.managers;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.swagger.client.api.AuthApi;
import io.swagger.client.api.PlatformApi;
import io.swagger.client.model.CaptchaCheckResult;
import io.swagger.client.model.CaptchaDetails;
import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.RecoveryCodesViewModel;
import io.swagger.client.model.RegisterViewModel;
import io.swagger.client.model.TwoFactorAuthenticator;
import io.swagger.client.model.TwoFactorAuthenticatorConfirm;
import io.swagger.client.model.TwoFactorCodeWithPassword;
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

	private AuthApi authApi;

	private PlatformApi platformApi;

	private SharedPreferencesUtil sharedPreferencesUtil;

	private SettingsManager settingsManager;

	private Subscription getTwoFactorStatusSubscription;

	public AuthManager(AuthApi authApi, PlatformApi platformApi, SharedPreferencesUtil sharedPreferencesUtil, SettingsManager settingsManager) {
		this.authApi = authApi;
		this.platformApi = platformApi;
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

	public Observable<String> login(String email, String password, String tfaCode, boolean useRecoveryCode, CaptchaCheckResult captchaCheckResult) {
		LoginViewModel model = new LoginViewModel();
		model.setEmail(email);
		model.setPassword(password);
		if (useRecoveryCode) {
			model.setRecoveryCode(tfaCode);
		}
		else {
			model.setTwoFactorCode(tfaCode);
		}
		model.rememberMe(true);
		model.setClient("Android");
		model.setCaptchaCheckResult(captchaCheckResult);

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
		saveNewToken(token);
		getTwoFactorStatus();
	}

	public void saveNewToken(String token) {
		String newToken = "Bearer " + token;
		sharedPreferencesUtil.saveToken(newToken);
		AuthManager.token.onNext(newToken);
		Timber.d("TOKEN: %s", newToken);
		getTokenResponseSubject.onNext(newToken);
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
		return authApi.getTwoStepAuthStatus();
	}

	public Observable<TwoFactorAuthenticator> createTfaKey() {
		return authApi.createTwoStepAuth();
	}

	public Observable<RecoveryCodesViewModel> confirmTfa(String sharedKey, String password, String code) {
		TwoFactorAuthenticatorConfirm model = new TwoFactorAuthenticatorConfirm();
		model.setSharedKey(sharedKey);
		model.setPassword(password);
		model.setCode(code);
		return authApi.confirmTwoStepAuth(model);
	}

	public Observable<Void> disableTfa(String password, String code) {
		TwoFactorCodeWithPassword model = new TwoFactorCodeWithPassword();
		model.setPassword(password);
		model.setTwoFactorCode(code);
		return authApi.disableTwoStepAuth(model);
	}

	public Observable<Void> register(String email, String password, String confirmPassword, CaptchaCheckResult captchaCheckResult) {
		RegisterViewModel model = new RegisterViewModel();
		model.setEmail(email);
		model.setPassword(password);
		model.setConfirmPassword(confirmPassword);
		model.setCaptchaCheckResult(captchaCheckResult);
		return authApi.register(model);
	}

	public Observable<Void> sendForgotPassword(String email, CaptchaCheckResult captchaCheckResult) {
		ForgotPasswordViewModel model = new ForgotPasswordViewModel();
		model.setEmail(email);
		model.setCaptchaCheckResult(captchaCheckResult);
		return authApi.forgotPassword(model);
	}

	public Observable<String> sendChangePassword(String oldPassword, String newPassword, String confirmPassword) {
		ChangePasswordViewModel model = new ChangePasswordViewModel();
		model.setOldPassword(oldPassword);
		model.setPassword(newPassword);
		model.setConfirmPassword(confirmPassword);
		return authApi.changePassword(model);
	}

	private Observable<String> getLoginApiObservable(LoginViewModel model) {
		return authApi.authorize(model);
	}

	public Observable<CaptchaDetails> checkRiskControl(String route) {
		return platformApi.getRiskControlInfo(route, "Android", BuildConfig.VERSION_NAME);
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
