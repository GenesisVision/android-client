package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.TradingAccountDetailsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */
public class ShowTradingAccountDetailsEvent
{
	private final TradingAccountDetailsModel tradingAccountDetailsModel;

	public ShowTradingAccountDetailsEvent(TradingAccountDetailsModel tradingAccountDetailsModel) {
		this.tradingAccountDetailsModel = tradingAccountDetailsModel;
	}

	public TradingAccountDetailsModel getTradingAccountDetailsModel() {
		return tradingAccountDetailsModel;
	}
}
