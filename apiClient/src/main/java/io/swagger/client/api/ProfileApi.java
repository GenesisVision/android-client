package io.swagger.client.api;

import io.swagger.client.model.FcmTokenViewModel;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.ProfileHeaderViewModel;
import io.swagger.client.model.SocialLinksViewModel;
import io.swagger.client.model.UpdatePersonalDetailViewModel;
import io.swagger.client.model.UpdateProfileViewModel;
import io.swagger.client.model.UpdateSocialLinkViewModel;
import io.swagger.client.model.UpdateSocialLinksViewModel;
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
	@GET("v2.0/profile")
	Observable<ProfileFullViewModel> getProfileFull(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get header profile
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProfileHeaderViewModel&gt;
	 */
	@GET("v2.0/profile/header")
	Observable<ProfileHeaderViewModel> getProfileHeader(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get social links
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;SocialLinksViewModel&gt;
	 */
	@GET("v2.0/profile/sociallinks")
	Observable<SocialLinksViewModel> getSocialLinks(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param authorization JWT access token (required)
	 * @return Call&lt;String&gt;
	 */
	@POST("v2.0/profile/verification/token")
	Observable<String> getVerificationToken(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Remove avatar
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/avatar/remove")
	Observable<Void> removeAvatar(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/push/token/remove")
	Observable<Void> removeFcmToken(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body FcmTokenViewModel body
	);

	/**
	 * Disable public investor profile
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/investor/public/off")
	Observable<Void> switchPublicInvestorOff(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Enable public investor profile
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/investor/public/on")
	Observable<Void> switchPublicInvestorOn(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Add or update all social links
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/sociallinks/all/update")
	Observable<Void> updateAllSocialLinks(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body UpdateSocialLinksViewModel body
	);

	/**
	 * Update avatar
	 *
	 * @param fileId        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/avatar/update/{fileId}")
	Observable<Void> updateAvatar(
			@retrofit2.http.Path("fileId") String fileId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/push/token")
	Observable<Void> updateFcmToken(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body FcmTokenViewModel body
	);

	/**
	 * Update user personal details
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/personal/update")
	Observable<Void> updatePersonalDetails(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body UpdatePersonalDetailViewModel body
	);

	/**
	 * Update profile
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/update")
	Observable<Void> updateProfile(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body UpdateProfileViewModel body
	);

	/**
	 * Add or update social links
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/sociallinks/update")
	Observable<Void> updateSocialLinks(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body UpdateSocialLinkViewModel body
	);

}
