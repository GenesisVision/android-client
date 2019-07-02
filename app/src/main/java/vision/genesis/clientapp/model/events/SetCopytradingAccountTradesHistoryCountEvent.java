package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/07/2019.
 */
public class SetCopytradingAccountTradesHistoryCountEvent
{
	private Integer tradesHistoryCount;

	public SetCopytradingAccountTradesHistoryCountEvent(Integer tradesHistoryCount) {
		this.tradesHistoryCount = tradesHistoryCount;
	}

	public Integer getTradesHistoryCount() {
		return tradesHistoryCount;
	}
}
