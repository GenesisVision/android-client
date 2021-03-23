package vision.genesis.clientapp.feature.main.program.create;

import com.arellomobile.mvp.MvpView;

import java.util.Map;

import io.swagger.client.model.Broker;
import io.swagger.client.model.Currency;
import vision.genesis.clientapp.model.CreateProgramModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public interface CreateProgramView extends MvpView
{
	void initViewPager(Boolean needBrokerSelect, Boolean needPublicInfo, Boolean needDeposit, CreateProgramModel model);

	void setIsExchangeProgram(Boolean isExchangeProgram);

	void setMinDeposit(Map<String, Double> minDepositInfo, Currency accountCurrency);

	void showAccountSettings(Broker selectedBroker);

	void showPublicInfo();

	void showSettings();

	void showDeposit();

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
