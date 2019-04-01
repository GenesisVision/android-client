package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetDashboardFundsCountEvent
{
	private Integer fundsCount;

	public SetDashboardFundsCountEvent(Integer fundsCount) {
		this.fundsCount = fundsCount;
	}

	public Integer getFundsCount() {
		return fundsCount;
	}
}
