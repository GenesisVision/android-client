package vision.genesis.clientapp.feature.main.copytrading.subscription_settings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/06/2019.
 */

public class SubscriptionSettingsFragment extends BaseFragment implements SubscriptionSettingsView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static SubscriptionSettingsFragment with(SubscriptionSettingsModel model) {
		SubscriptionSettingsFragment fragment = new SubscriptionSettingsFragment();
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


	@BindView(R.id.type)
	public TextView type;

	@BindView(R.id.type_description)
	public TextView typeDescription;


	@BindView(R.id.group_volume_percentage)
	public ViewGroup volumePercentageGroup;

	@BindView(R.id.label_volume_percentage)
	public TextView volumePercentageLabel;

	@BindView(R.id.edittext_volume_percentage)
	public EditText volumePercentage;

	@BindView(R.id.max_volume_percentage)
	public TextView volumePercentageMax;


	@BindView(R.id.group_equivalent)
	public ViewGroup equivalentGroup;

	@BindView(R.id.label_equivalent)
	public TextView equivalentLabel;

	@BindView(R.id.edittext_equivalent)
	public EditText equivalent;

	@BindView(R.id.currency_equivalent)
	public TextView equivalentCurrency;

	@BindView(R.id.max_equivalent)
	public TextView equivalentMax;


	@BindView(R.id.group_tolerance_percentage)
	public ViewGroup tolerancePercentageGroup;

	@BindView(R.id.label_tolerance_percentage)
	public TextView tolerancePercentageLabel;

	@BindView(R.id.edittext_tolerance_percentage)
	public EditText tolerancePercentage;

	@BindView(R.id.max_tolerance_percentage)
	public TextView tolerancePercentageMax;


	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@InjectPresenter
	SubscriptionSettingsPresenter presenter;

	private ArrayList<String> typeOptions;

	private Integer selectedTypePosition;

	private Unbinder unbinder;


	@OnClick(R.id.group_type)
	public void onTypeClicked() {
		if (getActivity() != null) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.select_subscription_type), typeOptions, selectedTypePosition);
			fragment.setListener(presenter);
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.group_edittext_volume_percentage)
	public void onVolumePercentageClicked() {
		showSoftKeyboard(volumePercentage);
	}

	@OnClick(R.id.group_edittext_tolerance_percentage)
	public void onTolerancePercentageClicked() {
		showSoftKeyboard(tolerancePercentage);
	}

	@OnClick(R.id.group_equivalent)
	public void onEquivalentClicked() {
		showSoftKeyboard(equivalent);
	}

	@OnClick(R.id.label_volume_percentage)
	public void onLabelVolumePercentageClicked() {
		presenter.onLabelVolumePercentageClicked();
	}

	@OnClick(R.id.label_equivalent)
	public void onLabelEquivalentClicked() {
		presenter.onLabelEquivalentClicked();
	}

	@OnClick(R.id.label_tolerance_percentage)
	public void onLabelTolerancePercentageClicked() {
		presenter.onLabelTolerancePercentageClicked();
	}

	@OnClick(R.id.max_volume_percentage)
	public void onVolumePercentageMaxClicked() {
		presenter.onVolumePercentageMaxClicked();
	}

	@OnClick(R.id.max_equivalent)
	public void onEquivalentMaxClicked() {
		presenter.onEquivalentMaxClicked();
	}

	@OnClick(R.id.max_tolerance_percentage)
	public void onTolerancePercentageMaxClicked() {
		presenter.onTolerancePercentageMaxClicked();
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmButtonClicked() {
		presenter.onConfirmButtonClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_subscription_settings, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		if (getArguments() != null) {
			SubscriptionSettingsModel model = getArguments().getParcelable(EXTRA_MODEL);
			if (model != null) {
				updateView(model);
				presenter.setModel(model);
				setTextListeners();
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

	private void updateView(SubscriptionSettingsModel model) {
		stepGroup.setVisibility(model.isNeedStep() ? View.VISIBLE : View.GONE);
		stepNumber.setText(model.getStepNumber());
		stepTitle.setText(model.getStepTitle());

		confirmButton.setText(model.getButtonText());
		confirmButton.setEnabled(false);

		volumePercentageLabel.setText(String.format(Locale.getDefault(), "%s (min %s%%)", getString(R.string.volume_percentage),
				StringFormatUtil.formatAmount(SubscriptionSettingsModel.VOLUME_PERCENTAGE_MIN, 0, 2)));

		equivalentLabel.setText(String.format(Locale.getDefault(), "%s %s (min %s)",
				SubscriptionSettingsModel.EQUIVALENT_CURRENCY,
				getString(R.string.equivalent),
				StringFormatUtil.formatCurrencyAmount(SubscriptionSettingsModel.EQUIVALENT_MIN, SubscriptionSettingsModel.EQUIVALENT_CURRENCY)));

		tolerancePercentageLabel.setText(String.format(Locale.getDefault(), "%s (min %s%%)", getString(R.string.tolerance_percentage),
				StringFormatUtil.formatAmount(SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MIN, 0, 2)));

	}

	private void setTextListeners() {
		RxTextView.textChanges(volumePercentage)
				.subscribe(charSequence -> presenter.onVolumePercentageChanged(charSequence.toString()));
		RxTextView.textChanges(equivalent)
				.subscribe(charSequence -> presenter.onEquivalentChanged(charSequence.toString()));
		RxTextView.textChanges(tolerancePercentage)
				.subscribe(charSequence -> presenter.onTolerancePercentageChanged(charSequence.toString()));
	}

	private void setFonts() {
		stepNumber.setTypeface(TypefaceUtil.semibold());
		stepTitle.setTypeface(TypefaceUtil.semibold());

		volumePercentageMax.setTypeface(TypefaceUtil.semibold());
		equivalentMax.setTypeface(TypefaceUtil.semibold());
		tolerancePercentageMax.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setTypeOptions(ArrayList<String> typeOptions) {
		this.typeOptions = typeOptions;
	}

	@Override
	public void setType(String type, Integer position) {
		this.type.setText(type);
		this.selectedTypePosition = position;
	}

	@Override
	public void setTypeDescription(String typeDescription) {
		this.typeDescription.setText(typeDescription);
	}

	@Override
	public void setVolumePercentage(String value) {
		this.volumePercentage.setText(value);
		this.volumePercentage.setSelection(value.length(), value.length());
	}

	@Override
	public void setEquivalent(String value) {
		this.equivalent.setText(value);
		this.equivalent.setSelection(value.length(), value.length());
	}

	@Override
	public void setTolerancePercentage(String value) {
		this.tolerancePercentage.setText(value);
		this.tolerancePercentage.setSelection(value.length(), value.length());
	}

	@Override
	public void showByBalanceFields() {
		volumePercentageGroup.setVisibility(View.GONE);
		equivalentGroup.setVisibility(View.GONE);
	}

	@Override
	public void showPercentageFields() {
		volumePercentageGroup.setVisibility(View.VISIBLE);
		equivalentGroup.setVisibility(View.GONE);
	}

	@Override
	public void showFixedFields() {
		volumePercentageGroup.setVisibility(View.GONE);
		equivalentGroup.setVisibility(View.VISIBLE);
	}

	@Override
	public void setConfirmButtonEnabled(boolean enabled) {
		confirmButton.setEnabled(enabled);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, stepNumber);
	}

	private void showSoftKeyboard(EditText edittext) {
		InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		edittext.requestFocus();
		if (imm != null) {
			imm.showSoftInput(edittext, 0);
		}
	}
}