package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */
public class SetReferralFriendsCountEvent
{
	private final Integer count;

	public SetReferralFriendsCountEvent(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}
}
