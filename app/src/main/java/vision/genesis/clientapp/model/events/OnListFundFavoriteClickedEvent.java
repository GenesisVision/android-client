package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/10/2018.
 */
public class OnListFundFavoriteClickedEvent
{
	private final UUID fundId;

	private final boolean favorite;

	public OnListFundFavoriteClickedEvent(UUID fundId, boolean favorite) {
		this.fundId = fundId;
		this.favorite = favorite;
	}

	public UUID getFundId() {
		return fundId;
	}

	public boolean isFavorite() {
		return favorite;
	}
}
