package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.CommonPublicAssetsViewModel;
import io.swagger.client.model.Currency;
import io.swagger.client.model.DashboardAssetStatus;
import io.swagger.client.model.DashboardAssets;
import io.swagger.client.model.DashboardChart;
import io.swagger.client.model.DashboardChartAssets;
import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.DashboardPortfolio;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.DashboardTradingAssetItemsViewModel;
import io.swagger.client.model.DashboardTradingDetails;
import io.swagger.client.model.ExchangeAssetItemsViewModel;
import io.swagger.client.model.FundInvestingDetailsListItemsViewModel;
import io.swagger.client.model.FundsFilterSorting;
import io.swagger.client.model.ProgramInvestingDetailsListItemsViewModel;
import io.swagger.client.model.ProgramsFilterSorting;
import retrofit2.http.GET;
import rx.Observable;

public interface DashboardApi
{
	/**
	 * @param assets           (optional)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param showIn           (optional)
	 * @param skipStatistic    (optional)
	 * @return Call&lt;DashboardChart&gt;
	 */
	@GET("v2.0/dashboard/chart")
	Observable<DashboardChart> getChart(
			@retrofit2.http.Query("Assets") List<UUID> assets, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("ShowIn") Currency showIn, @retrofit2.http.Query("SkipStatistic") Boolean skipStatistic
	);

	/**
	 * Active assets for chart
	 *
	 * @return Call&lt;DashboardChartAssets&gt;
	 */
	@GET("v2.0/dashboard/chart/assets")
	Observable<DashboardChartAssets> getChartAssets();


	/**
	 * @param currency (optional)
	 * @return Call&lt;DashboardSummary&gt;
	 */
	@GET("v2.0/dashboard/summary")
	Observable<DashboardSummary> getDashboardSummary(
			@retrofit2.http.Query("currency") Currency currency
	);

	/**
	 * @param brokerId (optional)
	 * @return Call&lt;ExchangeAssetItemsViewModel&gt;
	 */
	@GET("v2.0/dashboard/trading/exchange/credentials/all")
	Observable<ExchangeAssetItemsViewModel> getExchangeAccountsCredentials(
			@retrofit2.http.Query("brokerId") UUID brokerId
	);

	/**
	 * @param topAssetsCount (optional)
	 * @return Call&lt;DashboardAssets&gt;
	 */
	@GET("v2.0/dashboard/holdings")
	Observable<DashboardAssets> getHoldings(
			@retrofit2.http.Query("topAssetsCount") Integer topAssetsCount
	);

	/**
	 * @param currency   (optional)
	 * @param eventsTake (optional)
	 * @return Call&lt;DashboardInvestingDetails&gt;
	 */
	@GET("v2.0/dashboard/investing")
	Observable<DashboardInvestingDetails> getInvestingDetails(
			@retrofit2.http.Query("currency") Currency currency, @retrofit2.http.Query("eventsTake") Integer eventsTake
	);

	/**
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
	 * @param skipStatistic    (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;FundInvestingDetailsListItemsViewModel&gt;
	 */
	@GET("v2.0/dashboard/investing/funds")
	Observable<FundInvestingDetailsListItemsViewModel> getInvestingFunds(
			@retrofit2.http.Query("Sorting") FundsFilterSorting sorting, @retrofit2.http.Query("ShowIn") Currency showIn, @retrofit2.http.Query("Status") DashboardAssetStatus status, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("OwnerId") UUID ownerId, @retrofit2.http.Query("ShowFavorites") Boolean showFavorites, @retrofit2.http.Query("SkipStatistic") Boolean skipStatistic, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
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
	 * @param skipStatistic    (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;ProgramInvestingDetailsListItemsViewModel&gt;
	 */
	@GET("v2.0/dashboard/investing/programs")
	Observable<ProgramInvestingDetailsListItemsViewModel> getInvestingPrograms(
			@retrofit2.http.Query("Sorting") ProgramsFilterSorting sorting, @retrofit2.http.Query("ShowIn") Currency showIn, @retrofit2.http.Query("Status") DashboardAssetStatus status, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("OwnerId") UUID ownerId, @retrofit2.http.Query("ShowFavorites") Boolean showFavorites, @retrofit2.http.Query("SkipStatistic") Boolean skipStatistic, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param showIn           (optional)
	 * @param skipStatistic    (optional)
	 * @return Call&lt;DashboardTradingAssetItemsViewModel&gt;
	 */
	@GET("v2.0/dashboard/trading/mostprofitable")
	Observable<DashboardTradingAssetItemsViewModel> getMostProfitableAssets(
			@retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("ShowIn") Currency showIn, @retrofit2.http.Query("SkipStatistic") Boolean skipStatistic
	);

	/**
	 * Money distribution in percents
	 *
	 * @return Call&lt;DashboardPortfolio&gt;
	 */
	@GET("v2.0/dashboard/portfolio")
	Observable<DashboardPortfolio> getPortfolio();


	/**
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param showIn           (optional)
	 * @param status           (optional)
	 * @param skipStatistic    (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;DashboardTradingAssetItemsViewModel&gt;
	 */
	@GET("v2.0/dashboard/trading/private")
	Observable<DashboardTradingAssetItemsViewModel> getPrivateTradingAssets(
			@retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("ShowIn") Currency showIn, @retrofit2.http.Query("Status") DashboardAssetStatus status, @retrofit2.http.Query("SkipStatistic") Boolean skipStatistic, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param showIn           (optional)
	 * @param status           (optional)
	 * @param skipStatistic    (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;DashboardTradingAssetItemsViewModel&gt;
	 */
	@GET("v2.0/dashboard/trading/public")
	Observable<DashboardTradingAssetItemsViewModel> getPublicTradingAssets(
			@retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("ShowIn") Currency showIn, @retrofit2.http.Query("Status") DashboardAssetStatus status, @retrofit2.http.Query("SkipStatistic") Boolean skipStatistic, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Recommended assets to invest (programs, funds and follows). Funds in passed currency
	 *
	 * @param currency    (optional)
	 * @param take        (optional)
	 * @param onlyFollows (optional)
	 * @return Call&lt;CommonPublicAssetsViewModel&gt;
	 */
	@GET("v2.0/dashboard/recommendations")
	Observable<CommonPublicAssetsViewModel> getRecommendations(
			@retrofit2.http.Query("currency") Currency currency, @retrofit2.http.Query("take") Integer take, @retrofit2.http.Query("onlyFollows") Boolean onlyFollows
	);

	/**
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param showIn           (optional)
	 * @param status           (optional)
	 * @param skipStatistic    (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;DashboardTradingAssetItemsViewModel&gt;
	 */
	@GET("v2.0/dashboard/trading/private/selfmanaged")
	Observable<DashboardTradingAssetItemsViewModel> getSelfManagedFunds(
			@retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("ShowIn") Currency showIn, @retrofit2.http.Query("Status") DashboardAssetStatus status, @retrofit2.http.Query("SkipStatistic") Boolean skipStatistic, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * @param currency   (optional)
	 * @param eventsTake (optional)
	 * @return Call&lt;DashboardTradingDetails&gt;
	 */
	@GET("v2.0/dashboard/trading")
	Observable<DashboardTradingDetails> getTradingDetails(
			@retrofit2.http.Query("currency") Currency currency, @retrofit2.http.Query("eventsTake") Integer eventsTake
	);

}
