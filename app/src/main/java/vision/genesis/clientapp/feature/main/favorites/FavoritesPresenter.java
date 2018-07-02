package vision.genesis.clientapp.feature.main.favorites;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.InvestmentProgramsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.FilterSortingOption;
import vision.genesis.clientapp.model.InvestmentProgramExtended;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.ProgramIsFavoriteChangedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */

@InjectViewState
public class FavoritesPresenter extends MvpPresenter<FavoritesView>
{
	private static int TAKE = 100;

	private static int FAVORITE_GROUPS_COUNT = 1;

	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public ProgramsManager programsManager;

	private Subscription userSubscription;

	private Subscription getProgramsSubscription;

	private Subscription getTournamentProgramsSubscription;

	private List<InvestmentProgramExtended> programsToAdd = new ArrayList<>();

	private List<InvestmentProgramExtended> investmentProgramsList = new ArrayList<>();

	private List<InvestmentProgramExtended> tournamentProgramsList = new ArrayList<>();

	private int groupsLoaded = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().setRefreshing(true);
		subscribeToUser();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null)
			userSubscription.unsubscribe();
		if (getProgramsSubscription != null)
			getProgramsSubscription.unsubscribe();

		if (getTournamentProgramsSubscription != null)
			getTournamentProgramsSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onFilterClicked() {
//		EventBus.getDefault().post(new ShowFiltersEvent());
	}

	void onSortingSelected(FilterSortingOption selectedOption) {
//		filter.setSorting(selectedOption.option);
//		investManager.setFilter(filter);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getFavorites();
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		getFavorites();
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated, this::handleUserError);
	}

	private void userUpdated(User user) {
		if (user == null)
			userLoggedOff();
		else
			userLoggedOn();
	}

	private void userLoggedOn() {
		getFavorites();
		getViewState().showUserLoggedOff(false);
	}

	private void userLoggedOff() {
		getViewState().showUserLoggedOff(true);
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

	private InvestmentProgramsFilter createFilter() {
		InvestmentProgramsFilter filter = new InvestmentProgramsFilter();
		filter.setSkip(0);
		filter.setTake(TAKE);
		filter.setEquityChartLength(10);
		return filter;
	}

	private void getFavorites() {
		groupsLoaded = 0;
		getPrograms();
//		getTournamentPrograms();
	}

	private void getPrograms() {
		if (getProgramsSubscription != null)
			getProgramsSubscription.unsubscribe();

		InvestmentProgramsFilter filter = createFilter();
		filter.setShowMyFavorites(true);

		getProgramsSubscription = programsManager.getProgramsList(filter)
				.subscribeOn(Schedulers.computation())
				.map(this::prepareData)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProgramsList,
						this::handleGetProgramsListError);
	}

	private InvestmentProgramsViewModel prepareData(InvestmentProgramsViewModel model) {
		investmentProgramsList = InvestmentProgramExtended.extendInvestmentPrograms(model.getInvestmentPrograms());
		return model;
	}

	private void handleGetProgramsList(InvestmentProgramsViewModel model) {
		getProgramsSubscription.unsubscribe();

		getViewState().showNoInternet(false);
		getViewState().showEmptyList(false);
		groupsLoaded++;

//		investmentProgramsList = programsToAdd;
		if (!investmentProgramsList.isEmpty())
			getViewState().showEmptyList(false);

		getViewState().setInvestmentPrograms(investmentProgramsList);
		onLoadingFinishedMaybe();
	}

	private void handleGetProgramsListError(Throwable error) {
		getProgramsSubscription.unsubscribe();
//		getTournamentProgramsSubscription.unsubscribe();

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
//		filter.setShowMyFavorites(true);
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
//		if (!tournamentProgramsList.isEmpty())
//			getViewState().showEmptyList(false);
//
//		getViewState().setTournamentPrograms(tournamentProgramsList);
//		onLoadingFinishedMaybe();
//	}

	private void setFavoritesCount() {
		getViewState().setFavoritesCount(StringFormatUtil.formatAmount(
				investmentProgramsList.size() + tournamentProgramsList.size(), 0, 0));
	}

	private void onLoadingFinishedMaybe() {
		if (groupsLoaded == FAVORITE_GROUPS_COUNT) {
			getViewState().setRefreshing(false);
			getViewState().showProgressBar(false);

			if (investmentProgramsList.isEmpty() && tournamentProgramsList.isEmpty()) {
				getViewState().showEmptyList(true);
			}
		}
		setFavoritesCount();
	}

	private void removeProgram(UUID programId) {
		for (int i = 0; i < investmentProgramsList.size(); i++) {
			if (investmentProgramsList.get(i).getData().getId().equals(programId)) {
				investmentProgramsList.remove(i);
				break;
			}
		}
		for (int i = 0; i < tournamentProgramsList.size(); i++) {
			if (tournamentProgramsList.get(i).getData().getId().equals(programId)) {
				tournamentProgramsList.remove(i);
				break;
			}
		}

		if (investmentProgramsList.isEmpty() && tournamentProgramsList.isEmpty())
			getViewState().showEmptyList(true);

		setFavoritesCount();
	}

	@Subscribe
	public void onEventMainThread(ProgramIsFavoriteChangedEvent event) {
		if (event.isFavorite)
			getFavorites();
		else {
			removeProgram(event.programId);
			getViewState().removeProgram(event.programId);
		}
	}
}
