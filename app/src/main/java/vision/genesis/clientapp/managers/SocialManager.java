package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.SocialApi;
import io.swagger.client.model.EditPost;
import io.swagger.client.model.EditablePost;
import io.swagger.client.model.MediaPostItemsViewModel;
import io.swagger.client.model.NewPost;
import io.swagger.client.model.Post;
import io.swagger.client.model.PostItemsViewModel;
import io.swagger.client.model.RePost;
import io.swagger.client.model.SocialLinkType;
import io.swagger.client.model.SocialSummary;
import rx.Observable;
import vision.genesis.clientapp.model.PostsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */
public class SocialManager
{
	private SocialApi socialApi;

	public SocialManager(SocialApi socialApi) {
		this.socialApi = socialApi;
	}

	public Observable<PostItemsViewModel> getFeed(PostsFilter filter, int skip, int take) {
		return socialApi.getFeed(filter.getUserId(), filter.getTagContentId(),
				filter.getTagContentIds(), filter.getUserMode(),
				filter.getHashTags(), filter.getMask(),
				filter.getShowTop(), filter.getShowLiked(), filter.getShowOnlyUserPosts(),
				skip, take);
	}

	public Observable<MediaPostItemsViewModel> getMedia(String mask, SocialLinkType type, int skip, int take) {
		return socialApi.getSocialMedia(mask, type, skip, take);
	}

	public Observable<Void> likePost(UUID postId) {
		return socialApi.likePost(postId);
	}

	public Observable<Void> unlikePost(UUID postId) {
		return socialApi.unlikePost(postId);
	}

	public Observable<Post> getPost(UUID postId) {
		return socialApi.getPost(postId.toString());
	}

	public Observable<Void> createPost(NewPost body) {
		return socialApi.addPost(body);
	}

	public Observable<Void> repost(NewPost newPost, UUID repostId) {
		RePost body = new RePost();
		body.setId(repostId);
		body.setText(newPost.getText());
		body.setImages(newPost.getImages());
		return socialApi.rePost(body);
	}

	public Observable<Void> sendComment(NewPost body) {
		return socialApi.addPost(body);
	}

	public Observable<Void> follow(UUID userId) {
		return socialApi.followUser(userId);
	}

	public Observable<Void> unfollow(UUID userId) {
		return socialApi.unfollowUser(userId);
	}

	public Observable<Void> pinPost(UUID postId) {
		return socialApi.pinPost(postId);
	}

	public Observable<Void> unpinPost(UUID postId) {
		return socialApi.unpinPost(postId);
	}

	public Observable<EditablePost> getOriginalPost(UUID postId) {
		return socialApi.getOriginalPost(postId.toString());
	}

	public Observable<Void> editPost(EditPost editablePost) {
		return socialApi.editPost(editablePost);
	}

	public Observable<Void> deletePost(UUID postId) {
		return socialApi.deletePost(postId);
	}

	public Observable<Void> revertDelete(UUID postId) {
		return socialApi.revertDeletingPost(postId);
	}

	public Observable<Void> reportPost(UUID postId, String reason, String description) {
		return socialApi.spamReport(postId.toString(), reason, description);
	}

	public Observable<SocialSummary> getSummary() {
		return socialApi.getSocialSummary();
	}
}