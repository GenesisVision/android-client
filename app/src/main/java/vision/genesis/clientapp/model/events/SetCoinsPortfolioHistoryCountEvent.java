package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/10/2021.
 */
public class SetCoinsPortfolioHistoryCountEvent
{
	private final Integer count;

	public SetCoinsPortfolioHistoryCountEvent(int count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}
}
