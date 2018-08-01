package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.ProgramChart;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramsList;
import io.swagger.client.model.TradesViewModel;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface ProgramApi
{
	/**
	 * Get manager chart
	 *
	 * @param id        (required)
	 * @param timeFrame (optional)
	 * @return Call&lt;ProgramChart&gt;
	 */
	@GET("v1.0/program/{id}/chart")
	Observable<ProgramChart> v10ProgramByIdChartGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("TimeFrame") String timeFrame
	);

	/**
	 * Add to favorites
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/program/{id}/favorite/add")
	Observable<Void> v10ProgramByIdFavoriteAddPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Remove from favorites
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/program/{id}/favorite/remove")
	Observable<Void> v10ProgramByIdFavoriteRemovePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Program details
	 *
	 * @param id            (required)
	 * @param authorization (optional)
	 * @return Call&lt;ProgramDetailsFull&gt;
	 */
	@GET("v1.0/program/{id}")
	Observable<ProgramDetailsFull> v10ProgramByIdGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get manager trade history
	 *
	 * @param id       (required)
	 * @param dateFrom (optional)
	 * @param dateTo   (optional)
	 * @param symbol   (optional)
	 * @param sorting  (optional)
	 * @param skip     (optional)
	 * @param take     (optional)
	 * @return Call&lt;TradesViewModel&gt;
	 */
	@GET("v1.0/program/{id}/trades")
	Observable<TradesViewModel> v10ProgramByIdTradesGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Programs list
	 *
	 * @param authorization (optional)
	 * @param levelMin      (optional)
	 * @param levelMax      (optional)
	 * @param profitAvgMin  (optional)
	 * @param profitAvgMax  (optional)
	 * @param timeFrame     (optional)
	 * @param mask          (optional)
	 * @param facetId       (optional)
	 * @param isFavorite    (optional)
	 * @param currency      (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;ProgramsList&gt;
	 */
	@GET("v1.0/program/list")
	Observable<ProgramsList> v10ProgramListGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("LevelMin") Integer levelMin, @retrofit2.http.Query("LevelMax") Integer levelMax, @retrofit2.http.Query("ProfitAvgMin") Double profitAvgMin, @retrofit2.http.Query("ProfitAvgMax") Double profitAvgMax, @retrofit2.http.Query("TimeFrame") String timeFrame, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("FacetId") UUID facetId, @retrofit2.http.Query("IsFavorite") Boolean isFavorite, @retrofit2.http.Query("Currency") String currency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
