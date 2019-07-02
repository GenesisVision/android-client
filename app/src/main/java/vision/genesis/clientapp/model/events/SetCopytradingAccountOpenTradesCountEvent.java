package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/07/2019.
 */
public class SetCopytradingAccountOpenTradesCountEvent
{
	private Integer openTradesCount;

	public SetCopytradingAccountOpenTradesCountEvent(Integer openTradesCount) {
		this.openTradesCount = openTradesCount;
	}

	public Integer getOpenTradesCount() {
		return openTradesCount;
	}
}
