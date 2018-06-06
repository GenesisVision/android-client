package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/06/2018.
 */
public class OnCheckTfaErrorEvent
{
	private String error;

	public OnCheckTfaErrorEvent(String error) {

		this.error = error;
	}

	public String getError() {
		return error;
	}
}
