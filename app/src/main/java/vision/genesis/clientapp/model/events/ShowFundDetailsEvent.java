package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.FundDetailsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */
public class ShowFundDetailsEvent
{
	private FundDetailsModel fundDetailsModel;

	public ShowFundDetailsEvent(FundDetailsModel fundDetailsModel) {
		this.fundDetailsModel = fundDetailsModel;
	}

	public FundDetailsModel getFundDetailsModel() {
		return fundDetailsModel;
	}
}
