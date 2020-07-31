package vision.genesis.clientapp.model.events;

import io.swagger.client.model.Post;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 31/07/2020.
 */
public class ShowReportPostEvent
{
	private final Post post;

	public ShowReportPostEvent(Post post) {
		this.post = post;
	}

	public Post getPost() {
		return post;
	}
}
