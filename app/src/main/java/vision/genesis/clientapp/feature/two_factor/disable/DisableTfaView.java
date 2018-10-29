package vision.genesis.clientapp.feature.two_factor.disable;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

public interface DisableTfaView extends MvpView
{
	void setDisableButtonAvailability(boolean available);

	void showMessageDialog(int imageResourceId, String title, String message, boolean mustRead, MessageBottomSheetDialog.OnButtonClickListener listener);

	void onBackPressed();

	void showProgress(boolean show);

	void showSnackbar(String text);

	void finishActivity();
}
