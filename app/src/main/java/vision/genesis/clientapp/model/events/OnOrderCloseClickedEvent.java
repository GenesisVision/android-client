package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.terminal.binance_api.BinanceOrder;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/03/2021.
 */
public class OnOrderCloseClickedEvent
{
	private BinanceOrder order;

	public OnOrderCloseClickedEvent(BinanceOrder order) {
		this.order = order;
	}

	public BinanceOrder getOrder() {
		return order;
	}
}
