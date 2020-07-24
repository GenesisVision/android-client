package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.SocialApi;
import io.swagger.client.model.MediaPostItemsViewModel;
import io.swagger.client.model.NewPost;
import io.swagger.client.model.Post;
import io.swagger.client.model.PostItemsViewModel;
import io.swagger.client.model.RePost;
import io.swagger.client.model.SocialLinkType;
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
}