package vision.genesis.clientapp.model.events;

import io.swagger.client.model.FundFacet;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/04/2019.
 */
public class OnFundFacetClickedEvent
{
	private final FundFacet facet;

	public OnFundFacetClickedEvent(FundFacet facet) {
		this.facet = facet;
	}

	public FundFacet getFacet() {
		return facet;
	}
}
