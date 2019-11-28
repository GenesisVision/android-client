package vision.genesis.clientapp.feature.main.follows_list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.FollowDetailsList;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/11/2019.
 */

interface FollowsListView extends MvpView
{
	void setFollows(List<FollowDetailsList> follows);

	void addFollows(List<FollowDetailsList> follows);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmptyList(boolean show);

	void showFiltersActivity(ProgramsFilter filter);

	void showFiltersActive(boolean show);

	void changeFollowIsFavorite(UUID programId, boolean isFavorite);

	void showBottomProgress(boolean show);
}
