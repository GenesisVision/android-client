package vision.genesis.clientapp.feature.main.trading_account.change_broker;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.Broker;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

public interface ChangeBrokerView extends MvpView
{
	void showAccountSettings(Broker selectedBroker);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
