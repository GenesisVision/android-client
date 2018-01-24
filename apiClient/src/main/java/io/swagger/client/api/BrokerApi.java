package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.BrokerInitData;
import io.swagger.client.model.BrokersFilter;
import io.swagger.client.model.BrokersViewModel;
import io.swagger.client.model.ClosePeriodData;
import io.swagger.client.model.NewManager;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface BrokerApi
{
	/**
	 * @param request (optional)
	 * @return Call&lt;UUID&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/account/create")
	Observable<UUID> apiBrokerAccountCreatePost(
			@retrofit2.http.Body NewManager request
	);

	/**
	 * @param brokerTradeServerId (required)
	 * @return Call&lt;BrokerInitData&gt;
	 */
	@GET("api/broker/initData")
	Observable<BrokerInitData> apiBrokerInitDataGet(
			@retrofit2.http.Query("brokerTradeServerId") UUID brokerTradeServerId
	);

	/**
	 * @param investmentProgramId (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/broker/period/close")
	Observable<Void> apiBrokerPeriodCloseGet(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId
	);

	/**
	 * @param periodId (required)
	 * @param balance  (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/broker/period/setStartBalance")
	Observable<Void> apiBrokerPeriodSetStartBalanceGet(
			@retrofit2.http.Query("periodId") UUID periodId, @retrofit2.http.Query("balance") Double balance
	);

	/**
	 * @param investmentProgramId (required)
	 * @return Call&lt;ClosePeriodData&gt;
	 */
	@GET("api/broker/period/сlosingData")
	Observable<ClosePeriodData> apiBrokerPeriodlosingDataGet(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId
	);

	/**
	 * @param filter (optional)
	 * @return Call&lt;BrokersViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/manager/brokers")
	Observable<BrokersViewModel> apiManagerBrokersPost(
			@retrofit2.http.Body BrokersFilter filter
	);

}
