package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/01/2021.
 */
public class OnEmailVerificationSuccessEvent
{
	private String token;

	public OnEmailVerificationSuccessEvent(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}
