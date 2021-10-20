package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/10/2021.
 */
public class SetCoinsPortfolioAssetsCountEvent
{
	private final Integer count;

	public SetCoinsPortfolioAssetsCountEvent(int count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}
}
