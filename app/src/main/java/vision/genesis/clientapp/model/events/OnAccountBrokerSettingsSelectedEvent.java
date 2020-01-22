package vision.genesis.clientapp.model.events;

import io.swagger.client.model.BrokerAccountType;
import io.swagger.client.model.Currency;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/11/2019.
 */
public class OnAccountBrokerSettingsSelectedEvent
{
	private BrokerAccountType brokerAccountType;

	private Currency currency;

	private Integer leverage;

	public OnAccountBrokerSettingsSelectedEvent(BrokerAccountType brokerAccountType, Currency currency, Integer leverage) {
		this.brokerAccountType = brokerAccountType;
		this.currency = currency;
		this.leverage = leverage;
	}

	public BrokerAccountType getBrokerAccountType() {
		return brokerAccountType;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Integer getLeverage() {
		return leverage;
	}
}
