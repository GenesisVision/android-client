package io.swagger.client.api;

import io.swagger.client.model.DExchangeRequest;
import io.swagger.client.model.DExchangeResponse;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface DexchangeApi
{
	/**
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;DExchangeResponse&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/dexchange/node")
	Observable<DExchangeResponse> v10DexchangeNodePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body DExchangeRequest model
	);

}
