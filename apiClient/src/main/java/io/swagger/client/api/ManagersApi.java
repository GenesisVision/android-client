package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.AssetsPartsChangeRequest;
import io.swagger.client.model.ManagerProfile;
import io.swagger.client.model.ManagerProfileDetails;
import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.NewProgramRequest;
import io.swagger.client.model.ProgramRequests;
import io.swagger.client.model.ProgramUpdate;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface ManagersApi
{
	/**
	 * Manager details
	 *
	 * @param id (required)
	 * @return Call&lt;ManagerProfileDetails&gt;
	 */
	@GET("v1.0/managers/{id}/details")
	Observable<ManagerProfileDetails> v10ManagersByIdDetailsGet(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * Manager profile
	 *
	 * @param id (required)
	 * @return Call&lt;ManagerProfile&gt;
	 */
	@GET("v1.0/managers/{id}")
	Observable<ManagerProfile> v10ManagersByIdGet(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * Update fund assets parts
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/managers/funds/{id}/assets/update")
	Observable<Void> v10ManagersFundsByIdAssetsUpdatePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body AssetsPartsChangeRequest model
	);

	/**
	 * Close existing investment program/fund
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param twoFactorCode (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/managers/funds/{id}/close")
	Observable<Void> v10ManagersFundsByIdClosePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("twoFactorCode") String twoFactorCode
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
	@GET("v1.0/managers/funds/{id}/requests/{skip}/{take}")
	Observable<ProgramRequests> v10ManagersFundsByIdRequestsBySkipByTakeGet(
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
	@POST("v1.0/managers/funds/{id}/update")
	Observable<Void> v10ManagersFundsByIdUpdatePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ProgramUpdate model
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
	@POST("v1.0/managers/funds/create")
	Observable<Void> v10ManagersFundsCreatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewFundRequest request
	);

	/**
	 * Get GVT investment to create fund
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Double&gt;
	 */
	@GET("v1.0/managers/funds/investment/amount")
	Observable<Double> v10ManagersFundsInvestmentAmountGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Cancel investment program/fund request
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/managers/funds/requests/{id}/cancel")
	Observable<Void> v10ManagersFundsRequestsByIdCancelPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Close existing investment program/fund
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param twoFactorCode (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/managers/programs/{id}/close")
	Observable<Void> v10ManagersProgramsByIdClosePost(
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
	@POST("v1.0/managers/programs/{id}/deposit/{amount}")
	Observable<Void> v10ManagersProgramsByIdDepositByAmountPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("amount") Double amount, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Close current period
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/managers/programs/{id}/period/close")
	Observable<Void> v10ManagersProgramsByIdPeriodClosePost(
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
	@GET("v1.0/managers/programs/{id}/requests/{skip}/{take}")
	Observable<ProgramRequests> v10ManagersProgramsByIdRequestsBySkipByTakeGet(
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
	@POST("v1.0/managers/programs/{id}/update")
	Observable<Void> v10ManagersProgramsByIdUpdatePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ProgramUpdate model
	);

	/**
	 * Withdraw
	 *
	 * @param id            (required)
	 * @param amount        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/managers/programs/{id}/withdraw/{amount}")
	Observable<Void> v10ManagersProgramsByIdWithdrawByAmountPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("amount") Double amount, @retrofit2.http.Header("Authorization") String authorization
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
	@POST("v1.0/managers/programs/create")
	Observable<Void> v10ManagersProgramsCreatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewProgramRequest request
	);

	/**
	 * Get GVT investment to create program
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Double&gt;
	 */
	@GET("v1.0/managers/programs/investment/amount")
	Observable<Double> v10ManagersProgramsInvestmentAmountGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Cancel investment program/fund request
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/managers/programs/requests/{id}/cancel")
	Observable<Void> v10ManagersProgramsRequestsByIdCancelPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

}
