package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.EditPost;
import io.swagger.client.model.NewPost;
import io.swagger.client.model.RePost;
import io.swagger.client.model.UserFeedMode;

/**
 * API tests for SocialApi
 */
public class SocialApiTest
{

	private SocialApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(SocialApi.class);
	}


	/**
	 * Add post
	 */
	@Test
	public void addPostTest() {
		NewPost body = null;
		// Void response = api.addPost(body);

		// TODO: test validations
	}

	/**
	 * Delete post
	 */
	@Test
	public void deletePostTest() {
		UUID id = null;
		// Void response = api.deletePost(id);

		// TODO: test validations
	}

	/**
	 * Edit post
	 */
	@Test
	public void editPostTest() {
		EditPost body = null;
		// Void response = api.editPost(body);

		// TODO: test validations
	}

	/**
	 * Follow user
	 */
	@Test
	public void followUserTest() {
		UUID userId = null;
		// Void response = api.followUser(userId);

		// TODO: test validations
	}

	/**
	 * Get feed
	 */
	@Test
	public void getFeedTest() {
		UUID userId = null;
		UserFeedMode userMode = null;
		List<String> hashTags = null;
		String mask = null;
		Boolean showTop = null;
		Boolean showLiked = null;
		Integer skip = null;
		Integer take = null;
		// PostItemsViewModel response = api.getFeed(userId, userMode, hashTags, mask, showTop, showLiked, skip, take);

		// TODO: test validations
	}

	/**
	 * Get post
	 */
	@Test
	public void getPostTest() {
		UUID id = null;
		// EditablePost response = api.getPost(id);

		// TODO: test validations
	}

	/**
	 * Get social summary
	 */
	@Test
	public void getSocialSummaryTest() {
		// SocialSummary response = api.getSocialSummary();

		// TODO: test validations
	}

	/**
	 * Like
	 */
	@Test
	public void likePostTest() {
		UUID id = null;
		// Void response = api.likePost(id);

		// TODO: test validations
	}

	/**
	 * Pin post
	 */
	@Test
	public void pinPostTest() {
		UUID id = null;
		// Void response = api.pinPost(id);

		// TODO: test validations
	}

	/**
	 * RePost
	 */
	@Test
	public void rePostTest() {
		RePost body = null;
		// Void response = api.rePost(body);

		// TODO: test validations
	}

	/**
	 * Revert deleting post
	 */
	@Test
	public void revertDeletingPostTest() {
		UUID id = null;
		// Void response = api.revertDeletingPost(id);

		// TODO: test validations
	}

	/**
	 * Unfollow user
	 */
	@Test
	public void unfollowUserTest() {
		UUID userId = null;
		// Void response = api.unfollowUser(userId);

		// TODO: test validations
	}

	/**
	 * Unlike
	 */
	@Test
	public void unlikePostTest() {
		UUID id = null;
		// Void response = api.unlikePost(id);

		// TODO: test validations
	}

	/**
	 * Unpin post
	 */
	@Test
	public void unpinPostTest() {
		UUID id = null;
		// Void response = api.unpinPost(id);

		// TODO: test validations
	}
}
