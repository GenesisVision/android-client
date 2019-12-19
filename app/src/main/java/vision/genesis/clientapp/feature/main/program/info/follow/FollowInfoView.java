package vision.genesis.clientapp.feature.main.program.info.follow;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.SignalSubscription;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

interface FollowInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setDetails(ProgramFollowDetailsFull followDetails);

	void setSubscriptions(List<SignalSubscription> subscriptions);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void showSubscriptionButtons(boolean show);

	void showSnackbarMessage(String message);

	void showLoginActivity();

	void showFollowTradesActivity(UUID followId);

	void showUnfollowTradesActivity(UUID followId, UUID tradingAccountId, String followName);
}