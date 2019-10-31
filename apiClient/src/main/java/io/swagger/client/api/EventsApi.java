package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.InvestmentEventViewModels;
import retrofit2.http.GET;
import rx.Observable;

public interface EventsApi
{
	/**
	 * Events
	 *
	 * @param authorization JWT access token (required)
	 * @param eventLocation (optional)
	 * @param assetId       (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param eventType     (optional)
	 * @param assetType     (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;InvestmentEventViewModels&gt;
	 */
	@GET("v2.0/events")
	Observable<InvestmentEventViewModels> getEvents(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("EventLocation") String eventLocation, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("EventType") String eventType, @retrofit2.http.Query("AssetType") String assetType, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
