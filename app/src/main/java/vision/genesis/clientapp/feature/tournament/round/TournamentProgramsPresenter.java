package vision.genesis.clientapp.feature.tournament.round;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.events.ProgramIsFavoriteChangedEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/05/2018.
 */

@InjectViewState
public class TournamentProgramsPresenter extends MvpPresenter<TournamentProgramsView>
{
	@Inject
	public InvestManager investManager;

	private int round;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getPrograms();
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onShow() {
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getPrograms();
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		getPrograms();
	}

	@Subscribe
	public void onEventMainThread(ProgramIsFavoriteChangedEvent event) {
		getViewState().changeProgramIsFavorite(event.programId, event.isFavorite);
	}

	public void setRound(int round) {
		this.round = round;
		getPrograms();
	}

	private void getPrograms() {
		if (investManager != null && round > 0) {

		}
	}
}
