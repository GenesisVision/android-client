package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 31/07/2020.
 */
public class OpenUrlEvent
{
	private final String url;

	public OpenUrlEvent(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
