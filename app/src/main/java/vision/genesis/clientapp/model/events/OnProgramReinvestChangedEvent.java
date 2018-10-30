package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/10/2018.
 */
public class OnProgramReinvestChangedEvent
{
	private final UUID programId;

	private final Boolean reinvest;

	public OnProgramReinvestChangedEvent(UUID programId, Boolean reinvest) {
		this.programId = programId;
		this.reinvest = reinvest;
	}

	public UUID getProgramId() {
		return programId;
	}

	public Boolean getReinvest() {
		return reinvest;
	}
}
