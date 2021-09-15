package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */
public class SetOrderHistoryCountEvent
{
	private Integer count;

	public SetOrderHistoryCountEvent(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}
}
