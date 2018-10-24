package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.ManagerDashboard;
import io.swagger.client.model.ManagerFunds;
import io.swagger.client.model.ManagerPortfolioEvents;
import io.swagger.client.model.ManagerPrograms;
import retrofit2.http.GET;
import rx.Observable;

public interface ManagerApi
{
	/**
	 * Manager events
	 *
	 * @param authorization JWT access token (required)
	 * @param assetId       (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param type          (optional)
	 * @param assetType     (optional)
	 * @return Call&lt;ManagerPortfolioEvents&gt;
	 */
	@GET("v1.0/manager/events")
	Observable<ManagerPortfolioEvents> v10ManagerEventsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("Type") String type, @retrofit2.http.Query("AssetType") String assetType
	);

	/**
	 * Manager funds
	 *
	 * @param authorization JWT access token (required)
	 * @param sorting       (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param pointsCount   (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;ManagerFunds&gt;
	 */
	@GET("v1.0/manager/funds")
	Observable<ManagerFunds> v10ManagerFundsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("PointsCount") Integer pointsCount, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Manager dashboard
	 *
	 * @param authorization JWT access token (required)
	 * @param eventsTake    (optional)
	 * @param requestsSkip  (optional)
	 * @param requestsTake  (optional)
	 * @return Call&lt;ManagerDashboard&gt;
	 */
	@GET("v1.0/manager")
	Observable<ManagerDashboard> v10ManagerGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("eventsTake") Integer eventsTake, @retrofit2.http.Query("requestsSkip") Integer requestsSkip, @retrofit2.http.Query("requestsTake") Integer requestsTake
	);

	/**
	 * Manager programs
	 *
	 * @param authorization JWT access token (required)
	 * @param sorting       (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param pointsCount   (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;ManagerPrograms&gt;
	 */
	@GET("v1.0/manager/programs")
	Observable<ManagerPrograms> v10ManagerProgramsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("PointsCount") Integer pointsCount, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
