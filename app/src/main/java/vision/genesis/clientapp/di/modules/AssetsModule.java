package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.AssetsApi;
import io.swagger.client.api.BrokersApi;
import io.swagger.client.api.CopytradingApi;
import io.swagger.client.api.DashboardApi;
import io.swagger.client.api.EventsApi;
import io.swagger.client.api.ExchangesApi;
import io.swagger.client.api.FollowApi;
import io.swagger.client.api.FundsApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.api.PartnershipApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.api.RateApi;
import io.swagger.client.api.SearchApi;
import io.swagger.client.api.SignalApi;
import io.swagger.client.api.TradingaccountApi;
import io.swagger.client.api.UsersApi;
import io.swagger.client.api.WalletApi;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.managers.BrokersManager;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.EventsManager;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.PartnershipManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.SearchManager;
import vision.genesis.clientapp.managers.TradingAccountManager;
import vision.genesis.clientapp.managers.UsersManager;
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
	public DashboardManager provideDashboardManager(DashboardApi dashboardApi, InvestmentsApi investmentsApi, CopytradingApi copytradingApi) {
		return new DashboardManager(dashboardApi, investmentsApi, copytradingApi);
	}

	@Provides
	@Singleton
	public BrokersManager provideBrokersManager(BrokersApi brokersApi, ExchangesApi exchangesApi) {
		return new BrokersManager(brokersApi, exchangesApi);
	}

	@Provides
	@Singleton
	public FollowsManager provideFollowsManager(SignalApi signalApi, FollowApi followApi) {
		return new FollowsManager(signalApi, followApi);
	}

	@Provides
	@Singleton
	public EventsManager provideEventsManager(EventsApi eventsApi) {
		return new EventsManager(eventsApi);
	}

	@Provides
	@Singleton
	public FundsManager provideFundsManager(FundsApi fundsApi, InvestmentsApi investmentsApi, AssetsApi assetsApi, EventsApi eventsApi) {
		return new FundsManager(fundsApi, investmentsApi, assetsApi, eventsApi);
	}

	@Provides
	@Singleton
	public PartnershipManager providePartnershipManager(PartnershipApi partnershipApi) {
		return new PartnershipManager(partnershipApi);
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
	public TradingAccountManager provideTradingAccountManager(TradingaccountApi tradingAccountApi) {
		return new TradingAccountManager(tradingAccountApi);
	}

	@Provides
	@Singleton
	public UsersManager provideUsersManager(UsersApi usersApi) {
		return new UsersManager(usersApi);
	}

	@Provides
	@Singleton
	public WalletManager provideWalletManager(WalletApi walletApi) {
		return new WalletManager(walletApi);
	}
}
