package io.swagger.client.api;

import io.swagger.client.model.RateViewModel;
import retrofit2.http.GET;
import rx.Observable;

public interface RateApi
{
	/**
	 * Get rate
	 *
	 * @param from (required)
	 * @param to   (required)
	 * @return Call&lt;RateViewModel&gt;
	 */
	@GET("v1.0/rate/{from}/{to}")
	Observable<RateViewModel> v10RateByFromByToGet(
			@retrofit2.http.Path("from") String from, @retrofit2.http.Path("to") String to
	);

}
