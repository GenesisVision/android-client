package vision.genesis.clientapp.feature.main.notifications;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.NotificationViewModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/10/2018.
 */

interface NotificationsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setRefreshing(boolean refreshing);

	void setNotifications(List<NotificationViewModel> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void addNotifications(List<NotificationViewModel> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void showSnackbarMessage(String message);

	void showProgramDetails(ProgramDetailsModel programDetailsModel);
}
