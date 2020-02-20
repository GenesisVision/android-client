package vision.genesis.clientapp.model.events;

import java.util.UUID;

import io.swagger.client.model.FundChartStatistic;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/02/2020.
 */
public class OnFundStatisticsUpdatedEvent
{
	private final UUID fundId;

	private final FundChartStatistic statistic;

	private final String baseCurrency;

	public OnFundStatisticsUpdatedEvent(UUID fundId, FundChartStatistic statistic, String baseCurrency) {
		this.fundId = fundId;
		this.statistic = statistic;
		this.baseCurrency = baseCurrency;
	}

	public UUID getFundId() {
		return fundId;
	}

	public FundChartStatistic getStatistic() {
		return statistic;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}
}
