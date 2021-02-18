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
import vision.genesis.clientapp.feature.main.terminal.select_account.SelectAccountBottomSheetFragment;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

@InjectViewState
public class TerminalPresenter extends MvpPresenter<TerminalView> implements SelectAccountBottomSheetFragment.OnAccountSelectedListener
{
	@Inject
	public TerminalManager terminalManager;

	private Subscription getAccountsSubscription;

	private String selectedSymbol;

	private ArrayList<ExchangeAsset> accounts = new ArrayList<>();

	private ExchangeAsset selectedAccount;

	private int selectedAccountPosition = -1;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getAccounts();
	}

	@Override
	public void onDestroy() {
		if (getAccountsSubscription != null) {
			getAccountsSubscription.unsubscribe();
		}

		super.onDestroy();
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
			onAccountSelected(accounts.get(0), 0);
		}
		getViewState().showAccountArrow(accounts.size() > 1);
	}

	private void handleGetAccountsError(Throwable throwable) {
		getAccountsSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
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
	public void onAccountSelected(ExchangeAsset account, Integer position) {
		this.selectedAccount = account;
		this.selectedAccountPosition = position;
		getViewState().setSelectedAccount(account, selectedAccountPosition);
	}
}
