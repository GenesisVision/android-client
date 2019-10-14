package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.DashboardApi;
import io.swagger.client.api.FundsApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.api.RateApi;
import io.swagger.client.api.SearchApi;
import io.swagger.client.api.SignalApi;
import io.swagger.client.api.WalletApi;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.FundsManager;
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
	public DashboardManager provideDashboardManager(DashboardApi dashboardApi) {
		return new DashboardManager(dashboardApi);
	}


	@Provides
	@Singleton
	public ProgramsManager provideProgramsManager(DashboardApi dashboardApi, ProgramsApi programApi) {
		return new ProgramsManager(dashboardApi, programApi);
	}

	@Provides
	@Singleton
	public FundsManager provideFundsManager(DashboardApi dashboardApi, FundsApi fundsApi) {
		return new FundsManager(dashboardApi, fundsApi);
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
