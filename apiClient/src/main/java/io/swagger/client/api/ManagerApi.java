package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.FundAssetPart;
import io.swagger.client.model.FundInvestInfo;
import io.swagger.client.model.FundsList;
import io.swagger.client.model.ManagerAssets;
import io.swagger.client.model.ManagerDashboard;
import io.swagger.client.model.ManagerFundWithdrawInfo;
import io.swagger.client.model.ManagerPortfolioEvents;
import io.swagger.client.model.ManagerProfile;
import io.swagger.client.model.ManagerProfileDetails;
import io.swagger.client.model.ManagerProgramCreateResult;
import io.swagger.client.model.ManagerProgramWithdrawInfo;
import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.NewProgramRequest;
import io.swagger.client.model.ProgramInvestInfo;
import io.swagger.client.model.ProgramMinimumDeposit;
import io.swagger.client.model.ProgramPwdUpdate;
import io.swagger.client.model.ProgramRequests;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.ProgramsList;
import io.swagger.client.model.TwoFactorAuthenticator;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface ManagerApi
{
	/**
	 * Manager assets list
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ManagerAssets&gt;
	 */
	@GET("v1.0/manager/assets")
	Observable<ManagerAssets> v10ManagerAssetsGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

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
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;ManagerPortfolioEvents&gt;
	 */
	@GET("v1.0/manager/events")
	Observable<ManagerPortfolioEvents> v10ManagerEventsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("Type") String type, @retrofit2.http.Query("AssetType") String assetType, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Update fund assets parts
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param assets        (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/manager/funds/{id}/assets/update")
	Observable<Void> v10ManagerFundsByIdAssetsUpdatePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body List<FundAssetPart> assets
	);

	/**
	 * Close existing fund
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
	 * Deposit.  Invest in GVT if currency is empty
	 *
	 * @param id            (required)
	 * @param amount        (required)
	 * @param authorization JWT access token (required)
	 * @param currency      (optional, default to 100)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/funds/{id}/invest/{amount}")
	Observable<Void> v10ManagerFundsByIdInvestByAmountPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("amount") Double amount, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency
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
	 * Withdraw from fund. Percent is % of manager total money.  Withdraw in GVT if currency is empty
	 *
	 * @param id            (required)
	 * @param percent       (required)
	 * @param authorization JWT access token (required)
	 * @param currency      (optional, default to 100)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/funds/{id}/withdraw/{percent}")
	Observable<Void> v10ManagerFundsByIdWithdrawByPercentPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("percent") Double percent, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency
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
	 * @param authorization     JWT access token (required)
	 * @param sorting           (optional)
	 * @param from              (optional)
	 * @param to                (optional)
	 * @param chartPointsCount  (optional)
	 * @param currencySecondary (optional)
	 * @param actionStatus      (optional)
	 * @param skip              (optional)
	 * @param take              (optional)
	 * @return Call&lt;FundsList&gt;
	 */
	@GET("v1.0/manager/funds")
	Observable<FundsList> v10ManagerFundsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("CurrencySecondary") String currencySecondary, @retrofit2.http.Query("ActionStatus") String actionStatus, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
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
	 * @param assetId       (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param type          (optional)
	 * @param assetType     (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;ManagerDashboard&gt;
	 */
	@GET("v1.0/manager")
	Observable<ManagerDashboard> v10ManagerGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("Type") String type, @retrofit2.http.Query("AssetType") String assetType, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Confirm 2FA for program if required (for brokers like Huobi)
	 *
	 * @param authorization JWT access token (required)
	 * @param programId     (optional)
	 * @param code          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/programs/2fa/confirm")
	Observable<Void> v10ManagerPrograms2faConfirmPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("programId") UUID programId, @retrofit2.http.Query("code") String code
	);

	/**
	 * Get 2FA for program if needed
	 *
	 * @param authorization JWT access token (required)
	 * @param programId     (optional)
	 * @return Call&lt;TwoFactorAuthenticator&gt;
	 */
	@GET("v1.0/manager/programs/2fa/get")
	Observable<TwoFactorAuthenticator> v10ManagerPrograms2faGetGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("programId") UUID programId
	);

	/**
	 * Close existing investment program
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
	 * Deposit  Invest in GVT if currency is empty
	 *
	 * @param id            (required)
	 * @param amount        (required)
	 * @param authorization JWT access token (required)
	 * @param currency      (optional, default to 100)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/programs/{id}/invest/{amount}")
	Observable<Void> v10ManagerProgramsByIdInvestByAmountPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("amount") Double amount, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency
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
	 * Change program password
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/manager/programs/{id}/password/change")
	Observable<Void> v10ManagerProgramsByIdPasswordChangePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ProgramPwdUpdate model
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
	 * Withdraw from investment program in GVT
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
	 * Withdraw from investment program in program currency
	 *
	 * @param id            (required)
	 * @param amount        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/programs/{id}/withdraw/multi/{amount}")
	Observable<Void> v10ManagerProgramsByIdWithdrawMultiByAmountPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("amount") Double amount, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Create an investment program
	 *
	 * @param authorization JWT access token (required)
	 * @param request       (optional)
	 * @return Call&lt;ManagerProgramCreateResult&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/manager/programs/create")
	Observable<ManagerProgramCreateResult> v10ManagerProgramsCreatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewProgramRequest request
	);

	/**
	 * Manager programs
	 *
	 * @param authorization     JWT access token (required)
	 * @param sorting           (optional)
	 * @param from              (optional)
	 * @param to                (optional)
	 * @param chartPointsCount  (optional)
	 * @param currencySecondary (optional)
	 * @param actionStatus      (optional)
	 * @param skip              (optional)
	 * @param take              (optional)
	 * @return Call&lt;ProgramsList&gt;
	 */
	@GET("v1.0/manager/programs")
	Observable<ProgramsList> v10ManagerProgramsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("CurrencySecondary") String currencySecondary, @retrofit2.http.Query("ActionStatus") String actionStatus, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get investment amount to create program
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramMinimumDeposit&gt;
	 */
	@GET("v1.0/manager/programs/investment/amount")
	Observable<ProgramMinimumDeposit> v10ManagerProgramsInvestmentAmountGet(
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

	/**
	 * Make manager&#39;s program signal provider
	 *
	 * @param authorization   JWT access token (required)
	 * @param programId       (optional)
	 * @param subscriptionFee (optional)
	 * @param successFee      (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/signal/create")
	Observable<Void> v10ManagerSignalCreatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("ProgramId") UUID programId, @retrofit2.http.Query("SubscriptionFee") Double subscriptionFee, @retrofit2.http.Query("SuccessFee") Double successFee
	);

	/**
	 * Make manager&#39;s program signal provider
	 *
	 * @param authorization   JWT access token (required)
	 * @param programId       (optional)
	 * @param subscriptionFee (optional)
	 * @param successFee      (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/manager/signal/edit")
	Observable<Void> v10ManagerSignalEditPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("ProgramId") UUID programId, @retrofit2.http.Query("SubscriptionFee") Double subscriptionFee, @retrofit2.http.Query("SuccessFee") Double successFee
	);

}
