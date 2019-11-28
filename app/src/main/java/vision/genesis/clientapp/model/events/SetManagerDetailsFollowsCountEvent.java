package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/11/2019.
 */
public class SetManagerDetailsFollowsCountEvent
{
	private final Integer followsCount;

	public SetManagerDetailsFollowsCountEvent(Integer followsCount) {
		this.followsCount = followsCount;
	}

	public Integer getFollowsCount() {
		return followsCount;
	}
}
