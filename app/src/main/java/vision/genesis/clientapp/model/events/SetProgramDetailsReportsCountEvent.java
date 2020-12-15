package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */
public class SetProgramDetailsReportsCountEvent
{
	private final Integer total;

	public SetProgramDetailsReportsCountEvent(Integer total) {
		this.total = total;
	}

	public Integer getTotal() {
		return total;
	}
}
