package vision.genesis.clientapp.model.events;

import io.swagger.client.model.CoinsAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2021.
 */
public class OnCoinListBuyButtonClickedEvent
{
	private CoinsAsset coin;

	public OnCoinListBuyButtonClickedEvent(CoinsAsset coin) {

		this.coin = coin;
	}

	public CoinsAsset getCoin() {
		return coin;
	}
}
