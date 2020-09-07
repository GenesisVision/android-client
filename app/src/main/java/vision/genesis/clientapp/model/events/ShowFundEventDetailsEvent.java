package vision.genesis.clientapp.model.events;

import io.swagger.client.model.FundHistoryEventViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/10/2019.
 */
public class ShowFundEventDetailsEvent
{
	private FundHistoryEventViewModel event;

	public ShowFundEventDetailsEvent(FundHistoryEventViewModel event) {
		this.event = event;
	}

	public FundHistoryEventViewModel getEvent() {
		return event;
	}
}
