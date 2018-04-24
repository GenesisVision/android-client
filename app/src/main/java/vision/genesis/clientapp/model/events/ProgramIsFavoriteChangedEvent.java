package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/04/2018.
 */
public class ProgramIsFavoriteChangedEvent
{
	public final UUID programId;

	public final boolean isFavorite;

	public ProgramIsFavoriteChangedEvent(UUID programId, boolean isFavorite) {
		this.programId = programId;
		this.isFavorite = isFavorite;
	}
}
