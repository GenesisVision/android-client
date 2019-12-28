package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/12/2019.
 */
public class OnExternalApiKeyConfirmEvent
{
	private final String apiKey;

	private final String apiSecret;

	public OnExternalApiKeyConfirmEvent(String apiKey, String apiSecret) {
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}
}
