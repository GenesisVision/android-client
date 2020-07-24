package vision.genesis.clientapp.model.events;

import io.swagger.client.model.Post;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/07/2020.
 */
public class OnShowRepostEvent
{
	private final Post post;

	public OnShowRepostEvent(Post post) {
		this.post = post;
	}

	public Post getPost() {
		return post;
	}
}
