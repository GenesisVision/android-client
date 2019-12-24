package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/07/2019.
 */
public class SetCopytradingTradesHistoryCountEvent
{
	private Integer tradesHistoryCount;

	public SetCopytradingTradesHistoryCountEvent(Integer tradesHistoryCount) {
		this.tradesHistoryCount = tradesHistoryCount;
	}

	public Integer getTradesHistoryCount() {
		return tradesHistoryCount;
	}
}
