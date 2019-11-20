package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;

import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.ItemsViewModelFollowDetailsList;
import retrofit2.http.GET;
import rx.Observable;

public interface FollowApi
{
	/**
	 * Follow asset details
	 *
	 * @param id            (required)
	 * @param authorization (optional)
	 * @return Call&lt;FollowDetailsFull&gt;
	 */
	@GET("v2.0/follow/{id}")
	Observable<FollowDetailsFull> getFollowAssetDetails(
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
	 * @param showFavorites    (optional)
	 * @param skip             (optional)
	 * @param take             (optional)
	 * @return Call&lt;ItemsViewModelFollowDetailsList&gt;
	 */
	@GET("v2.0/follow")
	Observable<ItemsViewModelFollowDetailsList> getFollowAssets(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("ShowIn") String showIn, @retrofit2.http.Query("Tags") List<String> tags, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("ShowFavorites") Boolean showFavorites, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
