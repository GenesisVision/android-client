package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.InvestmentProgramUpdate;
import io.swagger.client.model.ManagerProfile;
import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.NewProgramRequest;
import io.swagger.client.model.ProgramRequests;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface ManagersApi
{
	/**
	 * Manager profile
	 *
	 * @param id (required)
	 * @return Call&lt;ManagerProfile&gt;
	 */
	@GET("v1.0/managers/{id}")
	Observable<ManagerProfile> v10ManagersByIdGet(
			@retrofit2.http.Path("id") UUID id
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
	 * Close existing investment program
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/managers/programs/{id}/close")
	Observable<Void> v10ManagersProgramsByIdClosePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
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
	 * Get requests
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
	 * Update investment program details
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
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body InvestmentProgramUpdate model
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
	 * Cancel request
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
