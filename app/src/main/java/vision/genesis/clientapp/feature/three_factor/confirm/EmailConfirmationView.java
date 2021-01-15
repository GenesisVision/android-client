package vision.genesis.clientapp.feature.three_factor.confirm;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/01/2020.
 */

interface EmailConfirmationView extends MvpView
{
	void setCode(String code);

	void setKeyboardKeysEnabled(boolean enabled);

	void showProgress(boolean show);

	void showToastMessage(String message);

	void showSnackbarMessage(String message);

	void finishActivity();
}
