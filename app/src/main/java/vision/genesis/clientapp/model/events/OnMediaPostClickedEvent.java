package vision.genesis.clientapp.model.events;

import io.swagger.client.model.MediaPost;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/07/2020.
 */
public class OnMediaPostClickedEvent
{
	private final MediaPost post;

	public OnMediaPostClickedEvent(MediaPost post) {
		this.post = post;
	}

	public MediaPost getPost() {
		return post;
	}
}
