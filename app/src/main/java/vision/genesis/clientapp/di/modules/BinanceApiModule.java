package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.JSON;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.net.GsonCustomConverterFactory;
import vision.genesis.clientapp.net.api.BinanceApi;
import vision.genesis.clientapp.net.api.client.BinanceApiClient;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/01/2021.
 */

@Module
public class BinanceApiModule
{
	@Provides
	@Singleton
	BinanceApiClient provideBinanceApiClient(OkHttpClient httpClient) {
		Retrofit.Builder adapterBuilder = new Retrofit.Builder()
				.addConverterFactory(ScalarsConverterFactory.create())
				.addConverterFactory(GsonCustomConverterFactory.create(new JSON().getGson()))
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.baseUrl(BuildConfig.BINANCE_API_ADDRESS);
		BinanceApiClient apiClient = new BinanceApiClient();
		apiClient.setAdapterBuilder(adapterBuilder);
		apiClient.configureFromOkclient(httpClient);
		return apiClient;
	}

	@Provides
	@Singleton
	public BinanceApi provideBinanceApi(BinanceApiClient apiClient) {
		return apiClient.createService(BinanceApi.class);
	}
}
