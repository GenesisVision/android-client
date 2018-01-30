package vision.genesis.clientapp.model.events;


import vision.genesis.clientapp.model.InvestmentProgram;

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
