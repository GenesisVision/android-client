package vision.genesis.clientapp.feature.main.notifications.fund;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/10/2018.
 */

interface FundNotificationsSettingsView extends MvpView
{
	void setNewsChecked(Boolean checked);

	void setStructureChecked(Boolean checked);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
