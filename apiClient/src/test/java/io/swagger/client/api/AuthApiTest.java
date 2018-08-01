package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;
import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.PasswordModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.RegisterManagerViewModel;
import io.swagger.client.model.ResetPasswordViewModel;
import io.swagger.client.model.TwoFactorAuthenticatorConfirm;
import io.swagger.client.model.TwoFactorCodeModel;

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
	 * 2FA confirm
	 */
	@Test
	public void v10Auth2faConfirmPostTest() {
		String authorization = null;
		TwoFactorAuthenticatorConfirm model = null;
		// RecoveryCodesViewModel response = api.v10Auth2faConfirmPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * 2FA create
	 */
	@Test
	public void v10Auth2faCreatePostTest() {
		String authorization = null;
		// TwoFactorAuthenticator response = api.v10Auth2faCreatePost(authorization);

		// TODO: test validations
	}

	/**
	 * 2FA disable
	 */
	@Test
	public void v10Auth2faDisablePostTest() {
		String authorization = null;
		TwoFactorCodeModel model = null;
		// Void response = api.v10Auth2faDisablePost(authorization, model);

		// TODO: test validations
	}

	/**
	 * 2FA status
	 */
	@Test
	public void v10Auth2faGetTest() {
		String authorization = null;
		// TwoFactorStatus response = api.v10Auth2faGet(authorization);

		// TODO: test validations
	}

	/**
	 * 2FA generate new recovery codes
	 */
	@Test
	public void v10Auth2faRecoverycodesNewPostTest() {
		String authorization = null;
		PasswordModel model = null;
		// RecoveryCodesViewModel response = api.v10Auth2faRecoverycodesNewPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * 2FA recovery codes
	 */
	@Test
	public void v10Auth2faRecoverycodesPostTest() {
		String authorization = null;
		PasswordModel model = null;
		// RecoveryCodesViewModel response = api.v10Auth2faRecoverycodesPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Change password
	 */
	@Test
	public void v10AuthPasswordChangePostTest() {
		String authorization = null;
		ChangePasswordViewModel model = null;
		// Void response = api.v10AuthPasswordChangePost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Forgot password for investor
	 */
	@Test
	public void v10AuthPasswordForgotInvestorPostTest() {
		ForgotPasswordViewModel model = null;
		// Void response = api.v10AuthPasswordForgotInvestorPost(model);

		// TODO: test validations
	}

	/**
	 * Forgot password for manager
	 */
	@Test
	public void v10AuthPasswordForgotManagerPostTest() {
		ForgotPasswordViewModel model = null;
		// Void response = api.v10AuthPasswordForgotManagerPost(model);

		// TODO: test validations
	}

	/**
	 * Reset password
	 */
	@Test
	public void v10AuthPasswordResetPostTest() {
		ResetPasswordViewModel model = null;
		// String response = api.v10AuthPasswordResetPost(model);

		// TODO: test validations
	}

	/**
	 * Authorize
	 */
	@Test
	public void v10AuthSigninBrokerPostTest() {
		LoginViewModel model = null;
		// String response = api.v10AuthSigninBrokerPost(model);

		// TODO: test validations
	}

	/**
	 * Authorize
	 */
	@Test
	public void v10AuthSigninInvestorPostTest() {
		LoginViewModel model = null;
		// String response = api.v10AuthSigninInvestorPost(model);

		// TODO: test validations
	}

	/**
	 * Authorize
	 */
	@Test
	public void v10AuthSigninManagerPostTest() {
		LoginViewModel model = null;
		// String response = api.v10AuthSigninManagerPost(model);

		// TODO: test validations
	}

	/**
	 * Confirm email after registration
	 */
	@Test
	public void v10AuthSignupConfirmPostTest() {
		String userId = null;
		String code = null;
		// String response = api.v10AuthSignupConfirmPost(userId, code);

		// TODO: test validations
	}

	/**
	 * New investor registration
	 */
	@Test
	public void v10AuthSignupInvestorPostTest() {
		RegisterInvestorViewModel model = null;
		// Void response = api.v10AuthSignupInvestorPost(model);

		// TODO: test validations
	}

	/**
	 * New manager registration
	 */
	@Test
	public void v10AuthSignupManagerPostTest() {
		RegisterManagerViewModel model = null;
		// Void response = api.v10AuthSignupManagerPost(model);

		// TODO: test validations
	}

	/**
	 * Update auth token
	 */
	@Test
	public void v10AuthTokenUpdatePostTest() {
		String authorization = null;
		// String response = api.v10AuthTokenUpdatePost(authorization);

		// TODO: test validations
	}
}
