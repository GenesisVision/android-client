package vision.genesis.clientapp.net.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;
import vision.genesis.clientapp.model.terminal.binance_api.TickerPriceModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/01/2021.
 */

public interface BinanceApi
{
	@Headers({
			"Content-Type:application/json"
	})
	@GET("v3/ticker/price")
	Observable<List<TickerPriceModel>> getTickersPrices();

}
