package vision.genesis.clientapp.feature.main.trading_account.change_password;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

interface ChangeTradingAccountPasswordView extends MvpView
{
	void clearErrors();

	void setNewPasswordError(String error);

	void setRepeatPasswordError(String error);

	void showMessageDialog(int imageResourceId, String title, String message, boolean mustRead, MessageBottomSheetDialog.OnButtonClickListener listener);

	void showProgressBar(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
