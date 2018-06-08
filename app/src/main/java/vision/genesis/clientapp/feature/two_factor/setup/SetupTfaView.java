package vision.genesis.clientapp.feature.two_factor.setup;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

public interface SetupTfaView extends MvpView
{
	void onBackPressed();

	void setKey(String sharedKey, String authenticatorUri);

	void showNextStep();

	void showProgress(boolean show);

	void showSnackbar(String text);

	void onConfirmSuccess(ArrayList<String> codes);

	void finishActivity();

	void hideKeyboard();
}
