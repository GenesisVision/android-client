package vision.genesis.clientapp.model.events;

import io.swagger.client.model.OrderModel;
import io.swagger.client.model.TradesViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/02/2019.
 */
public class OnOpenPositionClickedEvent
{
	private final TradesViewModel model;

	private OrderModel openPosition;

	public OnOpenPositionClickedEvent(OrderModel openPosition, TradesViewModel model) {
		this.openPosition = openPosition;
		this.model = model;
	}

	public OrderModel getOpenPosition() {
		return openPosition;
	}

	public TradesViewModel getModel() {
		return model;
	}
}
