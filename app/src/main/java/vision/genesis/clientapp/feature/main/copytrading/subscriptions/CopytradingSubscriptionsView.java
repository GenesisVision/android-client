package vision.genesis.clientapp.feature.main.copytrading.subscriptions;

import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.SignalSubscription;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/12/2019.
 */

interface CopytradingSubscriptionsView extends MvpView
{
	void setData(List<SignalSubscription> activeList, List<SignalSubscription> archiveList);

	void showEditSubscriptionActivity(SubscriptionSettingsModel model, UUID followId, UUID tradingAccountId, Boolean external);

	void showUnfollowTradesActivity(UUID followId, UUID tradingAccountId, String followName, Boolean isExternal);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
