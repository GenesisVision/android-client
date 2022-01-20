package vision.genesis.clientapp.managers;

import static android.content.Context.KEYGUARD_SERVICE;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.google.firebase.iid.FirebaseInstanceId;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.swagger.client.api.AuthApi;
import io.swagger.client.api.PlatformApi;
import io.swagger.client.api.ProfileApi;
import io.swagger.client.model.AppPlatform;
import io.swagger.client.model.CaptchaCheckResult;
import io.swagger.client.model.CaptchaDetails;
import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.FcmTokenViewModel;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.RecoveryCodesViewModel;
import io.swagger.client.model.RegisterViewModel;
import io.swagger.client.model.ThreeFactorAuthenticatorConfirm;
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

	private Subscription updateFcmTokenSubscription;

	private Subscription disableFcmTokenSubscription;

	private Subscription getTwoFactorStatusSubscription;

	private AuthApi authApi;

	private ProfileApi profileApi;

	private PlatformApi platformApi;

	private SharedPreferencesUtil sharedPreferencesUtil;

	private SettingsManager settingsManager;

	private TerminalManager terminalManager;

	private String fcmToken = "";

	public AuthManager(AuthApi authApi, ProfileApi profileApi, PlatformApi platformApi, SharedPreferencesUtil sharedPreferencesUtil, SettingsManager settingsManager, TerminalManager terminalManager) {
		this.authApi = authApi;
		this.profileApi = profileApi;
		this.platformApi = platformApi;
		this.sharedPreferencesUtil = sharedPreferencesUtil;
		this.settingsManager = settingsManager;
		this.terminalManager = terminalManager;

		keyguardManager = (KeyguardManager) GenesisVisionApplication.INSTANCE.getSystemService(KEYGUARD_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			this.fingerprintManager = (FingerprintManager) GenesisVisionApplication.INSTANCE.getSystemService(Context.FINGERPRINT_SERVICE);
		}

		EventBus.getDefault().register(this);

		tryGetSavedToken();
		tryGetFcmToken();
	}

	private void tryGetFcmToken() {
		fcmToken = sharedPreferencesUtil.getFcmToken();
		if (fcmToken == null || fcmToken.isEmpty()) {
			FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(instanceIdResult -> {
				if (instanceIdResult != null) {
					fcmToken = instanceIdResult.getToken();
					if (!fcmToken.isEmpty()) {
						Timber.d("FCM_TOKEN: %s", fcmToken);
						updateFcmTokenMaybe(fcmToken);
					}
				}
			});
		}
		else {
			Timber.d("FCM_TOKEN: %s", fcmToken);
			updateFcmTokenMaybe(fcmToken);
		}
	}

	private void tryGetSavedToken() {
		String token = sharedPreferencesUtil.getToken();
		if (token != null) {
			Timber.d("TOKEN: %s", token);
			AuthManager.token.onNext(token);
			getTwoFactorStatus();
		}
		else {
			userSubject.onNext(null);
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

	public void handleGetTokenResponse(String token) {
		saveNewToken(token);
		if (fcmToken != null && !fcmToken.isEmpty()) {
			updateFcmTokenMaybe(fcmToken);
		}
		getTwoFactorStatus();
	}

	public void saveNewToken(String token) {
		String newToken = "Bearer " + token;
		sharedPreferencesUtil.saveToken(newToken);
		AuthManager.token.onNext(newToken);
		Timber.d("TOKEN: %s", newToken);
	}

	public void updateFcmTokenMaybe(String token) {
		this.fcmToken = token;
		saveNewFcmToken(token);

		if (AuthManager.token.getValue() != null) {

			FcmTokenViewModel model = new FcmTokenViewModel();
			model.setToken(token);
			model.setPlatform(AppPlatform.ANDROID);

			if (updateFcmTokenSubscription != null) {
				updateFcmTokenSubscription.unsubscribe();
			}

			updateFcmTokenSubscription = profileApi.updateFcmToken(model)
					.observeOn(Schedulers.newThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> updateFcmTokenSubscription.unsubscribe(),
							throwable -> updateFcmTokenSubscription.unsubscribe());

		}
	}

	private void disableFcmToken() {
		if (AuthManager.token.getValue() != null && fcmToken != null && !fcmToken.isEmpty()) {
			if (disableFcmTokenSubscription != null) {
				disableFcmTokenSubscription.unsubscribe();
			}

			FcmTokenViewModel model = new FcmTokenViewModel();
			model.setToken(fcmToken);
			model.setPlatform(AppPlatform.ANDROID);

			disableFcmTokenSubscription = profileApi.removeFcmToken(model)
					.observeOn(Schedulers.newThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> finishLogout(),
							throwable -> finishLogout());
		}
	}

	private void finishLogout() {
		disableFcmTokenSubscription.unsubscribe();
		AuthManager.token.onNext(null);
	}

	private void saveNewFcmToken(String newFcmToken) {
		sharedPreferencesUtil.saveFcmToken(newFcmToken);
		Timber.d("FCM_TOKEN: %s", newFcmToken);
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
							getTokenResponseSubject.onNext("");
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

	public Observable<Void> register(String username, String email, String password, String confirmPassword, CaptchaCheckResult captchaCheckResult) {
		RegisterViewModel model = new RegisterViewModel();
		model.setUserName(username);
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

	public Observable<String> confirmThreeStepAuth(ThreeFactorAuthenticatorConfirm body) {
		return authApi.confirmThreeStepAuth(body);
	}

	public Observable<String> logoutFromOtherDevices() {
		return authApi.logoutFromAnotherDevices();
	}

	public void logout() {
		disableFcmToken();
		sharedPreferencesUtil.saveToken(null);
		settingsManager.logout();
		terminalManager.logout();
		userSubject.onNext(null);
	}

	@Subscribe
	public void onEventMainThread(OnUnauthorizedResponseGetEvent event) {
		AuthManager.token.onNext(null);
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

	public void saveTempToken(String tempToken) {
		sharedPreferencesUtil.saveTempToken(tempToken);
	}

	public String getTempToken() {
		return sharedPreferencesUtil.getTempToken();
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
