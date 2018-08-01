package io.swagger.client.api;

import io.swagger.client.model.PlatformAssets;
import retrofit2.http.GET;
import rx.Observable;

public interface OefApi
{
	/**
	 * Get all supported assets for OEFs
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;PlatformAssets&gt;
	 */
	@GET("v1.0/oef/asset/all")
	Observable<PlatformAssets> v10OefAssetAllGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

}
