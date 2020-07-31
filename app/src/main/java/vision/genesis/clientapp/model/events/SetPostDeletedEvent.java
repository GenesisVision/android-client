package vision.genesis.clientapp.model.events;

import io.swagger.client.model.Post;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 31/07/2020.
 */
public class SetPostDeletedEvent
{
	private final Post post;

	private final boolean isDeleted;

	public SetPostDeletedEvent(Post post, boolean isDeleted) {
		this.post = post;
		this.isDeleted = isDeleted;
	}

	public Post getPost() {
		return post;
	}

	public boolean isDeleted() {
		return isDeleted;
	}
}
