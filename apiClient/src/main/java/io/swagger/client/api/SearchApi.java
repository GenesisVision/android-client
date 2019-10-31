package io.swagger.client.api;

import io.swagger.client.model.SearchViewModel;
import retrofit2.http.GET;
import rx.Observable;

public interface SearchApi
{
	/**
	 * Program / fund / manager search
	 *
	 * @param authorization (optional)
	 * @param mask          (optional)
	 * @param take          (optional)
	 * @return Call&lt;SearchViewModel&gt;
	 */
	@GET("v2.0/search")
	Observable<SearchViewModel> search(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("mask") String mask, @retrofit2.http.Query("take") Integer take
	);

}
