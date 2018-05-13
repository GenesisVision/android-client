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
import vision.genesis.clientapp.managers.InvestManager;

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
	public InvestManager investManager;

	private Subscription platformStatusSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

//		updateToken();
		getPlatformStatus();
	}

	@Override
	public void onDestroy() {
		if (platformStatusSubscription != null)
			platformStatusSubscription.unsubscribe();

		super.onDestroy();
	}

	private void getPlatformStatus() {
		platformStatusSubscription = investManager.getPlatformStatus()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::onPlatformStatusSuccess,
						this::onPlatformStatusError);
	}

	private void onPlatformStatusSuccess(PlatformStatus response) {
		showMainActivity();
	}

	private void onPlatformStatusError(Throwable error) {
		showMainActivity();
	}

	private void showMainActivity() {
		if (platformStatusSubscription != null)
			platformStatusSubscription.unsubscribe();
		getViewState().showMainActivity();
	}
}