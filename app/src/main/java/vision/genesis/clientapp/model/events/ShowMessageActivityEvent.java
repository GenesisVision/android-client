package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/03/2018.
 */
public class ShowMessageActivityEvent
{
	public String message;

	public int imageResourceId;

	public boolean mustRead;

	public ShowMessageActivityEvent(String message, int imageResourceId, boolean mustRead) {
		this.message = message;
		this.imageResourceId = imageResourceId;
		this.mustRead = mustRead;
	}
}
