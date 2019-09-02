package io.swagger.client.api;

import io.swagger.client.model.CaptchaDetails;
import io.swagger.client.model.LevelsParamsInfo;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.PlatformStatistic;
import io.swagger.client.model.ProgramsLevelsInfo;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface PlatformApi
{
	/**
	 * Server date
	 *
	 * @return Call&lt;String&gt;
	 */
	@POST("v1.0/platform/date")
	Observable<String> v10PlatformDatePost();


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
	 * @param currency (optional, default to 100)
	 * @return Call&lt;ProgramsLevelsInfo&gt;
	 */
	@GET("v1.0/platform/levels")
	Observable<ProgramsLevelsInfo> v10PlatformLevelsGet(
			@retrofit2.http.Query("currency") String currency
	);

	/**
	 * Investment programs levels parameters
	 *
	 * @param currency (optional, default to 104)
	 * @return Call&lt;LevelsParamsInfo&gt;
	 */
	@GET("v1.0/platform/levels/parameters")
	Observable<LevelsParamsInfo> v10PlatformLevelsParametersGet(
			@retrofit2.http.Query("currency") String currency
	);

	/**
	 * Platform captcha details.
	 *
	 * @param route   (required)
	 * @param client  (optional)
	 * @param version (optional)
	 * @return Call&lt;CaptchaDetails&gt;
	 */
	@GET("v1.0/platform/riskcontrol")
	Observable<CaptchaDetails> v10PlatformRiskcontrolGet(
			@retrofit2.http.Query("Route") String route, @retrofit2.http.Query("Client") String client, @retrofit2.http.Query("Version") String version
	);

	/**
	 * Platform statistic
	 *
	 * @return Call&lt;PlatformStatistic&gt;
	 */
	@GET("v1.0/platform/statistic")
	Observable<PlatformStatistic> v10PlatformStatisticGet();
    

}
