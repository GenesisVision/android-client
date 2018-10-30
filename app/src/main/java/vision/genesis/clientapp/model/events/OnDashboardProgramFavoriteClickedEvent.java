package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/10/2018.
 */
public class OnDashboardProgramFavoriteClickedEvent
{
	private final UUID programId;

	private final boolean favorite;

	public OnDashboardProgramFavoriteClickedEvent(UUID programId, boolean favorite) {
		this.programId = programId;
		this.favorite = favorite;
	}

	public UUID getProgramId() {
		return programId;
	}

	public boolean isFavorite() {
		return favorite;
	}
}
