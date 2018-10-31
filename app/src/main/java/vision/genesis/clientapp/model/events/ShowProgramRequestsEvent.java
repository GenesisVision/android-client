package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/10/2018.
 */
public class ShowProgramRequestsEvent
{
	private UUID programId;

	public ShowProgramRequestsEvent(UUID programId) {
		this.programId = programId;
	}

	public UUID getProgramId() {
		return programId;
	}
}
