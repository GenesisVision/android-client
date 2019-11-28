package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/11/2019.
 */
public class OnListFollowFavoriteClickedEvent
{
	private final UUID followId;

	private final Boolean isFavorite;

	public OnListFollowFavoriteClickedEvent(UUID followId, Boolean isFavorite) {
		this.followId = followId;
		this.isFavorite = isFavorite;
	}

	public UUID getFollowId() {
		return followId;
	}

	public Boolean isFavorite() {
		return isFavorite;
	}
}
