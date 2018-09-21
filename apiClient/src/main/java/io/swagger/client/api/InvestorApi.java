package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.DashboardChartValue;
import io.swagger.client.model.DashboardPortfolioEvents;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.InvestInfo;
import io.swagger.client.model.ProgramRequests;
import io.swagger.client.model.ProgramsList;
import io.swagger.client.model.WithdrawInfo;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface InvestorApi
{
	/**
	 * Summary dashboard info
	 *
	 * @param authorization JWT access token (required)
	 * @param assetId       (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param type          (optional)
	 * @param assetType     (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @param chartCurrency (optional)
	 * @param chartFrom     (optional)
	 * @param chartTo       (optional)
	 * @param requestsSkip  (optional)
	 * @param requestsTake  (optional)
	 * @return Call&lt;DashboardSummary&gt;
	 */
	@GET("v1.0/investor")
	Observable<DashboardSummary> v10InvestorGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("Type") String type, @retrofit2.http.Query("AssetType") String assetType, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take, @retrofit2.http.Query("chartCurrency") String chartCurrency, @retrofit2.http.Query("chartFrom") DateTime chartFrom, @retrofit2.http.Query("chartTo") DateTime chartTo, @retrofit2.http.Query("requestsSkip") Integer requestsSkip, @retrofit2.http.Query("requestsTake") Integer requestsTake
	);

	/**
	 * Portfolio charts
	 *
	 * @param authorization JWT access token (required)
	 * @param currency      (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @return Call&lt;DashboardChartValue&gt;
	 */
	@GET("v1.0/investor/portfolio/chart")
	Observable<DashboardChartValue> v10InvestorPortfolioChartGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency, @retrofit2.http.Query("from") DateTime from, @retrofit2.http.Query("to") DateTime to
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
	 * investing
	 *
	 * @param id            (required)
	 * @param amount        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/investor/programs/{id}/invest/{amount}")
	Observable<Void> v10InvestorProgramsByIdInvestByAmountPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("amount") Double amount, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Data for investing
	 *
	 * @param id            (required)
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;InvestInfo&gt;
	 */
	@GET("v1.0/investor/programs/{id}/invest/info/{currency}")
	Observable<InvestInfo> v10InvestorProgramsByIdInvestInfoByCurrencyGet(
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
	 * Get program requests
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
	 * Withdrawal
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
	 * Data for withdrawal
	 *
	 * @param id            (required)
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WithdrawInfo&gt;
	 */
	@GET("v1.0/investor/programs/{id}/withdraw/info/{currency}")
	Observable<WithdrawInfo> v10InvestorProgramsByIdWithdrawInfoByCurrencyGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Programs list
	 *
	 * @param authorization    JWT access token (required)
	 * @param sorting          (optional)
	 * @param from             (optional)
	 * @param to               (optional)
	 * @param chartPointsCount (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;ProgramsList&gt;
	 */
	@GET("v1.0/investor/programs")
	Observable<ProgramsList> v10InvestorProgramsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Cancel request
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
	 * @param id            (optional)
	 * @return Call&lt;ProgramRequests&gt;
	 */
	@GET("v1.0/investor/requests/{skip}/{take}")
	Observable<ProgramRequests> v10InvestorRequestsBySkipByTakeGet(
			@retrofit2.http.Path("skip") Integer skip, @retrofit2.http.Path("take") Integer take, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("id") UUID id
	);

}
