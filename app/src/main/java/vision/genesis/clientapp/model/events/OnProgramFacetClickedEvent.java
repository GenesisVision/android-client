package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ProgramFacet;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/04/2019.
 */
public class OnProgramFacetClickedEvent
{
	private final ProgramFacet facet;

	public OnProgramFacetClickedEvent(ProgramFacet facet) {
		this.facet = facet;
	}

	public ProgramFacet getFacet() {
		return facet;
	}
}
