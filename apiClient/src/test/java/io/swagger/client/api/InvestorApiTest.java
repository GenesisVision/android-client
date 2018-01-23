package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;
import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentsFilter;

/**
 * API tests for InvestorApi
 */
public class InvestorApiTest
{

	private InvestorApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(InvestorApi.class);
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiInvestorInvestmentsInvestPostTest() {
		Invest model = null;
		// Void response = api.apiInvestorInvestmentsInvestPost(model);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiInvestorInvestmentsPostTest() {
		InvestmentsFilter filter = null;
		// InvestmentProgramsViewModel response = api.apiInvestorInvestmentsPost(filter);

		// TODO: test validations
	}
}
