package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVision
 * Created by Vitaly on 3/1/18.
 */

public class OnCancelRequestClickedEvent
{
	private UUID requestId;

	public OnCancelRequestClickedEvent(UUID requestId) {
		this.requestId = requestId;
	}

	public UUID getRequestId() {
		return requestId;
	}
}
