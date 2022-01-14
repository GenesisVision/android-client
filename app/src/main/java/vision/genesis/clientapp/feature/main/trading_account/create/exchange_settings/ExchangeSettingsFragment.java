package vision.genesis.clientapp.feature.main.trading_account.create.exchange_settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ExchangeInfo;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/01/2022.
 */

public class ExchangeSettingsFragment extends BaseFragment implements ExchangeSettingsView
{
	private static String EXTRA_ASSET_ID = "extra_asset_id";

	private static String EXTRA_IS_CREATING_NEW_PROGRAM = "extra_is_creating_new_program";

	public static ExchangeSettingsFragment with(UUID assetId, boolean isCreatingNewProgram) {
		ExchangeSettingsFragment fragment = new ExchangeSettingsFragment();
		Bundle arguments = new Bundle(2);
		arguments.putSerializable(EXTRA_ASSET_ID, assetId);
		arguments.putBoolean(EXTRA_IS_CREATING_NEW_PROGRAM, isCreatingNewProgram);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.step_number)
	public TextView stepNumber;

	@BindView(R.id.step_title)
	public TextView stepTitle;

	@BindView(R.id.account_type)
	public TextView accountType;

	@BindView(R.id.account_type_arrow)
	public ImageView accountTypeArrow;

	@BindView(R.id.account_type_description)
	public TextView accountTypeDescription;

	@BindView(R.id.currency)
	public TextView currency;

	@BindView(R.id.currency_arrow)
	public ImageView currencyArrow;

	@BindView(R.id.button_next)
	public PrimaryButton nextButton;

	@InjectPresenter
	public ExchangeSettingsPresenter presenter;

	private Unbinder unbinder;

	private ArrayList<String> accountTypeOptions;

	private Integer selectedAccountTypePosition = -1;

	private ArrayList<String> currencyOptions;

	private Integer selectedCurrencyPosition = -1;

	@OnClick(R.id.group_account_type)
	public void onAccountTypeClicked() {
		if (getActivity() != null && accountTypeOptions != null && accountTypeOptions.size() > 1) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.account_type), accountTypeOptions, selectedAccountTypePosition);
			fragment.setListener((position, text) -> presenter.onAccountTypeOptionSelected(position, text));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.group_currency)
	public void onCurrencyClicked() {
		if (getActivity() != null && currencyOptions != null && currencyOptions.size() > 1) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.currency), currencyOptions, selectedCurrencyPosition);
			fragment.setListener((position, text) -> presenter.onCurrencyOptionSelected(position, text));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.button_next)
	public void onNextClicked() {
		presenter.onConfirmClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_exchange_settings, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		nextButton.setEnabled(false);

		if (getArguments() != null) {
			UUID assetId = (UUID) getArguments().getSerializable(EXTRA_ASSET_ID);
			Boolean isCreatingNewProgram = getArguments().getBoolean(EXTRA_IS_CREATING_NEW_PROGRAM, false);
			presenter.setIsCreatingNewProgram(isCreatingNewProgram);
			if (assetId != null) {
				presenter.setAssetId(assetId);

				nextButton.setText(getString(R.string.update));
			}
			else {
				nextButton.setText(getString(R.string.next));
			}
			return;
		}

		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		stepNumber.setTypeface(TypefaceUtil.semibold());
		stepTitle.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setAccountTypeOptions(ArrayList<String> accountTypeOptions) {
		this.accountTypeOptions = accountTypeOptions;
		if (accountTypeOptions.size() <= 1) {
			this.accountType.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
			this.accountTypeArrow.setVisibility(View.INVISIBLE);
		}
		else {
			this.accountType.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));
			this.accountTypeArrow.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setCurrencyOptions(ArrayList<String> currencyOptions) {
		this.currencyOptions = currencyOptions;
		if (currencyOptions.size() <= 1) {
			this.currency.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
			this.currencyArrow.setVisibility(View.INVISIBLE);
		}
		else {
			this.currency.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));
			this.currencyArrow.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setAccountType(String accountType, Integer position) {
		this.accountType.setText(accountType);
		this.selectedAccountTypePosition = position;
	}

	@Override
	public void setCurrency(String currency, Integer position) {
		this.currency.setText(currency);
		this.selectedCurrencyPosition = position;
	}

	@Override
	public void setAccountTypeDescription(String description) {
		this.accountTypeDescription.setText(description);
	}

	@Override
	public void setButtonText(String text) {
		this.nextButton.setText(text);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, stepNumber);
	}

	@Override
	public void setNextButtonEnabled(boolean enabled) {
		nextButton.setEnabled(enabled);
	}

	public void setSelectedExchange(ExchangeInfo selectedExchange) {
		presenter.setExchange(selectedExchange);
	}

	public void setSteps(String stepNumberText, String buttonText) {
		stepNumber.setText(stepNumberText);
		nextButton.setText(buttonText);
	}
}