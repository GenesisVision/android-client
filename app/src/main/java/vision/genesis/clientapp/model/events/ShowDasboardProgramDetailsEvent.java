package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ProgramInvestingDetailsList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/11/2019.
 */
public class ShowDasboardProgramDetailsEvent
{
	private final ProgramInvestingDetailsList program;

	public ShowDasboardProgramDetailsEvent(ProgramInvestingDetailsList program) {
		this.program = program;
	}

	public ProgramInvestingDetailsList getProgram() {
		return program;
	}
}
