package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.FundBalanceChart;
import io.swagger.client.model.FundProfitCharts;
import retrofit2.http.GET;
import rx.Observable;

public interface FundsApi
{
	/**
	 * Fund balance chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @return Call&lt;FundBalanceChart&gt;
	 */
	@GET("v2.0/funds/{id}/charts/balance")
	Observable<FundBalanceChart> getFundBalanceChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency
	);

	/**
	 * Fund profit chart
	 *
	 * @param id               (required)
	 * @param dateFrom         (optional)
	 * @param dateTo           (optional)
	 * @param maxPointCount    (optional)
	 * @param currency         (optional)
	 * @param currencies       (optional)
	 * @param chartAssetsCount (optional)
	 * @return Call&lt;FundProfitCharts&gt;
	 */
	@GET("v2.0/funds/{id}/charts/profit")
	Observable<FundProfitCharts> getFundProfitChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency, @retrofit2.http.Query("currencies") List<String> currencies, @retrofit2.http.Query("chartAssetsCount") Integer chartAssetsCount
	);

}
