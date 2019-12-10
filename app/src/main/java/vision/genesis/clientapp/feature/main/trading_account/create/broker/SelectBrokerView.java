package vision.genesis.clientapp.feature.main.trading_account.create.broker;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.Broker;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

interface SelectBrokerView extends MvpView
{
	void setBrokers(List<Broker> brokers);

	void selectBroker(Broker selectedBroker, int position);

	void showBrokerInfo(Broker broker);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void setNextButtonEnabled(boolean enabled);
}