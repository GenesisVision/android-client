package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetProgramDetailsOpenPositionsCountEvent
{
	private Integer openPositionsCount;

	public SetProgramDetailsOpenPositionsCountEvent(Integer openPositionsCount) {
		this.openPositionsCount = openPositionsCount;
	}

	public Integer getOpenPositionsCount() {
		return openPositionsCount;
	}
}
