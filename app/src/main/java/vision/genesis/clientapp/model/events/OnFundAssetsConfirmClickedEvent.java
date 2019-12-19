package vision.genesis.clientapp.model.events;

import java.util.List;

import io.swagger.client.model.FundAssetPart;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/12/2019.
 */
public class OnFundAssetsConfirmClickedEvent
{
	private final List<FundAssetPart> requestAssets;

	public OnFundAssetsConfirmClickedEvent(List<FundAssetPart> requestAssets) {
		this.requestAssets = requestAssets;
	}

	public List<FundAssetPart> getRequestAssets() {
		return requestAssets;
	}
}
