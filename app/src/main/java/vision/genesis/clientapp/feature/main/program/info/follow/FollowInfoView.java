package vision.genesis.clientapp.feature.main.program.info.follow;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.UUID;

import io.swagger.client.model.FollowDetailsFull;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

interface FollowInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setFollowDetails(FollowDetailsFull followDetails);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void showSubscriptionButtons(boolean show);

	void showSnackbarMessage(String message);

	void showLoginActivity();

	void showCreateCopytradingAccountActivity(SubscriptionSettingsModel model);

	void showSubscriptionSettings(SubscriptionSettingsModel model, boolean isEdit);

	void showUnfollowTradesActivity(UUID programId, String programName);
}