package vision.genesis.clientapp.feature.auth.forgot_password;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVision
 * Created by Vitaly on 4/2/18.
 */

interface ForgotPasswordView extends MvpView
{
	void showProgressBar(boolean show);

	void showSnackbarMessage(String message);

	void setEmailError(String error);

	void finishActivity();
}
