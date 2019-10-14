package io.swagger.client.api;

import java.util.List;

import io.swagger.client.model.RateModel;
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
	 * @return Call&lt;RateModel&gt;
	 */
	@GET("v2.0/rate/{from}/{to}")
	Observable<RateModel> getRate(
			@retrofit2.http.Path("from") String from, @retrofit2.http.Path("to") String to
	);

	/**
	 * Get rate by exchange
	 *
	 * @param exchange (required)
	 * @param from     (required)
	 * @param to       (required)
	 * @return Call&lt;RateModel&gt;
	 */
	@GET("v2.0/rate/{exchange}/{from}/{to}")
	Observable<RateModel> getRateExchange(
			@retrofit2.http.Path("exchange") String exchange, @retrofit2.http.Path("from") String from, @retrofit2.http.Path("to") String to
	);

	/**
	 * Get rates
	 *
	 * @param from (optional)
	 * @param to   (optional)
	 * @return Call&lt;RatesModel&gt;
	 */
	@GET("v2.0/rate")
	Observable<RatesModel> getRates(
			@retrofit2.http.Query("from") List<String> from, @retrofit2.http.Query("to") List<String> to
	);

}
