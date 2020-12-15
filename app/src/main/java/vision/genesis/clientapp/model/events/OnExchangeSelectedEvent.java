package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ExchangeAccountType;
import io.swagger.client.model.ExchangeInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/12/2020.
 */
public class OnExchangeSelectedEvent
{
	private final ExchangeInfo selectedExchange;

	private final ExchangeAccountType selectedAccountType;

	public OnExchangeSelectedEvent(ExchangeInfo selectedExchange, ExchangeAccountType selectedAccountType) {
		this.selectedExchange = selectedExchange;
		this.selectedAccountType = selectedAccountType;
	}

	public ExchangeInfo getSelectedExchange() {
		return selectedExchange;
	}

	public ExchangeAccountType getSelectedAccountType() {
		return selectedAccountType;
	}
}
