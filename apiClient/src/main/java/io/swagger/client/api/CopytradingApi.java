package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;

import io.swagger.client.model.ItemsViewModelCopyTradingAccountInfo;
import retrofit2.http.GET;
import rx.Observable;

public interface CopytradingApi
{
	/**
	 * Get GV Follow assets
	 *
	 * @param authorization     (optional)
	 * @param tags              (optional)
	 * @param statisticDateFrom (optional)
	 * @param statisticDateTo   (optional)
	 * @param chartPointsCount  (optional)
	 * @param facetId           (optional)
	 * @param mask              (optional)
	 * @param showFavorites     (optional)
	 * @param skip              (optional)
	 * @param take              (optional)
	 * @return Call&lt;ItemsViewModelCopyTradingAccountInfo&gt;
	 */
	@GET("v2.0/copytrading")
	Observable<ItemsViewModelCopyTradingAccountInfo> getSignalAssets(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Tags") List<String> tags, @retrofit2.http.Query("StatisticDateFrom") DateTime statisticDateFrom, @retrofit2.http.Query("StatisticDateTo") DateTime statisticDateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("ShowFavorites") Boolean showFavorites, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
