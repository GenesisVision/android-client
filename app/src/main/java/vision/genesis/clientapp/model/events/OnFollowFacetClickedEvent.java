package vision.genesis.clientapp.model.events;

import io.swagger.client.model.AssetFacet;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/12/2019.
 */
public class OnFollowFacetClickedEvent
{
	private final AssetFacet facet;

	public OnFollowFacetClickedEvent(AssetFacet facet) {
		this.facet = facet;
	}

	public AssetFacet getFacet() {
		return facet;
	}
}
