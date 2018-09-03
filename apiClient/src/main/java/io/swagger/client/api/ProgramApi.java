package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.InvestToProgram;
import io.swagger.client.model.NewProgramRequest;
import io.swagger.client.model.WalletsViewModel;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface ProgramApi
{
	/**
	 * Cancel investment request
	 *
	 * @param authorization JWT access token (required)
	 * @param requestId     (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/program/cancelInvestmentRequest")
	Observable<Void> v10ProgramCancelInvestmentRequestPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("requestId") UUID requestId
	);

	/**
	 * Create a investment program
	 *
	 * @param authorization JWT access token (required)
	 * @param request       (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/program/create")
	Observable<Void> v10ProgramCreatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewProgramRequest request
	);

	/**
	 * Invest to program
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;WalletsViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/program/invest")
	Observable<WalletsViewModel> v10ProgramInvestPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body InvestToProgram model
	);

}
