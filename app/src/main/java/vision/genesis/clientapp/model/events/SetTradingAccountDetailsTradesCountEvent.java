package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */
public class SetTradingAccountDetailsTradesCountEvent
{
	private Integer tradesCount;

	public SetTradingAccountDetailsTradesCountEvent(Integer tradesCount) {
		this.tradesCount = tradesCount;
	}

	public Integer getTradesCount() {
		return tradesCount;
	}
}
