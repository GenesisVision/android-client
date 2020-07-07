package vision.genesis.clientapp.feature.main.notifications.follow;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/07/2020.
 */

interface FollowNotificationsSettingsView extends MvpView
{
	void setNewsChecked(Boolean checked);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
