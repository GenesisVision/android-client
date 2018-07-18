package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import vision.genesis.clientapp.model.events.OnDashboardProgramsUpdateEvent;
import vision.genesis.clientapp.model.events.OnInvestButtonClickedEvent;
import vision.genesis.clientapp.model.events.ProgramIsFavoriteChangedEvent;

/**
 * GenesisVision
 * Created by Vitaly on 3/13/18.
 */

@InjectViewState
public class DashboardProgramsPresenter extends MvpPresenter<DashboardProgramsView>
{
	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		EventBus.getDefault().register(this);
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
		EventBus.getDefault().post(new OnDashboardProgramsUpdateEvent());
	}

	void onStartInvestingClicked() {
		EventBus.getDefault().post(new OnInvestButtonClickedEvent());
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		EventBus.getDefault().post(new OnDashboardProgramsUpdateEvent());
	}

	@Subscribe
	public void onEventMainThread(ProgramIsFavoriteChangedEvent event) {
		getViewState().changeProgramIsFavorite(event.programId, event.isFavorite);
	}
}
