package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVision
 * Created by Vitaly on 3/6/18.
 */

public class ShowInvestProgramEvent
{
	public UUID programId;

	public String programName;

	public ShowInvestProgramEvent(UUID programId, String programName) {
		this.programId = programId;
		this.programName = programName;
	}
}
