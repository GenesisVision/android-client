package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.NewInvestmentRequest;
import io.swagger.client.model.NewManager;

/**
 * API tests for ManagerApi
 */
public class ManagerApiTest
{

	private ManagerApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(ManagerApi.class);
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiBrokerAccountCreatePostTest() {
		NewManager request = null;
		// UUID response = api.apiBrokerAccountCreatePost(request);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerAccountNewInvestmentRequestPostTest() {
		NewInvestmentRequest request = null;
		// UUID response = api.apiManagerAccountNewInvestmentRequestPost(request);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerInvestmentCloseGetTest() {
		UUID investmentProgramId = null;
		// Void response = api.apiManagerInvestmentCloseGet(investmentProgramId);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerInvestmentGetTest() {
		UUID investmentProgramId = null;
		// InvestmentProgramViewModel response = api.apiManagerInvestmentGet(investmentProgramId);

		// TODO: test validations
	}
}
