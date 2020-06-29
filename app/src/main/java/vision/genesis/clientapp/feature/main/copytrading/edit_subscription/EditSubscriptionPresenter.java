package vision.genesis.clientapp.feature.main.copytrading.edit_subscription;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AttachToExternalSignalProviderExt;
import io.swagger.client.model.AttachToSignalProvider;
import io.swagger.client.model.Currency;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.model.events.OnSubscriptionSettingsConfirmEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */

@InjectViewState
public class EditSubscriptionPresenter extends MvpPresenter<EditSubscriptionView>
{
	@Inject
	public Context context;

	@Inject
	public FollowsManager followsManager;

	private Subscription updateSubscription;

	private AttachToSignalProvider updatedModel;

	private AttachToExternalSignalProviderExt updatedExtModel;

	private SubscriptionSettingsModel model;

	private UUID followId;

	private UUID tradingAccountId;

	private Boolean isExternal;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (updateSubscription != null) {
			updateSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	public void setData(SubscriptionSettingsModel model, UUID followId, UUID tradingAccountId, Boolean isExternal) {
		this.model = model;
		this.followId = followId;
		this.tradingAccountId = tradingAccountId;
		this.isExternal = isExternal;
		getViewState().initViewPager(model);
	}

	private void updateSubscription() {
		if (followsManager != null && followId != null && updatedModel != null) {
			getViewState().showProgress(true);

			updateSubscription = (isExternal
					? followsManager.updateExternalSubscription(followId, updatedExtModel)
					: followsManager.updateSubscription(followId, updatedModel))
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleUpdateSuccess,
							this::handleUpdateError);
		}
	}

	private void handleUpdateSuccess(Void response) {
		updateSubscription.unsubscribe();
		getViewState().finishActivity();
	}

	private void handleUpdateError(Throwable throwable) {
		updateSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnSubscriptionSettingsConfirmEvent event) {
		this.updatedModel = event.getModel();
		this.updatedModel.setTradingAccountId(tradingAccountId);

		this.updatedExtModel = new AttachToExternalSignalProviderExt();
		updatedExtModel.setFixedCurrency(Currency.fromValue(updatedModel.getFixedCurrency().getValue()));
		updatedExtModel.setFixedVolume(updatedModel.getFixedVolume());
		updatedExtModel.setMode(updatedModel.getMode());
		updatedExtModel.setOpenTolerancePercent(updatedModel.getOpenTolerancePercent());
		updatedExtModel.setPercent(updatedModel.getPercent());
		updatedExtModel.setTradingAccountId(tradingAccountId);

		updateSubscription();
	}
}