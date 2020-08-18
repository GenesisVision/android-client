package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/07/2020.
 */
public class OnAddNewPostClickedEvent
{
	private final UUID userId;

	public OnAddNewPostClickedEvent(UUID userId) {
		this.userId = userId;
	}

	public UUID getUserId() {
		return userId;
	}
}
