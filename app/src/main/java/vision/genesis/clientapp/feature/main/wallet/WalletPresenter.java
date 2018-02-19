package vision.genesis.clientapp.feature.main.wallet;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

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
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class WalletPresenter extends MvpPresenter<WalletView>
{
	private static int TAKE = 2;

	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	private Subscription balanceSubscription;

	private int skip = 0;

	private TransactionsFilter filter;

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
		super.onDestroy();

		if (balanceSubscription != null)
			balanceSubscription.unsubscribe();
	}

	void onResume() {
		updateBalance();
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
	}

	private void handleBalanceUpdateResponse(Double balance) {
		getViewState().hideBalanceProgress();
		getViewState().setBalance(balance);
	}

	private void handleBalanceUpdateError(Throwable error) {
		getViewState().hideBalanceProgress();
	}

	private void getTransactions(boolean forceUpdate) {
		if (forceUpdate) {
			skip = 0;
			filter.setSkip(skip);
		}

		balanceSubscription = walletManager.getMockTransactions(filter)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetTransactionsResponse,
						this::handleGetTransactionsError);
	}

	private void handleGetTransactionsResponse(WalletTransactionsViewModel model) {
		balanceSubscription.unsubscribe();

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
		balanceSubscription.unsubscribe();

		getViewState().setRefreshing(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}
}
