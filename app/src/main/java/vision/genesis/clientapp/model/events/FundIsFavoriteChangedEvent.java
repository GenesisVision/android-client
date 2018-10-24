package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */
public class FundIsFavoriteChangedEvent
{
	private final UUID fundId;

	private final Boolean isFavorite;

	public FundIsFavoriteChangedEvent(UUID fundId, Boolean isFavorite) {

		this.fundId = fundId;
		this.isFavorite = isFavorite;
	}

	public UUID getFundId() {
		return fundId;
	}

	public Boolean getFavorite() {
		return isFavorite;
	}
}
