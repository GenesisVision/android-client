package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/08/2019.
 */
public class SetProgramDetailsPeriodHistoryCountEvent
{
	private Integer periodHistoryCount;

	public SetProgramDetailsPeriodHistoryCountEvent(Integer periodHistoryCount) {
		this.periodHistoryCount = periodHistoryCount;
	}

	public Integer getPeriodHistoryCount() {
		return periodHistoryCount;
	}
}
