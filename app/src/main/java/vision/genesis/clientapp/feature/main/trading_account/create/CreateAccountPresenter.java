package vision.genesis.clientapp.feature.main.trading_account.create;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.Broker;
import io.swagger.client.model.BrokerAccountType;
import io.swagger.client.model.BrokersInfo;
import io.swagger.client.model.Currency;
import io.swagger.client.model.ExchangeAccountType;
import io.swagger.client.model.ExchangeInfo;
import io.swagger.client.model.ExchangeInfoItemsViewModel;
import io.swagger.client.model.NewExchangeAccountRequest;
import io.swagger.client.model.NewTradingAccountRequest;
import io.swagger.client.model.TradingAccountCreateResult;
import io.swagger.client.model.TradingAccountMinCreateAmount;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.managers.BrokersManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CreateAccountModel;
import vision.genesis.clientapp.model.events.OnAccountBrokerSettingsSelectedEvent;
import vision.genesis.clientapp.model.events.OnAccountExchangeSettingsSelectedEvent;
import vision.genesis.clientapp.model.events.OnBrokerSelectedEvent;
import vision.genesis.clientapp.model.events.OnCreateAccountCreateButtonClickedEvent;
import vision.genesis.clientapp.model.events.OnCreateAccountSuccessEvent;
import vision.genesis.clientapp.model.events.OnExchangeSelectedEvent;
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

	@Inject
	public SettingsManager settingsManager;

	private NewTradingAccountRequest request = new NewTradingAccountRequest();

	private Subscription platformInfoSubscription;

	private Subscription getBrokersSubscription;

	private Subscription getExchangesSubscription;

	private Subscription createAccountSubscription;

	private ExchangeInfo selectedExchange;

	private Broker selectedBroker;

	private CreateAccountModel model;

	private List<TradingAccountMinCreateAmount> minAmounts;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().setRequestObjectToFragments(request);
