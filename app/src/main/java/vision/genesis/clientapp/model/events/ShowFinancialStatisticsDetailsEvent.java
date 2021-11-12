package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ProgramPeriodViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/11/2021.
 */
public class ShowFinancialStatisticsDetailsEvent
{
	private ProgramPeriodViewModel period;

	public ShowFinancialStatisticsDetailsEvent(ProgramPeriodViewModel period) {
		this.period = period;
	}

	public ProgramPeriodViewModel getPeriod() {
		return period;
	}
}
