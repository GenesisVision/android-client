package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
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
	public AuthManager provideAuthManager(InvestorApi investorApi, ManagerApi managerApi, SharedPreferencesUtil sharedPreferencesUtil, SettingsManager settingsManager) {
		return new AuthManager(investorApi, managerApi, sharedPreferencesUtil, settingsManager);
	}

	@Provides
	@Singleton
	public ProfileManager provideProfileManager(InvestorApi investorApi, ManagerApi managerApi) {
		return new ProfileManager(investorApi, managerApi);
	}
}
