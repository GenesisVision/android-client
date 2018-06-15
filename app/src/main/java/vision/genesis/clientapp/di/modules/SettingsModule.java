package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
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
	public SettingsManager provideSettingsManager(InvestorApi investorApi, ManagerApi managerApi, SharedPreferencesUtil sharedPreferencesUtil) {
		return new SettingsManager(investorApi, managerApi, sharedPreferencesUtil);
	}
}
