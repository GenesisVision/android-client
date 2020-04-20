package vision.genesis.clientapp.feature.main.copytrading.follow_trades;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetTypeExt;
import io.swagger.client.model.AttachToExternalSignalProviderExt;
import io.swagger.client.model.AttachToSignalProvider;
import io.swagger.client.model.Currency;
import io.swagger.client.model.ProgramFollowDetailsFull;
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

	private AttachToSignalProvider model;

	private AttachToExternalSignalProviderExt extModel;

	private UUID tradingAccountId;

	private ProgramFollowDetailsFull details;

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

	void setData(ProgramFollowDetailsFull details) {
		this.details = details;
		getViewState().initViewPager(details.getId());
	}

	private void attachToFollow() {
		if (followsManager != null && details != null && model != null) {
			getViewState().showProgress(true);

			attachSubscription = (details.getPublicInfo().getTypeExt().equals(AssetTypeExt.EXTERNALSIGNALTRADINGACCOUNT)
					? followsManager.subscribeToExternalFollowWithPrivate(details.getId(), extModel)
					: followsManager.subscribeToFollow(details.getId(), model))
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

		this.extModel = new AttachToExternalSignalProviderExt();
		extModel.setFixedCurrency(Currency.fromValue(model.getFixedCurrency().getValue()));
		extModel.setFixedVolume(model.getFixedVolume());
		extModel.setMode(model.getMode());
		extModel.setOpenTolerancePercent(model.getOpenTolerancePercent());
		extModel.setPercent(model.getPercent());
		extModel.setTradingAccountId(tradingAccountId);

		attachToFollow();
	}
}