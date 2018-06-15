package vision.genesis.clientapp.feature.splashscreen;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.PlatformStatus;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.NetworkManager;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

@InjectViewState
public class SplashScreenPresenter extends MvpPresenter<SplashScreenView>
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public NetworkManager networkManager;

	private Subscription platformStatusSubscription;

	private Subscription networkAvailabilitySubscription;

	private Subscription serverAvailabilitySubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getPlatformStatus();
	}

	@Override
	public void onDestroy() {
		if (platformStatusSubscription != null)
			platformStatusSubscription.unsubscribe();
		if (networkAvailabilitySubscription != null)
			networkAvailabilitySubscription.unsubscribe();
		if (serverAvailabilitySubscription != null)
			serverAvailabilitySubscription.unsubscribe();

		super.onDestroy();
	}

	void onTryAgainClicked() {
		getViewState().showProgress(true);
		getPlatformStatus();
	}

	private void getPlatformStatus() {
		platformStatusSubscription = settingsManager.getPlatformStatus()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::onPlatformStatusSuccess,
						this::onPlatformStatusError);
	}

	private void onPlatformStatusSuccess(PlatformStatus response) {
		showMainActivity();
	}

	private void onPlatformStatusError(Throwable error) {
		getViewState().showProgress(false);
		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showNetworkError();
			subscribeToNetworkAvailability();
		}
		else {
			getViewState().showServerError();
			subscribeToServerAvailability();
		}
	}

	private void subscribeToNetworkAvailability() {
		if (networkAvailabilitySubscription != null)
			networkAvailabilitySubscription.unsubscribe();
		networkAvailabilitySubscription = networkManager.networkAvailabilitySubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::networkAvailabilityHandler,
						Throwable::printStackTrace);
	}

	private void networkAvailabilityHandler(Boolean available) {
		if (available) {
			networkAvailabilitySubscription.unsubscribe();
			onTryAgainClicked();
		}
	}

	private void subscribeToServerAvailability() {
		if (serverAvailabilitySubscription != null)
			serverAvailabilitySubscription.unsubscribe();
		serverAvailabilitySubscription = NetworkManager.serverAvailabilitySubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::serverAvailabilityHandler,
						Throwable::printStackTrace);
	}

	private void serverAvailabilityHandler(Boolean available) {
		if (available) {
			serverAvailabilitySubscription.unsubscribe();
			onTryAgainClicked();
		}
	}

	private void showMainActivity() {
		if (platformStatusSubscription != null)
			platformStatusSubscription.unsubscribe();
		getViewState().showMainActivity();
	}
}