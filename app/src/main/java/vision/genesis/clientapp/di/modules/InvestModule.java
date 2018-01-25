package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import vision.genesis.clientapp.managers.InvestManager;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

@Module
public class InvestModule
{
	@Provides
	@Singleton
	public InvestManager provideInvestManager(InvestorApi investorApi, ManagerApi managerApi) {
		return new InvestManager(investorApi, managerApi);
	}
}
