package io.swagger.client.api;

import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.PlatformStatistic;
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
	 * Platform statistic
	 *
	 * @return Call&lt;PlatformStatistic&gt;
	 */
	@GET("v1.0/platform/statistic")
	Observable<PlatformStatistic> v10PlatformStatisticGet();


}
