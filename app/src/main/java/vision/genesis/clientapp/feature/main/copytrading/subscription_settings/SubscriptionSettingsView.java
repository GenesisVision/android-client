package vision.genesis.clientapp.feature.main.copytrading.subscription_settings;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/06/2019.
 */

interface SubscriptionSettingsView extends MvpView
{
	void setTypeOptions(ArrayList<String> typeOptions);

	void setType(String type, Integer position);

	void setTypeDescription(String typeDescription);

	void setMinDepositAmount(Double minDepositAmount, String currency);

	void setVolumePercentage(String value);

	void setEquivalent(String value);

	void setTolerancePercentage(String value);

	void showByBalanceFields();

	void showPercentageFields();

	void showFixedFields();

	void setButtonEnabled(boolean enabled);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
