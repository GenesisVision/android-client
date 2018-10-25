package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.FundInvestInfo;
import io.swagger.client.model.ManagerDashboard;
import io.swagger.client.model.ManagerFundWithdrawInfo;
import io.swagger.client.model.ManagerFunds;
import io.swagger.client.model.ManagerPortfolioEvents;
import io.swagger.client.model.ManagerProfile;
import io.swagger.client.model.ManagerProfileDetails;
import io.swagger.client.model.ManagerProgramWithdrawInfo;
import io.swagger.client.model.ManagerPrograms;
import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.NewProgramRequest;
import io.swagger.client.model.ProgramInvestInfo;
import io.swagger.client.model.ProgramRequests;
import io.swagger.client.model.ProgramUpdate;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface ManagerApi
{
	/**
	 * Manager details
	 *
	 * @param id (required)
	 * @return Call&lt;ManagerProfileDetails&gt;
	 */
	@GET("v1.0/manager/{id}/details")
	Observable<ManagerProfileDetails> v10ManagerByIdDetailsGet(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * Manager profile
	 *
	 * @param id (required)
	 * @return Call&lt;ManagerProfile&gt;
	 */
	@GET("v1.0/manager/{id}")
	Observable<ManagerProfile> v10ManagerByIdGet(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * Manager events
	 *
	 * @param authorization JWT access token (required)
	 * @param assetId       (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param type          (optional)
	 * @param assetType     (optional)
	 * @return Call&lt;ManagerPortfolioEvents&gt;
	 */
	@GET("v1.0/manager/events")
	Observable<ManagerPortfolioEvents> v10ManagerEventsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("Type") String type, @retrofit2.http.Query("AssetType") String assetType
	);

	/**
	 * Close existing investment program/fund
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param twoFactorCode (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/funds/{id}/close")
	Observable<Void> v10ManagerFundsByIdClosePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("twoFactorCode") String twoFactorCode
	);

	/**
	 * Deposit
	 *
	 * @param id            (required)
	 * @param amount        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/funds/{id}/invest/{amount}")
	Observable<Void> v10ManagerFundsByIdInvestByAmountPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("amount") Double amount, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Data for investing into the fund
	 *
	 * @param id            (required)
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;FundInvestInfo&gt;
	 */
	@GET("v1.0/manager/funds/{id}/invest/info/{currency}")
	Observable<FundInvestInfo> v10ManagerFundsByIdInvestInfoByCurrencyGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get investment program/fund requests
	 *
	 * @param id            (required)
	 * @param skip          (required)
	 * @param take          (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramRequests&gt;
	 */
	@GET("v1.0/manager/funds/{id}/requests/{skip}/{take}")
	Observable<ProgramRequests> v10ManagerFundsByIdRequestsBySkipByTakeGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("skip") Integer skip, @retrofit2.http.Path("take") Integer take, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Update investment program/fund details
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/manager/funds/{id}/update")
	Observable<Void> v10ManagerFundsByIdUpdatePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ProgramUpdate model
	);

	/**
	 * Withdraw from fund. Percent is % of investor total money.
	 *
	 * @param id            (required)
	 * @param percent       (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/funds/{id}/withdraw/{percent}")
	Observable<Void> v10ManagerFundsByIdWithdrawByPercentPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("percent") Double percent, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Data for withdrawal from fund
	 *
	 * @param id            (required)
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ManagerFundWithdrawInfo&gt;
	 */
	@GET("v1.0/manager/funds/{id}/withdraw/info/{currency}")
	Observable<ManagerFundWithdrawInfo> v10ManagerFundsByIdWithdrawInfoByCurrencyGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Create fund
	 *
	 * @param authorization JWT access token (required)
	 * @param request       (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/manager/funds/create")
	Observable<Void> v10ManagerFundsCreatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewFundRequest request
	);

	/**
	 * Manager funds
	 *
	 * @param authorization JWT access token (required)
	 * @param sorting       (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param pointsCount   (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;ManagerFunds&gt;
	 */
	@GET("v1.0/manager/funds")
	Observable<ManagerFunds> v10ManagerFundsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("PointsCount") Integer pointsCount, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get GVT investment to create fund
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Double&gt;
	 */
	@GET("v1.0/manager/funds/investment/amount")
	Observable<Double> v10ManagerFundsInvestmentAmountGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Cancel investment program/fund request
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/funds/requests/{id}/cancel")
	Observable<Void> v10ManagerFundsRequestsByIdCancelPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Manager dashboard
	 *
	 * @param authorization JWT access token (required)
	 * @param eventsTake    (optional)
	 * @param requestsSkip  (optional)
	 * @param requestsTake  (optional)
	 * @return Call&lt;ManagerDashboard&gt;
	 */
	@GET("v1.0/manager")
	Observable<ManagerDashboard> v10ManagerGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("eventsTake") Integer eventsTake, @retrofit2.http.Query("requestsSkip") Integer requestsSkip, @retrofit2.http.Query("requestsTake") Integer requestsTake
	);

	/**
	 * Close existing investment program/fund
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param twoFactorCode (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/programs/{id}/close")
	Observable<Void> v10ManagerProgramsByIdClosePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("twoFactorCode") String twoFactorCode
	);

	/**
	 * Deposit
	 *
	 * @param id            (required)
	 * @param amount        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/programs/{id}/invest/{amount}")
	Observable<Void> v10ManagerProgramsByIdInvestByAmountPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("amount") Double amount, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Data for investing into the program
	 *
	 * @param id            (required)
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramInvestInfo&gt;
	 */
	@GET("v1.0/manager/programs/{id}/invest/info/{currency}")
	Observable<ProgramInvestInfo> v10ManagerProgramsByIdInvestInfoByCurrencyGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Close current period
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/programs/{id}/period/close")
	Observable<Void> v10ManagerProgramsByIdPeriodClosePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get investment program/fund requests
	 *
	 * @param id            (required)
	 * @param skip          (required)
	 * @param take          (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramRequests&gt;
	 */
	@GET("v1.0/manager/programs/{id}/requests/{skip}/{take}")
	Observable<ProgramRequests> v10ManagerProgramsByIdRequestsBySkipByTakeGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("skip") Integer skip, @retrofit2.http.Path("take") Integer take, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Update investment program/fund details
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/manager/programs/{id}/update")
	Observable<Void> v10ManagerProgramsByIdUpdatePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ProgramUpdate model
	);

	/**
	 * Withdraw from program
	 *
	 * @param id            (required)
	 * @param amount        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/programs/{id}/withdraw/{amount}")
	Observable<Void> v10ManagerProgramsByIdWithdrawByAmountPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("amount") Double amount, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Data for withdrawal from investment program
	 *
	 * @param id            (required)
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ManagerProgramWithdrawInfo&gt;
	 */
	@GET("v1.0/manager/programs/{id}/withdraw/info/{currency}")
	Observable<ManagerProgramWithdrawInfo> v10ManagerProgramsByIdWithdrawInfoByCurrencyGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Create an investment program
	 *
	 * @param authorization JWT access token (required)
	 * @param request       (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/manager/programs/create")
	Observable<Void> v10ManagerProgramsCreatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewProgramRequest request
	);

	/**
	 * Manager programs
	 *
	 * @param authorization JWT access token (required)
	 * @param sorting       (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param pointsCount   (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;ManagerPrograms&gt;
	 */
	@GET("v1.0/manager/programs")
	Observable<ManagerPrograms> v10ManagerProgramsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("PointsCount") Integer pointsCount, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get GVT investment to create program
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Double&gt;
	 */
	@GET("v1.0/manager/programs/investment/amount")
	Observable<Double> v10ManagerProgramsInvestmentAmountGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Cancel investment program/fund request
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/programs/requests/{id}/cancel")
	Observable<Void> v10ManagerProgramsRequestsByIdCancelPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get all requests
	 *
	 * @param skip          (required)
	 * @param take          (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramRequests&gt;
	 */
	@GET("v1.0/manager/requests/{skip}/{take}")
	Observable<ProgramRequests> v10ManagerRequestsBySkipByTakeGet(
			@retrofit2.http.Path("skip") Integer skip, @retrofit2.http.Path("take") Integer take, @retrofit2.http.Header("Authorization") String authorization
	);

}
