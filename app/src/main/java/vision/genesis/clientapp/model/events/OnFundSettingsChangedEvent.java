package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ProgramUpdate;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/12/2019.
 */
public class OnFundSettingsChangedEvent
{
	private final ProgramUpdate model;

	public OnFundSettingsChangedEvent(ProgramUpdate model) {
		this.model = model;
	}

	public ProgramUpdate getModel() {
		return model;
	}
}
