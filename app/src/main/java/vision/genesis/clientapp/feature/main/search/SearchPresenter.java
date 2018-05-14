package vision.genesis.clientapp.feature.main.search;

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
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.events.ProgramIsFavoriteChangedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView>
{
	private static int TAKE = 20;

	private static int GROUPS_COUNT = 1;

	@Inject
	public Context context;

	@Inject
	public InvestManager investManager;

	private Subscription getProgramsSubscription;

	private List<InvestmentProgram> investmentProgramsList = new ArrayList<>();

	private List<InvestmentProgram> tournamentProgramsList = new ArrayList<>();

	private int groupsLoaded = 0;

	private int skip = 0;

	private InvestmentProgramsFilter filter;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		createFilter();
	}

	@Override
	public void onDestroy() {
		if (getProgramsSubscription != null)
			getProgramsSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onLastListItemVisible() {
		getPrograms(false);
	}

	private InvestmentProgramsFilter createFilter() {
		filter = new InvestmentProgramsFilter();
		filter.setSkip(0);
		filter.setTake(TAKE);
		filter.setEquityChartLength(10);
		return filter;
	}

	private void performSearch(String searchString) {
		groupsLoaded = 0;
		filter.setName(searchString);
		getViewState().showProgressBar(true);
		getPrograms(true);
//		getTournamentPrograms();
	}

	private void getPrograms(boolean forceUpdate) {
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
		getProgramsSubscription.unsubscribe();

		getViewState().showNoInternet(false);
		getViewState().showEmptyList(false);
		groupsLoaded++;

		List<InvestmentProgram> programs = model.getInvestmentPrograms();

		getViewState().setResultsCount(StringFormatUtil.formatAmount(model.getTotal(), 0, 0));

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

		onLoadingFinishedMaybe();
	}

	private void handleGetProgramsListError(Throwable error) {
		getProgramsSubscription.unsubscribe();

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showEmptyList(false);
			getViewState().showNoInternet(true);
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

//	private void getTournamentPrograms() {
//		if (getTournamentProgramsSubscription != null)
//			getTournamentProgramsSubscription.unsubscribe();
//
//		InvestmentProgramsFilter filter = createFilter();
////		filter.setRoundNumber(1);
//
//		getTournamentProgramsSubscription = investManager.getProgramsList(filter)
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribeOn(Schedulers.io())
//				.subscribe(this::handleGetTournamentProgramsList,
//						this::handleGetProgramsListError);
//	}
//
//	private void handleGetTournamentProgramsList(InvestmentProgramsViewModel model) {
//		getTournamentProgramsSubscription.unsubscribe();
//
//		getViewState().showNoInternet(false);
//
//		groupsLoaded++;
//
//		tournamentProgramsList = model.getInvestmentPrograms();
//
//		if (!tournamentProgramsList.isEmpty())
//			getViewState().showEmptyList(false);
//
//		getViewState().setTournamentPrograms(tournamentProgramsList);
//		onLoadingFinishedMaybe();
//	}

	private void onLoadingFinishedMaybe() {
		if (groupsLoaded == GROUPS_COUNT) {
			getViewState().showProgressBar(false);

			if (investmentProgramsList.isEmpty() && tournamentProgramsList.isEmpty()) {
				getViewState().showEmptyList(true);
			}
		}
	}

	@Subscribe
	public void onEventMainThread(ProgramIsFavoriteChangedEvent event) {
		getViewState().changeProgramIsFavorite(event.programId, event.isFavorite);
	}

	public void onSearchClicked(String text) {
		performSearch(text.trim());
	}
}
