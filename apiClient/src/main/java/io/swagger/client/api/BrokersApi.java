package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.BrokersInfo;
import io.swagger.client.model.BrokersProgramInfo;
import retrofit2.http.GET;
import rx.Observable;

public interface BrokersApi
{
	/**
	 * Get trade servers for program
	 *
	 * @param programId (required)
	 * @return Call&lt;BrokersProgramInfo&gt;
	 */
	@GET("v1.0/brokers/{programId}")
	Observable<BrokersProgramInfo> v10BrokersByProgramIdGet(
			@retrofit2.http.Path("programId") UUID programId
	);

	/**
	 * Get all trade servers
	 *
	 * @return Call&lt;BrokersInfo&gt;
	 */
	@GET("v1.0/brokers")
	Observable<BrokersInfo> v10BrokersGet();


}
