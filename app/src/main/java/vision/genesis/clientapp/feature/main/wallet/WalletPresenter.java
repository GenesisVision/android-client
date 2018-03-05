package vision.genesis.clientapp.feature.main.wallet;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.WalletTransaction;
import io.swagger.client.model.WalletTransactionsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.events.ShowDepositWalletActivityEvent;
import vision.genesis.clientapp.model.events.ShowWithdrawWalletActivityEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class WalletPresenter extends MvpPresenter<WalletView>
{
	private static final int TAKE = 10;

	private static final String FIAT_CURRENCY = WalletTransaction.CurrencyEnum.USD.toString();

	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	@Inject
	public RateManager rateManager;

	private Subscription balanceSubscription;

	private Subscription transactionsSubscription;

	private Subscription rateSubscription;

	private int skip = 0;

	private TransactionsFilter filter;

	private double gvtBalance = 0;

	private double rate = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		filter = new TransactionsFilter();
		filter.setSkip(0);
		filter.setTake(TAKE);

		getTransactions(true);
		getViewState().setRefreshing(true);
	}

	@Override
	public void onDestroy() {
		if (balanceSubscription != null)
			balanceSubscription.unsubscribe();
		if (transactionsSubscription != null)
			transactionsSubscription.unsubscribe();
		if (rateSubscription != null)
			rateSubscription.unsubscribe();

		super.onDestroy();
	}

	void onResume() {
		updateBalance();
	}

	void onWithdrawButtonClicked() {
		EventBus.getDefault().post(new ShowWithdrawWalletActivityEvent());
	}

	void onDepositButtonClicked() {
		EventBus.getDefault().post(new ShowDepositWalletActivityEvent());
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getTransactions(true);
	}

	void onLastListItemVisible() {
		getTransactions(false);
	}

	private void updateBalance() {
		getViewState().showBalanceProgress();
		balanceSubscription = walletManager.getBalance()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleBalanceUpdateResponse,
						this::handleBalanceUpdateError);

		updateRate();
	}

	private void handleBalanceUpdateResponse(Double balance) {
		gvtBalance = balance;
		getViewState().setBalance(balance);
		updateFiatBalance();
	}

	private void handleBalanceUpdateError(Throwable error) {
		getViewState().hideBalanceProgress();
	}

	private void updateRate() {
		rateSubscription = rateManager.getRate(WalletTransaction.CurrencyEnum.GVT.toString(), FIAT_CURRENCY)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::getRateSuccessHandler,
						this::getRateErrorHandler);
	}

	private void getRateSuccessHandler(Double rate) {
		rateSubscription.unsubscribe();
		getViewState().hideBalanceProgress();

		this.rate = rate;
		updateFiatBalance();
	}

	private void updateFiatBalance() {
		getViewState().setFiatBalance(rate * gvtBalance);
	}

	private void getRateErrorHandler(Throwable error) {
		rateSubscription.unsubscribe();
		getViewState().hideBalanceProgress();
	}

	private void getTransactions(boolean forceUpdate) {
		if (forceUpdate) {
			skip = 0;
			filter.setSkip(skip);
		}

		transactionsSubscription = walletManager.getTransactions(filter)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetTransactionsResponse,
						this::handleGetTransactionsError);
	}

	private void handleGetTransactionsResponse(WalletTransactionsViewModel model) {
		transactionsSubscription.unsubscribe();

		getViewState().setRefreshing(false);

		List<WalletTransaction> transactions = model.getTransactions();

		if (skip == 0)
			getViewState().setTransactions(transactions);
		else
			getViewState().addTransactions(transactions);

		skip += TAKE;
		filter.setTake(TAKE);
		filter.setSkip(skip);
	}

	private void handleGetTransactionsError(Throwable error) {
		transactionsSubscription.unsubscribe();

		getViewState().setRefreshing(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}
}
