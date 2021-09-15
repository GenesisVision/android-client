package vision.genesis.clientapp.model.events;

import io.swagger.client.model.BinanceRawOrder;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/03/2021.
 */
public class OnOrderCloseClickedEvent
{
	private BinanceRawOrder order;

	public OnOrderCloseClickedEvent(BinanceRawOrder order) {
		this.order = order;
	}

	public BinanceRawOrder getOrder() {
		return order;
	}
}
