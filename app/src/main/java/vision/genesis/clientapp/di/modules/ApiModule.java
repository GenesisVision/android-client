package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.ApiClient;
import io.swagger.client.JSON;
import io.swagger.client.api.AccountApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.net.LogJsonInterceptor;

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
				.addInterceptor(new LogJsonInterceptor());
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
	public AccountApi provideAccountApi(ApiClient apiClient) {
		return apiClient.createService(AccountApi.class);
	}
}
