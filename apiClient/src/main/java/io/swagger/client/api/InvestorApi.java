package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.DashboardChartValue;
import io.swagger.client.model.DashboardPortfolioEvents;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.FundInvestInfo;
import io.swagger.client.model.FundWithdrawInfo;
import io.swagger.client.model.FundsList;
import io.swagger.client.model.ProgramInvestInfo;
import io.swagger.client.model.ProgramRequests;
import io.swagger.client.model.ProgramWithdrawInfo;
import io.swagger.client.model.ProgramsList;
import io.swagger.client.model.SignalsList;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface InvestorApi
{
	/**
	 * Investing into the fund.  Invest in GVT if currency is empty
	 *
	 * @param id            (required)
	 * @param amount        (required)
	 * @param authorization JWT access token (required)
	 * @param currency      (optional, default to 100)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/investor/funds/{id}/invest/{amount}")
	Observable<Void> v10InvestorFundsByIdInvestByAmountPost(
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
	@GET("v1.0/investor/funds/{id}/invest/info/{currency}")
	Observable<FundInvestInfo> v10InvestorFundsByIdInvestInfoByCurrencyGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get program/fund requests
	 *
	 * @param id            (required)
	 * @param skip          (required)
	 * @param take          (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramRequests&gt;
	 */
	@GET("v1.0/investor/funds/{id}/requests/{skip}/{take}")
	Observable<ProgramRequests> v10InvestorFundsByIdRequestsBySkipByTakeGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("skip") Integer skip, @retrofit2.http.Path("take") Integer take, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Withdraw from fund. Percent is % of investor total money.  Withdraw in GVT if currency is empty
	 *
	 * @param id            (required)
	 * @param percent       (required)
	 * @param authorization JWT access token (required)
	 * @param currency      (optional, default to 100)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/investor/funds/{id}/withdraw/{percent}")
	Observable<Void> v10InvestorFundsByIdWithdrawByPercentPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("percent") Double percent, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency
	);

	/**
	 * Data for withdrawal from fund
	 *
	 * @param id            (required)
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;FundWithdrawInfo&gt;
	 */
	@GET("v1.0/investor/funds/{id}/withdraw/info/{currency}")
	Observable<FundWithdrawInfo> v10InvestorFundsByIdWithdrawInfoByCurrencyGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Dashboard funds list
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
	@GET("v1.0/investor/funds")
	Observable<FundsList> v10InvestorFundsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("CurrencySecondary") String currencySecondary, @retrofit2.http.Query("ActionStatus") String actionStatus, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Summary dashboard info
	 *
	 * @param authorization  JWT access token (required)
	 * @param chartCurrency  (optional)
	 * @param from           (optional)
	 * @param to             (optional)
	 * @param balancePoints  (optional)
	 * @param programsPoints (optional)
	 * @param eventsTake     (optional)
	 * @param requestsSkip   (optional)
	 * @param requestsTake   (optional)
	 * @return Call&lt;DashboardSummary&gt;
	 */
	@GET("v1.0/investor")
	Observable<DashboardSummary> v10InvestorGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("chartCurrency") String chartCurrency, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("BalancePoints") Integer balancePoints, @retrofit2.http.Query("ProgramsPoints") Integer programsPoints, @retrofit2.http.Query("eventsTake") Integer eventsTake, @retrofit2.http.Query("requestsSkip") Integer requestsSkip, @retrofit2.http.Query("requestsTake") Integer requestsTake
	);

	/**
	 * Portfolio charts
	 *
	 * @param authorization  JWT access token (required)
	 * @param currency       (optional)
	 * @param from           (optional)
	 * @param to             (optional)
	 * @param balancePoints  (optional)
	 * @param programsPoints (optional)
	 * @return Call&lt;DashboardChartValue&gt;
	 */
	@GET("v1.0/investor/portfolio/chart")
	Observable<DashboardChartValue> v10InvestorPortfolioChartGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("BalancePoints") Integer balancePoints, @retrofit2.http.Query("ProgramsPoints") Integer programsPoints
	);

	/**
	 * Portfolio events
	 *
	 * @param authorization JWT access token (required)
	 * @param assetId       (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param type          (optional)
	 * @param assetType     (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;DashboardPortfolioEvents&gt;
	 */
	@GET("v1.0/investor/portfolio/events")
	Observable<DashboardPortfolioEvents> v10InvestorPortfolioEventsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("Type") String type, @retrofit2.http.Query("AssetType") String assetType, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Investing into the program.  Invest in GVT if currency is empty
	 *
	 * @param id            (required)
	 * @param amount        (required)
	 * @param authorization JWT access token (required)
	 * @param currency      (optional, default to 100)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/investor/programs/{id}/invest/{amount}")
	Observable<Void> v10InvestorProgramsByIdInvestByAmountPost(
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
	@GET("v1.0/investor/programs/{id}/invest/info/{currency}")
	Observable<ProgramInvestInfo> v10InvestorProgramsByIdInvestInfoByCurrencyGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Disable reinvesting
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/investor/programs/{id}/reinvest/off")
	Observable<Void> v10InvestorProgramsByIdReinvestOffPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Enable reinvesting
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/investor/programs/{id}/reinvest/on")
	Observable<Void> v10InvestorProgramsByIdReinvestOnPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get program/fund requests
	 *
	 * @param id            (required)
	 * @param skip          (required)
	 * @param take          (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramRequests&gt;
	 */
	@GET("v1.0/investor/programs/{id}/requests/{skip}/{take}")
	Observable<ProgramRequests> v10InvestorProgramsByIdRequestsBySkipByTakeGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("skip") Integer skip, @retrofit2.http.Path("take") Integer take, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Withdraw from investment program in GVT
	 *
	 * @param id            (required)
	 * @param amount        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/investor/programs/{id}/withdraw/{amount}")
	Observable<Void> v10InvestorProgramsByIdWithdrawByAmountPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("amount") Double amount, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Data for withdrawal from investment program
	 *
	 * @param id            (required)
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramWithdrawInfo&gt;
	 */
	@GET("v1.0/investor/programs/{id}/withdraw/info/{currency}")
	Observable<ProgramWithdrawInfo> v10InvestorProgramsByIdWithdrawInfoByCurrencyGet(
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
	@POST("v1.0/investor/programs/{id}/withdraw/multi/{amount}")
	Observable<Void> v10InvestorProgramsByIdWithdrawMultiByAmountPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("amount") Double amount, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Dashboard program list
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
	@GET("v1.0/investor/programs")
	Observable<ProgramsList> v10InvestorProgramsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("CurrencySecondary") String currencySecondary, @retrofit2.http.Query("ActionStatus") String actionStatus, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Cancel investment program request
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/investor/programs/requests/{id}/cancel")
	Observable<Void> v10InvestorProgramsRequestsByIdCancelPost(
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
	@GET("v1.0/investor/requests/{skip}/{take}")
	Observable<ProgramRequests> v10InvestorRequestsBySkipByTakeGet(
			@retrofit2.http.Path("skip") Integer skip, @retrofit2.http.Path("take") Integer take, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Dashboard signal providers list
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
	 * @return Call&lt;SignalsList&gt;
	 */
	@GET("v1.0/investor/signals")
	Observable<SignalsList> v10InvestorSignalsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("CurrencySecondary") String currencySecondary, @retrofit2.http.Query("ActionStatus") String actionStatus, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
