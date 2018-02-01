package vision.genesis.clientapp.feature.main.profile;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.ProfileFullViewModel;
import ru.terrakok.cicerone.Router;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProfileManager;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public ProfileManager profileManager;

	private Router localRouter;

	private Subscription profileSubscription;

	public ProfilePresenter(Router router) {
		this.localRouter = router;
	}

	void onLogoutClicked() {
		authManager.logout();
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getProfile();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (profileSubscription != null)
			profileSubscription.unsubscribe();
	}

	private void getProfile() {
		profileSubscription = profileManager.getProfileFull()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
	}

	private void handleGetProfileSuccess(ProfileFullViewModel profileModel) {
//		getViewState().updateProfile(profileModel);
	}

	private void handleGetProfileError(Throwable error) {

	}
}
