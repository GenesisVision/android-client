package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.CreateProgramData;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/07/2018.
 */
public class SetCreateProgramDataEvent
{
	private CreateProgramData createProgramData;

	public SetCreateProgramDataEvent(CreateProgramData createProgramData) {
		this.createProgramData = createProgramData;
	}

	public CreateProgramData getCreateProgramData() {
		return createProgramData;
	}
}
