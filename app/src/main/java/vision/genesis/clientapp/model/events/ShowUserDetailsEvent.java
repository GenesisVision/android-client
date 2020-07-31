package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.UserDetailsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/03/2019.
 */
public class ShowUserDetailsEvent
{
	private UserDetailsModel model;

	public ShowUserDetailsEvent(UserDetailsModel model) {
		this.model = model;
	}

	public UserDetailsModel getModel() {
		return model;
	}
}
