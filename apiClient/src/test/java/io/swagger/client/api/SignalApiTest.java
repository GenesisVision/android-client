package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for SignalApi
 */
public class SignalApiTest
{

	private SignalApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(SignalApi.class);
	}

	/**
	 * Get copytrading accounts
	 */
	@Test
	public void v10SignalAccountsGetTest() {
		String authorization = null;
		// CopyTradingAccountsList response = api.v10SignalAccountsGet(authorization);

		// TODO: test validations
	}

	/**
	 * Subscribe to programs signals
	 */
	@Test
	public void v10SignalAttachByIdPostTest() {
		UUID id = null;
		String authorization = null;
		String mode = null;
		Double percent = null;
		Double openTolerancePercent = null;
		Double fixedVolume = null;
		String fixedCurrency = null;
		String initialDepositCurrency = null;
		Double initialDepositAmount = null;
		// Void response = api.v10SignalAttachByIdPost(id, authorization, mode, percent, openTolerancePercent, fixedVolume, fixedCurrency, initialDepositCurrency, initialDepositAmount);

		// TODO: test validations
	}

	/**
	 * Unsubscribe from program signals
	 */
	@Test
	public void v10SignalDetachByIdPostTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.v10SignalDetachByIdPost(id, authorization);

		// TODO: test validations
	}

	/**
	 * Get investors signals open trades
	 */
	@Test
	public void v10SignalOpensignaltradesGetTest() {
		String authorization = null;
		// OpenSignalTradesList response = api.v10SignalOpensignaltradesGet(authorization);

		// TODO: test validations
	}

	/**
	 * Update signal subscription settings
	 */
	@Test
	public void v10SignalUpdatePostTest() {
		String authorization = null;
		UUID id = null;
		String mode = null;
		Double percent = null;
		Double openTolerancePercent = null;
		Double fixedVolume = null;
		String fixedCurrency = null;
		String initialDepositCurrency = null;
		Double initialDepositAmount = null;
		// Void response = api.v10SignalUpdatePost(authorization, id, mode, percent, openTolerancePercent, fixedVolume, fixedCurrency, initialDepositCurrency, initialDepositAmount);

		// TODO: test validations
	}
}
