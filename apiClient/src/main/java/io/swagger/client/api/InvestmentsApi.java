package io.swagger.client.api;//retrofit2

import java.util.UUID;

import io.swagger.client.model.AssetInvestmentRequestItemsViewModel;
import io.swagger.client.model.Currency;
import io.swagger.client.model.FundWithdrawInfo;
import io.swagger.client.model.ProgramWithdrawInfo;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface InvestmentsApi
{
	/**
	 * Cancel investment request
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/requests/{id}/cancel")
	Observable<Void> cancelRequest(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Data for withdrawal from fund (in selected currency)
	 *
	 * @param id       (required)
	 * @param currency (optional)
	 * @return Call&lt;FundWithdrawInfo&gt;
	 */
	@GET("v2.0/investments/funds/{id}/withdraw/info")
	Observable<FundWithdrawInfo> getFundWithdrawInfo(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("currency") Currency currency
	);

	/**
	 * Data for withdrawal from investment program (in program currency)
	 *
	 * @param id (required)
	 * @return Call&lt;ProgramWithdrawInfo&gt;
	 */
	@GET("v2.0/investments/programs/{id}/withdraw/info")
	Observable<ProgramWithdrawInfo> getProgramWithdrawInfo(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Get all requests
	 *
	 * @param skip (required)
	 * @param take (required)
	 * @return Call&lt;AssetInvestmentRequestItemsViewModel&gt;
	 */
	@GET("v2.0/investments/requests/{skip}/{take}")
	Observable<AssetInvestmentRequestItemsViewModel> getRequests(
			@retrofit2.http.Path("skip") Integer skip, @retrofit2.http.Path("take") Integer take
	);

	/**
	 * Get program/fund requests
	 *
	 * @param id   (required)
	 * @param skip (required)
	 * @param take (required)
	 * @return Call&lt;AssetInvestmentRequestItemsViewModel&gt;
	 */
	@GET("v2.0/investments/requests/{id}/{skip}/{take}")
	Observable<AssetInvestmentRequestItemsViewModel> getRequestsByProgram(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("skip") Integer skip, @retrofit2.http.Path("take") Integer take
	);

	/**
	 * Investing into the fund
	 *
	 * @param id       (required)
	 * @param amount   (optional)
	 * @param walletId (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/funds/{id}/invest")
	Observable<Void> investIntoFund(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("amount") Double amount, @retrofit2.http.Query("walletId") UUID walletId
	);

	/**
	 * Investing into the program
	 *
	 * @param id       (required)
	 * @param amount   (optional)
	 * @param walletId (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/programs/{id}/invest")
	Observable<Void> investIntoProgram(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("amount") Double amount, @retrofit2.http.Query("walletId") UUID walletId
	);

	/**
	 * Disable autojoin
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/programs/{id}/autojoin/off")
	Observable<Void> switchAutoJoinOff(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Enable autojoin
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/programs/{id}/autojoin/on")
	Observable<Void> switchAutoJoinOn(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Disable reinvesting
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/programs/{id}/reinvest/off")
	Observable<Void> switchReinvestOff(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Enable reinvesting
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/programs/{id}/reinvest/on")
	Observable<Void> switchReinvestOn(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Withdraw from fund. Percent is % of manager total money
	 *
	 * @param id       (required)
	 * @param percent  (optional)
	 * @param currency (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/funds/{id}/withdraw")
	Observable<Void> withdrawFromFund(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("percent") Double percent, @retrofit2.http.Query("currency") Currency currency
	);

	/**
	 * Withdrawal from program
	 *
	 * @param id          (required)
	 * @param amount      (optional)
	 * @param withdrawAll (optional, default to false)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/investments/programs/{id}/withdraw")
	Observable<Void> withdrawFromProgram(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("amount") Double amount, @retrofit2.http.Query("withdrawAll") Boolean withdrawAll
	);

}
