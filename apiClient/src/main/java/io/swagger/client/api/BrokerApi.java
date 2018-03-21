package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.BrokerInitData;
import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.ClosePeriodData;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.InvestmentProgramAccrual;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.ManagerHistoryIpfsHash;
import io.swagger.client.model.NewManager;
import io.swagger.client.model.NewOpenTradesEvent;
import io.swagger.client.model.NewTradeEvent;
import io.swagger.client.model.ResetPasswordViewModel;
import io.swagger.client.model.StartValues;
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
	 * Confirm email after registration
	 *
	 * @param userId (optional)
	 * @param code   (optional)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/broker/auth/confirmEmail")
	Observable<Void> apiBrokerAuthConfirmEmailGet(
			@retrofit2.http.Query("userId") String userId, @retrofit2.http.Query("code") String code
	);

	/**
	 * Forgot password
	 *
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/auth/forgotPassword")
	Observable<Void> apiBrokerAuthForgotPasswordPost(
			@retrofit2.http.Body ForgotPasswordViewModel model
	);

	/**
	 * Reset password
	 *
	 * @param model (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/auth/resetPassword")
	Observable<String> apiBrokerAuthResetPasswordPost(
			@retrofit2.http.Body ResetPasswordViewModel model
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
	 * Change password
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/auth/сhangePassword")
	Observable<Void> apiBrokerAuthhangePasswordPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ChangePasswordViewModel model
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
	 * Accrue investors&#39; profits
	 *
	 * @param authorization JWT access token (required)
	 * @param accrual       (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/period/accrueProfits")
	Observable<Void> apiBrokerPeriodAccrueProfitsPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body InvestmentProgramAccrual accrual
	);

	/**
	 * Close investment period
	 *
	 * @param investmentProgramId (required)
	 * @param currentBalance      (required)
	 * @param authorization       JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("api/broker/period/close")
	Observable<Void> apiBrokerPeriodClosePost(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId, @retrofit2.http.Query("currentBalance") Double currentBalance, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Close investment program
	 *
	 * @param investmentProgramId (required)
	 * @param managerBalance      (required)
	 * @param authorization       JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("api/broker/period/processClosingProgram")
	Observable<Void> apiBrokerPeriodProcessClosingProgramPost(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId, @retrofit2.http.Query("managerBalance") Double managerBalance, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Process investment requests
	 *
	 * @param investmentProgramId (required)
	 * @param authorization       JWT access token (required)
	 * @return Call&lt;UUID&gt;
	 */
	@POST("api/broker/period/processInvestmentRequests")
	Observable<UUID> apiBrokerPeriodProcessInvestmentRequestsPost(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Update manager token initial price/total supply after loss
	 *
	 * @param investmentProgramId (required)
	 * @param investorLossShare   (required)
	 * @param authorization       JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("api/broker/period/reevaluateManagerToken")
	Observable<Void> apiBrokerPeriodReevaluateManagerTokenPost(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId, @retrofit2.http.Query("investorLossShare") Double investorLossShare, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Set investment period start balance, manager share, manager balance
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/period/setStartValues")
	Observable<Void> apiBrokerPeriodSetStartValuesPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body StartValues model
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

	/**
	 * Update manager history ipfs hash
	 *
	 * @param authorization JWT access token (required)
	 * @param data          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/trades/ipfsHash/update")
	Observable<Void> apiBrokerTradesIpfsHashUpdatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ManagerHistoryIpfsHash data
	);

	/**
	 * New trade event
	 *
	 * @param authorization JWT access token (required)
	 * @param tradeEvent    (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/trades/new")
	Observable<Void> apiBrokerTradesNewPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewTradeEvent tradeEvent
	);

	/**
	 * New open trades event
	 *
	 * @param authorization JWT access token (required)
	 * @param trades        (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/trades/openTrades/new")
	Observable<Void> apiBrokerTradesOpenTradesNewPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewOpenTradesEvent trades
	);

}
