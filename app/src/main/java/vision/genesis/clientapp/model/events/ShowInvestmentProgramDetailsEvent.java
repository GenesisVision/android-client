package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.ProgramInfoModel;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ShowInvestmentProgramDetailsEvent
{
	public ProgramInfoModel programInfoModel;

	public ShowInvestmentProgramDetailsEvent(ProgramInfoModel programInfoModel) {
		this.programInfoModel = programInfoModel;
	}
}
