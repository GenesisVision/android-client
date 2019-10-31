package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;
import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.PasswordModel;
import io.swagger.client.model.RegisterViewModel;
import io.swagger.client.model.ResendConfirmationViewModel;
import io.swagger.client.model.ResetPasswordViewModel;
import io.swagger.client.model.TwoFactorAuthenticatorConfirm;
import io.swagger.client.model.TwoFactorCodeWithPassword;

/**
 * API tests for AuthApi
 */
public class AuthApiTest
{

	private AuthApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(AuthApi.class);
	}


	/**
	 * Authorize
	 */
	@Test
	public void authorizeTest() {
		LoginViewModel body = null;
		// String response = api.authorize(body);

		// TODO: test validations
	}

	/**
	 * Change password
	 */
	@Test
	public void changePasswordTest() {
		String authorization = null;
		ChangePasswordViewModel body = null;
		// String response = api.changePassword(authorization, body);

		// TODO: test validations
	}

	/**
	 * Confirm email after registration
	 */
	@Test
	public void confirmEmailTest() {
		String userId = null;
		String code = null;
		// String response = api.confirmEmail(userId, code);

		// TODO: test validations
	}

	/**
	 * 2FA confirm
	 */
	@Test
	public void confirmTwoStepAuthTest() {
		String authorization = null;
		TwoFactorAuthenticatorConfirm body = null;
		// RecoveryCodesViewModel response = api.confirmTwoStepAuth(authorization, body);

		// TODO: test validations
	}

	/**
	 * 2FA create
	 */
	@Test
	public void createTwoStepAuthTest() {
		String authorization = null;
		// TwoFactorAuthenticator response = api.createTwoStepAuth(authorization);

		// TODO: test validations
	}

	/**
	 * 2FA generate new recovery codes
	 */
	@Test
	public void createTwoStepAuthRecoveryCodesTest() {
		String authorization = null;
		PasswordModel body = null;
		// RecoveryCodesViewModel response = api.createTwoStepAuthRecoveryCodes(authorization, body);

		// TODO: test validations
	}

	/**
	 * 2FA disable
	 */
	@Test
	public void disableTwoStepAuthTest() {
		String authorization = null;
		TwoFactorCodeWithPassword body = null;
		// Void response = api.disableTwoStepAuth(authorization, body);

		// TODO: test validations
	}

	/**
	 * Forgot password for investor
	 */
	@Test
	public void forgotPasswordTest() {
		ForgotPasswordViewModel body = null;
		// Void response = api.forgotPassword(body);

		// TODO: test validations
	}

	/**
	 * 2FA recovery codes
	 */
	@Test
	public void getTwoStepAuthRecoveryCodesTest() {
		String authorization = null;
		PasswordModel body = null;
		// RecoveryCodesViewModel response = api.getTwoStepAuthRecoveryCodes(authorization, body);

		// TODO: test validations
	}

	/**
	 * 2FA status
	 */
	@Test
	public void getTwoStepAuthStatusTest() {
		String authorization = null;
		// TwoFactorStatus response = api.getTwoStepAuthStatus(authorization);

		// TODO: test validations
	}

	/**
	 * Logout from another devices
	 */
	@Test
	public void logoutFromAnotherDevicesTest() {
		String authorization = null;
		// String response = api.logoutFromAnotherDevices(authorization);

		// TODO: test validations
	}

	/**
	 * New registration
	 */
	@Test
	public void registerTest() {
		RegisterViewModel body = null;
		// Void response = api.register(body);

		// TODO: test validations
	}

	/**
	 * Get phone number verification code
	 */
	@Test
	public void requestPhoneNumberVerificationCodeTest() {
		String authorization = null;
		// Integer response = api.requestPhoneNumberVerificationCode(authorization);

		// TODO: test validations
	}

	/**
	 * Resend Confirmation Link
	 */
	@Test
	public void resendConfirmationLinkTest() {
		ResendConfirmationViewModel body = null;
		// Void response = api.resendConfirmationLink(body);

		// TODO: test validations
	}

	/**
	 * Reset password
	 */
	@Test
	public void resetPasswordTest() {
		ResetPasswordViewModel body = null;
		// String response = api.resetPassword(body);

		// TODO: test validations
	}

	/**
	 * Update auth token
	 */
	@Test
	public void updateAuthTokenTest() {
		String authorization = null;
		// String response = api.updateAuthToken(authorization);

		// TODO: test validations
	}

	/**
	 * Verify phone number
	 */
	@Test
	public void validatePhoneNumberTest() {
		String authorization = null;
		String code = null;
		// Void response = api.validatePhoneNumber(authorization, code);

		// TODO: test validations
	}
}
