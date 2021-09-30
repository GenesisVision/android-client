package vision.genesis.clientapp.feature.main.terminal;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.BrokerTradeServerType;
import io.swagger.client.model.ExchangeAsset;
import io.swagger.client.model.TradingAccountPermission;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.terminal.place_order.PlaceOrderActivity;
import vision.genesis.clientapp.feature.main.terminal.select_account.SelectAccountBottomSheetFragment;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.User;
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

	private Subscription userSubscription;

	private Subscription getAccountsSubscription;

	private Subscription selectedAccountSubscription;

	private String selectedSymbol;

	private ArrayList<ExchangeAsset> accounts = new ArrayList<>();

	private ExchangeAsset selectedAccount;

	private String pendingAction;

	private User user = null;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

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

		super.onDestroy();
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
			getAccountsSubscription = terminalManager.getAccountsFor(BrokerTradeServerType.BINANCE, TradingAccountPermission.SPOT)
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
		if (user == null) {
			getViewState().showLoginActivity();
		}
		else {
			if (selectedAccount == null) {
				onAccountClicked();
				pendingAction = PlaceOrderActivity.OPERATION_TYPE_BUY;
			}
			else {
				getViewState().showPlaceOrderActivity(selectedSymbol, selectedAccount, PlaceOrderActivity.OPERATION_TYPE_BUY);
			}
		}
	}

	void onSellClicked() {
		if (user == null) {
			getViewState().showLoginActivity();
		}
		else {
			if (selectedAccount == null) {
				onAccountClicked();
				pendingAction = PlaceOrderActivity.OPERATION_TYPE_SELL;
			}
			else {
				getViewState().showPlaceOrderActivity(selectedSymbol, selectedAccount, PlaceOrderActivity.OPERATION_TYPE_SELL);
			}
		}
	}
}