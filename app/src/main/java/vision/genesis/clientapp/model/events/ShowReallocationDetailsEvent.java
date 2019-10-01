package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ReallocationModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/10/2019.
 */
public class ShowReallocationDetailsEvent
{
	private ReallocationModel reallocation;

	public ShowReallocationDetailsEvent(ReallocationModel reallocation) {
		this.reallocation = reallocation;
	}

	public ReallocationModel getReallocation() {
		return reallocation;
	}
}
