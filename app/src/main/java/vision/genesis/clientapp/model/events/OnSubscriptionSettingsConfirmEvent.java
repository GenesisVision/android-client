package vision.genesis.clientapp.model.events;

import io.swagger.client.model.AttachToSignalProvider;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */
public class OnSubscriptionSettingsConfirmEvent
{
	private final AttachToSignalProvider model;

	public OnSubscriptionSettingsConfirmEvent(AttachToSignalProvider model) {
		this.model = model;
	}

	public AttachToSignalProvider getModel() {
		return model;
	}
}
