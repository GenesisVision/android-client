package io.swagger.client.api;

import retrofit2.http.GET;
import rx.Observable;

public interface DashboardApi
{
	/**
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("v2.0/dashboard")
	Observable<Void> getDashboard(
			@retrofit2.http.Header("Authorization") String authorization
	);

}
