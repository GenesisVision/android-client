package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */
public class SetTradingAccountDetailsOpenPositionsCountEvent
{
	private Integer openPositionsCount;

	public SetTradingAccountDetailsOpenPositionsCountEvent(Integer openPositionsCount) {
		this.openPositionsCount = openPositionsCount;
	}

	public Integer getOpenPositionsCount() {
		return openPositionsCount;
	}
}
