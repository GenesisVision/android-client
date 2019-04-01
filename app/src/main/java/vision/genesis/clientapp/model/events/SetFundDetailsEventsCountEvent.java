package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetFundDetailsEventsCountEvent
{
	private Integer eventsCount;

	public SetFundDetailsEventsCountEvent(Integer eventsCount) {
		this.eventsCount = eventsCount;
	}

	public Integer getEventsCount() {
		return eventsCount;
	}
}
