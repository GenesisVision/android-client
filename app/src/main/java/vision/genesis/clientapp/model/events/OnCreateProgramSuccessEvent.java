package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/12/2019.
 */
public class OnCreateProgramSuccessEvent
{
	private final UUID assetId;

	public OnCreateProgramSuccessEvent(UUID assetId) {
		this.assetId = assetId;
	}

	public UUID getAssetId() {
		return assetId;
	}
}
