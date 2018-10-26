package vision.genesis.clientapp.feature.main.settings.security.change_password;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;

/**
 * GenesisVision
 * Created by Vitaly on 4/2/18.
 */

interface ChangePasswordView extends MvpView
{
	void clearErrors();

	void setOldPasswordError(String error);

	void setNewPasswordError(String error);

	void setRepeatPasswordError(String error);

	void showMessageDialog(int imageResourceId, String title, String message, boolean mustRead, MessageBottomSheetDialog.OnButtonClickListener listener);

	void showProgressBar(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
