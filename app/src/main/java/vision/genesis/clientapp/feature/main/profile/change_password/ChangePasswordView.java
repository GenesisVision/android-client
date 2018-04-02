package vision.genesis.clientapp.feature.main.profile.change_password;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVision
 * Created by Vitaly on 4/2/18.
 */

interface ChangePasswordView extends MvpView
{
	void clearErrors();

	void setOldPasswordError(String error);

	void setNewPasswordError(String error);

	void setConfirmPasswordError(String error);

	void showProgressBar(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity(int resultCode);
}
