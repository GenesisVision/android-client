package vision.genesis.clientapp.feature.main.trading_account.create;

import com.arellomobile.mvp.MvpView;

import java.util.Map;

import io.swagger.client.model.Broker;
import io.swagger.client.model.NewTradingAccountRequest;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

public interface CreateAccountView extends MvpView
{
	void setRequestObjectToFragments(NewTradingAccountRequest request);

	void showExchangeAccountDeposit(Map<String, Double> minDepositAmountInfo, String currency);

	void showBrokerAccountDeposit(Map<String, Double> minDepositAmountInfo, String currency);

	void showAccountSettings(Broker selectedBroker);

	void showSnackbarMessage(String message);

	void finishActivity();

	void showProgress(boolean show);

	void showNextStep();
}
