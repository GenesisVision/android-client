package vision.genesis.clientapp.feature.main.wallet.transactions;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.FilterItemInfo;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.TransactionInternalType;
import io.swagger.client.model.TransactionViewModel;
import io.swagger.client.model.TransactionViewModelItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.TransactionsFilter;
import vision.genesis.clientapp.model.events.SetSpecificWalletTransactionsCountEvent;
import vision.genesis.clientapp.model.events.SetWalletTransactionsCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

@InjectViewState
public class TransactionsPresenter extends MvpPresenter<TransactionsView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private Subscription transactionsSubscription;

	private int skip = 0;

	private TransactionsFilter filter;

	private List<TransactionViewModel> transactions = new ArrayList<TransactionViewModel>();

	private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

	private String location;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private List<FilterItemInfo> types = new ArrayList<>();

	private TransactionInternalType type = TransactionInternalType.ALL;

	private Integer selectedTypePosition = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		getPlatformInfo();
		getTransactions(true);
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}
		if (transactionsSubscription != null) {
			transactionsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(String location, String walletCurrency) {
		this.location = location;
		filter = new TransactionsFilter();
		filter.setWalletCurrency(walletCurrency);
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

	private void getPlatformInfo() {
		if (settingsManager != null) {
			platformInfoSubscription = settingsManager.getPlatformInfo()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPlatformInfoSuccess,
							this::handleGetPlatformInfoError);
		}
	}

	private void handleGetPlatformInfoSuccess(PlatformInfo platformInfo) {
		platformInfoSubscription.unsubscribe();

		types = platformInfo.getFilters().getWalletTransactions();
		ArrayList<String> typeOptions = new ArrayList<>();
		for (FilterItemInfo filterItemInfo : types) {
			typeOptions.add(filterItemInfo.getTitle());
		}
		getViewState().setTypeOptions(typeOptions);
		getViewState().setType(types.get(selectedTypePosition).getTitle(), selectedTypePosition);
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void getTransactions(boolean forceUpdate) {
		if (filter != null && walletManager != null) {
			if (forceUpdate) {
				skip = 0;
				filter.setSkip(skip);
			}

			filter.setDateRange(dateRange);

			if (transactionsSubscription != null) {
				transactionsSubscription.unsubscribe();
			}
			transactionsSubscription = walletManager.getTransactionsInternal(filter, type)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetTransactionsResponse,
							this::handleGetTransactionsError);
		}
	}

	private void handleGetTransactionsResponse(TransactionViewModelItemsViewModel model) {
		transactionsSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			transactions.clear();
			sections.clear();
		}

		if (location.equals(TransactionsFragment.LOCATION_WALLET)) {
			EventBus.getDefault().post(new SetWalletTransactionsCountEvent(model.getTotal()));
		}
		else if (location.equals(TransactionsFragment.LOCATION_SPECIFIC_WALLET)) {
			EventBus.getDefault().post(new SetSpecificWalletTransactionsCountEvent(model.getTotal()));
		}

		List<TransactionViewModel> newTransactions = model.getItems();

		int index = transactions.size();
		for (TransactionViewModel newTransaction : newTransactions) {
			String dateString = DateTimeUtil.formatShortDate(newTransaction.getDate());
			String lastSectionDate = sections.isEmpty() ? "" : sections.get(sections.size() - 1).getTitle().toString();
			if (!lastSectionDate.equals(dateString)) {
				sections.add(new SimpleSectionedRecyclerViewAdapter.Section(index, dateString));
			}
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

	public void onTypeOptionSelected(Integer position, String text) {
		this.selectedTypePosition = position;
		this.type = TransactionInternalType.fromValue(types.get(position).getKey());
		getViewState().setType(text, position);

		getViewState().showProgress(true);
		getTransactions(true);
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.dateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getTransactions(true);
	}
}
