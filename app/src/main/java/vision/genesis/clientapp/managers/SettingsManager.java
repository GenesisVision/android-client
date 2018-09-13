package vision.genesis.clientapp.managers;


import io.swagger.client.api.PlatformApi;
import io.swagger.client.model.PlatformInfo;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SettingsModel;
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
	private PlatformApi platformApi;

	private BehaviorSubject<SettingsModel> settingsSubject = BehaviorSubject.create();

	private BehaviorSubject<CurrencyEnum> baseCurrencySubject = BehaviorSubject.create();

	private BehaviorSubject<DateRange> dateRangeSubject = BehaviorSubject.create();

	private volatile BehaviorSubject<PlatformInfo> platformInfoBehaviorSubject;

	private Subscription getPlatformInfoSubscription;

	private SharedPreferencesUtil sharedPreferencesUtil;

	public SettingsManager(PlatformApi platformApi, SharedPreferencesUtil sharedPreferencesUtil) {
		this.platformApi = platformApi;
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

	public Observable<PlatformInfo> getPlatformInfo() {
		if (platformInfoBehaviorSubject == null) {
			platformInfoBehaviorSubject = BehaviorSubject.create();
			getPlatformInfoSubscription = platformStatus()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetPlatformInfoSuccess,
							this::handleGetPlatformInfoError);
		}
		return platformInfoBehaviorSubject;
	}

	private Observable<PlatformInfo> platformStatus() {
		return platformApi.v10PlatformInfoGet();
	}

	private void handleGetPlatformInfoSuccess(PlatformInfo response) {
		getPlatformInfoSubscription.unsubscribe();
		platformInfoBehaviorSubject.onNext(response);
	}

	private void handleGetPlatformInfoError(Throwable error) {
		if (platformInfoBehaviorSubject != null) {
			getPlatformInfoSubscription.unsubscribe();
			platformInfoBehaviorSubject.onError(error);
			platformInfoBehaviorSubject = null;
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

	public BehaviorSubject<DateRange> getDateRange() {
		if (dateRangeSubject.getValue() == null) {
			DateRange dateRange = getSavedDateRange();
			dateRange.updateDates(dateRange.getSelectedRange());
			dateRangeSubject.onNext(dateRange);
		}
		return dateRangeSubject;
	}

	private DateRange getSavedDateRange() {
		return new DateRange(sharedPreferencesUtil.getDateRange(),
				sharedPreferencesUtil.getDateRangeFrom(),
				sharedPreferencesUtil.getDateRangeTo());
	}

	public void saveDateRange(DateRange dateRange) {
		sharedPreferencesUtil.saveDateRange(dateRange.getSelectedRange().toString(),
				dateRange.getFrom().getMillis(),
				dateRange.getTo().getMillis());
		dateRangeSubject.onNext(dateRange);
	}

	public BehaviorSubject<CurrencyEnum> getBaseCurrency() {
		if (baseCurrencySubject.getValue() == null)
			baseCurrencySubject.onNext(CurrencyEnum.fromValue(sharedPreferencesUtil.getCurrency()));
		return baseCurrencySubject;
	}

	public void saveBaseCurrency(CurrencyEnum currency) {
		sharedPreferencesUtil.saveCurrency(currency.getValue());
		baseCurrencySubject.onNext(currency);
	}


}