package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetManagerDetailsProgramsCountEvent
{
	private Integer programsCount;

	public SetManagerDetailsProgramsCountEvent(Integer programsCount) {
		this.programsCount = programsCount;
	}

	public Integer getProgramsCount() {
		return programsCount;
	}
}