//		getPlatformInfo();
		getBrokers();
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}
		if (getBrokersSubscription != null) {
			getBrokersSubscription.unsubscribe();
		}
		if (getExchangesSubscription != null) {
			getExchangesSubscription.unsubscribe();
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

//	private void getPlatformInfo() {
//		if (settingsManager != null) {
//			platformInfoSubscription = settingsManager.getPlatformInfo()
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribeOn(Schedulers.newThread())
//					.subscribe(this::handleGetPlatformInfoSuccess,
//							this::handleGetPlatformInfoError);
//		}
//	}
//
//	private void handleGetPlatformInfoSuccess(PlatformInfo platformInfo) {
//		platformInfoSubscription.unsubscribe();
//
//		minAmounts = platformInfo.getAssetInfo().getTradingAccountInfo().getMinAmounts();
//	}
//
//	private void handleGetPlatformInfoError(Throwable throwable) {
//		platformInfoSubscription.unsubscribe();
//
//		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
//	}

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
					if (accountType.getId().equals(model.getBrokerId())) {
						request.setBrokerAccountTypeId(model.getBrokerId());
						request.setCurrency(model.getCurrency());
						request.setLeverage(model.getLeverage());

						this.selectedBroker = broker;
						showBrokerAccountDepositOrCreateAccount(accountType);
						getViewState().showProgress(false);
						break brokersLoop;
					}
				}
			}
			if (selectedBroker == null) {
				getExchanges();
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

	private void getExchanges() {
		if (getExchangesSubscription != null) {
			getExchangesSubscription.unsubscribe();
		}
		getExchangesSubscription = brokersManager.getExchanges()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::handleGetExchangesSuccess,
						this::handleGetBrokersError);
	}

	private void handleGetExchangesSuccess(ExchangeInfoItemsViewModel response) {
		getExchangesSubscription.unsubscribe();
		if (!response.getItems().isEmpty()) {
			brokersLoop:
			for (ExchangeInfo exchange : response.getItems()) {
				for (ExchangeAccountType accountType : exchange.getAccountTypes()) {
					if (accountType.getId().equals(model.getBrokerId())) {
						request.setBrokerAccountTypeId(model.getBrokerId());
						request.setCurrency(model.getCurrency());
						request.setLeverage(model.getLeverage());

						this.selectedExchange = exchange;
						showExchangeAccountDepositOrCreateAccount(accountType);
						getViewState().showProgress(false);
						break brokersLoop;
					}
				}
			}
		}
	}

	private void sendCreateAccountRequest() {
		getViewState().showProgress(true);

		NewExchangeAccountRequest exchangeRequest = new NewExchangeAccountRequest();
		exchangeRequest.setBrokerAccountTypeId(request.getBrokerAccountTypeId());
		exchangeRequest.setDepositAmount(request.getDepositAmount());
		exchangeRequest.setDepositWalletId(request.getDepositWalletId());

		createAccountSubscription = (selectedExchange != null
				? assetsManager.createExchangeAccount(exchangeRequest)
				: assetsManager.createTradingAccount(request))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleCreateAccountSuccess, this::handleCreateAccountError);
	}

	private void handleCreateAccountSuccess(TradingAccountCreateResult response) {
		createAccountSubscription.unsubscribe();
		if (response.isTwoFactorRequired()) {
			getViewState().showSetup2Fa(response.getId());
		}
		EventBus.getDefault().post(new OnCreateAccountSuccessEvent(response.getId()));
		getViewState().finishActivity();
	}

	private void handleCreateAccountError(Throwable throwable) {
		createAccountSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnExchangeSelectedEvent event) {
		this.selectedExchange = event.getSelectedExchange();
//		request.setBrokerAccountTypeId(event.getSelectedAccountType().getId());
		request.setCurrency(Currency.USDT);

//		showExchangeAccountDepositOrCreateAccount(event.getSelectedAccountType());
		getViewState().showExchangeSettings(event.getSelectedExchange());
	}

	private void showExchangeAccountDepositOrCreateAccount(ExchangeAccountType exchangeAccountType) {
		if (selectedExchange != null && exchangeAccountType != null) {
			if (exchangeAccountType.isIsDepositRequired()) {
				getViewState().showExchangeAccountDeposit(exchangeAccountType.getMinimumDepositsAmount(), request.getCurrency().getValue());
			}
			else {
				sendCreateAccountRequest();
			}
		}
	}

	@Subscribe
	public void onEventMainThread(OnBrokerSelectedEvent event) {
		this.selectedBroker = event.getSelectedBroker();
		getViewState().showBrokerSettings(event.getSelectedBroker());
	}

	@Subscribe
	public void onEventMainThread(OnSelectBrokerNextClickedEvent event) {
		getViewState().showBrokerSettings(selectedBroker);
	}

	@Subscribe
	public void onEventMainThread(OnAccountBrokerSettingsSelectedEvent event) {
		request.setBrokerAccountTypeId(event.getBrokerAccountType().getId());
		request.setCurrency(event.getCurrency());
		request.setLeverage(event.getLeverage());

		showBrokerAccountDepositOrCreateAccount(event.getBrokerAccountType());
	}

	@Subscribe
	public void onEventMainThread(OnAccountExchangeSettingsSelectedEvent event) {
		request.setBrokerAccountTypeId(event.getSelectedAccountType().getId());
		request.setCurrency(event.getCurrency());

		showExchangeAccountDepositOrCreateAccount(event.getSelectedAccountType());
	}

	private void showBrokerAccountDepositOrCreateAccount(BrokerAccountType brokerAccountType) {
		if (selectedBroker != null && brokerAccountType != null) {
			if (brokerAccountType.isIsDepositRequired()) {
				getViewState().showBrokerAccountDeposit(brokerAccountType.getMinimumDepositsAmount(), request.getCurrency().getValue());
			}
			else {
				sendCreateAccountRequest();
			}
		}
	}

	@Subscribe
	public void onEventMainThread(OnCreateAccountCreateButtonClickedEvent event) {
		sendCreateAccountRequest();
	}
}