package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/03/2018.
 */
public class ShowMessageActivityEvent
{
	public String message;

	public int imageResourceId;

	public ShowMessageActivityEvent(String message, int imageResourceId) {
		this.message = message;
		this.imageResourceId = imageResourceId;
	}
}
