package vision.genesis.clientapp.feature.splashscreen;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;

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
	public AuthManager authManager;

	private Subscription updateTokenSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		if (BuildConfig.FLAVOR.equals("tournament"))
			getViewState().showTournamentActivity();
		else
			updateToken();
	}

	@Override
	public void onDestroy() {
		if (updateTokenSubscription != null)
			updateTokenSubscription.unsubscribe();

		super.onDestroy();
	}

	private void updateToken() {
		updateTokenSubscription = authManager.updateToken()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::onUpdateTokenSuccess,
						this::onUpdateTokenError);
	}

	private void onUpdateTokenSuccess(String response) {
		showMainActivity();
	}

	private void onUpdateTokenError(Throwable error) {
		showMainActivity();
	}

	private void showMainActivity() {
		if (updateTokenSubscription != null)
			updateTokenSubscription.unsubscribe();
		getViewState().showMainActivity();
	}
}