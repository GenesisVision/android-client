package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.GuidesCategoryItemsViewModel;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface GuidesApi
{
	/**
	 * Get guides
	 *
	 * @return Call&lt;GuidesCategoryItemsViewModel&gt;
	 */
	@GET("v2.0/guides")
	Observable<GuidesCategoryItemsViewModel> getGuides();


	/**
	 * Pass guid
	 *
	 * @param id (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/guides/pass")
	Observable<Void> passGuide(
			@retrofit2.http.Query("id") UUID id
	);

}
