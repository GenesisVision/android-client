package vision.genesis.clientapp.feature.main.trading_account.create.broker;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.Broker;
import io.swagger.client.model.ExchangeInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

interface SelectBrokerView extends MvpView
{
	void setExchanges(List<ExchangeInfo> exchanges);

	void setBrokers(List<Broker> brokers);

	void selectExchange(ExchangeInfo selectedExchange, int position);

	void selectBroker(Broker selectedBroker, int position);

	void showExchangeInfo(ExchangeInfo exchange);

	void showBrokerInfo(Broker broker);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void setNextButtonEnabled(boolean enabled);
}