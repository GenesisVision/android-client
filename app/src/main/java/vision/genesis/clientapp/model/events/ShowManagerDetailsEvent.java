package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.ManagerDetailsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/03/2019.
 */
public class ShowManagerDetailsEvent
{
	private ManagerDetailsModel model;

	public ShowManagerDetailsEvent(ManagerDetailsModel model) {
		this.model = model;
	}

	public ManagerDetailsModel getModel() {
		return model;
	}
}
