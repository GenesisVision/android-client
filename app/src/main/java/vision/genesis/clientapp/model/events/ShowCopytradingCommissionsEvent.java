package vision.genesis.clientapp.model.events;

import io.swagger.client.model.OrderSignalModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/07/2019.
 */
public class ShowCopytradingCommissionsEvent
{
	private OrderSignalModel trade;

	public ShowCopytradingCommissionsEvent(OrderSignalModel trade) {
		this.trade = trade;
	}

	public OrderSignalModel getTrade() {
		return trade;
	}
}
