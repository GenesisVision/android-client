package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */
public class OnPublicInfoConfirmButtonClickedEvent
{
	private final String title;

	private final String description;

	private final String logo;

	public OnPublicInfoConfirmButtonClickedEvent(String title, String description, String logo) {
		this.title = title;
		this.description = description;
		this.logo = logo;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getLogo() {
		return logo;
	}
}
