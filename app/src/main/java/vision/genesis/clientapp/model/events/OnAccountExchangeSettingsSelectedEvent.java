package vision.genesis.clientapp.model.events;

import io.swagger.client.model.Currency;
import io.swagger.client.model.ExchangeAccountType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/01/2022.
 */
public class OnAccountExchangeSettingsSelectedEvent
{
	private final ExchangeAccountType selectedAccountType;

	private final Currency currency;

	public OnAccountExchangeSettingsSelectedEvent(ExchangeAccountType selectedAccountType, Currency currency) {
		this.selectedAccountType = selectedAccountType;
		this.currency = currency;
	}

	public ExchangeAccountType getSelectedAccountType() {
		return selectedAccountType;
	}

	public Currency getCurrency() {
		return currency;
	}
}
