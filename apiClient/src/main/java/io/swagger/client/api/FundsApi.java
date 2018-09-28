package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.FundChart;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.FundsList;
import io.swagger.client.model.PlatformAssets;
import io.swagger.client.model.RebalancesViewModel;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface FundsApi
{
	/**
	 * Get all supported assets for funds
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;PlatformAssets&gt;
	 */
	@GET("v1.0/funds/assets")
	Observable<PlatformAssets> v10FundsAssetsGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Add to favorites
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/funds/{id}/favorite/add")
	Observable<Void> v10FundsByIdFavoriteAddPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Remove from favorites
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/funds/{id}/favorite/remove")
	Observable<Void> v10FundsByIdFavoriteRemovePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Funds details
	 *
	 * @param id                (required)
	 * @param authorization     (optional)
	 * @param currencySecondary (optional)
	 * @return Call&lt;FundDetailsFull&gt;
	 */
	@GET("v1.0/funds/{id}")
	Observable<FundDetailsFull> v10FundsByIdGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currencySecondary") String currencySecondary
	);

	/**
	 * Fund profit chart
	 *
	 * @param id            (required)
	 * @param currency      (optional)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @return Call&lt;FundChart&gt;
	 */
	@GET("v1.0/funds/{id}/profitchart")
	Observable<FundChart> v10FundsByIdProfitchartGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("currency") String currency, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount
	);

	/**
	 * Rebalancing history
	 *
	 * @param id       (required)
	 * @param dateFrom (optional)
	 * @param dateTo   (optional)
	 * @param skip     (optional)
	 * @param take     (optional)
	 * @return Call&lt;RebalancesViewModel&gt;
	 */
	@GET("v1.0/funds/{id}/rebalancing")
	Observable<RebalancesViewModel> v10FundsByIdRebalancingGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Funds list
	 *
	 * @param authorization     (optional)
	 * @param sorting           (optional)
	 * @param currencySecondary (optional)
	 * @param statisticDateFrom (optional)
	 * @param statisticDateTo   (optional)
	 * @param chartPointsCount  (optional)
	 * @param mask              (optional)
	 * @param facetId           (optional)
	 * @param isFavorite        (optional)
	 * @param ids               (optional)
	 * @param skip              (optional)
	 * @param take              (optional)
	 * @return Call&lt;FundsList&gt;
	 */
	@GET("v1.0/funds")
	Observable<FundsList> v10FundsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("CurrencySecondary") String currencySecondary, @retrofit2.http.Query("StatisticDateFrom") DateTime statisticDateFrom, @retrofit2.http.Query("StatisticDateTo") DateTime statisticDateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("FacetId") UUID facetId, @retrofit2.http.Query("IsFavorite") Boolean isFavorite, @retrofit2.http.Query("Ids") List<UUID> ids, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
