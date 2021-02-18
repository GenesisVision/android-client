package vision.genesis.clientapp.net.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceRawExchangeInfo;
import vision.genesis.clientapp.model.terminal.binance_api.DepthListModel;
import vision.genesis.clientapp.model.terminal.binance_api.TickerPriceModel;
import vision.genesis.clientapp.model.terminal.binance_api.TradeModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/01/2021.
 */

public interface BinanceApi
{
	@Headers({
			"Content-Type:application/json"
	})
	@GET("v3/exchangeInfo")
	Observable<BinanceRawExchangeInfo> getExchangeInfo();

	@Headers({
			"Content-Type:application/json"
	})
	@GET("v3/ticker/price")
	Observable<List<TickerPriceModel>> getTickersPrices();

	@Headers({
			"Content-Type:application/json"
	})
	@GET("v3/trades")
	Observable<List<TradeModel>> getTrades(@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("limit") Integer limit);

	@Headers({
			"Content-Type:application/json"
	})
	@GET("v3/depth")
	Observable<DepthListModel> getDepth(@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("limit") Integer limit);
}
