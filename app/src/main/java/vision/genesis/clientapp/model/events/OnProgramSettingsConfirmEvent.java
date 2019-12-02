package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.ProgramSettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/11/2019.
 */
public class OnProgramSettingsConfirmEvent
{
	private final ProgramSettingsModel model;

	public OnProgramSettingsConfirmEvent(ProgramSettingsModel model) {
		this.model = model;
	}

	public ProgramSettingsModel getModel() {
		return model;
	}
}
