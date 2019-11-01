package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.FundBalanceChart;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.FundProfitCharts;
import io.swagger.client.model.ItemsViewModelFundDetailsList;
import io.swagger.client.model.ItemsViewModelReallocationModel;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface FundsApi
{
	/**
	 * Add to favorites
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/funds/{id}/favorite/add")
	Observable<Void> addToFavorites(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Fund balance chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @return Call&lt;FundBalanceChart&gt;
	 */
	@GET("v2.0/funds/{id}/charts/balance")
	Observable<FundBalanceChart> getFundBalanceChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency
	);

	/**
	 * Fund details
	 *
	 * @param id            (required)
	 * @param authorization (optional)
	 * @param currency      (optional)
	 * @return Call&lt;FundDetailsFull&gt;
	 */
	@GET("v2.0/funds/{id}")
	Observable<FundDetailsFull> getFundDetails(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency
	);

	/**
	 * Fund profit chart
	 *
	 * @param id               (required)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param maxPointCount    (optional)
	 * @param currency         (optional)
	 * @param currencies       (optional)
	 * @param chartAssetsCount (optional)
	 * @return Call&lt;FundProfitCharts&gt;
	 */
	@GET("v2.0/funds/{id}/charts/profit")
	Observable<FundProfitCharts> getFundProfitChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency, @retrofit2.http.Query("currencies") List<Object> currencies, @retrofit2.http.Query("chartAssetsCount") Integer chartAssetsCount
	);

	/**
	 * Funds list
	 *
	 * @param authorization     (optional)
	 * @param showIn            (optional)
	 * @param assets            (optional)
	 * @param statisticDateFrom (optional)
	 * @param statisticDateTo   (optional)
	 * @param chartPointsCount  (optional)
	 * @param facetId           (optional)
	 * @param mask              (optional)
	 * @param showFavorites     (optional)
	 * @param skip              (optional)
	 * @param take              (optional)
	 * @return Call&lt;ItemsViewModelFundDetailsList&gt;
	 */
	@GET("v2.0/funds")
	Observable<ItemsViewModelFundDetailsList> getFunds(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("ShowIn") String showIn, @retrofit2.http.Query("Assets") List<String> assets, @retrofit2.http.Query("StatisticDateFrom") DateTime statisticDateFrom, @retrofit2.http.Query("StatisticDateTo") DateTime statisticDateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("ShowFavorites") Boolean showFavorites, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get history of asset part update requests
	 *
	 * @param id       (required)
	 * @param dateFrom (optional)
	 * @param dateTo   (optional)
	 * @param skip     (optional)
	 * @param take     (optional)
	 * @return Call&lt;ItemsViewModelReallocationModel&gt;
	 */
	@GET("v2.0/funds/{id}/reallocations")
	Observable<ItemsViewModelReallocationModel> getReallocatingHistory(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Remove from favorites
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/funds/{id}/favorite/remove")
	Observable<Void> removeFromFavorites(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

}
