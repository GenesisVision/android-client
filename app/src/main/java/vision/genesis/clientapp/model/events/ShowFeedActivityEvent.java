package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/07/2020.
 */
public class ShowFeedActivityEvent
{
	private final String type;

	public ShowFeedActivityEvent(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
