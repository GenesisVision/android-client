package vision.genesis.clientapp.feature.main.wallet.transactions;

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
 * Created by Vitaly on 3/5/18.
 */

@InjectViewState
public class TransactionsPresenter extends MvpPresenter<TransactionsView>
{
	private static final int TAKE = 10;

	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	private Subscription transactionsSubscription;

	private int skip = 0;

	private TransactionsFilter filter = new TransactionsFilter();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		filter.setSkip(0);
		filter.setTake(TAKE);

		getTransactions(true);
		getViewState().setRefreshing(true);
	}

	@Override
	public void onDestroy() {
		if (transactionsSubscription != null)
			transactionsSubscription.unsubscribe();

		super.onDestroy();
	}

	void onShow() {
		getTransactions(true);
	}

	void setType(String type) {
		filter.setType(TransactionsFilter.TypeEnum.fromValue(type));
		if (walletManager != null)
			getTransactions(true);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getTransactions(true);
	}

	void onLastListItemVisible() {
		getTransactions(false);
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
