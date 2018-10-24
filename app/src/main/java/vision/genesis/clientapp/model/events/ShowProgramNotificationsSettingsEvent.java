package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/10/2018.
 */
public class ShowProgramNotificationsSettingsEvent
{
	private final UUID programId;

	private final String programName;

	public ShowProgramNotificationsSettingsEvent(UUID programId, String programName) {
		this.programId = programId;
		this.programName = programName;
	}

	public UUID getProgramId() {
		return programId;
	}

	public String getProgramName() {
		return programName;
	}
}
