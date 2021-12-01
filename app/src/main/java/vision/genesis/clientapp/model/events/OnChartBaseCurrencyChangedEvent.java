package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/11/2021.
 */
public class OnChartBaseCurrencyChangedEvent
{
	private UUID assetId;

	private String currency;

	public OnChartBaseCurrencyChangedEvent(UUID assetId, String currency) {
		this.assetId = assetId;
		this.currency = currency;
	}

	public UUID getAssetId() {
		return assetId;
	}

	public String getCurrency() {
		return currency;
	}
}
