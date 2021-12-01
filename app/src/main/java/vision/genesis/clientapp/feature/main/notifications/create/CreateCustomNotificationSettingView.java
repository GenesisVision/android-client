package vision.genesis.clientapp.feature.main.notifications.create;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/10/2018.
 */

interface CreateCustomNotificationSettingView extends MvpView
{
	void setTypeOptions(ArrayList<String> typeOptions);

	void setType(String type, Integer position);

	void showProfitInput();

	void showLevelInput();

	void showInvestInput();

	void setCreateButtonEnabled(Boolean enabled);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(Boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
