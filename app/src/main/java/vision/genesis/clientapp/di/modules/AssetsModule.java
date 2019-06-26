package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.FundsApi;
import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.api.RateApi;
import io.swagger.client.api.SearchApi;
import io.swagger.client.api.SignalApi;
import io.swagger.client.api.WalletApi;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.InvestorDashboardManager;
import vision.genesis.clientapp.managers.ManagerDashboardManager;
import vision.genesis.clientapp.managers.ManagersManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.SearchManager;
import vision.genesis.clientapp.managers.SignalsManager;
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
	public ProgramsManager provideProgramsManager(InvestorApi investorApi, ProgramsApi programApi) {
		return new ProgramsManager(investorApi, programApi);
	}

	@Provides
	@Singleton
	public FundsManager provideFundsManager(InvestorApi investorApi, FundsApi fundsApi) {
		return new FundsManager(investorApi, fundsApi);
	}

	@Provides
	@Singleton
	public ManagersManager provideManagersManager(ManagerApi managerApi) {
		return new ManagersManager(managerApi);
	}

	@Provides
	@Singleton
	public WalletManager provideWalletManager(WalletApi walletApi) {
		return new WalletManager(walletApi);
	}

	@Provides
	@Singleton
	public RateManager provideRateManager(RateApi rateApi) {
		return new RateManager(rateApi);
	}

	@Provides
	@Singleton
	public SearchManager provideSearchManager(SearchApi searchApi) {
		return new SearchManager(searchApi);
	}

	@Provides
	@Singleton
	public SignalsManager provideSignalsManager(SignalApi signalApi) {
		return new SignalsManager(signalApi);
	}
}
