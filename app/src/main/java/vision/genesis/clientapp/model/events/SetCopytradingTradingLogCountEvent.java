package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/08/2019.
 */
public class SetCopytradingTradingLogCountEvent
{
	private Integer count;

	public SetCopytradingTradingLogCountEvent(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}
}
