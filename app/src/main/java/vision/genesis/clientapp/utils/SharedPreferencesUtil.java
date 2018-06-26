package vision.genesis.clientapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

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


	private static final String SETTINGS = "settings";

	private static final String KEY_TWO_FACTOR_ENABLED = "keyTwoFactorEnabled";

	private static final String KEY_PIN_CODE_ENABLED = "keyPinCodeEnabled";

	private static final String KEY_FINGERPRINT_ENABLED = "keyFingerprintEnabled";

	private static final String KEY_PIN_CODE_HASH = "keyPinCodeHash";

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
}
