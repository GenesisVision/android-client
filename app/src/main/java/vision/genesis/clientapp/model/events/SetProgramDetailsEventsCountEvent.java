package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetProgramDetailsEventsCountEvent
{
	private Integer eventsCount;

	public SetProgramDetailsEventsCountEvent(Integer eventsCount) {
		this.eventsCount = eventsCount;
	}

	public Integer getEventsCount() {
		return eventsCount;
	}
}
