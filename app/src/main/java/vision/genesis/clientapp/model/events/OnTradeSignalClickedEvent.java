package vision.genesis.clientapp.model.events;

import io.swagger.client.model.OrderSignalModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/08/2018.
 */

public class OnTradeSignalClickedEvent
{
	private OrderSignalModel trade;

	public OnTradeSignalClickedEvent(OrderSignalModel trade) {
		this.trade = trade;
	}

	public OrderSignalModel getTrade() {
		return trade;
	}
}
