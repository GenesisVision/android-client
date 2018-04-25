package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.TooltipModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/04/2018.
 */
public class ShowTooltipEvent
{
	public TooltipModel tooltipModel;

	public ShowTooltipEvent(TooltipModel tooltipModel) {
		this.tooltipModel = tooltipModel;
	}
}
