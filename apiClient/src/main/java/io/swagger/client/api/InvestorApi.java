package io.swagger.client.api;

import org.joda.time.DateTime;

import io.swagger.client.model.DashboardChartValue;
import io.swagger.client.model.DashboardPortfolioEvents;
import io.swagger.client.model.DashboardProgramsList;
import retrofit2.http.GET;
import rx.Observable;

public interface InvestorApi
{
	/**
	 * Value chart
	 *
	 * @param authorization JWT access token (required)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param currency      (optional)
	 * @return Call&lt;DashboardChartValue&gt;
	 */
	@GET("v1.0/investor/dashboard/chart/value")
	Observable<DashboardChartValue> v10InvestorDashboardChartValueGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("from") DateTime from, @retrofit2.http.Query("to") DateTime to, @retrofit2.http.Query("currency") String currency
	);

	/**
	 * Portfolio events
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;DashboardPortfolioEvents&gt;
	 */
	@GET("v1.0/investor/dashboard/events")
	Observable<DashboardPortfolioEvents> v10InvestorDashboardEventsGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Programs list
	 *
	 * @param authorization     JWT access token (required)
	 * @param sorting           (optional)
	 * @param statisticDateFrom (optional)
	 * @param statisticDateTo   (optional)
	 * @param skip              (optional)
	 * @param take              (optional)
	 * @return Call&lt;DashboardProgramsList&gt;
	 */
	@GET("v1.0/investor/dashboard/program/list")
	Observable<DashboardProgramsList> v10InvestorDashboardProgramListGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("StatisticDateFrom") DateTime statisticDateFrom, @retrofit2.http.Query("StatisticDateTo") DateTime statisticDateTo, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
