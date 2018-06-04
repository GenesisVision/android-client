package vision.genesis.clientapp.feature.two_factor.setup;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.RecoveryCode;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

public interface SetupTfaView extends MvpView
{
	void onBackPressed();

	void setKey(String sharedKey);

	void showNextStep();

	void showProgress(boolean show);

	void showSnackbar(String text);

	void onConfirmSuccess(List<RecoveryCode> codes);
}
