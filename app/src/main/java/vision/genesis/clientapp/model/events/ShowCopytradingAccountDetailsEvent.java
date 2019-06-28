package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.CopytradingAccountModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2019.
 */
public class ShowCopytradingAccountDetailsEvent
{
	private CopytradingAccountModel model;

	public ShowCopytradingAccountDetailsEvent(CopytradingAccountModel model) {
		this.model = model;
	}

	public CopytradingAccountModel getModel() {
		return model;
	}
}
