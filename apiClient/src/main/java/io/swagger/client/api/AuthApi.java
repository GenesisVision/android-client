package io.swagger.client.api;

import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.PasswordModel;
import io.swagger.client.model.RecoveryCodesViewModel;
import io.swagger.client.model.RegisterViewModel;
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
	 * Authorize
	 *
	 * @param body (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/auth/signin")
	Observable<String> authorize(
			@retrofit2.http.Body LoginViewModel body
	);

	/**
	 * Change password
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/auth/password/change")
	Observable<String> changePassword(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ChangePasswordViewModel body
	);

	/**
	 * Confirm email after registration
	 *
	 * @param userId (optional)
	 * @param code   (optional)
	 * @return Call&lt;String&gt;
	 */
	@POST("v2.0/auth/signup/confirm")
	Observable<String> confirmEmail(
			@retrofit2.http.Query("userId") String userId, @retrofit2.http.Query("code") String code
	);

	/**
	 * 2FA confirm
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;RecoveryCodesViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/auth/2fa/confirm")
	Observable<RecoveryCodesViewModel> confirmTwoStepAuth(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body TwoFactorAuthenticatorConfirm body
	);

	/**
	 * 2FA create
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;TwoFactorAuthenticator&gt;
	 */
	@POST("v2.0/auth/2fa/create")
	Observable<TwoFactorAuthenticator> createTwoStepAuth(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * 2FA generate new recovery codes
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;RecoveryCodesViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/auth/2fa/recoverycodes/new")
	Observable<RecoveryCodesViewModel> createTwoStepAuthRecoveryCodes(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body PasswordModel body
	);

	/**
	 * 2FA disable
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/auth/2fa/disable")
	Observable<Void> disableTwoStepAuth(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body TwoFactorCodeModel body
	);

	/**
	 * Forgot password for investor
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/auth/password/forgot")
	Observable<Void> forgotPassword(
			@retrofit2.http.Body ForgotPasswordViewModel body
	);

	/**
	 * 2FA recovery codes
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;RecoveryCodesViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/auth/2fa/recoverycodes")
	Observable<RecoveryCodesViewModel> getTwoStepAuthRecoveryCodes(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body PasswordModel body
	);

	/**
	 * 2FA status
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;TwoFactorStatus&gt;
	 */
	@GET("v2.0/auth/2fa")
	Observable<TwoFactorStatus> getTwoStepAuthStatus(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Logout from another devices
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;String&gt;
	 */
	@POST("v2.0/auth/token/devices/logout")
	Observable<String> logoutFromAnotherDevices(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * New registration
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/auth/signup")
	Observable<Void> register(
			@retrofit2.http.Body RegisterViewModel body
	);

	/**
	 * Get phone number verification code
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Integer&gt;
	 */
	@POST("v2.0/auth/phone/code")
	Observable<Integer> requestPhoneNumberVerificationCode(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Resend Confirmation Link
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/auth/resendconfirmationlink")
	Observable<Void> resendConfirmationLink(
			@retrofit2.http.Body ResendConfirmationViewModel body
	);

	/**
	 * Reset password
	 *
	 * @param body (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/auth/password/reset")
	Observable<String> resetPassword(
			@retrofit2.http.Body ResetPasswordViewModel body
	);

	/**
	 * Update auth token
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;String&gt;
	 */
	@POST("v2.0/auth/token/update")
	Observable<String> updateAuthToken(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Verify phone number
	 *
	 * @param authorization JWT access token (required)
	 * @param code          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/auth/phone/verify")
	Observable<Void> validatePhoneNumber(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("code") String code
	);

}
