package vision.genesis.clientapp.model.events;

import io.swagger.client.model.PostTag;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/07/2020.
 */
public class OnPostTagClickedEvent
{
	private final PostTag postTag;

	public OnPostTagClickedEvent(PostTag postTag) {
		this.postTag = postTag;
	}

	public PostTag getPostTag() {
		return postTag;
	}
}
