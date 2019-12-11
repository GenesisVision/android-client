package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ProgramUpdate;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */
public class OnProgramSettingsChangedEvent
{
	private final ProgramUpdate model;

	public OnProgramSettingsChangedEvent(ProgramUpdate model) {
		this.model = model;
	}

	public ProgramUpdate getModel() {
		return model;
	}
}
