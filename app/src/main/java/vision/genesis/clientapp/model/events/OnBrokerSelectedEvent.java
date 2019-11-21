package vision.genesis.clientapp.model.events;

import io.swagger.client.model.Broker;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/11/2019.
 */
public class OnBrokerSelectedEvent
{
	private final Broker selectedBroker;

	public OnBrokerSelectedEvent(Broker selectedBroker) {
		this.selectedBroker = selectedBroker;
	}

	public Broker getSelectedBroker() {
		return selectedBroker;
	}
}
