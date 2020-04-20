package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.AssetFilterType;
import io.swagger.client.model.EventGroupType;
import io.swagger.client.model.InvestmentEventLocation;
import io.swagger.client.model.InvestmentEventType;
import io.swagger.client.model.InvestmentEventViewModels;
import retrofit2.http.GET;
import rx.Observable;

public interface EventsApi
{
	/**
	 * Events
	 *
	 * @param eventLocation    (optional)
	 * @param assetId          (optional)
	 * @param from             (optional)
	 * @param to               (optional)
	 * @param eventType        (optional)
	 * @param assetType        (optional)
	 * @param assetsIds        (optional)
	 * @param forceFilterByIds (optional)
	 * @param eventGroup       (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;InvestmentEventViewModels&gt;
	 */
	@GET("v2.0/events")
	Observable<InvestmentEventViewModels> getEvents(
			@retrofit2.http.Query("EventLocation") InvestmentEventLocation eventLocation, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("EventType") InvestmentEventType eventType, @retrofit2.http.Query("AssetType") AssetFilterType assetType, @retrofit2.http.Query("AssetsIds") List<UUID> assetsIds, @retrofit2.http.Query("ForceFilterByIds") Boolean forceFilterByIds, @retrofit2.http.Query("EventGroup") EventGroupType eventGroup, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
