package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/07/2019.
 */
public class SetDashboardTradesHistoryCountEvent
{
	private Integer tradesHistoryCount;

	public SetDashboardTradesHistoryCountEvent(Integer tradesHistoryCount) {
		this.tradesHistoryCount = tradesHistoryCount;
	}

	public Integer getTradesHistoryCount() {
		return tradesHistoryCount;
	}
}
