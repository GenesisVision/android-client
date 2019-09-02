package io.swagger.client.api;

import io.swagger.client.model.ExternalKeyAddViewModel;
import io.swagger.client.model.ExternalKeysViewModel;
import io.swagger.client.model.FcmTokenViewModel;
import io.swagger.client.model.IdModel;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.ProfileHeaderViewModel;
import io.swagger.client.model.SocialLinksViewModel;
import io.swagger.client.model.UpdatePersonalDetailViewModel;
import io.swagger.client.model.UpdateProfileViewModel;
import io.swagger.client.model.UpdateSocialLinkViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface ProfileApi
{
	/**
	 * Remove avatar
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/profile/avatar/remove")
	Observable<Void> v10ProfileAvatarRemovePost(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Update avatar
	 *
	 * @param fileId        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/profile/avatar/update/{fileId}")
	Observable<Void> v10ProfileAvatarUpdateByFileIdPost(
			@retrofit2.http.Path("fileId") String fileId, @retrofit2.http.Header("Authorization") String authorization
	);

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
	 * Add external exchange key
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/profile/keys/add")
	Observable<Void> v10ProfileKeysAddPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ExternalKeyAddViewModel model
	);

	/**
	 * Delete external exchange key
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/profile/keys/delete")
	Observable<Void> v10ProfileKeysDeletePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body IdModel model
	);

	/**
	 * Get external exchange keys
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ExternalKeysViewModel&gt;
	 */
	@GET("v1.0/profile/keys")
	Observable<ExternalKeysViewModel> v10ProfileKeysGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Update user personal details
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/profile/personal/update")
	Observable<Void> v10ProfilePersonalUpdatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body UpdatePersonalDetailViewModel model
	);

	/**
	 * @param authorization JWT access token (required)
	 * @param token         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/profile/push/token")
	Observable<Void> v10ProfilePushTokenPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body FcmTokenViewModel token
	);

	/**
	 * Get social links
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;SocialLinksViewModel&gt;
	 */
	@GET("v1.0/profile/sociallinks")
	Observable<SocialLinksViewModel> v10ProfileSociallinksGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Add or update social links
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/profile/sociallinks/update")
	Observable<Void> v10ProfileSociallinksUpdatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body UpdateSocialLinkViewModel model
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

	/**
	 * @param authorization JWT access token (required)
	 * @return Call&lt;String&gt;
	 */
	@POST("v1.0/profile/verification/token")
	Observable<String> v10ProfileVerificationTokenPost(
			@retrofit2.http.Header("Authorization") String authorization
	);

}
