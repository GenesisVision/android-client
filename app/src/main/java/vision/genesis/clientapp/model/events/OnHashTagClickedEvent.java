package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/07/2020.
 */
public class OnHashTagClickedEvent
{
	private final String hashTag;

	public OnHashTagClickedEvent(String hashTag) {
		this.hashTag = hashTag;
	}

	public String getHashTag() {
		return hashTag;
	}
}
