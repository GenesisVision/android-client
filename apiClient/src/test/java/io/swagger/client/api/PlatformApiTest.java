package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;

/**
 * API tests for PlatformApi
 */
public class PlatformApiTest
{

	private PlatformApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(PlatformApi.class);
	}


	/**
	 * Get all supported assets for funds
	 */
	@Test
	public void getAllPlatformAssetsTest() {
		// PlatformAssets response = api.getAllPlatformAssets();

		// TODO: test validations
	}

	/**
	 * Server date
	 */
	@Test
	public void getPlatformDateTest() {
		// String response = api.getPlatformDate();

		// TODO: test validations
	}

	/**
	 * Platform info
	 */
	@Test
	public void getPlatformInfoTest() {
		// PlatformInfo response = api.getPlatformInfo();

		// TODO: test validations
	}

	/**
	 * Investment programs levels
	 */
	@Test
	public void getProgramLevelsTest() {
		String currency = null;
		// ProgramsLevelsInfo response = api.getProgramLevels(currency);

		// TODO: test validations
	}

	/**
	 * Investment programs levels parameters
	 */
	@Test
	public void getProgramLevelsParamsTest() {
		String currency = null;
		// LevelsParamsInfo response = api.getProgramLevelsParams(currency);

		// TODO: test validations
	}

	/**
	 * Risk control
	 */
	@Test
	public void getRiskControlInfoTest() {
		String route = null;
		String client = null;
		String version = null;
		// CaptchaDetails response = api.getRiskControlInfo(route, client, version);

		// TODO: test validations
	}
}
