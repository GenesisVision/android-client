package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.ItemsViewModelProgramDetailsList;
import io.swagger.client.model.ProgramBalanceChart;
import io.swagger.client.model.ProgramDetailsFullOld;
import io.swagger.client.model.ProgramProfitCharts;
import retrofit2.http.GET;
import rx.Observable;

public interface ProgramsApi
{
	/**
	 * Program balance chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @return Call&lt;ProgramBalanceChart&gt;
	 */
	@GET("v2.0/programs/{id}/charts/balance")
	Observable<ProgramBalanceChart> getProgramBalanceChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency
	);

	/**
	 * Program details
	 *
	 * @param id            (required)
	 * @param authorization (optional)
	 * @return Call&lt;ProgramDetailsFullOld&gt;
	 */
	@GET("v2.0/programs/{id}")
	Observable<ProgramDetailsFullOld> getProgramDetails(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Program profit chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @param currencies    (optional)
	 * @return Call&lt;ProgramProfitCharts&gt;
	 */
	@GET("v2.0/programs/{id}/charts/profit")
	Observable<ProgramProfitCharts> getProgramProfitChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency, @retrofit2.http.Query("currencies") List<String> currencies
	);

	/**
	 * Programs list
	 *
	 * @param authorization (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;ItemsViewModelProgramDetailsList&gt;
	 */
	@GET("v2.0/programs")
	Observable<ItemsViewModelProgramDetailsList> getPrograms(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
