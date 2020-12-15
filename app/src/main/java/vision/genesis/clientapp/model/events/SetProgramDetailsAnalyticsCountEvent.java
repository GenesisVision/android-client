package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */
public class SetProgramDetailsAnalyticsCountEvent
{
	private final Integer count;

	public SetProgramDetailsAnalyticsCountEvent(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}
}
