package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetDashboardProgramsCountEvent
{
	private Integer programsCount;

	public SetDashboardProgramsCountEvent(Integer programsCount) {
		this.programsCount = programsCount;
	}

	public Integer getProgramsCount() {
		return programsCount;
	}
}
