package vision.genesis.clientapp.feature.main.copytrading.follow_trades;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AttachToSignalProvider;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.model.events.OnSelectSubscriptionTradingAccountConfirmEvent;
import vision.genesis.clientapp.model.events.OnSubscriptionSettingsConfirmEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */

@InjectViewState
public class FollowTradesPresenter extends MvpPresenter<FollowTradesView>
{
	@Inject
	public Context context;

	@Inject
	public FollowsManager followsManager;

	private Subscription attachSubscription;

	private UUID followId;

	private AttachToSignalProvider model;

	private UUID tradingAccountId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (attachSubscription != null) {
			attachSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID followId) {
		this.followId = followId;
		getViewState().initViewPager(followId);
	}

	private void attachToFollow() {
		if (followsManager != null && followId != null && model != null) {
			getViewState().showProgress(true);

			attachSubscription = followsManager.subscribeToFollow(followId, model)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleAttachSuccess,
							this::handleAttachError);
		}
	}

	private void handleAttachSuccess(Void response) {
		attachSubscription.unsubscribe();
		getViewState().finishActivity();
	}

	private void handleAttachError(Throwable throwable) {
		attachSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnSelectSubscriptionTradingAccountConfirmEvent event) {
		this.tradingAccountId = event.getTradingAccountId();

		getViewState().showSettings();
	}

	@Subscribe
	public void onEventMainThread(OnSubscriptionSettingsConfirmEvent event) {
		this.model = event.getModel();
		this.model.setTradingAccountId(tradingAccountId);

		attachToFollow();
	}
}