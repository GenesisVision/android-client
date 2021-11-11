package vision.genesis.clientapp.feature.main.trading_account.two_factor;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/11/2021.
 */

public interface SetupAccountTfaView extends MvpView
{
	void showProgress(boolean show);

	void setKey(String sharedKey, String authenticatorUri);

	void setActivateButtonAvailability(boolean enabled);

	void showButtonProgress(boolean show);

	void showSnackbar(String text);

	void onBackPressed();

	void finishActivity();
}
