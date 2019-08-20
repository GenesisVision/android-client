package vision.genesis.clientapp.model.events;

import io.swagger.client.model.OrderModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/08/2018.
 */

public class OnTradeClickedEvent
{
	private OrderModel trade;

	public OnTradeClickedEvent(OrderModel trade) {
		this.trade = trade;
	}

	public OrderModel getTrade() {
		return trade;
	}
}
