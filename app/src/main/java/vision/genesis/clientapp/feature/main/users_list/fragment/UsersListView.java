package vision.genesis.clientapp.feature.main.users_list.fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.PublicProfile;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/03/2019.
 */

interface UsersListView extends MvpView
{
	void setUsers(List<PublicProfile> managers);

	void addUsers(List<PublicProfile> managers);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmptyList(boolean show);

	void showFiltersActive(boolean show);

	void showFiltersActivity(ProgramsFilter filter);
}
