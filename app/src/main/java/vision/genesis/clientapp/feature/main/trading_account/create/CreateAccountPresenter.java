package vision.genesis.clientapp.feature.main.trading_account.create;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.Broker;
import io.swagger.client.model.BrokerAccountType;
import io.swagger.client.model.NewTradingAccountRequest;
import io.swagger.client.model.TradingAccountCreateResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.model.events.OnAccountBrokerSettingsSelectedEvent;
import vision.genesis.clientapp.model.events.OnBrokerSelectedEvent;
import vision.genesis.clientapp.model.events.OnCreateAccountCreateButtonClickedEvent;
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

	private NewTradingAccountRequest request = new NewTradingAccountRequest();

	private Subscription createAccountSubscription;

	private Broker selectedBroker;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().setRequestObjectToFragments(request);
	}

	@Override
	public void onDestroy() {
		if (createAccountSubscription != null) {
			createAccountSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
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
	public void onEventMainThread(OnAccountBrokerSettingsSelectedEvent event) {
		Double minDepositAmount = 0.0;
		for (BrokerAccountType accountType : selectedBroker.getAccountTypes()) {
			if (accountType.getId().equals(request.getBrokerAccountTypeId())) {
				minDepositAmount = accountType.getMinimumDepositsAmount().get(request.getCurrency().getValue());
				break;
			}
		}
		getViewState().showAccountDeposit(minDepositAmount, request.getCurrency().getValue());
	}

	@Subscribe
	public void onEventMainThread(OnCreateAccountCreateButtonClickedEvent event) {
		sendCreateAccountRequest();
	}
}