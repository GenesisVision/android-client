package io.swagger.client.api;

import io.swagger.client.model.CaptchaDetails;
import io.swagger.client.model.LevelsParamsInfo;
import io.swagger.client.model.PlatformInfo;
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
	@POST("v2.0/platform/date")
	Observable<String> getPlatformDate();


	/**
	 * Platform info
	 *
	 * @return Call&lt;PlatformInfo&gt;
	 */
	@GET("v2.0/platform/info")
	Observable<PlatformInfo> getPlatformInfo();


	/**
	 * Investment programs levels
	 *
	 * @param currency (optional)
	 * @return Call&lt;ProgramsLevelsInfo&gt;
	 */
	@GET("v2.0/platform/levels")
	Observable<ProgramsLevelsInfo> getProgramLevels(
			@retrofit2.http.Query("currency") String currency
	);

	/**
	 * Investment programs levels parameters
	 *
	 * @param currency (optional)
	 * @return Call&lt;LevelsParamsInfo&gt;
	 */
	@GET("v2.0/platform/levels/parameters")
	Observable<LevelsParamsInfo> getProgramLevelsParams(
			@retrofit2.http.Query("currency") String currency
	);

	/**
	 * Risk control
	 *
	 * @param route   (required)
	 * @param client  (optional)
	 * @param version (optional)
	 * @return Call&lt;CaptchaDetails&gt;
	 */
	@GET("v2.0/platform/riskcontrol")
	Observable<CaptchaDetails> getRiskControlInfo(
			@retrofit2.http.Query("Route") String route, @retrofit2.http.Query("Client") String client, @retrofit2.http.Query("Version") String version
	);

}
