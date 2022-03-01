package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/02/2022.
 */
public class SetPositionsCountEvent
{
	private final int count;

	private final boolean hasIsolated;

	public SetPositionsCountEvent(int count, boolean hasIsolated) {
		this.count = count;
		this.hasIsolated = hasIsolated;
	}

	public int getCount() {
		return count;
	}

	public boolean isHasIsolated() {
		return hasIsolated;
	}
}
