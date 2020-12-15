package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ProgramPeriodViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */
public class ShowReportDetailsEvent
{
	private final ProgramPeriodViewModel period;

	public ShowReportDetailsEvent(ProgramPeriodViewModel period) {
		this.period = period;
	}

	public ProgramPeriodViewModel getPeriod() {
		return period;
	}
}
