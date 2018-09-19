package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/09/2018.
 */
public class OnPortfolioAssetsChangedEvent
{
	private Integer index;

	public OnPortfolioAssetsChangedEvent(Integer index) {
		this.index = index;
	}

	public Integer getIndex() {
		return index;
	}
}
