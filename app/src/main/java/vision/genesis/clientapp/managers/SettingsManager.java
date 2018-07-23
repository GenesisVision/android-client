package vision.genesis.clientapp.managers;


import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.PlatformStatus;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.SettingsModel;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.SharedPreferencesUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.hash.HashGenerationException;
import vision.genesis.clientapp.utils.hash.HashGeneratorUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

public class SettingsManager
{
	private InvestorApi investorApi;

	private ManagerApi managerApi;

	private BehaviorSubject<SettingsModel> settingsSubject = BehaviorSubject.create();

	private volatile BehaviorSubject<PlatformStatus> platformStatusBehaviorSubject;

	private Subscription getPlatformStatusSubscription;

	private SharedPreferencesUtil sharedPreferencesUtil;

	public SettingsManager(InvestorApi investorApi, ManagerApi managerApi, SharedPreferencesUtil sharedPreferencesUtil) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;
		this.sharedPreferencesUtil = sharedPreferencesUtil;
		settingsSubject.onNext(new SettingsModel());
		getSettings();
	}

	private void getSettings() {
		SettingsModel settingsModel = settingsSubject.getValue();
		settingsModel.setPinCodeEnabled(sharedPreferencesUtil.getPinCodeEnabled());
		settingsModel.setFingerprintEnabled(sharedPreferencesUtil.getFingerprintEnabled());
		settingsModel.setTheme(sharedPreferencesUtil.getTheme());
		settingsSubject.onNext(settingsModel);
		ThemeUtil.setTheme(settingsModel.getTheme());
	}

	public void setTwoFactorEnabled(boolean enabled) {
		sharedPreferencesUtil.setTwoFactorEnabled(enabled);
		SettingsModel settingsModel = settingsSubject.getValue();
		settingsModel.setTwoFactorEnabled(enabled);
		settingsSubject.onNext(settingsModel);
	}

	public void setPinCodeEnabled(boolean enabled) {
		sharedPreferencesUtil.setPinCodeEnabled(enabled);
		SettingsModel settingsModel = settingsSubject.getValue();
		settingsModel.setPinCodeEnabled(enabled);
		settingsSubject.onNext(settingsModel);
	}

	public boolean getFingerprintEnabled() {
		return settingsSubject.getValue().isFingerprintEnabled();
	}

	public void setFingerprintEnabled(boolean enabled) {
		sharedPreferencesUtil.setFingerprintEnabled(enabled);
		SettingsModel settingsModel = settingsSubject.getValue();
		settingsModel.setFingerprintEnabled(enabled);
		settingsSubject.onNext(settingsModel);
	}

	public void setTheme(String newTheme) {
		ThemeUtil.setTheme(newTheme);
		sharedPreferencesUtil.setTheme(newTheme);
		SettingsModel settingsModel = settingsSubject.getValue();
		settingsModel.setTheme(newTheme);
		settingsSubject.onNext(settingsModel);
	}

	public boolean setPin(String pin) {
		try {
			String hashedPin = HashGeneratorUtil.generateSHA256(pin);
			sharedPreferencesUtil.setPinHash(hashedPin);
			return true;
		} catch (HashGenerationException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkPin(String pin) {
		String savedPinHash = sharedPreferencesUtil.getPinHash();
		try {
			String hashedPin = HashGeneratorUtil.generateSHA256(pin);
			return hashedPin.equals(savedPinHash);
		} catch (HashGenerationException e) {
			e.printStackTrace();
			return false;
		}
	}

	public BehaviorSubject<SettingsModel> getSettingsSubject() {
		return settingsSubject;
	}

	public Observable<PlatformStatus> getPlatformStatus() {
		if (platformStatusBehaviorSubject == null) {
			platformStatusBehaviorSubject = BehaviorSubject.create();
			getPlatformStatusSubscription = platformStatus()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetPlatformStatusSuccess,
							this::handleGetPlatformStatusError);
		}
		return platformStatusBehaviorSubject;
	}

	private Observable<PlatformStatus> platformStatus() {
		return Constants.IS_INVESTOR
				? investorApi.apiInvestorPlatformStatusGet()
				: managerApi.apiManagerPlatformStatusGet();
	}

	private void handleGetPlatformStatusSuccess(PlatformStatus response) {
		getPlatformStatusSubscription.unsubscribe();
		platformStatusBehaviorSubject.onNext(response);
	}

	private void handleGetPlatformStatusError(Throwable error) {
		if (platformStatusBehaviorSubject != null) {
			getPlatformStatusSubscription.unsubscribe();
			platformStatusBehaviorSubject.onError(error);
			platformStatusBehaviorSubject = null;
		}
	}

	public void logout() {
		settingsSubject.onNext(new SettingsModel());
		sharedPreferencesUtil.setTwoFactorEnabled(false);
		sharedPreferencesUtil.setPinCodeEnabled(false);
	}

	public boolean isScreenLockEnabled() {
		return settingsSubject.getValue().isPinCodeEnabled();
	}
}