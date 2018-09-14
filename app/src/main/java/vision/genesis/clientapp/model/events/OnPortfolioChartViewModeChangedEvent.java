package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/09/2018.
 */
public class OnPortfolioChartViewModeChangedEvent
{
	private Boolean isViewMode;

	public OnPortfolioChartViewModeChangedEvent(Boolean isViewMode) {

		this.isViewMode = isViewMode;
	}

	public Boolean getViewMode() {
		return isViewMode;
	}
}
