package io.swagger.client.api;

import java.util.List;

import io.swagger.client.model.RatesModel;
import retrofit2.http.GET;
import rx.Observable;

public interface RateApi
{
	/**
	 * Get rate
	 *
	 * @param from (required)
	 * @param to   (required)
	 * @return Call&lt;Double&gt;
	 */
	@GET("v1.0/rate/{from}/{to}")
	Observable<Double> v10RateByFromByToGet(
			@retrofit2.http.Path("from") String from, @retrofit2.http.Path("to") String to
	);

	/**
	 * Get rates
	 *
	 * @param from (optional)
	 * @param to   (optional)
	 * @return Call&lt;RatesModel&gt;
	 */
	@GET("v1.0/rate")
	Observable<RatesModel> v10RateGet(
			@retrofit2.http.Query("from") List<String> from, @retrofit2.http.Query("to") List<String> to
	);

}
