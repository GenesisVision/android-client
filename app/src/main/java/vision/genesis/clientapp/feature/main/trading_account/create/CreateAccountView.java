package vision.genesis.clientapp.feature.main.trading_account.create;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.Broker;
import io.swagger.client.model.NewTradingAccountRequest;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

public interface CreateAccountView extends MvpView
{
	void setRequestObjectToFragments(NewTradingAccountRequest request);

	void showExchangeAccountDeposit(Double minDepositAmount, String currency);

	void showBrokerAccountDeposit(Double minDepositAmount, String currency);

	void showAccountSettings(Broker selectedBroker);

	void showSnackbarMessage(String message);

	void finishActivity();

	void showProgress(boolean show);

	void showNextStep();
}
