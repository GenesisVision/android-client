package vision.genesis.clientapp.model;

import io.swagger.client.model.BinanceRawExchangeInfo;
import io.swagger.client.model.BinanceRawFuturesUsdtExchangeInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/01/2022.
 */
public class BinanceExchangeInfo
{
	private BinanceRawExchangeInfo spotInfo;

	private BinanceRawFuturesUsdtExchangeInfo futuresInfo;

	public BinanceRawExchangeInfo getSpotInfo() {
		return spotInfo;
	}

	public void setSpotInfo(BinanceRawExchangeInfo spotInfo) {
		this.spotInfo = spotInfo;
	}

	public BinanceRawFuturesUsdtExchangeInfo getFuturesInfo() {
		return futuresInfo;
	}

	public void setFuturesInfo(BinanceRawFuturesUsdtExchangeInfo futuresInfo) {
		this.futuresInfo = futuresInfo;
	}
}
