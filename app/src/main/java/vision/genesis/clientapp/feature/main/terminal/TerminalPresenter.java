package vision.genesis.clientapp.feature.main.terminal;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.BrokerTradeServerType;
import io.swagger.client.model.Currency;
import io.swagger.client.model.ExchangeAccountType;
import io.swagger.client.model.ExchangeAsset;
import io.swagger.client.model.ExchangeInfo;
import io.swagger.client.model.ExchangeInfoItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.terminal.place_order.PlaceOrderActivity;
import vision.genesis.clientapp.feature.main.terminal.select_account.SelectAccountBottomSheetFragment;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.BrokersManager;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.CreateAccountModel;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.OnCreateAccountSuccessEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

@InjectViewState
public class TerminalPresenter extends MvpPresenter<TerminalView> implements SelectAccountBottomSheetFragment.OnAccountSelectedListener
{
	@Inject
	public AuthManager authManager;

	@Inject
	public TerminalManager terminalManager;

	@Inject
	public BrokersManager brokersManager;

	private Subscription userSubscription;

	private Subscription getAccountsSubscription;

	private Subscription selectedAccountSubscription;

	private Subscription getBrokersSubscription;

	private String selectedSymbol;

	private ArrayList<ExchangeAsset> accounts = new ArrayList<>();

	private ExchangeAsset selectedAccount;

	private String pendingAction;

	private User user = null;

	private boolean isWaitingForNewAccount = false;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}
		if (getAccountsSubscription != null) {
			getAccountsSubscription.unsubscribe();
		}
		if (selectedAccountSubscription != null) {
			selectedAccountSubscription.unsubscribe();
		}
		if (getBrokersSubscription != null) {
			getBrokersSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	public void onResume() {
		if (user != null && accounts.isEmpty()) {
			getAccounts();
		}
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::handleUserUpdate, error -> this.user = null);
	}

	private void handleUserUpdate(User user) {
		this.user = user;
		if (user != null) {
			getAccounts();
		}
		else {
			if (getAccountsSubscription != null) {
				getAccountsSubscription.unsubscribe();
			}
		}
		getViewState().showAccountGroup(user != null);
	}

	private void getAccounts() {
		if (terminalManager != null && selectedSymbol != null) {
			getAccountsSubscription = terminalManager.getAccountsFor(BrokerTradeServerType.BINANCE, terminalManager.getCurrentMarket())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetAccountsSuccess,
							this::handleGetAccountsError);
		}
	}

	private void handleGetAccountsSuccess(List<ExchangeAsset> response) {
		getAccountsSubscription.unsubscribe();

		this.accounts = new ArrayList<>(response);
		if (accounts.size() == 1) {
			isWaitingForNewAccount = false;
			onAccountSelected(accounts.get(0));
		}
		getViewState().showAccountArrow(accounts.size() > 1);

		subscribeToSelectedAccount();
	}

	private void handleGetAccountsError(Throwable throwable) {
		getAccountsSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void subscribeToSelectedAccount() {
		if (terminalManager != null) {
			if (selectedAccountSubscription != null) {
				selectedAccountSubscription.unsubscribe();
			}
			selectedAccountSubscription = terminalManager.subscribeToSelectedAccount()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleAccountChanged,
							error -> {
							});
		}
	}

	private void handleAccountChanged(ExchangeAsset account) {
		this.selectedAccount = account;
		getViewState().setSelectedAccount(account);

		if (selectedAccount != null) {
			if (pendingAction != null) {
				if (pendingAction.equals(PlaceOrderActivity.OPERATION_TYPE_BUY)) {
					onBuyClicked();
				}
				else if (pendingAction.equals(PlaceOrderActivity.OPERATION_TYPE_SELL)) {
					onSellClicked();
				}
				pendingAction = null;
			}
		}
	}

	void onSymbolChanged(String selectedSymbol) {
		this.selectedSymbol = selectedSymbol;
		getViewState().showProgressBar(false);
		getViewState().setSelectedSymbol(this.selectedSymbol);
	}

	void onAccountClicked() {
		if (accounts != null && accounts.size() > 1) {
			getViewState().showSelectAccount(accounts);
		}
	}

	@Override
	public void onAccountSelected(ExchangeAsset account) {
		if (terminalManager != null) {
			terminalManager.setSelectedAccount(account);
		}
	}

	void onBuyClicked() {
		handleOperationButtonClick(PlaceOrderActivity.OPERATION_TYPE_BUY);
	}

	void onSellClicked() {
		handleOperationButtonClick(PlaceOrderActivity.OPERATION_TYPE_SELL);
	}

	private void handleOperationButtonClick(String operationType) {
		if (user == null) {
			getViewState().showLoginActivity();
		}
		else if (accounts.isEmpty()) {
			showCreateAccount();
		}
		else {
			if (selectedAccount == null) {
				onAccountClicked();
				pendingAction = operationType;
			}
			else {
				getViewState().showPlaceOrderActivity(selectedSymbol, selectedAccount, operationType);
			}
		}
	}

	private void showCreateAccount() {
		if (brokersManager != null) {
			if (getBrokersSubscription != null) {
				getBrokersSubscription.unsubscribe();
			}
			getBrokersSubscription = brokersManager.getExchanges()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetBrokersSuccess,
							this::handleGetBrokersError);
		}
	}

	private void handleGetBrokersSuccess(ExchangeInfoItemsViewModel response) {
		getBrokersSubscription.unsubscribe();
		String requiredAccountTypeName = terminalManager.getCurrentMarket().getValue();
		if (!response.getItems().isEmpty()) {
			brokersLoop:
			for (ExchangeInfo exchange : response.getItems()) {
				for (ExchangeAccountType accountType : exchange.getAccountTypes()) {
					if ((accountType.getType().equals(BrokerTradeServerType.BINANCE))
							&& (accountType.getName().equals(requiredAccountTypeName))) {
						CreateAccountModel model = new CreateAccountModel(
								accountType.getId(),
								exchange.getLogoUrl(),
								Currency.fromValue(accountType.getCurrencies().get(0)),
								1
						);
						getViewState().showCreateAccount(model);
						isWaitingForNewAccount = true;
						break brokersLoop;
					}
				}
			}
		}
	}

	private void handleGetBrokersError(Throwable throwable) {
		getBrokersSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnCreateAccountSuccessEvent event) {
		if (isWaitingForNewAccount) {
			getViewState().showNewAccountProcessingDialog();
		}
	}
}