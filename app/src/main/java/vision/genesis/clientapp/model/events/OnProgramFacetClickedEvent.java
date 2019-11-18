package vision.genesis.clientapp.model.events;

import io.swagger.client.model.AssetFacet;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/04/2019.
 */
public class OnProgramFacetClickedEvent
{
	private final AssetFacet facet;

	public OnProgramFacetClickedEvent(AssetFacet facet) {
		this.facet = facet;
	}

	public AssetFacet getFacet() {
		return facet;
	}
}
