package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */
public class SetTradeHistoryCountEvent
{
	private Integer count;

	public SetTradeHistoryCountEvent(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}
}
