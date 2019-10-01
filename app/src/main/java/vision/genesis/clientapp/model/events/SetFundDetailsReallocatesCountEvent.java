package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2019.
 */
public class SetFundDetailsReallocatesCountEvent
{
	private Integer count;

	public SetFundDetailsReallocatesCountEvent(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}
}
