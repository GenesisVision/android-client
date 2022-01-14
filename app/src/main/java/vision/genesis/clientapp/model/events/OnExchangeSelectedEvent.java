package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ExchangeInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/12/2020.
 */
public class OnExchangeSelectedEvent
{
	private final ExchangeInfo selectedExchange;

	public OnExchangeSelectedEvent(ExchangeInfo selectedExchange) {
		this.selectedExchange = selectedExchange;
	}

	public ExchangeInfo getSelectedExchange() {
		return selectedExchange;
	}
}
