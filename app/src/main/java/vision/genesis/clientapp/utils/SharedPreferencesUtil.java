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
}
