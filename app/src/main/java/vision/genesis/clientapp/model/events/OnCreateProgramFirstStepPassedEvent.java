package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/07/2018.
 */
public class OnCreateProgramFirstStepPassedEvent
{
	private final String title;

	private final String description;

	private String logo;

	public OnCreateProgramFirstStepPassedEvent(String logo, String title, String description) {
		this.logo = logo;
		this.title = title;
		this.description = description;
	}

	public String getLogo() {
		return logo;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
}
