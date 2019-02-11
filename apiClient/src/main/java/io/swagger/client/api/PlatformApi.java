package io.swagger.client.api;

import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.PlatformStatistic;
import io.swagger.client.model.ProgramsLevelsInfo;
import retrofit2.http.GET;
import rx.Observable;

public interface PlatformApi
{
	/**
	 * Platform info
	 *
	 * @return Call&lt;PlatformInfo&gt;
	 */
	@GET("v1.0/platform/info")
	Observable<PlatformInfo> v10PlatformInfoGet();


	/**
	 * Investment programs levels
	 *
	 * @param currency (optional)
	 * @return Call&lt;ProgramsLevelsInfo&gt;
	 */
	@GET("v1.0/platform/levels")
	Observable<ProgramsLevelsInfo> v10PlatformLevelsGet(
			@retrofit2.http.Query("currency") String currency
	);

	/**
	 * Platform statistic
	 *
	 * @return Call&lt;PlatformStatistic&gt;
	 */
	@GET("v1.0/platform/statistic")
	Observable<PlatformStatistic> v10PlatformStatisticGet();


}
