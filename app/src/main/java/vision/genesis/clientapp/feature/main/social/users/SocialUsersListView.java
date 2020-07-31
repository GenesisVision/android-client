package vision.genesis.clientapp.feature.main.social.users;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.UserDetailsList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/03/2019.
 */

interface SocialUsersListView extends MvpView
{
	void setUsers(List<UserDetailsList> users);

	void addUsers(List<UserDetailsList> users);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showProgressBar(boolean show);

	void showBottomProgress(boolean show);
}
