package vision.genesis.clientapp.feature.main.trading_account.create.settings;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.Broker;
import io.swagger.client.model.BrokerAccountType;
import io.swagger.client.model.Currency;
import io.swagger.client.model.NewTradingAccountRequest;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

@InjectViewState
public class CreateAccountSettingsPresenter extends MvpPresenter<CreateAccountSettingsView>
{
	@Inject
	public Context context;

	private NewTradingAccountRequest request;

	private Broker broker;

	private BrokerAccountType selectedAccountType;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	void setRequest(NewTradingAccountRequest request) {
		this.request = request;
	}

	void setBroker(Broker broker) {
		this.broker = broker;

		if (request != null) {
			request.setBrokerAccountTypeId(null);
			request.setCurrency(null);
			request.setLeverage(null);
		}

		ArrayList<String> accountTypeOptions = new ArrayList<>();
		for (BrokerAccountType accountType : broker.getAccountTypes()) {
			accountTypeOptions.add(accountType.getName());
		}
		getViewState().setAccountTypeOptions(accountTypeOptions);
		onAccountTypeOptionSelected(0, accountTypeOptions.get(0));
	}

	private void updateNextButtonAvailability() {
		getViewState().setNextButtonEnabled(request != null
				&& request.getBrokerAccountTypeId() != null
				&& request.getCurrency() != null
				&& request.getLeverage() != null);
	}

	void onAccountTypeOptionSelected(Integer position, String text) {
		selectedAccountType = broker.getAccountTypes().get(position);
		if (request != null) {
			request.setBrokerAccountTypeId(selectedAccountType.getId());
			request.setCurrency(null);
			request.setLeverage(null);

			getViewState().setAccountType(text, position);
			getViewState().setAccountTypeDescription(String.format(Locale.getDefault(), "%s: %s",
					context.getString(R.string.trading_platform), selectedAccountType.getType().getValue()));

			getViewState().setCurrencyOptions(new ArrayList<>(selectedAccountType.getCurrencies()));
			onCurrencyOptionSelected(0, selectedAccountType.getCurrencies().get(0));

			ArrayList<String> leverageOptions = new ArrayList<>();
			for (Integer leverage : selectedAccountType.getLeverages()) {
				leverageOptions.add(String.format(Locale.getDefault(), "1:%d", leverage));
			}
			getViewState().setLeverageOptions(leverageOptions);
			onLeverageOptionSelected(0, leverageOptions.get(0));

			updateNextButtonAvailability();
		}
	}

	void onCurrencyOptionSelected(Integer position, String text) {
		if (request != null) {
			request.setCurrency(Currency.fromValue(selectedAccountType.getCurrencies().get(position)));

			getViewState().setCurrency(text, position);

			updateNextButtonAvailability();
		}
	}

	void onLeverageOptionSelected(Integer position, String text) {
		if (request != null) {
			request.setLeverage(selectedAccountType.getLeverages().get(position));

			getViewState().setLeverage(text, position);

			updateNextButtonAvailability();
		}
	}
}
