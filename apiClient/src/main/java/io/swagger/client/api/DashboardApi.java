package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.DashboardAssets;
import io.swagger.client.model.DashboardChart;
import io.swagger.client.model.DashboardChartAssets;
import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.DashboardPortfolio;
import io.swagger.client.model.DashboardRecommendations;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.DashboardTradingDetails;
import io.swagger.client.model.ItemsViewModelDashboardTradingAsset;
import io.swagger.client.model.ItemsViewModelFundInvestingDetailsList;
import io.swagger.client.model.ItemsViewModelProgramInvestingDetailsList;
import retrofit2.http.GET;
import rx.Observable;

public interface DashboardApi
{
	/**
	 * @param authorization    JWT access token (required)
	 * @param assets           (optional)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param showIn           (optional)
	 * @return Call&lt;DashboardChart&gt;
	 */
	@GET("v2.0/dashboard/chart")
	Observable<DashboardChart> getChart(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Assets") List<UUID> assets, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("ShowIn") String showIn
	);

	/**
	 * Active assets for chart
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;DashboardChartAssets&gt;
	 */
	@GET("v2.0/dashboard/chart/assets")
	Observable<DashboardChartAssets> getChartAssets(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param authorization  JWT access token (required)
	 * @param topAssetsCount (optional)
	 * @return Call&lt;DashboardAssets&gt;
	 */
	@GET("v2.0/dashboard/holdings")
	Observable<DashboardAssets> getHoldings(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("topAssetsCount") Integer topAssetsCount
	);

	/**
	 * @param authorization JWT access token (required)
	 * @param currency      (optional)
	 * @param eventsTake    (optional)
	 * @return Call&lt;DashboardInvestingDetails&gt;
	 */
	@GET("v2.0/dashboard/investing")
	Observable<DashboardInvestingDetails> getInvestingDetails(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency, @retrofit2.http.Query("eventsTake") Integer eventsTake
	);

	/**
	 * @param authorization    JWT access token (required)
	 * @param sorting          (optional)
	 * @param showIn           (optional)
	 * @param status           (optional)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param facetId          (optional)
	 * @param mask             (optional)
	 * @param ownerId          (optional)
	 * @param showFavorites    (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;ItemsViewModelFundInvestingDetailsList&gt;
	 */
	@GET("v2.0/dashboard/investing/funds")
	Observable<ItemsViewModelFundInvestingDetailsList> getInvestingFunds(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("ShowIn") String showIn, @retrofit2.http.Query("Status") String status, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("OwnerId") UUID ownerId, @retrofit2.http.Query("ShowFavorites") Boolean showFavorites, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * @param authorization    JWT access token (required)
	 * @param sorting          (optional)
	 * @param showIn           (optional)
	 * @param status           (optional)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param facetId          (optional)
	 * @param mask             (optional)
	 * @param ownerId          (optional)
	 * @param showFavorites    (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;ItemsViewModelProgramInvestingDetailsList&gt;
	 */
	@GET("v2.0/dashboard/investing/programs")
	Observable<ItemsViewModelProgramInvestingDetailsList> getInvestingPrograms(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("ShowIn") String showIn, @retrofit2.http.Query("Status") String status, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("OwnerId") UUID ownerId, @retrofit2.http.Query("ShowFavorites") Boolean showFavorites, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * @param authorization    JWT access token (required)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param showIn           (optional)
	 * @return Call&lt;ItemsViewModelDashboardTradingAsset&gt;
	 */
	@GET("v2.0/dashboard/trading/mostprofitable")
	Observable<ItemsViewModelDashboardTradingAsset> getMostProfitableAssets(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("ShowIn") String showIn
	);

	/**
	 * Money distribution in percents. Empty list if no money at all
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;DashboardPortfolio&gt;
	 */
	@GET("v2.0/dashboard/portfolio")
	Observable<DashboardPortfolio> getPortfolio(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param authorization    JWT access token (required)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param showIn           (optional)
	 * @param status           (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;ItemsViewModelDashboardTradingAsset&gt;
	 */
	@GET("v2.0/dashboard/trading/private")
	Observable<ItemsViewModelDashboardTradingAsset> getPrivateTradingAssets(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("ShowIn") String showIn, @retrofit2.http.Query("Status") String status, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * @param authorization    JWT access token (required)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param showIn           (optional)
	 * @param status           (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;ItemsViewModelDashboardTradingAsset&gt;
	 */
	@GET("v2.0/dashboard/trading/public")
	Observable<ItemsViewModelDashboardTradingAsset> getPublicTradingAssets(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("ShowIn") String showIn, @retrofit2.http.Query("Status") String status, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Recommended assets to invest (programs, funds and signals). Funds in passed currency
	 *
	 * @param authorization JWT access token (required)
	 * @param currency      (optional)
	 * @return Call&lt;DashboardRecommendations&gt;
	 */
	@GET("v2.0/dashboard/recommendations")
	Observable<DashboardRecommendations> getRecommendations(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency
	);

	/**
	 * @param authorization JWT access token (required)
	 * @param currency      (optional)
	 * @return Call&lt;DashboardSummary&gt;
	 */
	@GET("v2.0/dashboard/summary")
	Observable<DashboardSummary> getSummary(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency
	);

	/**
	 * @param authorization JWT access token (required)
	 * @param currency      (optional)
	 * @param eventsTake    (optional)
	 * @return Call&lt;DashboardTradingDetails&gt;
	 */
	@GET("v2.0/dashboard/trading")
	Observable<DashboardTradingDetails> getTradingDetails(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency, @retrofit2.http.Query("eventsTake") Integer eventsTake
	);

}
