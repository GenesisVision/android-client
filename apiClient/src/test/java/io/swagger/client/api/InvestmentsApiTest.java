package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for InvestmentsApi
 */
public class InvestmentsApiTest
{

	private InvestmentsApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(InvestmentsApi.class);
	}


	/**
	 * Cancel investment request
	 */
	@Test
	public void cancelRequestTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.cancelRequest(id, authorization);

		// TODO: test validations
	}

	/**
	 * Data for withdrawal from fund (in selected currency)
	 */
	@Test
	public void getFundWithdrawInfoTest() {
		UUID id = null;
		String authorization = null;
		String currency = null;
		// FundWithdrawInfo response = api.getFundWithdrawInfo(id, authorization, currency);

		// TODO: test validations
	}

	/**
	 * Data for withdrawal from investment program (in program currency)
	 */
	@Test
	public void getProgramWithdrawInfoTest() {
		UUID id = null;
		String authorization = null;
		// ProgramWithdrawInfo response = api.getProgramWithdrawInfo(id, authorization);

		// TODO: test validations
	}

	/**
	 * Get all requests
	 */
	@Test
	public void getRequestsTest() {
		Integer skip = null;
		Integer take = null;
		String authorization = null;
		// ItemsViewModelAssetInvestmentRequest response = api.getRequests(skip, take, authorization);

		// TODO: test validations
	}

	/**
	 * Get program/fund requests
	 */
	@Test
	public void getRequestsByProgramTest() {
		UUID id = null;
		Integer skip = null;
		Integer take = null;
		String authorization = null;
		// ItemsViewModelAssetInvestmentRequest response = api.getRequestsByProgram(id, skip, take, authorization);

		// TODO: test validations
	}

	/**
	 * Investing into the fund
	 */
	@Test
	public void investIntoFundTest() {
		UUID id = null;
		String authorization = null;
		Double amount = null;
		UUID walletId = null;
		// Void response = api.investIntoFund(id, authorization, amount, walletId);

		// TODO: test validations
	}

	/**
	 * Investing into the program
	 */
	@Test
	public void investIntoProgramTest() {
		UUID id = null;
		String authorization = null;
		Double amount = null;
		UUID walletId = null;
		// Void response = api.investIntoProgram(id, authorization, amount, walletId);

		// TODO: test validations
	}

	/**
	 * Disable reinvesting
	 */
	@Test
	public void switchReinvestOffTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.switchReinvestOff(id, authorization);

		// TODO: test validations
	}

	/**
	 * Enable reinvesting
	 */
	@Test
	public void switchReinvestOnTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.switchReinvestOn(id, authorization);

		// TODO: test validations
	}

	/**
	 * Withdraw from fund. Percent is % of manager total money
	 */
	@Test
	public void withdrawFromFundTest() {
		UUID id = null;
		String authorization = null;
		Double percent = null;
		String currency = null;
		// Void response = api.withdrawFromFund(id, authorization, percent, currency);

		// TODO: test validations
	}

	/**
	 * Withdrawal from program
	 */
	@Test
	public void withdrawFromProgramTest() {
		UUID id = null;
		String authorization = null;
		Double amount = null;
		Boolean withdrawAll = null;
		// Void response = api.withdrawFromProgram(id, authorization, amount, withdrawAll);

		// TODO: test validations
	}
}
