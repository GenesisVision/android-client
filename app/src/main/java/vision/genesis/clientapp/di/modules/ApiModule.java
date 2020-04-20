package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.ApiClient;
import io.swagger.client.JSON;
import io.swagger.client.api.AssetsApi;
import io.swagger.client.api.AuthApi;
import io.swagger.client.api.BrokersApi;
import io.swagger.client.api.CopytradingApi;
import io.swagger.client.api.DashboardApi;
import io.swagger.client.api.EventsApi;
import io.swagger.client.api.FileApi;
import io.swagger.client.api.FollowApi;
import io.swagger.client.api.FundsApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.api.NotificationsApi;
import io.swagger.client.api.PartnershipApi;
import io.swagger.client.api.PlatformApi;
import io.swagger.client.api.ProfileApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.api.RateApi;
import io.swagger.client.api.SearchApi;
import io.swagger.client.api.SignalApi;
import io.swagger.client.api.TradingaccountApi;
import io.swagger.client.api.UsersApi;
import io.swagger.client.api.WalletApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.net.AuthenticationInterceptor;
import vision.genesis.clientapp.net.LogJsonInterceptor;
import vision.genesis.clientapp.net.UnauthorizedInterceptor;

/**
 * GenesisVision
 * Created by Vitaly on 1/22/18.
 */

@Module
public class ApiModule
{
	@Provides
	@Singleton
	OkHttpClient provideHttpClient() {
		OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
				.addInterceptor(new AuthenticationInterceptor())
				.addInterceptor(new LogJsonInterceptor())
				.addInterceptor(new UnauthorizedInterceptor());
		return httpClientBuilder.build();
	}

	@Provides
	@Singleton
	Retrofit.Builder provideAdapterBuilder() {
		return new Retrofit.Builder()
				.addConverterFactory(ScalarsConverterFactory.create())
				.addConverterFactory(vision.genesis.clientapp.net.GsonCustomConverterFactory.create(new JSON().getGson()))
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.baseUrl(BuildConfig.API_ADDRESS);
	}

	@Provides
	@Singleton
	ApiClient provideApiClient(Retrofit.Builder adapterBuilder, OkHttpClient httpClient) {
		ApiClient apiClient = new ApiClient();
		apiClient.setAdapterBuilder(adapterBuilder);
		apiClient.configureFromOkclient(httpClient);
		return apiClient;
	}

	@Provides
	@Singleton
	public AssetsApi provideAssetsApi(ApiClient apiClient) {
		return apiClient.createService(AssetsApi.class);
	}

	@Provides
	@Singleton
	public AuthApi provideAuthApi(ApiClient apiClient) {
		return apiClient.createService(AuthApi.class);
	}

	@Provides
	@Singleton
	public BrokersApi provideBrokersApi(ApiClient apiClient) {
		return apiClient.createService(BrokersApi.class);
	}

	@Provides
	@Singleton
	public CopytradingApi provideCopytradingApi(ApiClient apiClient) {
		return apiClient.createService(CopytradingApi.class);
	}

	@Provides
	@Singleton
	public DashboardApi provideDashboardApi(ApiClient apiClient) {
		return apiClient.createService(DashboardApi.class);
	}

	@Provides
	@Singleton
	public EventsApi provideEventsApi(ApiClient apiClient) {
		return apiClient.createService(EventsApi.class);
	}

	@Provides
	@Singleton
	public FileApi provideFileApi(ApiClient apiClient) {
		return apiClient.createService(FileApi.class);
	}

	@Provides
	@Singleton
	public FollowApi provideFollowApi(ApiClient apiClient) {
		return apiClient.createService(FollowApi.class);
	}

	@Provides
	@Singleton
	public FundsApi provideFundsApi(ApiClient apiClient) {
		return apiClient.createService(FundsApi.class);
	}

	@Provides
	@Singleton
	public InvestmentsApi provideInvestmentsApi(ApiClient apiClient) {
		return apiClient.createService(InvestmentsApi.class);
	}

	@Provides
	@Singleton
	public NotificationsApi provideNotificationsApi(ApiClient apiClient) {
		return apiClient.createService(NotificationsApi.class);
	}

	@Provides
	@Singleton
	public PartnershipApi providePartnershipApi(ApiClient apiClient) {
		return apiClient.createService(PartnershipApi.class);
	}

	@Provides
	@Singleton
	public PlatformApi providePlatformApi(ApiClient apiClient) {
		return apiClient.createService(PlatformApi.class);
	}

	@Provides
	@Singleton
	public ProfileApi provideProfileApi(ApiClient apiClient) {
		return apiClient.createService(ProfileApi.class);
	}

	@Provides
	@Singleton
	public ProgramsApi provideProgramsApi(ApiClient apiClient) {
		return apiClient.createService(ProgramsApi.class);
	}

	@Provides
	@Singleton
	public RateApi provideRateApi(ApiClient apiClient) {
		return apiClient.createService(RateApi.class);
	}

	@Provides
	@Singleton
	public SearchApi provideSearchApi(ApiClient apiClient) {
		return apiClient.createService(SearchApi.class);
	}

	@Provides
	@Singleton
	public SignalApi provideSignalApi(ApiClient apiClient) {
		return apiClient.createService(SignalApi.class);
	}

	@Provides
	@Singleton
	public TradingaccountApi provideTradingAccountApi(ApiClient apiClient) {
		return apiClient.createService(TradingaccountApi.class);
	}

	@Provides
	@Singleton
	public UsersApi provideUsersApi(ApiClient apiClient) {
		return apiClient.createService(UsersApi.class);
	}

	@Provides
	@Singleton
	public WalletApi provideWalletApi(ApiClient apiClient) {
		return apiClient.createService(WalletApi.class);
	}
}
