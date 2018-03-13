package vision.genesis.clientapp.feature.main.dashboard.programs;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import vision.genesis.clientapp.model.events.OnDashboardProgramsUpdateEvent;
import vision.genesis.clientapp.model.events.OnInvestButtonClickedEvent;

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
}
