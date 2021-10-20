package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/10/2021.
 */

public class OnCoinFavoriteChangedEvent
{
	private final UUID coinId;

	private final boolean favorite;

	public OnCoinFavoriteChangedEvent(UUID coinId, boolean favorite) {
		this.coinId = coinId;
		this.favorite = favorite;
	}

	public UUID getCoinId() {
		return coinId;
	}

	public boolean isFavorite() {
		return favorite;
	}
}
