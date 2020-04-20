package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.AccountBalanceChart;
import io.swagger.client.model.Currency;
import io.swagger.client.model.FollowDetailsListItemItemsViewModel;
import io.swagger.client.model.FollowFilterSorting;
import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramProfitPercentCharts;
import io.swagger.client.model.SignalSubscriptionItemsViewModel;
import io.swagger.client.model.TradeSorting;
import io.swagger.client.model.TradesSignalViewModel;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface FollowApi
{
	/**
	 * Add to favorites
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/follow/{id}/favorite/add")
	Observable<Void> addToFavorites(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Manually close trade by symbol for asset
	 *
	 * @param id     (required)
	 * @param symbol (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/follow/{id}/trades/close")
	Observable<Void> closeAssetTrade(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("Symbol") String symbol
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
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") Currency currency
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
	 * @param isFollow        (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;TradesSignalViewModel&gt;
	 */
	@GET("v2.0/follow/{id}/trades")
	Observable<TradesSignalViewModel> getAssetTrades(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("Sorting") TradeSorting sorting, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") Currency accountCurrency, @retrofit2.http.Query("IsFollow") Boolean isFollow, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
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
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") Currency currency
	);

	/**
	 * Follow asset details
	 *
	 * @param id          (required)
	 * @param logoQuality (optional)
	 * @return Call&lt;ProgramFollowDetailsFull&gt;
	 */
	@GET("v2.0/follow/{id}")
	Observable<ProgramFollowDetailsFull> getFollowAssetDetails(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Query("logoQuality") ImageQuality logoQuality
	);

	/**
	 * Follow assets
	 *
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
	 * @return Call&lt;FollowDetailsListItemItemsViewModel&gt;
	 */
	@GET("v2.0/follow")
	Observable<FollowDetailsListItemItemsViewModel> getFollowAssets(
			@retrofit2.http.Query("Sorting") FollowFilterSorting sorting, @retrofit2.http.Query("ShowIn") Currency showIn, @retrofit2.http.Query("Tags") List<String> tags, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("OwnerId") UUID ownerId, @retrofit2.http.Query("ShowFavorites") Boolean showFavorites, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get subscriptions to current asset
	 *
	 * @param id         (required)
	 * @param onlyActive (optional)
	 * @return Call&lt;SignalSubscriptionItemsViewModel&gt;
	 */
	@POST("v2.0/follow/{id}/subscriptions")
	Observable<SignalSubscriptionItemsViewModel> getFollowSubscriptionsForAsset(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("onlyActive") Boolean onlyActive
	);

	/**
	 * Get subscriptions for my trading account
	 *
	 * @param id         (required)
	 * @param onlyActive (optional)
	 * @return Call&lt;SignalSubscriptionItemsViewModel&gt;
	 */
	@POST("v2.0/follow/account/own/{id}/subscriptions")
	Observable<SignalSubscriptionItemsViewModel> getFollowSubscriptionsForOwnAccount(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("onlyActive") Boolean onlyActive
	);

	/**
	 * Follow profit percent charts
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @param currencies    (optional)
	 * @return Call&lt;ProgramProfitPercentCharts&gt;
	 */
	@GET("v2.0/follow/{id}/charts/profit/percent")
	Observable<ProgramProfitPercentCharts> getProfitPercentCharts(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") Currency currency, @retrofit2.http.Query("currencies") List<Currency> currencies
	);

	/**
	 * Remove from favorites
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/follow/{id}/favorite/remove")
	Observable<Void> removeFromFavorites(
			@retrofit2.http.Path("id") UUID id
	);

}
