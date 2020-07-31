package vision.genesis.clientapp.feature.main.user.followers;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ProfilePublicShort;
import io.swagger.client.model.PublicProfileFollow;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.managers.UsersManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class UserFollowersPresenter extends MvpPresenter<UserFollowersView>
{
	@Inject
	public UsersManager usersManager;

	@Inject
	public SocialManager socialManager;

	private Subscription getUsersSubscription;

	private List<ProfilePublicShort> users = new ArrayList<>();

	private UUID userId;

	private String type;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getUsers();
	}

	@Override
	public void onDestroy() {
		if (getUsersSubscription != null) {
			getUsersSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(UUID userId, String type) {
		this.userId = userId;
		this.type = type;
		getUsers();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getUsers();
	}

	private void getUsers() {
		if (usersManager != null && userId != null) {
			if (getUsersSubscription != null) {
				getUsersSubscription.unsubscribe();
			}

			getUsersSubscription = usersManager.getFollow(userId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetUsersResponse,
							this::handleGetUsersError);
		}
	}

	private void handleGetUsersResponse(PublicProfileFollow response) {
		getUsersSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		users.clear();

		if (type.equals(UserFollowersActivity.TYPE_FOLLOWERS)) {
			users = response.getFollowers();
		}
		else if (type.equals(UserFollowersActivity.TYPE_FOLLOWING)) {
			users = response.getFollowing();
		}

		getViewState().setUsers(users);
	}

	private void handleGetUsersError(Throwable throwable) {
		getUsersSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}
}
