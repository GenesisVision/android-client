package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/11/2021.
 */
public class SetProgramDetailsFinancialStatisticsCountEvent
{
	private Integer count;

	public SetProgramDetailsFinancialStatisticsCountEvent(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

}
