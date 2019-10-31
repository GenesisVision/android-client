package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.FundWithdrawInfo;
import io.swagger.client.model.ItemsViewModelAssetInvestmentRequest;
import io.swagger.client.model.ProgramWithdrawInfo;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface InvestmentsApi
{
	/**
	 * Cancel investment request
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/requests/{id}/cancel")
	Observable<Void> cancelRequest(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Data for withdrawal from fund (in selected currency)
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param currency      (optional)
	 * @return Call&lt;FundWithdrawInfo&gt;
	 */
	@GET("v2.0/investments/funds/{id}/withdraw/info")
	Observable<FundWithdrawInfo> getFundWithdrawInfo(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency
	);

	/**
	 * Data for withdrawal from investment program (in program currency)
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramWithdrawInfo&gt;
	 */
	@GET("v2.0/investments/programs/{id}/withdraw/info")
	Observable<ProgramWithdrawInfo> getProgramWithdrawInfo(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get all requests
	 *
	 * @param skip          (required)
	 * @param take          (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ItemsViewModelAssetInvestmentRequest&gt;
	 */
	@GET("v2.0/investments/requests/{skip}/{take}")
	Observable<ItemsViewModelAssetInvestmentRequest> getRequests(
			@retrofit2.http.Path("skip") Integer skip, @retrofit2.http.Path("take") Integer take, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get program/fund requests
	 *
	 * @param id            (required)
	 * @param skip          (required)
	 * @param take          (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ItemsViewModelAssetInvestmentRequest&gt;
	 */
	@GET("v2.0/investments/requests/{id}/{skip}/{take}")
	Observable<ItemsViewModelAssetInvestmentRequest> getRequestsByProgram(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("skip") Integer skip, @retrofit2.http.Path("take") Integer take, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Investing into the fund
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param amount        (optional)
	 * @param walletId      (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/funds/{id}/invest")
	Observable<Void> investIntoFund(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("amount") Double amount, @retrofit2.http.Query("walletId") UUID walletId
	);

	/**
	 * Investing into the program
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param amount        (optional)
	 * @param walletId      (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/programs/{id}/invest")
	Observable<Void> investIntoProgram(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("amount") Double amount, @retrofit2.http.Query("walletId") UUID walletId
	);

	/**
	 * Disable reinvesting
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/programs/{id}/reinvest/off")
	Observable<Void> switchReinvestOff(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Enable reinvesting
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/programs/{id}/reinvest/on")
	Observable<Void> switchReinvestOn(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Withdraw from fund. Percent is % of manager total money
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param percent       (optional)
	 * @param currency      (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/funds/{id}/withdraw")
	Observable<Void> withdrawFromFund(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("percent") Double percent, @retrofit2.http.Query("currency") String currency
	);

	/**
	 * Withdrawal from program
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param amount        (optional)
	 * @param withdrawAll   (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/programs/{id}/withdraw")
	Observable<Void> withdrawFromProgram(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("amount") Double amount, @retrofit2.http.Query("withdrawAll") Boolean withdrawAll
	);

}
