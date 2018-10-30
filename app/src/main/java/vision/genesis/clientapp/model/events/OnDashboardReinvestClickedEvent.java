package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/10/2018.
 */
public class OnDashboardReinvestClickedEvent
{
	private final UUID programId;

	private final Boolean isReinvest;

	public OnDashboardReinvestClickedEvent(UUID programId, Boolean isReinvest) {
		this.programId = programId;
		this.isReinvest = isReinvest;
	}

	public UUID getProgramId() {
		return programId;
	}

	public Boolean getReinvest() {
		return isReinvest;
	}
}
