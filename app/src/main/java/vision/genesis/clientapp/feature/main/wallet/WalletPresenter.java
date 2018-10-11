package vision.genesis.clientapp.feature.main.wallet;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.WalletSummary;
import io.swagger.client.model.WalletTransaction;
import io.swagger.client.model.WalletTransactionsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.currency.SelectCurrencyFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class WalletPresenter extends MvpPresenter<WalletView> implements SelectCurrencyFragment.OnCurrencyChangedListener, DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public WalletManager walletManager;

	private Subscription baseCurrencySubscription;

	private Subscription balanceSubscription;

	private Subscription transactionsSubscription;

	private CurrencyEnum baseCurrency;

	private int skip = 0;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK);

	private List<WalletTransaction> transactions = new ArrayList<>();

	private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		subscribeToBaseCurrency();
		getTransactions(true);
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null)
			baseCurrencySubscription.unsubscribe();
		if (balanceSubscription != null)
			balanceSubscription.unsubscribe();
		if (transactionsSubscription != null)
			transactionsSubscription.unsubscribe();

		super.onDestroy();
	}

	void onResume() {
		updateBalance();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		updateBalance();
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getTransactions(false);
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getViewState().setBaseCurrency(baseCurrency);
		getViewState().showProgress(true);
		updateBalance();
	}

	private void updateBalance() {
		if (baseCurrency != null) {
			if (balanceSubscription != null)
				balanceSubscription.unsubscribe();
			balanceSubscription = walletManager.getWallet(baseCurrency)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleBalanceUpdateResponse,
							this::handleBalanceUpdateError);
		}
	}

	private void handleBalanceUpdateResponse(WalletSummary response) {
		balanceSubscription.unsubscribe();

		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		getViewState().setBalance(response);
	}

	private void handleBalanceUpdateError(Throwable throwable) {
		balanceSubscription.unsubscribe();

		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getTransactions(boolean forceUpdate) {
		if (forceUpdate) {
			skip = 0;
		}

		if (transactionsSubscription != null)
			transactionsSubscription.unsubscribe();
		transactionsSubscription = walletManager.getTransactions(dateRange, skip, TAKE)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetTransactionsResponse,
						this::handleGetTransactionsError);
	}

	private void handleGetTransactionsResponse(WalletTransactionsViewModel response) {
		transactionsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (skip == 0) {
			transactions.clear();
			sections.clear();
		}

		List<WalletTransaction> newTransactions = response.getTransactions();

		int index = transactions.size();
		for (WalletTransaction newTransaction : newTransactions) {
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
	}

	private void handleGetTransactionsError(Throwable throwable) {
		transactionsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onCurrencyChanged(CurrencyEnum currency) {
		settingsManager.saveBaseCurrency(currency);
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.dateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getTransactions(true);
	}
}
