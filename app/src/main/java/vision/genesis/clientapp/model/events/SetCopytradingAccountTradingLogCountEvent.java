package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/08/2019.
 */
public class SetCopytradingAccountTradingLogCountEvent
{
	private Integer eventsCount;

	public SetCopytradingAccountTradingLogCountEvent(Integer eventsCount) {
		this.eventsCount = eventsCount;
	}

	public Integer getEventsCount() {
		return eventsCount;
	}
}
