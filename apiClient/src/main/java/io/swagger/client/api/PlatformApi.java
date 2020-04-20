package io.swagger.client.api;

import io.swagger.client.model.AssetInfo;
import io.swagger.client.model.CaptchaDetails;
import io.swagger.client.model.Currency;
import io.swagger.client.model.LandingInfo;
import io.swagger.client.model.LevelsParamsInfo;
import io.swagger.client.model.PlatformAssets;
import io.swagger.client.model.PlatformEvents;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.ProgramsLevelsInfo;
import io.swagger.client.model.SiteMapInfo;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface PlatformApi
{
	/**
	 * Get all supported assets for funds
	 *
	 * @return Call&lt;PlatformAssets&gt;
	 */
	@GET("v2.0/platform/assets")
	Observable<PlatformAssets> getAllPlatformAssets();


	/**
	 * Get asset description
	 *
	 * @param asset (required)
	 * @return Call&lt;AssetInfo&gt;
	 */
	@GET("v2.0/platform/asset/{asset}")
	Observable<AssetInfo> getPlatformAssetInfo(
			@retrofit2.http.Path("asset") String asset
	);

	/**
	 * Server date
	 *
	 * @return Call&lt;String&gt;
	 */
	@POST("v2.0/platform/date")
	Observable<String> getPlatformDate();


	/**
	 * Get platform events
	 *
	 * @param take (optional)
	 * @return Call&lt;PlatformEvents&gt;
	 */
	@GET("v2.0/platform/events")
	Observable<PlatformEvents> getPlatformEvents(
			@retrofit2.http.Query("take") Integer take
	);

	/**
	 * Platform info
	 *
	 * @return Call&lt;PlatformInfo&gt;
	 */
	@GET("v2.0/platform/info")
	Observable<PlatformInfo> getPlatformInfo();


	/**
	 * Platform landing info
	 *
	 * @param eventsTake   (optional)
	 * @param followTake   (optional)
	 * @param programsTake (optional)
	 * @param fundsTake    (optional)
	 * @param newsTake     (optional)
	 * @return Call&lt;LandingInfo&gt;
	 */
	@GET("v2.0/platform/landing")
	Observable<LandingInfo> getPlatformLandingInfo(
			@retrofit2.http.Query("eventsTake") Integer eventsTake, @retrofit2.http.Query("followTake") Integer followTake, @retrofit2.http.Query("programsTake") Integer programsTake, @retrofit2.http.Query("fundsTake") Integer fundsTake, @retrofit2.http.Query("newsTake") Integer newsTake
	);

	/**
	 * Investment programs levels
	 *
	 * @param currency (optional)
	 * @return Call&lt;ProgramsLevelsInfo&gt;
	 */
	@GET("v2.0/platform/levels")
	Observable<ProgramsLevelsInfo> getProgramLevels(
			@retrofit2.http.Query("currency") Currency currency
	);

	/**
	 * Investment programs levels parameters
	 *
	 * @param currency (optional)
	 * @return Call&lt;LevelsParamsInfo&gt;
	 */
	@GET("v2.0/platform/levels/parameters")
	Observable<LevelsParamsInfo> getProgramLevelsParams(
			@retrofit2.http.Query("currency") Currency currency
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

	/**
	 * Sitemap info
	 *
	 * @return Call&lt;SiteMapInfo&gt;
	 */
	@GET("v2.0/platform/sitemap")
	Observable<SiteMapInfo> getSitemapInfo();


}
