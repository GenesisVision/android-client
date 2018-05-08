package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */
public class SetProgramsTabCountEvent
{
	private Integer count;

	public SetProgramsTabCountEvent(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}
}
