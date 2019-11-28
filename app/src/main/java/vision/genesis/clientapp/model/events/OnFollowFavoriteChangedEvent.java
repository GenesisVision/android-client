package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/11/2019.
 */
public class OnFollowFavoriteChangedEvent
{
	private final UUID followId;

	private final Boolean favorite;

	public OnFollowFavoriteChangedEvent(UUID followId, Boolean favorite) {
		this.followId = followId;
		this.favorite = favorite;
	}

	public UUID getFollowId() {
		return followId;
	}

	public Boolean isFavorite() {
		return favorite;
	}
}
