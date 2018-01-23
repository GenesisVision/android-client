package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.ApiClient;
import io.swagger.client.JSON;
import io.swagger.client.api.AccountApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import vision.genesis.clientapp.BuildConfig;

/**
 * GenesisVision
 * Created by Vitaly on 1/22/18.
 */

@Module
public class ApiModule
{
	@Provides
	@Singleton
	Retrofit.Builder provideAdapterBuilder() {
		return new Retrofit
				.Builder()
				.baseUrl(BuildConfig.API_ADDRESS)
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(ScalarsConverterFactory.create())
				.addConverterFactory(vision.genesis.clientapp.net.GsonCustomConverterFactory.create(new JSON().getGson()));
	}

	@Provides
	@Singleton
	ApiClient provideApiClient(Retrofit.Builder adapterBuilder) {
		ApiClient apiClient = new ApiClient();
		apiClient.setAdapterBuilder(adapterBuilder);
		return apiClient;
	}

	@Provides
	@Singleton
	public AccountApi provideAccountApi(ApiClient apiClient) {
		return apiClient.createService(AccountApi.class);
	}
}
