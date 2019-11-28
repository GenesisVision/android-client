package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.swagger.client.ApiClient;

/**
 * API tests for UsersApi
 */
public class UsersApiTest
{

	private UsersApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(UsersApi.class);
	}


	/**
	 * Public profile
	 */
	@Test
	public void getManagerProfileTest() {
		String id = null;
		// PublicProfile response = api.getManagerProfile(id);

		// TODO: test validations
	}

	/**
	 * Get users list
	 */
	@Test
	public void getUsersListTest() {
		String facetId = null;
		String sorting = null;
		List<String> tags = null;
		Integer skip = null;
		Integer take = null;
		// ItemsViewModelUserDetailsList response = api.getUsersList(facetId, sorting, tags, skip, take);

		// TODO: test validations
	}
}
