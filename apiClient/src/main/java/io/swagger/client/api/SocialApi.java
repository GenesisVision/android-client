package io.swagger.client.api;//retrofit2

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.EditPost;
import io.swagger.client.model.EditablePost;
import io.swagger.client.model.MediaPost;
import io.swagger.client.model.MediaPostItemsViewModel;
import io.swagger.client.model.NewPost;
import io.swagger.client.model.Post;
import io.swagger.client.model.PostItemsViewModel;
import io.swagger.client.model.ProfilePublicShortItemsViewModel;
import io.swagger.client.model.RePost;
import io.swagger.client.model.SocialLinkType;
import io.swagger.client.model.SocialSummary;
import io.swagger.client.model.UserFeedMode;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface SocialApi
{
	/**
	 * Add post
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/social/feed/add")
	Observable<Void> addPost(
			@retrofit2.http.Body NewPost body
	);

	/**
	 * Delete post
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/social/feed/{id}/delete")
	Observable<Void> deletePost(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Edit post
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/social/feed/edit")
	Observable<Void> editPost(
			@retrofit2.http.Body EditPost body
	);

	/**
	 * Follow user
	 *
	 * @param userId (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/social/user/{userId}/follow")
	Observable<Void> followUser(
			@retrofit2.http.Path("userId") UUID userId
	);

	/**
	 * Get feed
	 *
	 * @param userId            (optional)
	 * @param tagContentId      (optional)
	 * @param tagContentIds     (optional)
	 * @param userMode          (optional)
	 * @param hashTags          (optional)
	 * @param mask              (optional)
	 * @param showTop           (optional)
	 * @param showLiked         (optional)
	 * @param showOnlyUserPosts (optional)
	 * @param skip              (optional)
	 * @param take              (optional)
	 * @return Call&lt;PostItemsViewModel&gt;
	 */
	@GET("v2.0/social/feed")
	Observable<PostItemsViewModel> getFeed(
			@retrofit2.http.Query("UserId") UUID userId, @retrofit2.http.Query("TagContentId") UUID tagContentId, @retrofit2.http.Query("TagContentIds") List<UUID> tagContentIds, @retrofit2.http.Query("UserMode") UserFeedMode userMode, @retrofit2.http.Query("HashTags") List<String> hashTags, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("ShowTop") Boolean showTop, @retrofit2.http.Query("ShowLiked") Boolean showLiked, @retrofit2.http.Query("ShowOnlyUserPosts") Boolean showOnlyUserPosts, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get original post/comment
	 *
	 * @param id (required)
	 * @return Call&lt;EditablePost&gt;
	 */
	@GET("v2.0/social/feed/{id}/original")
	Observable<EditablePost> getOriginalPost(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * Get post
	 *
	 * @param id (required)
	 * @return Call&lt;Post&gt;
	 */
	@GET("v2.0/social/feed/{id}")
	Observable<Post> getPost(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * Get post/comment likes users
	 *
	 * @param id (required)
	 * @return Call&lt;ProfilePublicShortItemsViewModel&gt;
	 */
	@GET("v2.0/social/feed/{id}/users/likes")
	Observable<ProfilePublicShortItemsViewModel> getPostLikesUsers(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * Get post/comment reposts users
	 *
	 * @param id (required)
	 * @return Call&lt;ProfilePublicShortItemsViewModel&gt;
	 */
	@GET("v2.0/social/feed/{id}/users/reposts")
	Observable<ProfilePublicShortItemsViewModel> getPostRepostsUsers(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * Get social media
	 *
	 * @param mask (optional)
	 * @param type (optional)
	 * @param skip (optional)
	 * @param take (optional)
	 * @return Call&lt;MediaPostItemsViewModel&gt;
	 */
	@GET("v2.0/social/feed/media")
	Observable<MediaPostItemsViewModel> getSocialMedia(
			@retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("Type") SocialLinkType type, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get social media post
	 *
	 * @param id (required)
	 * @return Call&lt;MediaPost&gt;
	 */
	@GET("v2.0/social/feed/media/{id}")
	Observable<MediaPost> getSocialMediaPost(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * Get social summary
	 *
	 * @return Call&lt;SocialSummary&gt;
	 */
	@GET("v2.0/social/feed/summary")
	Observable<SocialSummary> getSocialSummary();


	/**
	 * Like
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/social/feed/{id}/like")
	Observable<Void> likePost(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Pin post
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/social/feed/{id}/pin")
	Observable<Void> pinPost(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * RePost
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/social/feed/repost")
	Observable<Void> rePost(
			@retrofit2.http.Body RePost body
	);

	/**
	 * Revert deleting post
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/social/feed/{id}/delete/revert")
	Observable<Void> revertDeletingPost(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Report post/comment
	 *
	 * @param id     (required)
	 * @param reason (optional)
	 * @param text   (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/social/feed/{id}/report")
	Observable<Void> spamReport(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Query("reason") String reason, @retrofit2.http.Query("text") String text
	);

	/**
	 * Unfollow user
	 *
	 * @param userId (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/social/user/{userId}/unfollow")
	Observable<Void> unfollowUser(
			@retrofit2.http.Path("userId") UUID userId
	);

	/**
	 * Unlike
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/social/feed/{id}/unlike")
	Observable<Void> unlikePost(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Unpin post
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/social/feed/{id}/unpin")
	Observable<Void> unpinPost(
			@retrofit2.http.Path("id") UUID id
	);

}
