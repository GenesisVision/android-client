package vision.genesis.clientapp.managers;

import java.util.List;
import java.util.UUID;

import io.swagger.client.api.UsersApi;
import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.PublicProfile;
import io.swagger.client.model.PublicProfileFollow;
import io.swagger.client.model.UserDetailsListItemsViewModel;
import io.swagger.client.model.UsersFilterSorting;
import io.swagger.client.model.UsersFilterTimeframe;
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

	public Observable<PublicProfileFollow> getFollow(UUID userId) {
		return usersApi.getUserProfileFollowDetails(userId.toString());
	}

	public Observable<UserDetailsListItemsViewModel> getUsers(UsersFilterSorting sorting, UsersFilterTimeframe timeframe, List<String> tags, int skip, int take) {
		return usersApi.getUsersList(sorting, timeframe, tags, skip, take);
	}
}
