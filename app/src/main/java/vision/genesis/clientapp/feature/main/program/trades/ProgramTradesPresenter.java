package vision.genesis.clientapp.feature.main.program.trades;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.OrderSignalModel;
import io.swagger.client.model.TradesSignalViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.ExportManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.OnTradeClickedEvent;
import vision.genesis.clientapp.model.events.SetProgramDetailsTradesCountEvent;
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

	@Inject
	public ExportManager exportManager;

	private Subscription tradesSubscription;

	private int skip = 0;

	private UUID programId;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private List<OrderSignalModel> trades = new ArrayList<OrderSignalModel>();

	private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

	private Boolean showSwaps = false;

	private Boolean showTickets = false;

	private String programName = "";

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		if (programId != null) {
			getTrades(true);
		}
	}

	@Override
	public void onDestroy() {
		if (tradesSubscription != null) {
			tradesSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID programId, String programName) {
		this.programId = programId;
		this.programName = programName;
		if (programsManager != null) {
			getTrades(true);
		}
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

			if (tradesSubscription != null) {
				tradesSubscription.unsubscribe();
			}
			tradesSubscription = programsManager.getProgramTrades(programId, dateRange, skip, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetTradesResponse,
							this::handleGetTradesError);
		}
	}

	private void handleGetTradesResponse(TradesSignalViewModel model) {
		tradesSubscription.unsubscribe();
		getViewState().showProgress(false);

		getViewState().setTradesDelay(model.getTradesDelay());

		if (skip == 0) {
			trades.clear();
			sections.clear();
		}

		showSwaps = model.isShowSwaps();
		showTickets = model.isShowSwaps();

		EventBus.getDefault().post(new SetProgramDetailsTradesCountEvent(model.getTotal()));

		List<OrderSignalModel> newTrades = model.getItems();

		int index = trades.size();
		for (OrderSignalModel newTrade : newTrades) {
			String dateString = DateTimeUtil.formatShortDate(newTrade.getDate());
			String lastSectionDate = sections.isEmpty() ? "" : sections.get(sections.size() - 1).getTitle().toString();
			if (!lastSectionDate.equals(dateString)) {
				sections.add(new SimpleSectionedRecyclerViewAdapter.Section(index, dateString));
			}
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

	@Subscribe
	public void onEventMainThread(OnTradeClickedEvent event) {
		getViewState().showTradeDetails(event.getTrade(), showSwaps, showTickets);
	}

	void onExportClicked() {
		if (exportManager != null && dateRange != null && programId != null) {
			exportManager.exportProgramTrades(programId, programName, dateRange);
		}
	}
}
