package vision.genesis.clientapp.feature.tournament.participants;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.ParticipantViewModel;
import io.swagger.client.model.ParticipantsFilter;
import io.swagger.client.model.ParticipantsSummaryViewModel;
import io.swagger.client.model.ParticipantsViewModel;
import ru.terrakok.cicerone.Router;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.Screens;
import vision.genesis.clientapp.managers.TournamentManager;
import vision.genesis.clientapp.model.events.OnParticipantItemListClicked;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class ParticipantsPresenter extends MvpPresenter<ParticipantsView>
{
	private static int TAKE = 5;

	@Inject
	public Context context;

	@Inject
	public TournamentManager tournamentManager;

	@Inject
	public Router router;

	private Subscription filterSubscription;

	private Subscription getParticipantsSubscription;

	private Subscription getParticipantsCountSubscription;

	private List<ParticipantViewModel> participants = new ArrayList<>();

	private int skip = 0;

	private ParticipantsFilter filter;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getParticipantsCount();
		subscribeToFilter();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (filterSubscription != null)
			filterSubscription.unsubscribe();

		if (getParticipantsSubscription != null)
			getParticipantsSubscription.unsubscribe();

		if (getParticipantsCountSubscription != null)
			getParticipantsCountSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);
	}

	void onFilterClicked() {
		router.navigateTo(Screens.TOUR_PARTICIPANTS_FILTERS);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getParticipantsList(true);
	}

	void onLastListItemVisible() {
		getParticipantsList(false);
	}

	private void getParticipantsCount() {
		getParticipantsCountSubscription = tournamentManager.getParticipantsCount()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetParticipantsCountResponse,
						this::handleGetParticipantsCountError);
	}

	private void handleGetParticipantsCountResponse(ParticipantsSummaryViewModel model) {
		getParticipantsCountSubscription.unsubscribe();
		getViewState().setParticipantsCount(model.getParticipantsCount());
	}

	private void handleGetParticipantsCountError(Throwable throwable) {
		getParticipantsCountSubscription.unsubscribe();
	}

	private void subscribeToFilter() {
		filterSubscription = tournamentManager.filterSubject
				.observeOn(Schedulers.newThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::filterUpdatedHandler,
						error -> {
						});
	}

	private void filterUpdatedHandler(ParticipantsFilter partici) {
		filter = partici;
		filter.setSkip(0);
		filter.setTake(TAKE);
		getViewState().setRefreshing(true);
		getParticipantsList(true);
	}

	private void getParticipantsList(boolean forceUpdate) {
		if (forceUpdate) {
			skip = 0;
			filter.setSkip(skip);
		}

		getParticipantsSubscription = tournamentManager.getParticipantsList(filter)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetParticipantsListResponse,
						this::handleGetParticipantsListError);
	}

	private void handleGetParticipantsListResponse(ParticipantsViewModel model) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);

		getParticipantsSubscription.unsubscribe();

		List<ParticipantViewModel> participants = model.getParticipants();

		if (participants.size() == 0) {
			if (skip == 0)
				getViewState().showEmptyList();
			return;
		}

		if (skip == 0) {
			this.participants.clear();
			getViewState().setParticipants(participants);
		}
		else {
			getViewState().addParticipants(participants);
		}
		this.participants.addAll(participants);
		skip += TAKE;
		filter.setTake(TAKE);
		filter.setSkip(skip);
	}

	private void handleGetParticipantsListError(Throwable error) {
		getParticipantsSubscription.unsubscribe();

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		if (ApiErrorResolver.isNetworkError(error)) {
			if (this.participants.size() == 0)
				getViewState().showNoInternet(true);
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Subscribe
	public void onEventMainThread(OnParticipantItemListClicked event) {
		router.navigateTo(Screens.TOUR_PARTICIPANT_DETAILS, event.participant);
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		getParticipantsList(true);
	}
}
