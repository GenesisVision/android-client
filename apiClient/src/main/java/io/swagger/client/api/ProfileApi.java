package io.swagger.client.api;

import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.ProfileHeaderViewModel;
import io.swagger.client.model.UpdateProfileViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface ProfileApi
{
	/**
	 * Get full profile
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProfileFullViewModel&gt;
	 */
	@GET("v1.0/profile")
	Observable<ProfileFullViewModel> v10ProfileGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get header profile
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProfileHeaderViewModel&gt;
	 */
	@GET("v1.0/profile/header")
	Observable<ProfileHeaderViewModel> v10ProfileHeaderGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Update avatar
	 *
	 * @param fileId        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/profile/update/avatar/{fileId}")
	Observable<Void> v10ProfileUpdateAvatarByFileIdPost(
			@retrofit2.http.Path("fileId") String fileId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Update profile
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/profile/update")
	Observable<Void> v10ProfileUpdatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body UpdateProfileViewModel model
	);

}
