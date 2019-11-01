package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.AssetsApi;
import io.swagger.client.api.DashboardApi;
import io.swagger.client.api.EventsApi;
import io.swagger.client.api.FundsApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.api.RateApi;
import io.swagger.client.api.SearchApi;
import io.swagger.client.api.SignalApi;
import io.swagger.client.api.WalletApi;
import vision.genesis.clientapp.managers.AssetsManager;
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
	public AssetsManager provideAssetsManager(AssetsApi assetsApi) {
		return new AssetsManager(assetsApi);
	}

	@Provides
	@Singleton
	public DashboardManager provideDashboardManager(DashboardApi dashboardApi, InvestmentsApi investmentsApi) {
		return new DashboardManager(dashboardApi, investmentsApi);
	}

	@Provides
	@Singleton
	public FundsManager provideFundsManager(FundsApi fundsApi, InvestmentsApi investmentsApi, EventsApi eventsApi) {
		return new FundsManager(fundsApi, investmentsApi, eventsApi);
	}

	@Provides
	@Singleton
	public ProgramsManager provideProgramsManager(ProgramsApi programsApi, InvestmentsApi investmentsApi, EventsApi eventsApi) {
		return new ProgramsManager(programsApi, investmentsApi, eventsApi);
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

	@Provides
	@Singleton
	public WalletManager provideWalletManager(WalletApi walletApi) {
		return new WalletManager(walletApi);
	}
}
