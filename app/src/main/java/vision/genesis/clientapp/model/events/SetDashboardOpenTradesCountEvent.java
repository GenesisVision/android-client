package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/07/2019.
 */
public class SetDashboardOpenTradesCountEvent
{
	private Integer openTradesCount;

	public SetDashboardOpenTradesCountEvent(Integer openTradesCount) {
		this.openTradesCount = openTradesCount;
	}

	public Integer getOpenTradesCount() {
		return openTradesCount;
	}
}
