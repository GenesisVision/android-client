package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.BrokersInfo;
import io.swagger.client.model.BrokersProgramInfo;
import retrofit2.http.GET;
import rx.Observable;

public interface BrokersApi
{
	/**
	 * Get brokers
	 *
	 * @return Call&lt;BrokersInfo&gt;
	 */
	@GET("v2.0/brokers")
	Observable<BrokersInfo> getBrokers();


	/**
	 * Get brokers for program
	 *
	 * @param programId (required)
	 * @return Call&lt;BrokersProgramInfo&gt;
	 */
	@GET("v2.0/brokers/{programId}")
	Observable<BrokersProgramInfo> getBrokersForProgram(
			@retrofit2.http.Path("programId") UUID programId
	);

}
