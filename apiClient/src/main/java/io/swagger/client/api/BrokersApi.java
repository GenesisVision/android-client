package io.swagger.client.api;

import io.swagger.client.model.BrokersInfo;
import retrofit2.http.GET;
import rx.Observable;

public interface BrokersApi
{
	/**
	 * Get all trade servers
	 *
	 * @return Call&lt;BrokersInfo&gt;
	 */
	@GET("v1.0/brokers")
	Observable<BrokersInfo> v10BrokersGet();


}
