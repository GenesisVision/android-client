package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.AuthApi;
import io.swagger.client.api.ProfileApi;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.utils.SharedPreferencesUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/22/18.
 */

@Module
public class AuthModule
{
	@Provides
	@Singleton
	public AuthManager provideAuthManager(AuthApi authApi, SharedPreferencesUtil sharedPreferencesUtil, SettingsManager settingsManager) {
		return new AuthManager(authApi, sharedPreferencesUtil, settingsManager);
	}

	@Provides
	@Singleton
	public ProfileManager provideProfileManager(ProfileApi profileApi) {
		return new ProfileManager(profileApi);
	}
}
