package vision.genesis.clientapp.feature.tournament.leaderboard;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.ParticipantViewModel;
import io.swagger.client.model.ParticipantsFilter;
import io.swagger.client.model.ParticipantsViewModel;
import ru.terrakok.cicerone.Router;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.TournamentManager;

/**
 * GenesisVision
 * Created by Vitaly on 2/9/18.
 */

@InjectViewState
public class LeaderboardPresenter extends MvpPresenter<LeaderboardView>
{
	@Inject
	public Context context;

	@Inject
	public TournamentManager tournamentManager;

	@Inject
	public Router router;

	private Subscription getParticipantsSubscription;

	private List<ParticipantViewModel> participants = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getParticipantsList();
	}

	@Override
	public void onDestroy() {
		if (getParticipantsSubscription != null)
			getParticipantsSubscription.unsubscribe();

		super.onDestroy();
	}

	void onBackClicked() {
		router.exit();
	}

	private void getParticipantsList() {
		getViewState().showLoading(true);

		ParticipantsFilter filter = new ParticipantsFilter();
		filter.setSkip(0);
		filter.setTake(10);

		getParticipantsSubscription = tournamentManager.getParticipantsList(filter)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetParticipantsListResponse,
						this::handleGetParticipantsListError);
	}

	private void handleGetParticipantsListResponse(ParticipantsViewModel model) {
		getViewState().showLoading(false);
		getViewState().showCannotLoad(false);

		getParticipantsSubscription.unsubscribe();

		List<ParticipantViewModel> participants = model.getParticipants();


	}

	private void handleGetParticipantsListError(Throwable error) {
		getParticipantsSubscription.unsubscribe();

		getViewState().showLoading(false);
		getViewState().showCannotLoad(true);
	}
}
