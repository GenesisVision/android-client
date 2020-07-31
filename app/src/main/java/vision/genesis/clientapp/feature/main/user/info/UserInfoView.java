package vision.genesis.clientapp.feature.main.user.info;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.PublicProfile;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

interface UserInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setUserDetails(PublicProfile managerDetails);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);
}