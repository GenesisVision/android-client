package vision.genesis.clientapp.feature.main.trading_account.create.exchange_settings;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/01/2022.
 */

interface ExchangeSettingsView extends MvpView
{
	void setAccountTypeOptions(ArrayList<String> accountTypeOptions);

	void setCurrencyOptions(ArrayList<String> currencyOptions);

	void setAccountType(String accountType, Integer position);

	void setCurrency(String currency, Integer position);

	void setAccountTypeDescription(String description);

	void setButtonText(String text);

	void showSnackbarMessage(String message);

	void setNextButtonEnabled(boolean enabled);
}