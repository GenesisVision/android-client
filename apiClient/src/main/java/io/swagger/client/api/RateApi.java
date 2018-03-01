package io.swagger.client.api;

import io.swagger.client.model.RateViewModel;
import io.swagger.client.model.RequestRate;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface RateApi
{
	/**
	 * Get rate
	 *
	 * @param model (optional)
	 * @return Call&lt;RateViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/rate")
	Observable<RateViewModel> apiRatePost(
			@retrofit2.http.Body RequestRate model
	);

}
