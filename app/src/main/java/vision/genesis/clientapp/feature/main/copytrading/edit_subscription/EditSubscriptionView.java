package vision.genesis.clientapp.feature.main.copytrading.edit_subscription;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.model.SubscriptionSettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */

public interface EditSubscriptionView extends MvpView
{
	void initViewPager(SubscriptionSettingsModel model);

	void showSettings();

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
