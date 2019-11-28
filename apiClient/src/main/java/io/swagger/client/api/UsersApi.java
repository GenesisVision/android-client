package io.swagger.client.api;

import java.util.List;

import io.swagger.client.model.ItemsViewModelUserDetailsList;
import io.swagger.client.model.PublicProfile;
import retrofit2.http.GET;
import rx.Observable;

public interface UsersApi
{
	/**
	 * Public profile
	 *
	 * @param id (required)
	 * @return Call&lt;PublicProfile&gt;
	 */
	@GET("v2.0/users/{id}")
	Observable<PublicProfile> getManagerProfile(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * Get users list
	 *
	 * @param facetId (optional)
	 * @param sorting (optional)
	 * @param tags    (optional)
	 * @param skip    (optional)
	 * @param take    (optional)
	 * @return Call&lt;ItemsViewModelUserDetailsList&gt;
	 */
	@GET("v2.0/users/list")
	Observable<ItemsViewModelUserDetailsList> getUsersList(
			@retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("Tags") List<String> tags, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
