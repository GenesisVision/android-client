package vision.genesis.clientapp.feature.main.program.trades;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.OrderModel;
import io.swagger.client.model.TradesViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 4/1/18.
 */

@InjectViewState
public class ProgramTradesPresenter extends MvpPresenter<ProgramTradesView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public ProgramsManager programsManager;

	private Subscription tradesSubscription;

	private int skip = 0;

	private UUID programId;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK);

	private List<OrderModel> trades = new ArrayList<>();

	private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		if (programId != null)
			getTrades(true);
	}

	@Override
	public void onDestroy() {
		if (tradesSubscription != null)
			tradesSubscription.unsubscribe();

		super.onDestroy();
	}

	void setProgramId(UUID programId) {
		this.programId = programId;
		if (programsManager != null)
			getTrades(true);
	}

	void onShow() {
		getTrades(false);
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getTrades(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getTrades(false);
	}

	private void getTrades(boolean forceUpdate) {
		if (dateRange != null && programId != null) {
			if (forceUpdate) {
				skip = 0;
			}

			if (tradesSubscription != null)
				tradesSubscription.unsubscribe();
			tradesSubscription = programsManager.getProgramTrades(programId, dateRange, skip, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetTradesResponse,
							this::handleGetTradesError);
		}
	}

	private void handleGetTradesResponse(TradesViewModel model) {
		tradesSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			trades.clear();
			sections.clear();
		}

		List<OrderModel> newTrades = model.getTrades();

		int index = trades.size();
		for (OrderModel newTrade : newTrades) {
			String dateString = DateTimeUtil.formatShortDate(newTrade.getDate());
			String lastSectionDate = sections.isEmpty() ? "" : sections.get(sections.size() - 1).getTitle().toString();
			if (!lastSectionDate.equals(dateString))
				sections.add(new SimpleSectionedRecyclerViewAdapter.Section(index, dateString));
			index++;
		}

		trades.addAll(newTrades);

		if (skip == 0) {
			getViewState().setTrades(newTrades, sections);
		}
		else {
			getViewState().addTrades(newTrades, sections);
		}

		skip += TAKE;
	}

	private void handleGetTradesError(Throwable error) {
		tradesSubscription.unsubscribe();
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
		getTrades(true);
	}
}
