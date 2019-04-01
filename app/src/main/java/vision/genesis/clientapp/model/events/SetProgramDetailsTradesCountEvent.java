package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetProgramDetailsTradesCountEvent
{
	private Integer tradesCount;

	public SetProgramDetailsTradesCountEvent(Integer tradesCount) {
		this.tradesCount = tradesCount;
	}

	public Integer getTradesCount() {
		return tradesCount;
	}
}
