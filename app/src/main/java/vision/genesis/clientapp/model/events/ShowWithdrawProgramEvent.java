package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVision
 * Created by Vitaly on 3/6/18.
 */

public class ShowWithdrawProgramEvent
{
	public UUID programId;

	public String programName;

	public Double investedTokens;

	public ShowWithdrawProgramEvent(UUID programId, String programName, Double investedTokens) {
		this.programId = programId;
		this.programName = programName;
		this.investedTokens = investedTokens;
	}
}
