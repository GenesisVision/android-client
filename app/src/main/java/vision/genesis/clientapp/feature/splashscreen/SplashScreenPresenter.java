package vision.genesis.clientapp.feature.splashscreen;

import android.content.Context;
import android.os.Handler;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.PlatformInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.BinanceExchangeInfo;
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
	public TerminalManager terminalManager;

	@Inject
	public NetworkManager networkManager;

	private Subscription platformStatusSubscription;

	private Subscription serverInfoSubscription;

	private Subscription baseCurrencySubscription;

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
		if (platformStatusSubscription != null) {
			platformStatusSubscription.unsubscribe();
		}
		if (serverInfoSubscription != null) {
			serverInfoSubscription.unsubscribe();
		}
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (networkAvailabilitySubscription != null) {
			networkAvailabilitySubscription.unsubscribe();
		}
		if (serverAvailabilitySubscription != null) {
			serverAvailabilitySubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onTryAgainClicked() {
		getViewState().showProgress(true);
		getPlatformStatus();
	}

	private void getPlatformStatus() {
		platformStatusSubscription = settingsManager.getPlatformInfo()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::onPlatformStatusSuccess,
						this::onPlatformStatusError);
	}

	private void onPlatformStatusSuccess(PlatformInfo response) {
		if (platformStatusSubscription != null) {
			platformStatusSubscription.unsubscribe();
		}
		getBinanceServerInfo();
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

	private void getBinanceServerInfo() {
		if (terminalManager != null) {
			if (serverInfoSubscription != null) {
				serverInfoSubscription.unsubscribe();
			}
			serverInfoSubscription = terminalManager.getBinanceServerInfo()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::onGetBinanceServerInfoSuccess,
							this::onGetBinanceServerInfoError);
		}
	}

	private void onGetBinanceServerInfoSuccess(BinanceExchangeInfo response) {
		if (serverInfoSubscription != null) {
			serverInfoSubscription.unsubscribe();
		}

		initBaseCurrency();
	}

	private void onGetBinanceServerInfoError(Throwable error) {
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

	private void initBaseCurrency() {
		if (AuthManager.token.getValue() != null) {
			baseCurrencySubscription = settingsManager.getBaseCurrency()
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> getViewState().showMainActivity(),
							throwable -> getViewState().showMainActivity());
		}
		else {
			getViewState().showMainActivity();
		}
	}

	private void subscribeToNetworkAvailability() {
		if (networkAvailabilitySubscription != null) {
			networkAvailabilitySubscription.unsubscribe();
		}
		networkAvailabilitySubscription = networkManager.networkAvailabilitySubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::networkAvailabilityHandler,
						Throwable::printStackTrace);
	}

	private void networkAvailabilityHandler(Boolean available) {
		if (available) {
			networkAvailabilitySubscription.unsubscribe();
			new Handler().postDelayed(this::onTryAgainClicked, 1000);
		}
	}

	private void subscribeToServerAvailability() {
		if (serverAvailabilitySubscription != null) {
			serverAvailabilitySubscription.unsubscribe();
		}
		serverAvailabilitySubscription = NetworkManager.serverAvailabilitySubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::serverAvailabilityHandler,
						Throwable::printStackTrace);
	}

	private void serverAvailabilityHandler(Boolean available) {
		if (available) {
			serverAvailabilitySubscription.unsubscribe();
//			new Handler().postDelayed(this::onTryAgainClicked, 500);
		}
	}
}