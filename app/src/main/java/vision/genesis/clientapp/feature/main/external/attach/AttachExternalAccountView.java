package vision.genesis.clientapp.feature.main.external.attach;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.Broker;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/12/2019.
 */

public interface AttachExternalAccountView extends MvpView
{
	void showApiKey(Broker selectedBroker);

	void showNextStep();

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
