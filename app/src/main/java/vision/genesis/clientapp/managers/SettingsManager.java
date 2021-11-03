package vision.genesis.clientapp.managers;


import java.util.Objects;

import io.swagger.client.api.PlatformApi;
import io.swagger.client.api.ProfileApi;
import io.swagger.client.model.AssetInfo;
import io.swagger.client.model.Currency;
import io.swagger.client.model.DashboardAssetStatus;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.ProgramsLevelsInfo;
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

	private ProfileApi profileApi;

	private BehaviorSubject<SettingsModel> settingsSubject = BehaviorSubject.create();

	private BehaviorSubject<CurrencyEnum> baseCurrencySubject = BehaviorSubject.create();

	private BehaviorSubject<DateRange> dateRangeSubject = BehaviorSubject.create();

	private volatile BehaviorSubject<PlatformInfo> platformInfoBehaviorSubject;

	private Subscription getPlatformInfoSubscription;

	private Subscription getProfileSubscription;

	private SharedPreferencesUtil sharedPreferencesUtil;

	public SettingsManager(PlatformApi platformApi, ProfileApi profileApi, SharedPreferencesUtil sharedPreferencesUtil) {
		this.platformApi = platformApi;
		this.profileApi = profileApi;
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

	void setTwoFactorEnabled(boolean enabled) {
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
			getPlatformInfoSubscription = platformApi.getPlatformInfo()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetPlatformInfoSuccess,
							this::handleGetPlatformInfoError);
		}
		return platformInfoBehaviorSubject;
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

	void logout() {
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

	public DashboardAssetStatus getSavedTradingPrivateStatus() {
		return DashboardAssetStatus.fromValue(sharedPreferencesUtil.getTradingPrivateStatus());
	}

	public void saveTradingPrivateStatus(DashboardAssetStatus tradingPrivateStatus) {
		sharedPreferencesUtil.saveTradingPrivateStatus(tradingPrivateStatus.getValue());
	}

	public DashboardAssetStatus getSavedTradingPublicStatus() {
		return DashboardAssetStatus.fromValue(sharedPreferencesUtil.getTradingPublicStatus());
	}

	public void saveTradingPublicStatus(DashboardAssetStatus tradingPublicStatus) {
		sharedPreferencesUtil.saveTradingPublicStatus(tradingPublicStatus.getValue());
	}

	public DashboardAssetStatus getSavedInvestmentsProgramsStatus() {
		return DashboardAssetStatus.fromValue(sharedPreferencesUtil.getInvestmentsProgramsStatus());
	}

	public void saveInvestmentsProgramsStatus(DashboardAssetStatus investmentsProgramsStatus) {
		sharedPreferencesUtil.saveInvestmentsProgramsStatus(investmentsProgramsStatus.getValue());
	}

	public DashboardAssetStatus getSavedInvestmentsFundsStatus() {
		return DashboardAssetStatus.fromValue(sharedPreferencesUtil.getInvestmentsFundsStatus());
	}

	public void saveInvestmentsFundsStatus(DashboardAssetStatus investmentsFundsStatus) {
		sharedPreferencesUtil.saveInvestmentsFundsStatus(investmentsFundsStatus.getValue());
	}

	public BehaviorSubject<CurrencyEnum> getBaseCurrency() {
		if (baseCurrencySubject.getValue() == null && (getProfileSubscription == null || getProfileSubscription.isUnsubscribed())) {
			getProfileSubscription = profileApi.getProfileFull()
					.observeOn(Schedulers.newThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(profile -> {
								getProfileSubscription.unsubscribe();
								if (profile.getPlatformCurrency() == null || profile.getPlatformCurrency().equals(Currency.UNDEFINED)) {
									saveBaseCurrency(Objects.requireNonNull(CurrencyEnum.fromValue(sharedPreferencesUtil.getCurrency())));
								}
								else {
									saveBaseCurrency(Objects.requireNonNull(CurrencyEnum.fromValue(profile.getPlatformCurrency().getValue())));
								}
							},
							throwable -> {
								getProfileSubscription.unsubscribe();
								saveBaseCurrency(Objects.requireNonNull(CurrencyEnum.fromValue(sharedPreferencesUtil.getCurrency())));
							});
		}
		return baseCurrencySubject;
	}

	public void saveBaseCurrency(CurrencyEnum currency) {
		sharedPreferencesUtil.saveCurrency(currency.getValue());
		baseCurrencySubject.onNext(currency);
	}

	public Observable<ProgramsLevelsInfo> getLevelsInfo(String currency) {
		return platformApi.getProgramLevels(Currency.fromValue(currency));
	}

	public void saveShowEvents(boolean showEvents) {
		sharedPreferencesUtil.saveShowEvents(showEvents);
	}

	public boolean getShowEvents() {
		return sharedPreferencesUtil.getShowEvents();
	}


	public void saveShowTrendingFirstTime(boolean showTrending) {
		sharedPreferencesUtil.saveShowTrendingFirstTime(showTrending);
	}

	public boolean getShowTrendingFirstTime() {
		return sharedPreferencesUtil.getShowTrendingFirstTime();
	}

	public Observable<AssetInfo> getAssetInfo(String asset) {
		return platformApi.getPlatformAssetInfo(asset);
	}
}