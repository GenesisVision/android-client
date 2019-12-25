package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.AccountBalanceChart;
import io.swagger.client.model.ItemsViewModelFollowDetailsListItem;
import io.swagger.client.model.ItemsViewModelSignalSubscription;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramProfitPercentCharts;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface FollowApi
{
	/**
	 * Add to favorites
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/follow/{id}/favorite/add")
	Observable<Void> addToFavorites(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Follow absolute profit chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @return Call&lt;AbsoluteProfitChart&gt;
	 */
	@GET("v2.0/follow/{id}/charts/profit/absolute")
	Observable<AbsoluteProfitChart> getAbsoluteProfitChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency
	);

	/**
	 * Follow balance chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @return Call&lt;AccountBalanceChart&gt;
	 */
	@GET("v2.0/follow/{id}/charts/balance")
	Observable<AccountBalanceChart> getBalanceChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency
	);

	/**
	 * Follow asset details
	 *
	 * @param id            (required)
	 * @param authorization (optional)
	 * @return Call&lt;ProgramFollowDetailsFull&gt;
	 */
	@GET("v2.0/follow/{id}")
	Observable<ProgramFollowDetailsFull> getFollowAssetDetails(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Follow assets
	 *
	 * @param authorization    (optional)
	 * @param sorting          (optional)
	 * @param showIn           (optional)
	 * @param tags             (optional)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param chartPointsCount (optional)
	 * @param facetId          (optional)
	 * @param mask             (optional)
	 * @param ownerId          (optional)
	 * @param showFavorites    (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;ItemsViewModelFollowDetailsListItem&gt;
	 */
	@GET("v2.0/follow")
	Observable<ItemsViewModelFollowDetailsListItem> getFollowAssets(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("ShowIn") String showIn, @retrofit2.http.Query("Tags") List<String> tags, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("OwnerId") UUID ownerId, @retrofit2.http.Query("ShowFavorites") Boolean showFavorites, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get subscriptions to current asset
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param onlyActive    (optional)
	 * @return Call&lt;ItemsViewModelSignalSubscription&gt;
	 */
	@POST("v2.0/follow/{id}/subscriptions")
	Observable<ItemsViewModelSignalSubscription> getFollowSubscriptionsForAsset(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("onlyActive") Boolean onlyActive
	);

	/**
	 * Get subscriptions for my trading account
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param onlyActive    (optional)
	 * @return Call&lt;ItemsViewModelSignalSubscription&gt;
	 */
	@POST("v2.0/follow/account/own/{id}/subscriptions")
	Observable<ItemsViewModelSignalSubscription> getFollowSubscriptionsForOwnAccount(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("onlyActive") Boolean onlyActive
	);

	/**
	 * Follow profit percent charts
	 *
	 * @param id            (required)
	 * @param authorization (optional)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @param currencies    (optional)
	 * @return Call&lt;ProgramProfitPercentCharts&gt;
	 */
	@GET("v2.0/follow/{id}/charts/profit/percent")
	Observable<ProgramProfitPercentCharts> getProfitPercentCharts(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency, @retrofit2.http.Query("currencies") List<Object> currencies
	);

	/**
	 * Remove from favorites
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/follow/{id}/favorite/remove")
	Observable<Void> removeFromFavorites(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

}