package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ValueChartBar;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/09/2018.
 */
public class OnPortfolioAssetsChangedEvent
{
	private ValueChartBar valueChartBar;

	public OnPortfolioAssetsChangedEvent(ValueChartBar valueChartBar) {
		this.valueChartBar = valueChartBar;
	}

	public ValueChartBar getValueChartBar() {
		return valueChartBar;
	}
}
