package vision.genesis.clientapp.feature.main.copytrading.subscriptions;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.SignalSubscription;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/12/2019.
 */

interface CopytradingSubscriptionsView extends MvpView
{
	void setData(List<SignalSubscription> activeList, List<SignalSubscription> archiveList);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
