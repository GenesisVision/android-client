package vision.genesis.clientapp.feature.main.trading_account.add_demo_funds;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/01/2020.
 */

interface AddDemoFundsView extends MvpView
{
	void setAmount(String amountText);

	void setAddFundsButtonEnabled(boolean enabled);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
