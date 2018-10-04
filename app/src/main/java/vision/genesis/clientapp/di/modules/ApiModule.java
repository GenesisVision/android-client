package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.ApiClient;
import io.swagger.client.JSON;
import io.swagger.client.api.AuthApi;
import io.swagger.client.api.FileApi;
import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.NotificationsApi;
import io.swagger.client.api.PlatformApi;
import io.swagger.client.api.ProfileApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.api.RateApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import vision.genesis.clientapp.BuildConfig;
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
	public InvestorApi provideInvestorApi(ApiClient apiClient) {
		return apiClient.createService(InvestorApi.class);
	}

//	@Provides
//	@Singleton
//	public ManagerApi provideManagerApi(ApiClient apiClient) {
//		return apiClient.createService(ManagerApi.class);
//	}

	@Provides
	@Singleton
	public AuthApi provideAuthApi(ApiClient apiClient) {
		return apiClient.createService(AuthApi.class);
	}

	@Provides
	@Singleton
	public FileApi provideFileApi(ApiClient apiClient) {
		return apiClient.createService(FileApi.class);
	}

	@Provides
	@Singleton
	public NotificationsApi provideNotificationsApi(ApiClient apiClient) {
		return apiClient.createService(NotificationsApi.class);
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
}
