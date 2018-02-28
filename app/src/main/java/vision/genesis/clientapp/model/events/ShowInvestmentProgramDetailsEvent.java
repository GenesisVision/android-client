package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ShowInvestmentProgramDetailsEvent
{
	public UUID programId;

	public ShowInvestmentProgramDetailsEvent(UUID programId) {
		this.programId = programId;
	}
}
