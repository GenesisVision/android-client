package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.api.RateApi;
import vision.genesis.clientapp.managers.InvestorDashboardManager;
import vision.genesis.clientapp.managers.ManagerDashboardManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.WalletManager;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

@Module
public class AssetsModule
{
	@Provides
	@Singleton
	public InvestorDashboardManager provideInvestorDashboardManager(InvestorApi investorApi) {
		return new InvestorDashboardManager(investorApi);
	}

	@Provides
	@Singleton
	public ManagerDashboardManager provideManagerDashboardManager(ManagerApi managerApi) {
		return new ManagerDashboardManager(managerApi);
	}

	@Provides
	@Singleton
	public ProgramsManager provideProgramsManager(InvestorApi investorApi, ManagerApi managerApi) {
		return new ProgramsManager(investorApi, managerApi);
	}

	@Provides
	@Singleton
	public WalletManager provideWalletManager(InvestorApi investorApi, ManagerApi managerApi) {
		return new WalletManager(investorApi, managerApi);
	}

	@Provides
	@Singleton
	public RateManager provideRateManager(RateApi rateApi) {
		return new RateManager(rateApi);
	}
}
