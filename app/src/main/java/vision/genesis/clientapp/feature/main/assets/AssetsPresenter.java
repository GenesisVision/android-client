package vision.genesis.clientapp.feature.main.assets;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.PlatformStatus;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.events.SetFavoritesTabCountEvent;
import vision.genesis.clientapp.model.events.SetProgramsTabCountEvent;

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
	public InvestManager investManager;

	private Subscription platformStatusSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getPlatformStatus();
	}

	@Override
	public void onDestroy() {
		if (platformStatusSubscription != null)
			platformStatusSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {

	}

	private void getPlatformStatus() {
		platformStatusSubscription = investManager.getPlatformStatus()
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

	@Subscribe
	public void onEventMainThread(SetFavoritesTabCountEvent event) {
		getViewState().setFavoritesTabCount(event.getCount());
	}

	@Subscribe
	public void onEventMainThread(SetProgramsTabCountEvent event) {
		getViewState().setProgramsTabCount(event.getCount());
	}
}
