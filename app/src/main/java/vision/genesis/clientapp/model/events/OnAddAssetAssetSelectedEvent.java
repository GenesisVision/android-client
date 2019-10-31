package vision.genesis.clientapp.model.events;

import io.swagger.client.model.PlatformAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/10/2019.
 */
public class OnAddAssetAssetSelectedEvent
{
	private final PlatformAsset asset;

	public OnAddAssetAssetSelectedEvent(PlatformAsset asset) {
		this.asset = asset;
	}

	public PlatformAsset getAsset() {
		return asset;
	}
}
