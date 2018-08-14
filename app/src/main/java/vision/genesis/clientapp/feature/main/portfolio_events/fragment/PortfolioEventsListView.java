package vision.genesis.clientapp.feature.main.portfolio_events.fragment;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.DashboardPortfolioEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/08/2018.
 */

interface PortfolioEventsListView extends MvpView
{
	void setEvents(List<DashboardPortfolioEvent> events);

	void setRefreshing(boolean show);
}
