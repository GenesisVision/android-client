package vision.genesis.clientapp.model.events;

import io.swagger.client.model.InvestmentEventViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/09/2019.
 */
public class ShowEventDetailsEvent
{
	private InvestmentEventViewModel event;

	public ShowEventDetailsEvent(InvestmentEventViewModel event) {

		this.event = event;
	}

	public InvestmentEventViewModel getEvent() {
		return event;
	}
}
