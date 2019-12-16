package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.FundSettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/12/2019.
 */
public class OnFundFeesConfirmEvent
{
	private final FundSettingsModel model;

	public OnFundFeesConfirmEvent(FundSettingsModel model) {
		this.model = model;
	}

	public FundSettingsModel getModel() {
		return model;
	}
}
