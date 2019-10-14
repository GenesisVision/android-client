package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.CreateWithdrawalRequestModel;
import io.swagger.client.model.InternalTransferRequest;

/**
 * API tests for WalletApi
 */
public class WalletApiTest
{

	private WalletApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(WalletApi.class);
	}


	/**
	 *
	 */
	@Test
	public void cancelWithdrawalRequestTest() {
		UUID txId = null;
		String authorization = null;
		// Void response = api.cancelWithdrawalRequest(txId, authorization);

		// TODO: test validations
	}

	/**
	 *
	 */
	@Test
	public void confirmWithdrawalRequestByCodeTest() {
		UUID requestId = null;
		String code = null;
		// Void response = api.confirmWithdrawalRequestByCode(requestId, code);

		// TODO: test validations
	}

	/**
	 *
	 */
	@Test
	public void createWithdrawalRequestTest() {
		String authorization = null;
		CreateWithdrawalRequestModel body = null;
		// Void response = api.createWithdrawalRequest(authorization, body);

		// TODO: test validations
	}

	/**
	 * GenesisMarkets commission data
	 */
	@Test
	public void getGMCommissionDataTest() {
		String authorization = null;
		// UserCommissionData response = api.getGMCommissionData(authorization);

		// TODO: test validations
	}

	/**
	 *
	 */
	@Test
	public void getUserWithdrawalSummaryTest() {
		String authorization = null;
		// WithdrawalSummary response = api.getUserWithdrawalSummary(authorization);

		// TODO: test validations
	}

	/**
	 * Wallet available
	 */
	@Test
	public void getWalletAvailableTest() {
		String currency = null;
		String authorization = null;
		// WalletMultiAvailable response = api.getWalletAvailable(currency, authorization);

		// TODO: test validations
	}

	/**
	 * Wallet summary
	 */
	@Test
	public void getWalletSummaryTest() {
		String currency = null;
		String authorization = null;
		// WalletMultiSummary response = api.getWalletSummary(currency, authorization);

		// TODO: test validations
	}

	/**
	 *
	 */
	@Test
	public void resendWithdrawalRequestEmailTest() {
		UUID txId = null;
		String authorization = null;
		// Void response = api.resendWithdrawalRequestEmail(txId, authorization);

		// TODO: test validations
	}

	/**
	 * Disable paying platform fees with GVT
	 */
	@Test
	public void switchPayFeeInGvtOffTest() {
		String authorization = null;
		// Void response = api.switchPayFeeInGvtOff(authorization);

		// TODO: test validations
	}

	/**
	 * Enable paying platform fees with GVT
	 */
	@Test
	public void switchPayFeeInGvtOnTest() {
		String authorization = null;
		// Void response = api.switchPayFeeInGvtOn(authorization);

		// TODO: test validations
	}

	/**
	 * Transfer money
	 */
	@Test
	public void transferTest() {
		String authorization = null;
		InternalTransferRequest body = null;
		// Void response = api.transfer(authorization, body);

		// TODO: test validations
	}

	/**
	 * Update deposit wallets
	 */
	@Test
	public void updateDepositWalletsTest() {
		String authorization = null;
		// WalletDepositSummary response = api.updateDepositWallets(authorization);

		// TODO: test validations
	}
}
