package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;

/**
 * API tests for PartnershipApi
 */
public class PartnershipApiTest
{

	private PartnershipApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(PartnershipApi.class);
	}


	/**
	 * Export rewards history.
	 */
	@Test
	public void exportHistoryTest() {
		String authorization = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer skip = null;
		Integer take = null;
		// String response = api.exportHistory(authorization, dateFrom, dateTo, skip, take);

		// TODO: test validations
	}

	/**
	 * Get partnership details.
	 */
	@Test
	public void getDetailsTest() {
		String authorization = null;
		String currency = null;
		// PartnershipDetails response = api.getDetails(authorization, currency);

		// TODO: test validations
	}

	/**
	 * Get agent friends (referrals and second level referrals).
	 */
	@Test
	public void getReferralsTest() {
		String authorization = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer skip = null;
		Integer take = null;
		// ItemsViewModelReferralFriend response = api.getReferrals(authorization, dateFrom, dateTo, skip, take);

		// TODO: test validations
	}

	/**
	 * Get history of agent rewards.
	 */
	@Test
	public void getRewardsHistoryTest() {
		String authorization = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer skip = null;
		Integer take = null;
		// ItemsViewModelRewardDetails response = api.getRewardsHistory(authorization, dateFrom, dateTo, skip, take);

		// TODO: test validations
	}
}
