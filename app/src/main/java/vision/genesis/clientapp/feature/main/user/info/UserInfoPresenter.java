package vision.genesis.clientapp.feature.main.user.info;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.PublicProfile;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.UsersManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

@InjectViewState
public class UserInfoPresenter extends MvpPresenter<UserInfoView>
{
	@Inject
	public AuthManager authManager;

	@Inject
	public UsersManager usersManager;

	private Subscription userDetailsSubscription;

	private UUID userId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getUserDetails();
	}

	@Override
	public void onDestroy() {
		if (userDetailsSubscription != null) {
			userDetailsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setUserId(UUID userId) {
		this.userId = userId;
		getUserDetails();
	}

	void onShow() {
		getUserDetails();
	}

	private void getUserDetails() {
		if (userId != null && usersManager != null) {
			if (userDetailsSubscription != null) {
				userDetailsSubscription.unsubscribe();
			}
			userDetailsSubscription = usersManager.getUser(userId.toString(), ImageQuality.HIGH)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleUserDetailsSuccess,
							this::handleUserDetailsError);
		}
	}

	private void handleUserDetailsSuccess(PublicProfile managerDetails) {
		userDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		getViewState().setUserDetails(managerDetails);
	}

	private void handleUserDetailsError(Throwable throwable) {
		userDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
	}
}
