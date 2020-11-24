package vision.genesis.clientapp.feature.main.trading_account.create;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.Broker;
import io.swagger.client.model.BrokerAccountType;
import io.swagger.client.model.BrokersInfo;
import io.swagger.client.model.NewTradingAccountRequest;
import io.swagger.client.model.TradingAccountCreateResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.managers.BrokersManager;
import vision.genesis.clientapp.model.CreateAccountModel;
import vision.genesis.clientapp.model.events.OnAccountBrokerSettingsSelectedEvent;
import vision.genesis.clientapp.model.events.OnBrokerSelectedEvent;
import vision.genesis.clientapp.model.events.OnCreateAccountCreateButtonClickedEvent;
import vision.genesis.clientapp.model.events.OnCreateAccountSuccessEvent;
import vision.genesis.clientapp.model.events.OnSelectBrokerNextClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

@InjectViewState
public class CreateAccountPresenter extends MvpPresenter<CreateAccountView>
{
	@Inject
	public AssetsManager assetsManager;

	@Inject
	public BrokersManager brokersManager;

	private NewTradingAccountRequest request = new NewTradingAccountRequest();

	private Subscription getBrokersSubscription;

	private Subscription createAccountSubscription;

	private Broker selectedBroker;

	private CreateAccountModel model;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().setRequestObjectToFragments(request);
		getBrokers();
	}

	@Override
	public void onDestroy() {
		if (getBrokersSubscription != null) {
			getBrokersSubscription.unsubscribe();
		}
		if (createAccountSubscription != null) {
			createAccountSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(CreateAccountModel model) {
		this.model = model;

		getBrokers();
	}

	private void getBrokers() {
		if (brokersManager != null && model != null) {
			if (getBrokersSubscription != null) {
				getBrokersSubscription.unsubscribe();
			}
			getBrokersSubscription = brokersManager.getAllBrokers()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetBrokersSuccess,
							this::handleGetBrokersError);
		}
	}

	private void handleGetBrokersSuccess(BrokersInfo response) {
		getBrokersSubscription.unsubscribe();
		if (!response.getBrokers().isEmpty()) {
			brokersLoop:
			for (Broker broker : response.getBrokers()) {
				for (BrokerAccountType accountType : broker.getAccountTypes()) {
					if (accountType.getId().equals(model.getBroker().getId())) {
						request.setBrokerAccountTypeId(model.getBroker().getId());
						request.setCurrency(model.getCurrency());
						request.setLeverage(model.getLeverage());

						this.selectedBroker = broker;
						showAccountDepositOrCreateAccount(accountType);
						getViewState().showProgress(false);
						break brokersLoop;
					}
				}
			}
		}
		else {
			getViewState().finishActivity();
		}
	}

	private void handleGetBrokersError(Throwable throwable) {
		getBrokersSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void sendCreateAccountRequest() {
		getViewState().showProgress(true);

		createAccountSubscription = assetsManager.createTradingAccount(request)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleCreateAccountSuccess, this::handleCreateAccountError);
	}

	private void handleCreateAccountSuccess(TradingAccountCreateResult response) {
		createAccountSubscription.unsubscribe();
		//TODO:
//		if (response.isTwoFactorRequired())
		EventBus.getDefault().post(new OnCreateAccountSuccessEvent(response.getId()));
		getViewState().finishActivity();
	}

	private void handleCreateAccountError(Throwable throwable) {
		createAccountSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnBrokerSelectedEvent event) {
		this.selectedBroker = event.getSelectedBroker();
		getViewState().showAccountSettings(event.getSelectedBroker());
	}

	@Subscribe
	public void onEventMainThread(OnSelectBrokerNextClickedEvent event) {
		getViewState().showAccountSettings(selectedBroker);
	}

	@Subscribe
	public void onEventMainThread(OnAccountBrokerSettingsSelectedEvent event) {
		request.setBrokerAccountTypeId(event.getBrokerAccountType().getId());
		request.setCurrency(event.getCurrency());
		request.setLeverage(event.getLeverage());

		showAccountDepositOrCreateAccount(event.getBrokerAccountType());
	}

	private void showAccountDepositOrCreateAccount(BrokerAccountType brokerAccountType) {
		Double minDepositAmount = 0.0;
		for (BrokerAccountType accountType : selectedBroker.getAccountTypes()) {
			if (accountType.getId().equals(request.getBrokerAccountTypeId())) {
				minDepositAmount = accountType.getMinimumDepositsAmount().get(request.getCurrency().getValue());
				break;
			}
		}
		if (brokerAccountType.isIsDepositRequired()) {
			getViewState().showAccountDeposit(minDepositAmount, request.getCurrency().getValue());
		}
		else {
			sendCreateAccountRequest();
		}
	}

	@Subscribe
	public void onEventMainThread(OnCreateAccountCreateButtonClickedEvent event) {
		sendCreateAccountRequest();
	}
}