package vision.genesis.clientapp.ui;

import java.util.UUID;

import io.swagger.client.model.Post;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/07/2020.
 */
public class OnShowPostDetailsEvent
{
	private final UUID postId;

	private final Post post;

	private final boolean showComments;

	public OnShowPostDetailsEvent(UUID postId, Post post, boolean showComments) {
		this.postId = postId;
		this.post = post;
		this.showComments = showComments;
	}

	public UUID getPostId() {
		return postId;
	}

	public Post getPost() {
		return post;
	}

	public boolean isShowComments() {
		return showComments;
	}
}
