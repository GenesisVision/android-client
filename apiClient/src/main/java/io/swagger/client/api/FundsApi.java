package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.FundAssetsListInfo;
import io.swagger.client.model.FundBalanceChart;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.FundProfitChart;
import io.swagger.client.model.FundsList;
import io.swagger.client.model.PlatformAssets;
import io.swagger.client.model.ProgramSets;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface FundsApi
{
	/**
	 * Get all supported assets for funds
	 *
	 * @return Call&lt;PlatformAssets&gt;
	 */
	@GET("v1.0/funds/assets")
	Observable<PlatformAssets> v10FundsAssetsGet();


	/**
	 * Fund assets info
	 *
	 * @param id (required)
	 * @return Call&lt;FundAssetsListInfo&gt;
	 */
	@GET("v1.0/funds/{id}/assets")
	Observable<FundAssetsListInfo> v10FundsByIdAssetsGet(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Fund balance chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @return Call&lt;FundBalanceChart&gt;
	 */
	@GET("v1.0/funds/{id}/charts/balance")
	Observable<FundBalanceChart> v10FundsByIdChartsBalanceGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount
	);

	/**
	 * Fund profit chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @return Call&lt;FundProfitChart&gt;
	 */
	@GET("v1.0/funds/{id}/charts/profit")
	Observable<FundProfitChart> v10FundsByIdChartsProfitGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount
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
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currencySecondary") String currencySecondary
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
	 * @param isEnabled         (optional)
	 * @param ids               (optional)
	 * @param managerId         (optional)
	 * @param programManagerId  (optional)
	 * @param skip              (optional)
	 * @param take              (optional)
	 * @return Call&lt;FundsList&gt;
	 */
	@GET("v1.0/funds")
	Observable<FundsList> v10FundsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("CurrencySecondary") String currencySecondary, @retrofit2.http.Query("StatisticDateFrom") DateTime statisticDateFrom, @retrofit2.http.Query("StatisticDateTo") DateTime statisticDateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("IsFavorite") Boolean isFavorite, @retrofit2.http.Query("IsEnabled") Boolean isEnabled, @retrofit2.http.Query("Ids") List<UUID> ids, @retrofit2.http.Query("ManagerId") String managerId, @retrofit2.http.Query("ProgramManagerId") UUID programManagerId, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Fund sets
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramSets&gt;
	 */
	@GET("v1.0/funds/sets")
	Observable<ProgramSets> v10FundsSetsGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

}
