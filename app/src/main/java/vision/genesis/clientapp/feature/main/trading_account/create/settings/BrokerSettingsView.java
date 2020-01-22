package vision.genesis.clientapp.feature.main.trading_account.create.settings;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

interface BrokerSettingsView extends MvpView
{
	void setAccountTypeOptions(ArrayList<String> accountTypeOptions);

	void setCurrencyOptions(ArrayList<String> currencyOptions);

	void setLeverageOptions(ArrayList<String> leverageOptions);

	void setAccountType(String accountType, Integer position);

	void setCurrency(String currency, Integer position);

	void setLeverage(String leverage, Integer position);

	void setAccountTypeDescription(String description);

	void setButtonText(String text);

	void showSnackbarMessage(String message);

	void setNextButtonEnabled(boolean enabled);
}