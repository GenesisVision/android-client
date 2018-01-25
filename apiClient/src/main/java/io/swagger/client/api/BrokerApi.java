package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.BrokerInitData;
import io.swagger.client.model.ClosePeriodData;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.NewManager;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface BrokerApi
{
	/**
	 * Create manager
	 *
	 * @param authorization JWT access token (required)
	 * @param request       (optional)
	 * @return Call&lt;UUID&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/account/create")
	Observable<UUID> apiBrokerAccountCreatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewManager request
	);

	/**
	 * Authorize
	 *
	 * @param model (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/auth/signIn")
	Observable<String> apiBrokerAuthSignInPost(
			@retrofit2.http.Body LoginViewModel model
	);

	/**
	 * Update auth token
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;String&gt;
	 */
	@GET("api/broker/auth/updateToken")
	Observable<String> apiBrokerAuthUpdateTokenGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get broker initial data
	 *
	 * @param brokerTradeServerId (required)
	 * @param authorization       JWT access token (required)
	 * @return Call&lt;BrokerInitData&gt;
	 */
	@GET("api/broker/initData")
	Observable<BrokerInitData> apiBrokerInitDataGet(
			@retrofit2.http.Query("brokerTradeServerId") UUID brokerTradeServerId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Close investment period
	 *
	 * @param investmentProgramId (required)
	 * @param authorization       JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/broker/period/close")
	Observable<Void> apiBrokerPeriodCloseGet(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Set investment period start balance
	 *
	 * @param periodId      (required)
	 * @param balance       (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/broker/period/setStartBalance")
	Observable<Void> apiBrokerPeriodSetStartBalanceGet(
			@retrofit2.http.Query("periodId") UUID periodId, @retrofit2.http.Query("balance") Double balance, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get data for closing investment period
	 *
	 * @param investmentProgramId (required)
	 * @param authorization       JWT access token (required)
	 * @return Call&lt;ClosePeriodData&gt;
	 */
	@GET("api/broker/period/сlosingData")
	Observable<ClosePeriodData> apiBrokerPeriodlosingDataGet(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId, @retrofit2.http.Header("Authorization") String authorization
	);

}
