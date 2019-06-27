package vision.genesis.clientapp.feature.main.copytrading.subscription_settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/06/2019.
 */

public class SubscriptionSettingsActivity extends BaseSwipeBackActivity implements SubscriptionSettingsView
{
	private static final String EXTRA_MODEL = "extra_model";

	private static final String EXTRA_IS_EDIT = "extra_is_edit";

	public static void startWith(Activity activity, SubscriptionSettingsModel model, boolean isEdit) {
		Intent intent = new Intent(activity.getApplicationContext(), SubscriptionSettingsActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.putExtra(EXTRA_IS_EDIT, isEdit);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.content)
	public ViewGroup content;

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


	@BindView(R.id.button)
	public PrimaryButton button;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	SubscriptionSettingsPresenter subscriptionSettingsPresenter;

	private SubscriptionSettingsModel model;

	private ArrayList<String> typeOptions;

	private Integer selectedTypePosition;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_type)
	public void onTypeClicked() {
		SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
				getString(R.string.select_subscription_type), typeOptions, selectedTypePosition);
		fragment.setListener(subscriptionSettingsPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@OnClick(R.id.group_edittext_volume_percentage)
	public void onAmountClicked() {
		showSoftKeyboard(volumePercentage);
	}

	@OnClick(R.id.label_volume_percentage)
	public void onLabelVolumePercentageClicked() {
		subscriptionSettingsPresenter.onLabelVolumePercentageClicked();
	}

	@OnClick(R.id.label_equivalent)
	public void onLabelEquivalentClicked() {
		subscriptionSettingsPresenter.onLabelEquivalentClicked();
	}

	@OnClick(R.id.label_tolerance_percentage)
	public void onLabelTolerancePercentageClicked() {
		subscriptionSettingsPresenter.onLabelTolerancePercentageClicked();
	}

	@OnClick(R.id.max_volume_percentage)
	public void onVolumePercentageMaxClicked() {
		subscriptionSettingsPresenter.onVolumePercentageMaxClicked();
	}

	@OnClick(R.id.max_equivalent)
	public void onEquivalentMaxClicked() {
		subscriptionSettingsPresenter.onEquivalentMaxClicked();
	}

	@OnClick(R.id.max_tolerance_percentage)
	public void onTolerancePercentageMaxClicked() {
		subscriptionSettingsPresenter.onTolerancePercentageMaxClicked();
	}

	@OnClick(R.id.button)
	public void onButtonClicked() {
		subscriptionSettingsPresenter.onButtonClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_subscription_settings);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			boolean isEdit = getIntent().getExtras().getBoolean(EXTRA_IS_EDIT);
			if (model != null) {
				subscriptionSettingsPresenter.setData(model, isEdit);

				if (isEdit)
					button.setText(getString(R.string.update_subscription));
				setProgramName(model.getProgramName());
				setLabels();
				setFonts();

				setTextListeners();
				return;
			}
		}
		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setLabels() {
		volumePercentageLabel.setText(String.format(Locale.getDefault(), "%s (min %s%%)", getString(R.string.volume_percentage),
				StringFormatUtil.formatAmount(SubscriptionSettingsModel.VOLUME_PERCENTAGE_MIN, 0, 2)));

		equivalentLabel.setText(String.format(Locale.getDefault(), "%s %s (min %s)",
				SubscriptionSettingsModel.EQUIVALENT_CURRENCY,
				getString(R.string.equivalent),
				StringFormatUtil.formatCurrencyAmount(SubscriptionSettingsModel.EQUIVALENT_MIN, SubscriptionSettingsModel.EQUIVALENT_CURRENCY)));

		tolerancePercentageLabel.setText(String.format(Locale.getDefault(), "%s (min %s%%)", getString(R.string.tolerance_percentage),
				StringFormatUtil.formatAmount(SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MIN, 0, 2)));
	}

	private void setProgramName(String accountCurrency) {
		this.programName.setText(accountCurrency);
	}

	private void setTextListeners() {
		RxTextView.textChanges(volumePercentage)
				.subscribe(charSequence -> subscriptionSettingsPresenter.onVolumePercentageChanged(charSequence.toString()));
		RxTextView.textChanges(equivalent)
				.subscribe(charSequence -> subscriptionSettingsPresenter.onEquivalentChanged(charSequence.toString()));
		RxTextView.textChanges(tolerancePercentage)
				.subscribe(charSequence -> subscriptionSettingsPresenter.onTolerancePercentageChanged(charSequence.toString()));
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
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
	public void setMinDepositAmount(Double minDepositAmount, String currency) {
//		amountToDepositLabel.setText(String.format(Locale.getDefault(), "%s (min %s)",
//				getString(R.string.amount_to_deposit), StringFormatUtil.formatCurrencyAmount(minDepositAmount, currency)));
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
	public void setButtonEnabled(boolean enabled) {
		button.setEnabled(enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		button.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_bottom);
	}

	private void showSoftKeyboard(EditText edittext) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		edittext.requestFocus();
		if (imm != null) {
			imm.showSoftInput(edittext, 0);
		}
	}
}