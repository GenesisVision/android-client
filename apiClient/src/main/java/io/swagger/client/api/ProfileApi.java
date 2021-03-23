package io.swagger.client.api;//retrofit2

import io.swagger.client.model.BetaTestingType;
import io.swagger.client.model.Currency;
import io.swagger.client.model.ExternalKycAccessToken;
import io.swagger.client.model.FcmTokenViewModel;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.ProfileHeaderViewModel;
import io.swagger.client.model.SocialLinksViewModel;
import io.swagger.client.model.SocialViewMode;
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
	 * @return Call&lt;ExternalKycAccessToken&gt;
	 */
	@POST("v2.0/profile/verification/mobile/token")
	Observable<ExternalKycAccessToken> getMobileVerificationToken();


	/**
	 * Get full profile
	 *
	 * @return Call&lt;ProfileFullViewModel&gt;
	 */
	@GET("v2.0/profile")
	Observable<ProfileFullViewModel> getProfileFull();


	/**
	 * Get header profile
	 *
	 * @return Call&lt;ProfileHeaderViewModel&gt;
	 */
	@GET("v2.0/profile/header")
	Observable<ProfileHeaderViewModel> getProfileHeader();


	/**
	 * Get social links
	 *
	 * @return Call&lt;SocialLinksViewModel&gt;
	 */
	@GET("v2.0/profile/sociallinks")
	Observable<SocialLinksViewModel> getSocialLinks();


	/**
	 * @return Call&lt;ExternalKycAccessToken&gt;
	 */
	@POST("v2.0/profile/verification/web/token")
	Observable<ExternalKycAccessToken> getWebVerificationToken();


	/**
	 * Remove avatar
	 *
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/avatar/remove")
	Observable<Void> removeAvatar();


	/**
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/push/token/remove")
	Observable<Void> removeFcmToken(
			@retrofit2.http.Body FcmTokenViewModel body
	);

	/**
	 * Disable beta feature
	 *
	 * @param feature (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/beta/off")
	Observable<Void> switchBetaFeatureOff(
			@retrofit2.http.Query("feature") BetaTestingType feature
	);

	/**
	 * Enable beta feature
	 *
	 * @param feature (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/beta/on")
	Observable<Void> switchBetaFeatureOn(
			@retrofit2.http.Query("feature") BetaTestingType feature
	);

	/**
	 * Disable public investor profile
	 *
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/investor/public/off")
	Observable<Void> switchPublicInvestorOff();


	/**
	 * Enable public investor profile
	 *
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/investor/public/on")
	Observable<Void> switchPublicInvestorOn();


	/**
	 * Add or update all social links
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/sociallinks/all/update")
	Observable<Void> updateAllSocialLinks(
			@retrofit2.http.Body UpdateSocialLinksViewModel body
	);

	/**
	 * Update avatar
	 *
	 * @param fileId (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/avatar/update/{fileId}")
	Observable<Void> updateAvatar(
			@retrofit2.http.Path("fileId") String fileId
	);

	/**
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/push/token")
	Observable<Void> updateFcmToken(
			@retrofit2.http.Body FcmTokenViewModel body
	);

	/**
	 * Update user personal details
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/personal/update")
	Observable<Void> updatePersonalDetails(
			@retrofit2.http.Body UpdatePersonalDetailViewModel body
	);

	/**
	 * Update profile
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/update")
	Observable<Void> updateProfile(
			@retrofit2.http.Body UpdateProfileViewModel body
	);

	/**
	 * Add or update social links
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/profile/sociallinks/update")
	Observable<Void> updateSocialLinks(
			@retrofit2.http.Body UpdateSocialLinkViewModel body
	);

	/**
	 * Update platform currency
	 *
	 * @param currency (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/currency/update")
	Observable<Void> updateUserPlatformCurrency(
			@retrofit2.http.Query("currency") Currency currency
	);

	/**
	 * Update user social settings
	 *
	 * @param whoCanPostToMayWall         (optional)
	 * @param whoCanViewCommentsOnMyPosts (optional)
	 * @param whoCanCommentOnMyPosts      (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/profile/social/settings/update")
	Observable<Void> updateUserSocialSettings(
			@retrofit2.http.Query("WhoCanPostToMayWall") SocialViewMode whoCanPostToMayWall, @retrofit2.http.Query("WhoCanViewCommentsOnMyPosts") SocialViewMode whoCanViewCommentsOnMyPosts, @retrofit2.http.Query("WhoCanCommentOnMyPosts") SocialViewMode whoCanCommentOnMyPosts
	);

}
