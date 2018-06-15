package vision.genesis.clientapp.feature.main.assets;

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

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/05/2018.
 */

@InjectViewState
public class AssetsPresenter extends MvpPresenter<AssetsView>
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	private Subscription platformStatusSubscription;

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

		super.onDestroy();
	}

	private void getPlatformStatus() {
		platformStatusSubscription = settingsManager.getPlatformStatus()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::onPlatformStatusSuccess,
						this::onPlatformStatusError);
	}

	private void onPlatformStatusSuccess(PlatformStatus response) {
		platformStatusSubscription.unsubscribe();
		getViewState().onPlatformStatusUpdated(response);
	}

	private void onPlatformStatusError(Throwable error) {
		platformStatusSubscription.unsubscribe();
	}
}
