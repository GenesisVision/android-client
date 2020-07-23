package vision.genesis.clientapp.feature.main.social;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.ProfileFullViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.events.OnMediaPostClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class SocialMainPresenter extends MvpPresenter<SocialMainView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription profileSubscription;

	private boolean isActive;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getProfileInfo();
	}

	@Override
	public void onDestroy() {
		if (profileSubscription != null) {
			profileSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
		isActive = true;
		updateAll();
	}

	void onPause() {
		isActive = false;
	}

	private void updateAll() {
		getViewState().updateMedia();
		getViewState().updateLive();
	}

	private void getProfileInfo() {
		profileSubscription = profileManager.getProfileFull(true)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		getViewState().showProgress(false);
	}

	private void handleGetProfileError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnMediaPostClickedEvent event) {
		if (isActive) {
			getViewState().openMediaUrl(event.getPost().getUrl());
		}
	}
}
