package vision.genesis.clientapp.feature.two_factor.disable;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

public interface DisableTfaView extends MvpView
{
	void onBackPressed();

	void showProgress(boolean show);

	void showSnackbar(String text);

	void finishWithSuccess();

	void setDisableButtonAvailability(boolean available);
}
