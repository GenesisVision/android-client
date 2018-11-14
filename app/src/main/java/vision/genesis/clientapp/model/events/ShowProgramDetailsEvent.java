package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.ProgramDetailsModel;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ShowProgramDetailsEvent
{
	public ProgramDetailsModel programDetailsModel;

	public ShowProgramDetailsEvent(ProgramDetailsModel programDetailsModel) {
		this.programDetailsModel = programDetailsModel;
	}
}
