package vision.genesis.clientapp.feature.main.program.create.settings;

import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.Currency;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.model.ProgramSettingsModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.DigitsInputFilter;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class ProgramSettingsFragment extends BaseFragment implements ProgramSettingsView
{
	private static String EXTRA_MODEL = "extra_model";

	public static ProgramSettingsFragment with(ProgramSettingsModel model) {
		ProgramSettingsFragment fragment = new ProgramSettingsFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_MODEL, model);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.group_step)
	public ViewGroup stepGroup;

	@BindView(R.id.step_number)
	public TextView stepNumber;

	@BindView(R.id.step_title)
	public TextView stepTitle;

	@BindView(R.id.period_length)
	public TextView periodLength;

	@BindView(R.id.group_period)
	public ViewGroup periodGroup;

	@BindView(R.id.group_processing)
	public ViewGroup processingGroup;

	@BindView(R.id.realtime_switch)
	public SwitchCompat realTimeSwitch;

	@BindView(R.id.group_processing_time)
	public ViewGroup processingTimeGroup;

	@BindView(R.id.processing_time)
	public TextView processingTime;

	@BindView(R.id.group_currency)
	public ViewGroup currencyGroup;

	@BindView(R.id.currency)
	public TextView currency;

	@BindView(R.id.investment_limit_switch)
	public SwitchCompat investmentLimitSwitch;

	@BindView(R.id.group_investment_limit)
	public ViewGroup investmentLimitGroup;

	@BindView(R.id.investment_limit)
	public EditText investmentLimit;

	@BindView(R.id.investment_limit_currency)
	public TextView investmentLimitCurrency;

	@BindView(R.id.trades_delay)
	public TextView tradesDelay;

	@BindView(R.id.group_stop_out)
	public ViewGroup stopOutGroup;

	@BindView(R.id.stop_out)
	public EditText stopOut;

	@BindView(R.id.stop_out_error)
	public TextView stopOutError;

	@BindView(R.id.management_fee)
	public EditText managementFee;

	@BindView(R.id.management_fee_description)
	public TextView managementFeeDescription;

	@BindView(R.id.label_success_fee)
	public TextView successFeeLabel;

	@BindView(R.id.success_fee)
	public EditText successFee;

	@BindView(R.id.success_fee_description)
	public TextView successFeeDescription;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@InjectPresenter
	public ProgramSettingsPresenter presenter;

	private Unbinder unbinder;

	private ArrayList<String> periodLengthOptions;

	private Integer selectedPeriodLengthPosition = -1;

	private ArrayList<String> processingTimeOptions;

	private Integer selectedProcessingTimePosition = -1;

	private ArrayList<String> currencyOptions;

	private Integer selectedCurrencyPosition = -1;

	private ArrayList<String> tradesDelayOptions;

	private Integer selectedTradesDelayPosition = -1;

	private ProgramSettingsModel model;

	private Boolean isExchangeProgram = false;

	private Double maxManagementFee = 0.0;

	private Double maxSuccessFee = 0.0;

	@OnClick(R.id.group_period_length)
	public void onPeriodLengthClicked() {
		if (getActivity() != null && periodLengthOptions != null && periodLengthOptions.size() > 1) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.period_length), periodLengthOptions, selectedPeriodLengthPosition);
			fragment.setListener((position, text) -> presenter.onPeriodLengthOptionSelected(position, text));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.group_processing_time)
	public void onProcessingTimeClicked() {
		if (getActivity() != null && processingTimeOptions != null && processingTimeOptions.size() > 1) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.processing_time), processingTimeOptions, selectedProcessingTimePosition);
			fragment.setListener((position, text) -> presenter.onProcessingTimeOptionSelected(position, text));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.group_choose_currency)
	public void onCurrencyClicked() {
		if (getActivity() != null && currencyOptions != null && currencyOptions.size() > 1) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.currency), currencyOptions, selectedCurrencyPosition);
			fragment.setListener((position, text) -> presenter.onCurrencyOptionSelected(position, text));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.group_trades_delay)
	public void onTradesDelayClicked() {
		if (getActivity() != null && tradesDelayOptions != null && tradesDelayOptions.size() > 1) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.trades_delay), tradesDelayOptions, selectedTradesDelayPosition);
			fragment.setListener((position, text) -> presenter.onTradesDelayOptionSelected(position, text));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.group_investment_limit)
	public void onInvestmentLimitClicked() {
		showSoftKeyboard(investmentLimit);
	}

	@OnClick(R.id.group_stop_out_edittext)
	public void onStopOutClicked() {
		showSoftKeyboard(stopOut);
	}

	@OnClick(R.id.group_management_fee)
	public void onManagementFeeClicked() {
		showSoftKeyboard(managementFee);
	}

	@OnClick(R.id.group_success_fee)
	public void onSuccessFeeClicked() {
		showSoftKeyboard(successFee);
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		hideSoftKeyboard();
		presenter.onConfirmClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_program_settings, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		if (getArguments() != null) {
			model = getArguments().getParcelable(EXTRA_MODEL);
			if (model != null) {
				updateView(model);
				setListeners();
				managementFee.setFilters(new InputFilter[]{new DigitsInputFilter(3, 4, 1000)});
				successFee.setFilters(new InputFilter[]{new DigitsInputFilter(3, 4, 1000)});
				presenter.setModel(model);
				return;
			}
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

	private void updateView(ProgramSettingsModel model) {
		stepGroup.setVisibility(model.isNeedStep() ? View.VISIBLE : View.GONE);
		stepNumber.setText(model.getStepNumber());
		stepTitle.setText(model.getStepTitle());

		periodGroup.setVisibility(model.isNew() && !model.isExchange() ? View.VISIBLE : View.GONE);
		processingGroup.setVisibility(model.isExchange() ? View.VISIBLE : View.GONE);
		currencyGroup.setVisibility(model.isNew() && model.isExchange() ? View.VISIBLE : View.GONE);
		stopOutGroup.setVisibility(!model.isExchange() ? View.VISIBLE : View.GONE);

		investmentLimitCurrency.setText(model.getCurrency());

		confirmButton.setText(model.getButtonText());
		confirmButton.setEnabled(false);
	}

	private void setListeners() {
		realTimeSwitch.setOnCheckedChangeListener((view, checked) -> {
			presenter.onRealTimeCheckedChanged(checked);
			processingTimeGroup.setVisibility(!checked ? View.VISIBLE : View.GONE);
		});

		investmentLimitSwitch.setOnCheckedChangeListener((view, checked) -> {
			presenter.onInvestmentLimitCheckedChanged(checked);
			investmentLimitGroup.setVisibility(checked ? View.VISIBLE : View.GONE);
			if (checked) {
				showSoftKeyboard(investmentLimit);
			}
			else {
				hideSoftKeyboard();
			}
		});

		RxTextView.textChanges(investmentLimit)
				.subscribe(charSequence -> presenter.onInvestmentLimitChanged(charSequence.toString()));
		RxTextView.textChanges(stopOut)
				.subscribe(charSequence -> presenter.onStopOutChanged(charSequence.toString()));
		RxTextView.textChanges(managementFee)
				.subscribe(charSequence -> presenter.onManagementFeeChanged(charSequence.toString()));
		RxTextView.textChanges(successFee)
				.subscribe(charSequence -> presenter.onSuccessFeeChanged(charSequence.toString()));
	}

	private void setFonts() {
		successFeeLabel.setText(StringFormatUtil.capitalize(successFeeLabel.getText().toString()));
	}

	@Override
	public void setPeriodLengthOptions(ArrayList<String> periodLengthOptions) {
		this.periodLengthOptions = periodLengthOptions;
	}

	@Override
	public void setPeriodLength(String periodLength, Integer position) {
		this.periodLength.setText(periodLength);
		this.selectedPeriodLengthPosition = position;
	}

	@Override
	public void setProcessingRealTimeChecked(Boolean checked) {
		realTimeSwitch.setChecked(checked);
	}

	@Override
	public void setProcessingTimeOptions(ArrayList<String> processingTimeOptions) {
		this.processingTimeOptions = processingTimeOptions;
	}

	@Override
	public void setProcessingTime(String processingTime, Integer position) {
		this.processingTime.setText(processingTime);
		this.selectedProcessingTimePosition = position;
	}

	@Override
	public void setCurrencyOptions(ArrayList<String> currencyOptions) {
		this.currencyOptions = currencyOptions;
	}

	@Override
	public void setCurrency(String currency, Integer position) {
		this.currency.setText(currency);
		this.investmentLimitCurrency.setText(currency);
		this.selectedCurrencyPosition = position;
	}

	@Override
	public void setInvestmentLimit(Double investmentLimit) {
		investmentLimitSwitch.setChecked(investmentLimit != null);
		if (investmentLimit == 0) {
			this.investmentLimit.setText("");
		}
		else {
			String investmentLimitText = StringFormatUtil.formatAmount(investmentLimit, 0, 4);
			this.investmentLimit.setText(investmentLimitText);
			this.investmentLimit.setSelection(investmentLimitText.length(), investmentLimitText.length());
		}
	}

	@Override
	public void setTradesDelayOptions(ArrayList<String> tradesDelayOptions) {
		this.tradesDelayOptions = tradesDelayOptions;
	}

	@Override
	public void setTradesDelay(String tradesDelay, Integer position) {
		this.tradesDelay.setText(tradesDelay);
		this.selectedTradesDelayPosition = position;
	}

	@Override
	public void setStopOutLevel(Double stopOutLevel) {
		String stopOutText = StringFormatUtil.formatAmount(stopOutLevel, 0, 4);
		if (stopOutLevel == 0) {
			this.stopOut.setText("");
		}
		else {
			this.stopOut.setText(stopOutText);
			this.stopOut.setSelection(stopOutText.length(), stopOutText.length());
		}
	}

	@Override
	public void setManagementFee(Double managementFeeValue) {
		String managementFeeText = StringFormatUtil.formatAmount(managementFeeValue, 0, 4);
		if (managementFeeValue == 0) {
			this.managementFee.setText("");
		}
		else {
			this.managementFee.setText(managementFeeText);
			this.managementFee.setSelection(managementFeeText.length(), managementFeeText.length());
		}
	}

	@Override
	public void setSuccessFee(Double successFeeValue) {
		String successFeeText = StringFormatUtil.formatAmount(successFeeValue, 0, 4);
		if (successFeeValue == 0) {
			this.successFee.setText("");
		}
		else {
			this.successFee.setText(successFeeText);
			this.successFee.setSelection(successFeeText.length(), successFeeText.length());
		}
	}

	@Override
	public void showStopOutError(String message) {
		stopOutError.setText(message);
		stopOutError.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideStopOutError() {
		stopOutError.setVisibility(View.GONE);
	}

	@Override
	public void updateManagementFeeDescription(Double maxManagementFee) {
		this.maxManagementFee = maxManagementFee;
		updateManagementFeeText(maxManagementFee, model.isExchange());
	}

	private void updateManagementFeeText(Double maxManagementFee, Boolean isExchange) {
		if (isExchange) {
			this.managementFeeDescription.setText(String.format(Locale.getDefault(),
					getString(R.string.template_program_settings_exchange_management_fee_description),
					StringFormatUtil.formatAmount(maxManagementFee, 0, 4)));
		}
		else {
			this.managementFeeDescription.setText(String.format(Locale.getDefault(),
					getString(R.string.template_program_settings_management_fee_description),
					StringFormatUtil.formatAmount(maxManagementFee, 0, 4)));
		}
	}

	@Override
	public void updateSuccessFeeDescription(Double maxSuccessFee) {
		this.maxSuccessFee = maxSuccessFee;
		updateSuccessFeeText(maxSuccessFee, model.isExchange());
	}

	private void updateSuccessFeeText(Double maxSuccessFee, Boolean isExchange) {
		if (isExchange) {
			this.successFeeDescription.setText(String.format(Locale.getDefault(),
					getString(R.string.template_program_settings_exchange_success_fee_description),
					StringFormatUtil.formatAmount(maxSuccessFee, 0, 4)));
		}
		else {
			this.successFeeDescription.setText(String.format(Locale.getDefault(),
					getString(R.string.template_program_settings_success_fee_description),
					StringFormatUtil.formatAmount(maxSuccessFee, 0, 4)));
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, stepNumber);
	}

	@Override
	public void setConfirmButtonEnabled(boolean enabled) {
		confirmButton.setEnabled(enabled);
	}

	private void showSoftKeyboard(View view) {
		InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		view.requestFocus();
		if (imm != null) {
			imm.showSoftInput(view, 0);
		}
	}

	private void hideSoftKeyboard() {
		if (getContext() != null) {
			InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			investmentLimit.clearFocus();
			stopOut.clearFocus();
			managementFee.clearFocus();
			successFee.clearFocus();
			if (imm != null) {
				imm.hideSoftInputFromWindow(managementFee.getWindowToken(), 0);
			}
		}
	}

	public void setSteps(String stepNumberText, String buttonText) {
		stepNumber.setText(stepNumberText);
		confirmButton.setText(buttonText);
	}

	public void setIsExchangeProgram(Boolean isExchangeProgram) {
		this.isExchangeProgram = isExchangeProgram;
		if (presenter != null) {
			presenter.setIsExchangeProgram(isExchangeProgram);
		}
		periodGroup.setVisibility(!isExchangeProgram ? View.VISIBLE : View.GONE);
		processingGroup.setVisibility(isExchangeProgram ? View.VISIBLE : View.GONE);
		currencyGroup.setVisibility(isExchangeProgram ? View.VISIBLE : View.GONE);
		stopOutGroup.setVisibility(!isExchangeProgram ? View.VISIBLE : View.GONE);

		updateManagementFeeText(maxManagementFee, isExchangeProgram);
		updateSuccessFeeText(maxSuccessFee, isExchangeProgram);
	}

	public void setCurrency(Currency accountCurrency) {
		investmentLimitCurrency.setText(accountCurrency.getValue());
	}
}