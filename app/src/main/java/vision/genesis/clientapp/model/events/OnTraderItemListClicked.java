package vision.genesis.clientapp.model.events;

import io.swagger.client.model.InvestmentProgram;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class OnTraderItemListClicked
{
	public InvestmentProgram investmentProgram;

	public OnTraderItemListClicked(InvestmentProgram investmentProgram) {
		this.investmentProgram = investmentProgram;
	}
}
