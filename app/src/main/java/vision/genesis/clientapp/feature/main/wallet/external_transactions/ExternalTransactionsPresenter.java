package vision.genesis.clientapp.feature.main.wallet.external_transactions;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.ItemsViewModelTransactionViewModel;
import io.swagger.client.model.TransactionFilter;
import io.swagger.client.model.TransactionViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.TransactionsFilter;
import vision.genesis.clientapp.model.events.OnTransactionCanceledEvent;
import vision.genesis.clientapp.model.events.SetSpecificWalletDepositsWithdrawalsCountEvent;
import vision.genesis.clientapp.model.events.SetWalletDepositsWithdrawalsCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

@InjectViewState
public class ExternalTransactionsPresenter extends MvpPresenter<ExternalTransactionsView>
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	private Subscription transactionsSubscription;

	private int skip = 0;

	private TransactionsFilter filter;

	private List<TransactionViewModel> transactions = new ArrayList<TransactionViewModel>();

	private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

	private String location;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);
		getTransactions(true);
	}

	@Override
	public void onDestroy() {
		if (transactionsSubscription != null)
			transactionsSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(String location, String walletCurrency) {
		this.location = location;
		filter = new TransactionsFilter();
		filter.setWalletCurrency(walletCurrency);
		filter.setType(TransactionFilter.EXTERNALS);
		filter.setSkip(0);
		filter.setTake(TAKE);
		getTransactions(true);
	}

	void onShow() {
		getTransactions(false);
	}


	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getTransactions(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getTransactions(false);
	}

	private void getTransactions(boolean forceUpdate) {
		if (filter != null && walletManager != null) {
			if (forceUpdate) {
				skip = 0;
				filter.setSkip(skip);
			}

			if (transactionsSubscription != null)
				transactionsSubscription.unsubscribe();
			transactionsSubscription = walletManager.getTransactions(filter)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetTransactionsResponse,
							this::handleGetTransactionsError);
		}
	}

	private void handleGetTransactionsResponse(ItemsViewModelTransactionViewModel model) {
		transactionsSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			transactions.clear();
			sections.clear();
		}

		if (location.equals(ExternalTransactionsFragment.LOCATION_WALLET))
			EventBus.getDefault().post(new SetWalletDepositsWithdrawalsCountEvent(model.getTotal()));
		else if (location.equals(ExternalTransactionsFragment.LOCATION_SPECIFIC_WALLET))
			EventBus.getDefault().post(new SetSpecificWalletDepositsWithdrawalsCountEvent(model.getTotal()));

		List<TransactionViewModel> newTransactions = model.getItems();

		int index = transactions.size();
		for (TransactionViewModel newTransaction : newTransactions) {
			String dateString = DateTimeUtil.formatShortDate(newTransaction.getDate());
			String lastSectionDate = sections.isEmpty() ? "" : sections.get(sections.size() - 1).getTitle().toString();
			if (!lastSectionDate.equals(dateString))
				sections.add(new SimpleSectionedRecyclerViewAdapter.Section(index, dateString));
			index++;
		}

		transactions.addAll(newTransactions);

		if (skip == 0) {
			getViewState().setTransactions(newTransactions, sections);
		}
		else {
			getViewState().addTransactions(newTransactions, sections);
		}

		skip += TAKE;
		filter.setTake(TAKE);
		filter.setSkip(skip);
	}

	private void handleGetTransactionsError(Throwable error) {
		transactionsSubscription.unsubscribe();

		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Subscribe
	public void onEventMainThread(OnTransactionCanceledEvent event) {
		getViewState().setStatusCanceled(event.getTransactionId());
	}
}
