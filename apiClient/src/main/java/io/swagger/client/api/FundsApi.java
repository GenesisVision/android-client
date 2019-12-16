package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.FundBalanceChart;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.FundProfitPercentCharts;
import io.swagger.client.model.ItemsViewModelFundDetailsListItem;
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
	 * Fund absolute profit chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @return Call&lt;AbsoluteProfitChart&gt;
	 */
	@GET("v2.0/funds/{id}/charts/profit/absolute")
	Observable<AbsoluteProfitChart> getFundAbsoluteProfitChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency
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
	 * Fund profit percent charts
	 *
	 * @param id               (required)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param maxPointCount    (optional)
	 * @param currency         (optional)
	 * @param currencies       (optional)
	 * @param chartAssetsCount (optional)
	 * @return Call&lt;FundProfitPercentCharts&gt;
	 */
	@GET("v2.0/funds/{id}/charts/profit/percent")
	Observable<FundProfitPercentCharts> getFundProfitPercentCharts(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency, @retrofit2.http.Query("currencies") List<Object> currencies, @retrofit2.http.Query("chartAssetsCount") Integer chartAssetsCount
	);

	/**
	 * Funds list
	 *
	 * @param authorization    (optional)
	 * @param sorting          (optional)
	 * @param showIn           (optional)
	 * @param assets           (optional)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param facetId          (optional)
	 * @param mask             (optional)
	 * @param ownerId          (optional)
	 * @param showFavorites    (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;ItemsViewModelFundDetailsListItem&gt;
	 */
	@GET("v2.0/funds")
	Observable<ItemsViewModelFundDetailsListItem> getFunds(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("ShowIn") String showIn, @retrofit2.http.Query("Assets") List<String> assets, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("OwnerId") UUID ownerId, @retrofit2.http.Query("ShowFavorites") Boolean showFavorites, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
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
