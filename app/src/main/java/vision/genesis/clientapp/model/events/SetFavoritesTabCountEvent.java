package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */
public class SetFavoritesTabCountEvent
{
	private int count;

	public SetFavoritesTabCountEvent(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}
}
