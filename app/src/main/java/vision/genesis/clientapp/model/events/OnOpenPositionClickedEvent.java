package vision.genesis.clientapp.model.events;

import io.swagger.client.model.OrderModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/02/2019.
 */
public class OnOpenPositionClickedEvent
{
	private OrderModel openPosition;

	public OnOpenPositionClickedEvent(OrderModel openPosition) {
		this.openPosition = openPosition;
	}

	public OrderModel getOpenPosition() {
		return openPosition;
	}
}
