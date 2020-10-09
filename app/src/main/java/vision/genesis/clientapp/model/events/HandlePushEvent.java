package vision.genesis.clientapp.model.events;

import android.os.Bundle;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/10/2020.
 */
public class HandlePushEvent
{
	private final Bundle pushData;

	public HandlePushEvent(Bundle pushData) {
		this.pushData = pushData;
	}

	public Bundle getPushData() {
		return pushData;
	}
}
