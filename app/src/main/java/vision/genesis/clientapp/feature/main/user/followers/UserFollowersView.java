package vision.genesis.clientapp.feature.main.user.followers;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.ProfilePublicShort;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

interface UserFollowersView extends MvpView
{
	void setUsers(List<ProfilePublicShort> mediaPosts);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setRefreshing(boolean refreshing);

	void showSnackbarMessage(String message);
}
