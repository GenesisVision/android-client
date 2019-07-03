package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.OpenTradeModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2019.
 */
public class ShowOpenTradeDetailsEvent
{
	private OpenTradeModel model;

	public ShowOpenTradeDetailsEvent(OpenTradeModel model) {
		this.model = model;
	}

	public OpenTradeModel getModel() {
		return model;
	}
}
