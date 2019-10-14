package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for BrokersApi
 */
public class BrokersApiTest
{

	private BrokersApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(BrokersApi.class);
	}


	/**
	 * Get brokers
	 */
	@Test
	public void getBrokersTest() {
		// BrokersInfo response = api.getBrokers();

		// TODO: test validations
	}

	/**
	 * Get brokers for program
	 */
	@Test
	public void getBrokersForProgramTest() {
		UUID programId = null;
		// BrokersProgramInfo response = api.getBrokersForProgram(programId);

		// TODO: test validations
	}
}
