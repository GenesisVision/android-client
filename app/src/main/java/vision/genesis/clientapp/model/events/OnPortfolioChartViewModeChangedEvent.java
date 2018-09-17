package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/09/2018.
 */
public class OnPortfolioChartViewModeChangedEvent
{
	private Boolean isViewMode;

	private Float chartBottomY;

	public OnPortfolioChartViewModeChangedEvent(Boolean isViewMode, Float chartBottomY) {
		this.isViewMode = isViewMode;
		this.chartBottomY = chartBottomY;
	}

	public Boolean getViewMode() {
		return isViewMode;
	}

	public Float getChartBottomY() {
		return chartBottomY;
	}
}
