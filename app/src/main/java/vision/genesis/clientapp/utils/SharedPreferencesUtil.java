package vision.genesis.clientapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import io.swagger.client.model.DashboardAssetStatus;

/**
 * GenesisVision
 * Created by Vitaly on 1/29/18.
 */

public class SharedPreferencesUtil
{
	private static final String USER_PREFS = "userPrefs";

	private static final String KEY_TOKEN = "keyToken";

	private static final String KEY_IGNORED_VERSION_UPDATE = "keyIgnoredVersionUpdate";

	private static final String KEY_ENABLE_TWO_FACTOR_ALREADY_SHOWN = "keyEnableTwoFactorAlreadyShown";

	private static final String KEY_FCM_TOKEN = "keyFcmToken";

	private static final String KEY_TEMP_TOKEN = "keyTempToken";


	private static final String SETTINGS = "settings";

	private static final String KEY_TWO_FACTOR_ENABLED = "keyTwoFactorEnabled";

	private static final String KEY_PIN_CODE_ENABLED = "keyPinCodeEnabled";

	private static final String KEY_FINGERPRINT_ENABLED = "keyFingerprintEnabled";

	private static final String KEY_PIN_CODE_HASH = "keyPinCodeHash";

	private static final String KEY_THEME = "keyTheme";

	private static final String KEY_DATE_RANGE = "keyDateRange";

	private static final String KEY_DATE_RANGE_FROM = "keyDateRangeFrom";

	private static final String KEY_DATE_RANGE_TO = "keyDateRangeTo";

	private static final String KEY_CURRENCY = "keyCurrency";

	private static final String KEY_SHOW_EVENTS = "keyShowEvents";

	private static final String KEY_SHOW_TRENDING_FIRST_TIME = "keyShowTrendingFirstTime";

	private static final String KEY_TRADING_PRIVATE_STATUS = "keyTradingPrivateStatus";

	private static final String KEY_TRADING_PUBLIC_STATUS = "keyTradingPublicStatus";

	private static final String KEY_INVESTMENTS_PROGRAMS_STATUS = "keyInvestmentsProgramsStatus";

	private static final String KEY_INVESTMENTS_FUNDS_STATUS = "keyInvestmentsFundsStatus";

	private static final String KEY_PIN_ERROR_TIMESTAMP = "keyPinErrorTimestamp";

	private Context context;

	public SharedPreferencesUtil(Context context) {
		this.context = context;
	}

