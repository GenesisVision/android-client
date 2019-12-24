package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/07/2019.
 */
public class SetCopytradingOpenTradesCountEvent
{
	private Integer openTradesCount;

	public SetCopytradingOpenTradesCountEvent(Integer openTradesCount) {
		this.openTradesCount = openTradesCount;
	}

	public Integer getOpenTradesCount() {
		return openTradesCount;
	}
}
