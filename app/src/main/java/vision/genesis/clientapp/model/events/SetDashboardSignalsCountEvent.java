package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */
public class SetDashboardSignalsCountEvent
{
	private final Integer signalsCount;

	public SetDashboardSignalsCountEvent(Integer signalsCount) {
		this.signalsCount = signalsCount;
	}

	public Integer getSignalsCount() {
		return signalsCount;
	}
}
