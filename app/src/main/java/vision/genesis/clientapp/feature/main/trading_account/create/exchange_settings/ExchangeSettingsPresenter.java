package vision.genesis.clientapp.feature.main.trading_account.create.exchange_settings;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.Currency;
import io.swagger.client.model.ExchangeAccountType;
import io.swagger.client.model.ExchangeInfo;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnAccountExchangeSettingsSelectedEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/01/2022.
 */

@InjectViewState
public class ExchangeSettingsPresenter extends MvpPresenter<ExchangeSettingsView>
{
	@Inject
	public Context context;

	private ExchangeInfo exchange;

	private ExchangeAccountType selectedAccountType;

	private UUID assetId;

	private Currency currency;

	private Boolean isCreatingNewProgram = false;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	void setAssetId(UUID assetId) {
		this.assetId = assetId;
	}

	void setIsCreatingNewProgram(Boolean isCreatingNewProgram) {
		this.isCreatingNewProgram = isCreatingNewProgram;
	}

	void setExchange(ExchangeInfo exchange) {
		this.exchange = exchange;

		this.selectedAccountType = null;
		this.currency = null;

		ArrayList<String> accountTypeOptions = new ArrayList<>();
		for (ExchangeAccountType accountType : this.exchange.getAccountTypes()) {
			accountTypeOptions.add(accountType.getName());
		}
		getViewState().setAccountTypeOptions(accountTypeOptions);
		onAccountTypeOptionSelected(0, accountTypeOptions.get(0));
	}

	private void updateNextButtonAvailability() {
		boolean currencyOk = assetId == null || currency != null;
		getViewState().setNextButtonEnabled(selectedAccountType != null && currencyOk);
	}

	void onAccountTypeOptionSelected(Integer position, String text) {
		selectedAccountType = exchange.getAccountTypes().get(position);
		currency = null;

		if (assetId == null && !isCreatingNewProgram) {
			getViewState().setButtonText(selectedAccountType.isIsDepositRequired()
					? context.getString(R.string.next)
					: context.getString(R.string.create_trading_account));
		}
		getViewState().setAccountType(text, position);
		getViewState().setAccountTypeDescription(String.format(Locale.getDefault(), "%s: %s",
				context.getString(R.string.trading_platform), selectedAccountType.getTypeTitle()));

		getViewState().setCurrencyOptions(new ArrayList<>(selectedAccountType.getCurrencies()));
		onCurrencyOptionSelected(0, selectedAccountType.getCurrencies().get(0));

		updateNextButtonAvailability();
	}

	void onCurrencyOptionSelected(Integer position, String text) {
		currency = Currency.fromValue(selectedAccountType.getCurrencies().get(position));
		getViewState().setCurrency(text, position);
		updateNextButtonAvailability();
	}

	void onConfirmClicked() {
		EventBus.getDefault().post(new OnAccountExchangeSettingsSelectedEvent(selectedAccountType, currency));
	}
}