	public void saveToken(String token) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putString(KEY_TOKEN, token)
				.apply();
	}

	public String getToken() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_TOKEN, null);
	}

	public void saveIgnoredVersionUpdate(int versionCode) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putInt(KEY_IGNORED_VERSION_UPDATE, versionCode)
				.apply();
	}

	public int getIgnoredVersionUpdate() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		return sharedPreferences.getInt(KEY_IGNORED_VERSION_UPDATE, 0);
	}

	public boolean isEnableTwoFactorAlreadyShown() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(KEY_ENABLE_TWO_FACTOR_ALREADY_SHOWN, false);
	}

	public void setEnableTwoFactorAlreadyShown(boolean shown) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putBoolean(KEY_ENABLE_TWO_FACTOR_ALREADY_SHOWN, shown)
				.apply();
	}

	public boolean getTwoFactorEnabled() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(KEY_TWO_FACTOR_ENABLED, false);
	}

	public void setTwoFactorEnabled(boolean enabled) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putBoolean(KEY_TWO_FACTOR_ENABLED, enabled)
				.apply();
	}

	public boolean getPinCodeEnabled() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(KEY_PIN_CODE_ENABLED, false);
	}

	public void setPinCodeEnabled(boolean enabled) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putBoolean(KEY_PIN_CODE_ENABLED, enabled)
				.apply();
	}

	public boolean getFingerprintEnabled() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(KEY_FINGERPRINT_ENABLED, false);
	}

	public void setFingerprintEnabled(boolean enabled) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putBoolean(KEY_FINGERPRINT_ENABLED, enabled)
				.apply();
	}

	public String getPinHash() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_PIN_CODE_HASH, "");
	}

	public void setPinHash(String hashedPin) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putString(KEY_PIN_CODE_HASH, hashedPin)
				.apply();
	}

	public String getTheme() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_THEME, ThemeUtil.THEME_DARK);
	}

	public void setTheme(String newTheme) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putString(KEY_THEME, newTheme)
				.apply();
	}

	public void saveDateRange(String dateRange, Long dateRangeFrom, Long dateRangeTo) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putString(KEY_DATE_RANGE, dateRange)
				.putLong(KEY_DATE_RANGE_FROM, dateRangeFrom)
				.putLong(KEY_DATE_RANGE_TO, dateRangeTo)
				.apply();
	}

	public String getDateRange() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_DATE_RANGE, "");
	}

	public Long getDateRangeFrom() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		return sharedPreferences.getLong(KEY_DATE_RANGE_FROM, 0);
	}

	public Long getDateRangeTo() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		return sharedPreferences.getLong(KEY_DATE_RANGE_TO, 0);
	}

	public void saveCurrency(String currency) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putString(KEY_CURRENCY, currency)
				.apply();
	}

	public String getCurrency() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_CURRENCY, "USD");
	}

	public void saveShowEvents(boolean showEvents) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putBoolean(KEY_SHOW_EVENTS, showEvents)
				.apply();
	}

	public boolean getShowEvents() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(KEY_SHOW_EVENTS, true);
	}

	public void saveShowTrendingFirstTime(boolean showTrending) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putBoolean(KEY_SHOW_TRENDING_FIRST_TIME, showTrending)
				.apply();
	}

	public boolean getShowTrendingFirstTime() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(KEY_SHOW_TRENDING_FIRST_TIME, true);
	}

	public void saveFcmToken(String fcmToken) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putString(KEY_FCM_TOKEN, fcmToken)
				.apply();
	}

	public String getFcmToken() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_FCM_TOKEN, null);
	}

	public void saveTradingPrivateStatus(String tradingPrivateStatus) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putString(KEY_TRADING_PRIVATE_STATUS, tradingPrivateStatus)
				.apply();
	}

	public String getTradingPrivateStatus() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_TRADING_PRIVATE_STATUS, DashboardAssetStatus.ACTIVE.getValue());
	}

	public void saveTradingPublicStatus(String tradingPublicStatus) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putString(KEY_TRADING_PUBLIC_STATUS, tradingPublicStatus)
				.apply();
	}

	public String getTradingPublicStatus() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_TRADING_PUBLIC_STATUS, DashboardAssetStatus.ACTIVE.getValue());
	}

	public void saveInvestmentsProgramsStatus(String status) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putString(KEY_INVESTMENTS_PROGRAMS_STATUS, status)
				.apply();
	}

	public String getInvestmentsProgramsStatus() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_INVESTMENTS_PROGRAMS_STATUS, DashboardAssetStatus.ACTIVE.getValue());
	}

	public void saveInvestmentsFundsStatus(String status) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putString(KEY_INVESTMENTS_FUNDS_STATUS, status)
				.apply();
	}

	public String getInvestmentsFundsStatus() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_INVESTMENTS_FUNDS_STATUS, DashboardAssetStatus.ACTIVE.getValue());
	}

	public void saveTempToken(String tempToken) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putString(KEY_TEMP_TOKEN, tempToken)
				.apply();
	}

	public String getTempToken() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_TEMP_TOKEN, null);
	}

	public long getPinErrorTimestamp() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		return sharedPreferences.getLong(KEY_PIN_ERROR_TIMESTAMP, 0L);
	}

	public void setPinErrorTimestamp(long currentTimeMillis) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
		sharedPreferences.edit()
				.putLong(KEY_PIN_ERROR_TIMESTAMP, currentTimeMillis)
				.apply();
	}
}
