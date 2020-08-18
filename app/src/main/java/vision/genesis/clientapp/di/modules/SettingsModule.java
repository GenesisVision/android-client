package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.PlatformApi;
import io.swagger.client.api.ProfileApi;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.utils.SharedPreferencesUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

@Module
public class SettingsModule
{
	@Provides
	@Singleton
	public SettingsManager provideSettingsManager(PlatformApi platformApi, ProfileApi profileApi, SharedPreferencesUtil sharedPreferencesUtil) {
		return new SettingsManager(platformApi, profileApi, sharedPreferencesUtil);
	}
}
