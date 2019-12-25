package vision.genesis.clientapp.feature.main.assets;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.PlatformInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.events.ShowFundsListEvent;
import vision.genesis.clientapp.model.events.ShowProgramsListEvent;

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

		EventBus.getDefault().register(this);

		getPlatformInfo();
	}

	@Override
	public void onDestroy() {
		if (platformStatusSubscription != null) {
			platformStatusSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	private void getPlatformInfo() {
		platformStatusSubscription = settingsManager.getPlatformInfo()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::onPlatformStatusSuccess,
						this::onPlatformStatusError);
	}

	private void onPlatformStatusSuccess(PlatformInfo response) {
		platformStatusSubscription.unsubscribe();
		getViewState().onPlatformInfoUpdated(response);
	}

	private void onPlatformStatusError(Throwable error) {
		platformStatusSubscription.unsubscribe();
	}

	@Subscribe
	public void onEventMainThread(ShowProgramsListEvent event) {
		getViewState().showPrograms();
	}

	@Subscribe
	public void onEventMainThread(ShowFundsListEvent event) {
		getViewState().showFunds();
	}
}
