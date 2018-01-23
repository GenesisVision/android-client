package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.RegisterManagerViewModel;

/**
 * API tests for AccountApi
 */
public class AccountApiTest
{

	private AccountApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(AccountApi.class);
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiBrokerAuthSignInPostTest() {
		LoginViewModel model = null;
		// String response = api.apiBrokerAuthSignInPost(model);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiBrokerAuthUpdateTokenGetTest() {
		// String response = api.apiBrokerAuthUpdateTokenGet();

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiInvestorAuthConfirmEmailGetTest() {
		String userId = null;
		String code = null;
		// Void response = api.apiInvestorAuthConfirmEmailGet(userId, code);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiInvestorAuthSignInPostTest() {
		LoginViewModel model = null;
		// String response = api.apiInvestorAuthSignInPost(model);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiInvestorAuthSignUpPostTest() {
		RegisterInvestorViewModel model = null;
		// Void response = api.apiInvestorAuthSignUpPost(model);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiInvestorAuthUpdateTokenGetTest() {
		// String response = api.apiInvestorAuthUpdateTokenGet();

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiInvestorProfileFullGetTest() {
		// ProfileFullViewModel response = api.apiInvestorProfileFullGet();

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiInvestorProfileGetTest() {
		// ProfileShortViewModel response = api.apiInvestorProfileGet();

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiInvestorProfileUpdatePostTest() {
		ProfileFullViewModel model = null;
		// Void response = api.apiInvestorProfileUpdatePost(model);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerAuthConfirmEmailGetTest() {
		String userId = null;
		String code = null;
		// Void response = api.apiManagerAuthConfirmEmailGet(userId, code);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerAuthSignInPostTest() {
		LoginViewModel model = null;
		// String response = api.apiManagerAuthSignInPost(model);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerAuthSignUpPostTest() {
		RegisterManagerViewModel model = null;
		// Void response = api.apiManagerAuthSignUpPost(model);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerAuthUpdateTokenGetTest() {
		// String response = api.apiManagerAuthUpdateTokenGet();

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerProfileFullGetTest() {
		// ProfileFullViewModel response = api.apiManagerProfileFullGet();

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerProfileGetTest() {
		// ProfileShortViewModel response = api.apiManagerProfileGet();

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerProfileUpdatePostTest() {
		ProfileFullViewModel model = null;
		// Void response = api.apiManagerProfileUpdatePost(model);

		// TODO: test validations
	}
}
