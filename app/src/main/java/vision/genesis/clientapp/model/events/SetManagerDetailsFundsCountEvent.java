package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetManagerDetailsFundsCountEvent
{
	private Integer fundsCount;

	public SetManagerDetailsFundsCountEvent(Integer fundsCount) {
		this.fundsCount = fundsCount;
	}

	public Integer getFundsCount() {
		return fundsCount;
	}
}
