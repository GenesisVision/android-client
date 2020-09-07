package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/09/2020.
 */
public class OnShowEventsCheckedChangedEvent
{
	private final boolean showEvents;

	public OnShowEventsCheckedChangedEvent(boolean showEvents) {
		this.showEvents = showEvents;
	}

	public boolean isShowEvents() {
		return showEvents;
	}
}
