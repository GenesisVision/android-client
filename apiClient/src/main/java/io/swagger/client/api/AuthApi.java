package io.swagger.client.api;

import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.PasswordModel;
import io.swagger.client.model.RecoveryCodesViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.RegisterManagerViewModel;
import io.swagger.client.model.ResendConfirmationViewModel;
import io.swagger.client.model.ResetPasswordViewModel;
import io.swagger.client.model.TwoFactorAuthenticator;
import io.swagger.client.model.TwoFactorAuthenticatorConfirm;
import io.swagger.client.model.TwoFactorCodeModel;
import io.swagger.client.model.TwoFactorStatus;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface AuthApi
{
	/**
	 * 2FA confirm
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;RecoveryCodesViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/2fa/confirm")
	Observable<RecoveryCodesViewModel> v10Auth2faConfirmPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body TwoFactorAuthenticatorConfirm model
	);

	/**
	 * 2FA create
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;TwoFactorAuthenticator&gt;
	 */
	@POST("v1.0/auth/2fa/create")
	Observable<TwoFactorAuthenticator> v10Auth2faCreatePost(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * 2FA disable
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/2fa/disable")
	Observable<Void> v10Auth2faDisablePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body TwoFactorCodeModel model
	);

	/**
	 * 2FA status
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;TwoFactorStatus&gt;
	 */
	@GET("v1.0/auth/2fa")
	Observable<TwoFactorStatus> v10Auth2faGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * 2FA generate new recovery codes
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;RecoveryCodesViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/2fa/recoverycodes/new")
	Observable<RecoveryCodesViewModel> v10Auth2faRecoverycodesNewPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body PasswordModel model
	);

	/**
	 * 2FA recovery codes
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;RecoveryCodesViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/2fa/recoverycodes")
	Observable<RecoveryCodesViewModel> v10Auth2faRecoverycodesPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body PasswordModel model
	);

	/**
	 * Change password
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/password/change")
	Observable<String> v10AuthPasswordChangePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ChangePasswordViewModel model
	);

	/**
	 * Forgot password for investor
	 *
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/password/forgot/investor")
	Observable<Void> v10AuthPasswordForgotInvestorPost(
			@retrofit2.http.Body ForgotPasswordViewModel model
	);

	/**
	 * Forgot password for manager
	 *
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/password/forgot/manager")
	Observable<Void> v10AuthPasswordForgotManagerPost(
			@retrofit2.http.Body ForgotPasswordViewModel model
	);

	/**
	 * Reset password
	 *
	 * @param model (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/password/reset")
	Observable<String> v10AuthPasswordResetPost(
			@retrofit2.http.Body ResetPasswordViewModel model
	);

	/**
	 * Add phone number
	 *
	 * @param authorization JWT access token (required)
	 * @param phoneNumber   (optional)
	 * @return Call&lt;Integer&gt;
	 */
	@POST("v1.0/auth/phone/add")
	Observable<Integer> v10AuthPhoneAddPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("phoneNumber") String phoneNumber
	);

	/**
	 * Verify phone number
	 *
	 * @param authorization JWT access token (required)
	 * @param token         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/auth/phone/verify")
	Observable<Void> v10AuthPhoneVerifyPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("token") String token
	);

	/**
	 * Resend Confirmation Link
	 *
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/resendconfirmationlink")
	Observable<Void> v10AuthResendconfirmationlinkPost(
			@retrofit2.http.Body ResendConfirmationViewModel model
	);

	/**
	 * Authorize
	 *
	 * @param model (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/signin/investor")
	Observable<String> v10AuthSigninInvestorPost(
			@retrofit2.http.Body LoginViewModel model
	);

	/**
	 * Authorize
	 *
	 * @param model (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/signin/manager")
	Observable<String> v10AuthSigninManagerPost(
			@retrofit2.http.Body LoginViewModel model
	);

	/**
	 * Confirm email after registration
	 *
	 * @param userId (optional)
	 * @param code   (optional)
	 * @return Call&lt;String&gt;
	 */
	@POST("v1.0/auth/signup/confirm")
	Observable<String> v10AuthSignupConfirmPost(
			@retrofit2.http.Query("userId") String userId, @retrofit2.http.Query("code") String code
	);

	/**
	 * New investor registration
	 *
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/signup/investor")
	Observable<Void> v10AuthSignupInvestorPost(
			@retrofit2.http.Body RegisterInvestorViewModel model
	);

	/**
	 * New manager registration
	 *
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/auth/signup/manager")
	Observable<Void> v10AuthSignupManagerPost(
			@retrofit2.http.Body RegisterManagerViewModel model
	);

	/**
	 * Logout from another devices
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;String&gt;
	 */
	@POST("v1.0/auth/token/devices/logout")
	Observable<String> v10AuthTokenDevicesLogoutPost(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Update auth token
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;String&gt;
	 */
	@POST("v1.0/auth/token/update")
	Observable<String> v10AuthTokenUpdatePost(
			@retrofit2.http.Header("Authorization") String authorization
	);

}
