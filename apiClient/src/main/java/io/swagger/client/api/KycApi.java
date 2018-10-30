package io.swagger.client.api;

import io.swagger.client.model.KycCallback;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface KycApi
{
	/**
	 * @param model (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/kyc/callback")
	Observable<String> v10KycCallbackPost(
			@retrofit2.http.Body KycCallback model
	);

}
