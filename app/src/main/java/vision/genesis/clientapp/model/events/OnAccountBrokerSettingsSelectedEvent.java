package vision.genesis.clientapp.model.events;

import java.util.UUID;

import io.swagger.client.model.Currency;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/11/2019.
 */
public class OnAccountBrokerSettingsSelectedEvent
{
	private UUID brokerAccountTypeId;

	private Currency currency;

	private Integer leverage;

	public OnAccountBrokerSettingsSelectedEvent(UUID brokerAccountTypeId, Currency currency, Integer leverage) {
		this.brokerAccountTypeId = brokerAccountTypeId;
		this.currency = currency;
		this.leverage = leverage;
	}

	public UUID getBrokerAccountTypeId() {
		return brokerAccountTypeId;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Integer getLeverage() {
		return leverage;
	}
}
