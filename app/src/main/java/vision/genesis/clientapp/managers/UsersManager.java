package vision.genesis.clientapp.managers;

import io.swagger.client.api.UsersApi;
import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.PublicProfile;
import rx.Observable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/11/2019.
 */

public class UsersManager
{
	private UsersApi usersApi;

	public UsersManager(UsersApi usersApi) {
		this.usersApi = usersApi;
	}

	public Observable<PublicProfile> getUser(String userId, ImageQuality imageQuality) {
		return usersApi.getUserProfile(userId, imageQuality);
	}

}
