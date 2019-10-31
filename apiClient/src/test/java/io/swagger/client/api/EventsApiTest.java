package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for EventsApi
 */
public class EventsApiTest
{

	private EventsApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(EventsApi.class);
	}


	/**
	 * Events
	 */
	@Test
	public void getEventsTest() {
		String authorization = null;
		String eventLocation = null;
		UUID assetId = null;
		DateTime from = null;
		DateTime to = null;
		String eventType = null;
		String assetType = null;
		Integer skip = null;
		Integer take = null;
		// InvestmentEventViewModels response = api.getEvents(authorization, eventLocation, assetId, from, to, eventType, assetType, skip, take);

		// TODO: test validations
	}
}
