package vision.genesis.clientapp.feature.main.search;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView>
{
	private static int TAKE = 30;

	private static int GROUPS_COUNT = 1;

	@Inject
	public Context context;

	@Inject
	public InvestManager investManager;

	private Subscription getProgramsSubscription;

	private Subscription getTournamentProgramsSubscription;

	private List<InvestmentProgram> investmentProgramsList = new ArrayList<>();

	private List<InvestmentProgram> tournamentProgramsList = new ArrayList<>();

	private String searchString = "";

	private int groupsLoaded = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (getProgramsSubscription != null)
			getProgramsSubscription.unsubscribe();

		if (getTournamentProgramsSubscription != null)
			getTournamentProgramsSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	private InvestmentProgramsFilter createFilter() {
		InvestmentProgramsFilter filter = new InvestmentProgramsFilter();
		filter.setSkip(0);
		filter.setTake(TAKE);
		filter.setEquityChartLength(36);
		filter.setName(searchString);
		return filter;
	}

	private void performSearch(String searchString) {
		groupsLoaded = 0;
		this.searchString = searchString;
		getViewState().showProgressBar(true);
		getPrograms();
//		getTournamentPrograms();
	}

	private void getPrograms() {
		if (getProgramsSubscription != null)
			getProgramsSubscription.unsubscribe();

		getProgramsSubscription = investManager.getProgramsList(createFilter())
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

		investmentProgramsList = model.getInvestmentPrograms();
		if (!investmentProgramsList.isEmpty())
			getViewState().showEmptyList(false);

		getViewState().setInvestmentPrograms(investmentProgramsList);
		onLoadingFinishedMaybe();
	}

	private void handleGetProgramsListError(Throwable error) {
		getProgramsSubscription.unsubscribe();
		getTournamentProgramsSubscription.unsubscribe();

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showEmptyList(false);
			getViewState().showNoInternet(true);
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	private void getTournamentPrograms() {
		if (getTournamentProgramsSubscription != null)
			getTournamentProgramsSubscription.unsubscribe();

		InvestmentProgramsFilter filter = createFilter();
//		filter.setRoundNumber(1);

		getTournamentProgramsSubscription = investManager.getProgramsList(filter)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetTournamentProgramsList,
						this::handleGetProgramsListError);
	}

	private void handleGetTournamentProgramsList(InvestmentProgramsViewModel model) {
		getTournamentProgramsSubscription.unsubscribe();

		getViewState().showNoInternet(false);

		groupsLoaded++;

		tournamentProgramsList = model.getInvestmentPrograms();
		if (!tournamentProgramsList.isEmpty())
			getViewState().showEmptyList(false);

		getViewState().setTournamentPrograms(tournamentProgramsList);
		onLoadingFinishedMaybe();
	}

	private void setFavoritesCount() {
//		EventBus.getDefault().post(new SetFavoritesTabCountEvent(investmentProgramsList.size() + tournamentProgramsList.size()));
	}

	private void onLoadingFinishedMaybe() {
		if (groupsLoaded == GROUPS_COUNT) {
			getViewState().showProgressBar(false);

			if (investmentProgramsList.isEmpty() && tournamentProgramsList.isEmpty()) {
				getViewState().showEmptyList(true);
			}
		}
		setFavoritesCount();
	}

	private void removeProgram(UUID programId) {
		for (int i = 0; i < investmentProgramsList.size(); i++) {
			if (investmentProgramsList.get(i).getId().equals(programId)) {
				investmentProgramsList.remove(i);
				break;
			}
		}
		for (int i = 0; i < tournamentProgramsList.size(); i++) {
			if (tournamentProgramsList.get(i).getId().equals(programId)) {
				tournamentProgramsList.remove(i);
				break;
			}
		}

		setFavoritesCount();
	}

	@Subscribe
	public void onEventMainThread(ProgramIsFavoriteChangedEvent event) {
		getViewState().changeProgramIsFavorite(event.programId, event.isFavorite);
	}

	public void onSearchClicked(String text) {
		performSearch(text.trim());
	}
}
