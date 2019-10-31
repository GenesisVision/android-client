package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.ItemsViewModelProgramDetailsList;
import io.swagger.client.model.ProgramBalanceChart;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramPeriodsViewModel;
import io.swagger.client.model.ProgramProfitCharts;
import io.swagger.client.model.SignalProviderSubscribers;
import io.swagger.client.model.TradesViewModel;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface ProgramsApi
{
	/**
	 * Add to favorites
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/programs/{id}/favorite/add")
	Observable<Void> addToFavorites(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Export periods
	 *
	 * @param id        (required)
	 * @param dateFrom  (optional)
	 * @param dateTo    (optional)
	 * @param numberMin (optional)
	 * @param numberMax (optional)
	 * @param status    (optional)
	 * @param skip      (optional)
	 * @param take      (optional)
	 * @return Call&lt;String&gt;
	 */
	@GET("v2.0/programs/{id}/periods/export")
	Observable<String> exportProgramPeriods(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("NumberMin") Integer numberMin, @retrofit2.http.Query("NumberMax") Integer numberMax, @retrofit2.http.Query("Status") String status, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Export period financial statistic
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param numberMin     (optional)
	 * @param numberMax     (optional)
	 * @param status        (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;String&gt;
	 */
	@GET("v2.0/programs/{id}/periods/export/statistic")
	Observable<String> exportProgramPeriodsFinStatistic(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("NumberMin") Integer numberMin, @retrofit2.http.Query("NumberMax") Integer numberMax, @retrofit2.http.Query("Status") String status, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Export trade history
	 *
	 * @param id              (required)
	 * @param dateFrom        (optional)
	 * @param dateTo          (optional)
	 * @param symbol          (optional)
	 * @param sorting         (optional)
	 * @param accountId       (optional)
	 * @param accountCurrency (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;String&gt;
	 */
	@GET("v2.0/programs/{id}/trades/export")
	Observable<String> exportProgramTrades(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Program balance chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @return Call&lt;ProgramBalanceChart&gt;
	 */
	@GET("v2.0/programs/{id}/charts/balance")
	Observable<ProgramBalanceChart> getProgramBalanceChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency
	);

	/**
	 * Program details
	 *
	 * @param id            (required)
	 * @param authorization (optional)
	 * @return Call&lt;ProgramDetailsFull&gt;
	 */
	@GET("v2.0/programs/{id}")
	Observable<ProgramDetailsFull> getProgramDetails(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Open positions
	 *
	 * @param id              (required)
	 * @param sorting         (optional)
	 * @param symbol          (optional)
	 * @param accountId       (optional)
	 * @param accountCurrency (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;TradesViewModel&gt;
	 */
	@GET("v2.0/programs/{id}/trades/open")
	Observable<TradesViewModel> getProgramOpenTrades(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Program periods
	 *
	 * @param id            (required)
	 * @param authorization (optional)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param numberMin     (optional)
	 * @param numberMax     (optional)
	 * @param status        (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;ProgramPeriodsViewModel&gt;
	 */
	@GET("v2.0/programs/{id}/periods")
	Observable<ProgramPeriodsViewModel> getProgramPeriods(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("NumberMin") Integer numberMin, @retrofit2.http.Query("NumberMax") Integer numberMax, @retrofit2.http.Query("Status") String status, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Program profit chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @param currencies    (optional)
	 * @return Call&lt;ProgramProfitCharts&gt;
	 */
	@GET("v2.0/programs/{id}/charts/profit")
	Observable<ProgramProfitCharts> getProgramProfitChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency, @retrofit2.http.Query("currencies") List<Object> currencies
	);

	/**
	 * Signal subscribers
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param status        (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;SignalProviderSubscribers&gt;
	 */
	@GET("v2.0/programs/{id}/subscribers")
	Observable<SignalProviderSubscribers> getProgramSubscribers(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Status") String status, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Trade history
	 *
	 * @param id              (required)
	 * @param dateFrom        (optional)
	 * @param dateTo          (optional)
	 * @param symbol          (optional)
	 * @param sorting         (optional)
	 * @param accountId       (optional)
	 * @param accountCurrency (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;TradesViewModel&gt;
	 */
	@GET("v2.0/programs/{id}/trades")
	Observable<TradesViewModel> getProgramTrades(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Programs list
	 *
	 * @param authorization     (optional)
	 * @param tags              (optional)
	 * @param programCurrency   (optional)
	 * @param levelMin          (optional)
	 * @param levelMax          (optional)
	 * @param statisticDateFrom (optional)
	 * @param statisticDateTo   (optional)
	 * @param chartPointsCount  (optional)
	 * @param chartCurrency     (optional)
	 * @param facetId           (optional)
	 * @param mask              (optional)
	 * @param skip              (optional)
	 * @param take              (optional)
	 * @return Call&lt;ItemsViewModelProgramDetailsList&gt;
	 */
	@GET("v2.0/programs")
	Observable<ItemsViewModelProgramDetailsList> getPrograms(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Tags") List<String> tags, @retrofit2.http.Query("ProgramCurrency") String programCurrency, @retrofit2.http.Query("LevelMin") Integer levelMin, @retrofit2.http.Query("LevelMax") Integer levelMax, @retrofit2.http.Query("StatisticDateFrom") DateTime statisticDateFrom, @retrofit2.http.Query("StatisticDateTo") DateTime statisticDateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("ChartCurrency") String chartCurrency, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Remove from favorites
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/programs/{id}/favorite/remove")
	Observable<Void> removeFromFavorites(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

}
