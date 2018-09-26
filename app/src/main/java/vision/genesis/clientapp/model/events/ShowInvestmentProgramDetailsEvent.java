package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.ProgramDetailsModel;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ShowInvestmentProgramDetailsEvent
{
	public ProgramDetailsModel programDetailsModel;

	public ShowInvestmentProgramDetailsEvent(ProgramDetailsModel programDetailsModel) {
		this.programDetailsModel = programDetailsModel;
	}
}
