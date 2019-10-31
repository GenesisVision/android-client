package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.ChangeBrokerProgramRequest;
import io.swagger.client.model.FundAssetPart;
import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.ProgramPwdUpdate;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.TwoFactorCodeModel;

/**
 * API tests for AssetsApi
 */
public class AssetsApiTest
{

	private AssetsApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(AssetsApi.class);
	}


	/**
	 * Cancel changing broker in existing program
	 */
	@Test
	public void cancelChangeBrokerTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.cancelChangeBroker(id, authorization);

		// TODO: test validations
	}

	/**
	 * Change broker in existing program
	 */
	@Test
	public void changeBrokerTest() {
		String authorization = null;
		UUID id = null;
		ChangeBrokerProgramRequest body = null;
		// Void response = api.changeBroker(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Change program password
	 */
	@Test
	public void changeProgramPasswordTest() {
		String authorization = null;
		UUID id = null;
		ProgramPwdUpdate body = null;
		// Void response = api.changeProgramPassword(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Close current period
	 */
	@Test
	public void closeCurrentPeriodTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.closeCurrentPeriod(id, authorization);

		// TODO: test validations
	}

	/**
	 * Close existing fund
	 */
	@Test
	public void closeFundTest() {
		String authorization = null;
		UUID id = null;
		TwoFactorCodeModel body = null;
		// Void response = api.closeFund(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Close existing investment program
	 */
	@Test
	public void closeInvestmentProgramTest() {
		String authorization = null;
		UUID id = null;
		TwoFactorCodeModel body = null;
		// Void response = api.closeInvestmentProgram(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Confirm 2FA for program if required (for brokers like Huobi)
	 */
	@Test
	public void confirmProgram2FATest() {
		String authorization = null;
		UUID id = null;
		TwoFactorCodeModel body = null;
		// Void response = api.confirmProgram2FA(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Create fund
	 */
	@Test
	public void createFundTest() {
		String authorization = null;
		NewFundRequest body = null;
		// Void response = api.createFund(authorization, body);

		// TODO: test validations
	}

	/**
	 * Get program data for levels calculator
	 */
	@Test
	public void getLevelsCalculatorTest() {
		UUID id = null;
		String authorization = null;
		// ProgramLevelInfo response = api.getLevelsCalculator(id, authorization);

		// TODO: test validations
	}

	/**
	 * Get 2FA for program if needed
	 */
	@Test
	public void getProgram2FATest() {
		UUID id = null;
		String authorization = null;
		// TwoFactorAuthenticator response = api.getProgram2FA(id, authorization);

		// TODO: test validations
	}

	/**
	 * Create an investment program
	 */
	@Test
	public void makeAccountProgramTest() {
		String authorization = null;
		// ProgramCreateResult response = api.makeAccountProgram(authorization);

		// TODO: test validations
	}

	/**
	 * Update investment program/fund details
	 */
	@Test
	public void updateAssetTest() {
		String authorization = null;
		UUID id = null;
		ProgramUpdate body = null;
		// Void response = api.updateAsset(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Update investment program/fund details
	 */
	@Test
	public void updateAsset_0Test() {
		String authorization = null;
		UUID id = null;
		ProgramUpdate body = null;
		// Void response = api.updateAsset_0(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Update fund assets parts
	 */
	@Test
	public void updateFundAssetsTest() {
		String authorization = null;
		UUID id = null;
		List<FundAssetPart> body = null;
		// Void response = api.updateFundAssets(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Make program signal provider
	 */
	@Test
	public void updateProgramSignalSettingsTest() {
		String authorization = null;
		UUID programId = null;
		Double volumeFee = null;
		Double successFee = null;
		// Void response = api.updateProgramSignalSettings(authorization, programId, volumeFee, successFee);

		// TODO: test validations
	}

	/**
	 * Make program signal provider
	 */
	@Test
	public void updateProgramSignalSettings_0Test() {
		String authorization = null;
		UUID programId = null;
		Double volumeFee = null;
		Double successFee = null;
		// Void response = api.updateProgramSignalSettings_0(authorization, programId, volumeFee, successFee);

		// TODO: test validations
	}
}
