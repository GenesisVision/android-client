package io.swagger.client.api;

import io.swagger.client.model.TradesViewModel;
import retrofit2.http.GET;
import rx.Observable;

public interface TradesApi
{
	/**
	 * @param ipfsHashId (optional)
	 * @return Call&lt;TradesViewModel&gt;
	 */
	@GET("api/trades/ipfsHistory")
	Observable<TradesViewModel> apiTradesIpfsHistoryGet(
			@retrofit2.http.Query("ipfsHashId") String ipfsHashId
	);

}
