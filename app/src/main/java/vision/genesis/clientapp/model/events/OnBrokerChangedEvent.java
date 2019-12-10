package vision.genesis.clientapp.model.events;

import org.joda.time.DateTime;

import io.swagger.client.model.Broker;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */
public class OnBrokerChangedEvent
{
	private final Broker newBroker;

	private final Integer newLeverage;

	private final DateTime creationDate;

	public OnBrokerChangedEvent(Broker newBroker, Integer newLeverage, DateTime creationDate) {
		this.newBroker = newBroker;
		this.newLeverage = newLeverage;
		this.creationDate = creationDate;
	}

	public Broker getNewBroker() {
		return newBroker;
	}

	public Integer getNewLeverage() {
		return newLeverage;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}
}
