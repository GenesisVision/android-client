package vision.genesis.clientapp.feature.main.trading_account.change_broker;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;

import javax.inject.Inject;

import io.swagger.client.model.Broker;
import io.swagger.client.model.ChangeBrokerProgramRequest;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.events.OnAccountBrokerSettingsSelectedEvent;
import vision.genesis.clientapp.model.events.OnBrokerChangedEvent;
import vision.genesis.clientapp.model.events.OnBrokerSelectedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

@InjectViewState
public class ChangeBrokerPresenter extends MvpPresenter<ChangeBrokerView>
{
	@Inject
	public AssetsManager assetsManager;

	private ChangeBrokerProgramRequest request = new ChangeBrokerProgramRequest();

	private Subscription changeBrokerSubscription;

	private Broker selectedBroker;

	private TradingAccountDetailsModel model;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (changeBrokerSubscription != null) {
			changeBrokerSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(TradingAccountDetailsModel model) {
		this.model = model;
	}

	private void sendChangeBrokerRequest() {
		getViewState().showProgress(true);

		changeBrokerSubscription = assetsManager.changeBroker(model.getAssetId(), request)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleChangeBrokerSuccess,
						this::handleChangeBrokerError);
	}

	private void handleChangeBrokerSuccess(Void response) {
		changeBrokerSubscription.unsubscribe();

		EventBus.getDefault().post(new OnBrokerChangedEvent(selectedBroker, request.getNewLeverage(), DateTime.now()));
		getViewState().finishActivity();
	}

	private void handleChangeBrokerError(Throwable throwable) {
		changeBrokerSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnBrokerSelectedEvent event) {
		this.selectedBroker = event.getSelectedBroker();
		getViewState().showAccountSettings(event.getSelectedBroker());
	}

	@Subscribe
	public void onEventMainThread(OnAccountBrokerSettingsSelectedEvent event) {
		request.setNewBrokerAccountTypeId(event.getBrokerAccountTypeId());
		request.setNewLeverage(event.getLeverage());

		sendChangeBrokerRequest();
	}
}