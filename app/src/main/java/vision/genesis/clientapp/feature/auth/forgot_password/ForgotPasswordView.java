package vision.genesis.clientapp.feature.auth.forgot_password;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;

/**
 * GenesisVision
 * Created by Vitaly on 4/2/18.
 */

interface ForgotPasswordView extends MvpView
{
	void showProgressBar(boolean show);

	void showSnackbarMessage(String message);

	void setEmailError(String error);

	void setButtonEnabled(boolean enabled);

	void showMessageDialog(int imageResourceId, String title, String message, boolean mustRead, MessageBottomSheetDialog.OnButtonClickListener listener);

	void finishActivity();
}
