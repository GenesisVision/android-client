package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.InvestmentProgramUpdate;
import io.swagger.client.model.NewProgramRequest;

/**
 * API tests for ManagersApi
 */
public class ManagersApiTest
{

	private ManagersApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(ManagersApi.class);
	}

	/**
	 * Manager profile
	 */
	@Test
	public void v10ManagersByIdGetTest() {
		UUID id = null;
		// ManagerProfile response = api.v10ManagersByIdGet(id);

		// TODO: test validations
	}

	/**
	 * Close existing investment program
	 */
	@Test
	public void v10ManagersProgramsByIdClosePostTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.v10ManagersProgramsByIdClosePost(id, authorization);

		// TODO: test validations
	}

	/**
	 * Close current period
	 */
	@Test
	public void v10ManagersProgramsByIdPeriodClosePostTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.v10ManagersProgramsByIdPeriodClosePost(id, authorization);

		// TODO: test validations
	}

	/**
	 * Get requests
	 */
	@Test
	public void v10ManagersProgramsByIdRequestsBySkipByTakeGetTest() {
		UUID id = null;
		Integer skip = null;
		Integer take = null;
		String authorization = null;
		// ProgramRequests response = api.v10ManagersProgramsByIdRequestsBySkipByTakeGet(id, skip, take, authorization);

		// TODO: test validations
	}

	/**
	 * Update investment program details
	 */
	@Test
	public void v10ManagersProgramsByIdUpdatePostTest() {
		UUID id = null;
		String authorization = null;
		InvestmentProgramUpdate model = null;
		// Void response = api.v10ManagersProgramsByIdUpdatePost(id, authorization, model);

		// TODO: test validations
	}

	/**
	 * Create an investment program
	 */
	@Test
	public void v10ManagersProgramsCreatePostTest() {
		String authorization = null;
		NewProgramRequest request = null;
		// Void response = api.v10ManagersProgramsCreatePost(authorization, request);

		// TODO: test validations
	}

	/**
	 * Cancel request
	 */
	@Test
	public void v10ManagersProgramsRequestsByIdCancelPostTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.v10ManagersProgramsRequestsByIdCancelPost(id, authorization);

		// TODO: test validations
	}
}
