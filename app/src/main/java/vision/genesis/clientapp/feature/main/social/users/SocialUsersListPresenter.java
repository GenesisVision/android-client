package vision.genesis.clientapp.feature.main.social.users;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.UserDetailsList;
import io.swagger.client.model.UserDetailsListItemsViewModel;
import io.swagger.client.model.UsersFilterSorting;
import io.swagger.client.model.UsersFilterTimeframe;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.UsersManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 31/07/2020.
 */

@InjectViewState
public class SocialUsersListPresenter extends MvpPresenter<SocialUsersListView>
{
	private static int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public UsersManager usersManager;

	private Subscription userSubscription;

	private Subscription getUsersSubscription;

	private List<UserDetailsList> usersList = new ArrayList<>();

	private int skip = 0;

	private List<UserDetailsList> usersToAdd = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToUser();
		getViewState().setRefreshing(true);
		getUsersList(true);
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}
		if (getUsersSubscription != null) {
			getUsersSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getUsersList(true);
	}

	void onLastListItemVisible() {
		getViewState().showBottomProgress(true);
		getUsersList(false);
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(user -> userUpdated(), error -> userUpdated());
	}

	private void userUpdated() {
		getUsersList(true);
	}

	private void getUsersList(boolean forceUpdate) {
		if (usersManager != null) {
			if (forceUpdate) {
				skip = 0;
			}

			if (getUsersSubscription != null) {
				getUsersSubscription.unsubscribe();
			}
			getUsersSubscription = usersManager.getUsers(UsersFilterSorting.BYFOLLOWERSDESC, UsersFilterTimeframe.MONTH, null, skip, TAKE)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetUsersList,
							this::handleGetUsersListError);
		}
	}

	private void handleGetUsersList(UserDetailsListItemsViewModel response) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showBottomProgress(false);

		if (getUsersSubscription != null) {
			getUsersSubscription.unsubscribe();
		}

		usersToAdd = response.getItems();

		if (usersToAdd.size() == 0) {
			return;
		}

		if (skip == 0) {
			usersList.clear();
			getViewState().setUsers(usersToAdd);
		}
		else {
			getViewState().addUsers(usersToAdd);
		}
		usersList.addAll(usersToAdd);
		skip += TAKE;
	}

	private void handleGetUsersListError(Throwable throwable) {
		getUsersSubscription.unsubscribe();
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showBottomProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
