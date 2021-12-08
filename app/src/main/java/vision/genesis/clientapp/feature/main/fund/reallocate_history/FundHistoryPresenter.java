package vision.genesis.clientapp.feature.main.fund.reallocate_history;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FilterItemInfo;
import io.swagger.client.model.FundHistoryEventFilterType;
import io.swagger.client.model.FundHistoryEventViewModel;
import io.swagger.client.model.FundHistoryEventViewModelItemsViewModel;
import io.swagger.client.model.PlatformInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.SetFundDetailsReallocatesCountEvent;
import vision.genesis.clientapp.model.events.ShowFundEventDetailsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2019.
 */

@InjectViewState
public class FundHistoryPresenter extends MvpPresenter<FundHistoryView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public FundsManager fundsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private Subscription historySubscription;

	private int skip = 0;

	private UUID fundId;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private List<FundHistoryEventViewModel> historyItems = new ArrayList<>();

	private List<FilterItemInfo> types = new ArrayList<>();

	private FundHistoryEventFilterType type = FundHistoryEventFilterType.ALL;

	private Integer selectedTypePosition = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		getPlatformInfo();

		if (fundId != null) {
			getHistory(true);
		}
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}
		if (historySubscription != null) {
			historySubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setFundId(UUID fundId) {
		this.fundId = fundId;
		if (fundsManager != null) {
			getHistory(true);
		}
	}

	void onShow() {
		getHistory(false);
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getHistory(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getHistory(false);
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

		types = platformInfo.getFilters().getFundsHistoryEvents();
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

	private void getHistory(boolean forceUpdate) {
		if (dateRange != null && fundId != null) {
			if (forceUpdate) {
				skip = 0;
			}

			if (historySubscription != null) {
				historySubscription.unsubscribe();
			}
			historySubscription = fundsManager.getHistory(fundId, dateRange, type, skip, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetHistoryResponse,
							this::handleGetHistoryError);
		}
	}

	private void handleGetHistoryResponse(FundHistoryEventViewModelItemsViewModel response) {
		historySubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			historyItems.clear();
		}

		EventBus.getDefault().post(new SetFundDetailsReallocatesCountEvent(response.getTotal()));

		List<FundHistoryEventViewModel> newHistoryItems = response.getItems();

		historyItems.addAll(newHistoryItems);

		if (skip == 0) {
			getViewState().setHistory(newHistoryItems);
		}
		else {
			getViewState().addHistory(newHistoryItems);
		}

		skip += TAKE;
	}

	private void handleGetHistoryError(Throwable error) {
		historySubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.dateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getHistory(true);
	}

	@Subscribe
	public void onEventMainThread(ShowFundEventDetailsEvent event) {
		getViewState().showEventDetails(event.getEvent());
	}

	void onTypeOptionSelected(Integer position, String text) {
		this.selectedTypePosition = position;
		this.type = FundHistoryEventFilterType.fromValue(types.get(position).getKey());
		getViewState().setType(text, position);

		getViewState().showProgress(true);
		getHistory(true);
	}
}
