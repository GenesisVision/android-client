package io.swagger.client.api;//retrofit2

import org.joda.time.DateTime;

import java.util.List;

import io.swagger.client.model.CoinsAssetItemsViewModel;
import io.swagger.client.model.CoinsFilterSorting;
import io.swagger.client.model.CoinsHistoryEventItemsViewModel;
import io.swagger.client.model.InternalTransferRequest;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface CoinsApi
{
	/**
	 * Coins list
	 *
	 * @param sorting (optional)
	 * @param assets  (optional)
	 * @param skip    (optional)
	 * @param take    (optional)
	 * @return Call&lt;CoinsAssetItemsViewModel&gt;
	 */
	@GET("v2.0/coins")
	Observable<CoinsAssetItemsViewModel> getCoins(
			@retrofit2.http.Query("Sorting") CoinsFilterSorting sorting, @retrofit2.http.Query("Assets") List<String> assets, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get coins history
	 *
	 * @param dateFrom (optional)
	 * @param dateTo   (optional)
	 * @param assets   (optional)
	 * @param skip     (optional)
	 * @param take     (optional)
	 * @return Call&lt;CoinsHistoryEventItemsViewModel&gt;
	 */
	@GET("v2.0/coins/history")
	Observable<CoinsHistoryEventItemsViewModel> getCoinsConvertingHistory(
			@retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Assets") List<String> assets, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get user coins
	 *
	 * @param sorting (optional)
	 * @param assets  (optional)
	 * @param skip    (optional)
	 * @param take    (optional)
	 * @return Call&lt;CoinsAssetItemsViewModel&gt;
	 */
	@GET("v2.0/coins/portfolio")
	Observable<CoinsAssetItemsViewModel> getUserCoins(
			@retrofit2.http.Query("Sorting") CoinsFilterSorting sorting, @retrofit2.http.Query("Assets") List<String> assets, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Transfer money
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/coins/transfer")
	Observable<Void> transfer(
			@retrofit2.http.Body InternalTransferRequest body
	);

}
