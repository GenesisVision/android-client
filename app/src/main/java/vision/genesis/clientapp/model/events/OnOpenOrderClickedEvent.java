package vision.genesis.clientapp.model.events;

import io.swagger.client.model.BinanceRawOrder;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/02/2021.
 */
public class OnOpenOrderClickedEvent
{
	private BinanceRawOrder order;

	public OnOpenOrderClickedEvent(BinanceRawOrder order) {
		this.order = order;
	}

	public BinanceRawOrder getOrder() {
		return order;
	}
}
