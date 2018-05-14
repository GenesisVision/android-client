package vision.genesis.clientapp.feature.main.programs_list;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgram;
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.InvestmentProgramsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.filters_sorting.SortingFiltersButtonsView;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.events.ProgramIsFavoriteChangedEvent;
import vision.genesis.clientapp.model.events.ProgramsListFiltersAppliedEvent;
import vision.genesis.clientapp.model.events.ProgramsListFiltersClearedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class ProgramsListPresenter extends MvpPresenter<ProgramsListView> implements SortingFiltersButtonsView.OnFilterUpdatedListener
{
	private static int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public InvestManager investManager;

	private Subscription getProgramsSubscription;

	private List<InvestmentProgram> investmentProgramsList = new ArrayList<>();

	private int skip = 0;

	private InvestmentProgramsFilter filter;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		createFilter();
		getViewState().setRefreshing(true);
		getProgramsList(true);
	}

	@Override
	public void onDestroy() {
		if (getProgramsSubscription != null)
			getProgramsSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getProgramsList(true);
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		getProgramsList(true);
	}

	void onLastListItemVisible() {
		getProgramsList(false);
	}

	private void createFilter() {
		filter = new InvestmentProgramsFilter();
		filter.setSkip(0);
		filter.setTake(TAKE);
		filter.setEquityChartLength(10);

		getViewState().updateFilter(filter);
	}

	public void onFilterUpdated(InvestmentProgramsFilter investmentsFilter) {
		filter = investmentsFilter;
		getViewState().setRefreshing(true);
		getProgramsList(true);
	}

	private void getProgramsList(boolean forceUpdate) {
		if (forceUpdate) {
			skip = 0;
			filter.setSkip(skip);
		}

		if (getProgramsSubscription != null)
			getProgramsSubscription.unsubscribe();
		getProgramsSubscription = investManager.getProgramsList(filter)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetProgramsList,
						this::handleGetProgramsListError);
	}

	private void handleGetProgramsList(InvestmentProgramsViewModel model) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);
		getViewState().showEmptyList(false);

		getProgramsSubscription.unsubscribe();

		List<InvestmentProgram> programs = model.getInvestmentPrograms();

		getViewState().setProgramsCount(StringFormatUtil.formatAmount(model.getTotal(), 0, 0));

		if (programs.size() == 0) {
			if (skip == 0)
				getViewState().showEmptyList(true);
			return;
		}

		if (skip == 0) {
			investmentProgramsList.clear();
			getViewState().setInvestmentPrograms(programs);
		}
		else {
			getViewState().addInvestmentPrograms(programs);
		}
		investmentProgramsList.addAll(programs);
		skip += TAKE;
		filter.setTake(TAKE);
		filter.setSkip(skip);
	}

	private void handleGetProgramsListError(Throwable error) {
		getProgramsSubscription.unsubscribe();

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		if (ApiErrorResolver.isNetworkError(error)) {
			if (investmentProgramsList.size() == 0) {
				getViewState().showEmptyList(false);
				getViewState().showNoInternet(true);
			}
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Subscribe
	public void onEventMainThread(ProgramsListFiltersAppliedEvent event) {
		getViewState().showFiltersActive(true);
	}

	@Subscribe
	public void onEventMainThread(ProgramsListFiltersClearedEvent event) {
		getViewState().showFiltersActive(false);
	}

	@Subscribe
	public void onEventMainThread(ProgramIsFavoriteChangedEvent event) {
		getViewState().changeProgramIsFavorite(event.programId, event.isFavorite);
	}
}
