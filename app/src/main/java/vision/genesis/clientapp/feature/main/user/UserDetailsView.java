package vision.genesis.clientapp.feature.main.user;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.UUID;

import io.swagger.client.model.PublicProfile;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

interface UserDetailsView extends MvpView
{
	void initViewPager(UUID userId, boolean canAddNewPost);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setUserDetails(PublicProfile userDetails);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();

	void showToast(String message);

	void showToolbarButtons(boolean show);

	void showNoInternet(boolean show);

	void showSnackbarMessage(String message);

	void showNoInternetProgress(boolean show);

	void showProgress(boolean show);

	void showFollowProgress(boolean show);

	void setRefreshing(boolean refreshing);

	void setProgramsCount(Integer programsCount);

	void setFundsCount(Integer fundsCount);

	void setFollowsCount(Integer followsCount);
}