package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/12/2019.
 */
public class ShowUnfollowTradesEvent
{
	private final UUID followId;

	private final UUID tradingAccountId;

	private final String followName;

	private final Boolean isExternal;

	public ShowUnfollowTradesEvent(UUID followId, UUID tradingAccountId, String followName, Boolean isExternal) {
		this.followId = followId;
		this.tradingAccountId = tradingAccountId;
		this.followName = followName;
		this.isExternal = isExternal;
	}

	public UUID getFollowId() {
		return followId;
	}

	public UUID getTradingAccountId() {
		return tradingAccountId;
	}

	public String getFollowName() {
		return followName;
	}

	public Boolean isExternal() {
		return isExternal;
	}
}
