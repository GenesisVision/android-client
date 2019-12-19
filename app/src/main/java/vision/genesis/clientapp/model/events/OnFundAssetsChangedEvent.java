package vision.genesis.clientapp.model.events;

import java.util.List;

import io.swagger.client.model.FundAssetPart;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/12/2019.
 */
public class OnFundAssetsChangedEvent
{
	private final List<FundAssetPart> newAssets;

	public OnFundAssetsChangedEvent(List<FundAssetPart> newAssets) {
		this.newAssets = newAssets;
	}

	public List<FundAssetPart> getNewAssets() {
		return newAssets;
	}
}
