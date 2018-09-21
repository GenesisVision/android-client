package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.ProgramsList;
import retrofit2.http.GET;
import rx.Observable;

public interface FundsApi
{
	/**
	 * Funds list
	 *
	 * @param authorization     (optional)
	 * @param sorting           (optional)
	 * @param statisticDateFrom (optional)
	 * @param statisticDateTo   (optional)
	 * @param chartPointsCount  (optional)
	 * @param mask              (optional)
	 * @param facetId           (optional)
	 * @param isFavorite        (optional)
	 * @param ids               (optional)
	 * @param skip              (optional)
	 * @param take              (optional)
	 * @return Call&lt;ProgramsList&gt;
	 */
	@GET("v1.0/funds")
	Observable<ProgramsList> v10FundsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("StatisticDateFrom") DateTime statisticDateFrom, @retrofit2.http.Query("StatisticDateTo") DateTime statisticDateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("FacetId") UUID facetId, @retrofit2.http.Query("IsFavorite") Boolean isFavorite, @retrofit2.http.Query("Ids") List<UUID> ids, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
