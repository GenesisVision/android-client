package vision.genesis.clientapp.managers;

import io.swagger.client.api.EventsApi;
import io.swagger.client.model.InvestmentEventLocation;
import io.swagger.client.model.InvestmentEventViewModels;
import rx.Observable;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

public class EventsManager
{
	private final EventsApi eventsApi;

	public EventsManager(EventsApi eventsApi) {
		this.eventsApi = eventsApi;
	}

	public Observable<InvestmentEventViewModels> getEvents(String eventsGroup, DateRange dateRange, Integer skip, Integer take) {
		return eventsApi.getEvents(AuthManager.token.getValue(), InvestmentEventLocation.ASSET.getValue(), null,
				dateRange.getFrom(), dateRange.getTo(),
				null, null,
				null, null,
				eventsGroup,
				skip, take);
	}
}