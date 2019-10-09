package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ProgramPeriodViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/10/2019.
 */
public class ShowPeriodDetailsEvent
{
	private ProgramPeriodViewModel period;

	public ShowPeriodDetailsEvent(ProgramPeriodViewModel period) {
		this.period = period;
	}

	public ProgramPeriodViewModel getPeriod() {
		return period;
	}
}
