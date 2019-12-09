package vision.genesis.clientapp.feature.main.follow.create.settings;

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

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.FollowSettingsModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/11/2019.
 */

public class FollowSettingsFragment extends BaseFragment implements FollowSettingsView
{
	private static String EXTRA_MODEL = "extra_model";

	public static FollowSettingsFragment with(FollowSettingsModel model) {
		FollowSettingsFragment fragment = new FollowSettingsFragment();
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

	@BindView(R.id.volume_fee)
	public EditText volumeFee;

	@BindView(R.id.volume_fee_description)
	public TextView volumeFeeDescription;

	@BindView(R.id.label_success_fee)
	public TextView successFeeLabel;

	@BindView(R.id.success_fee)
	public EditText successFee;

	@BindView(R.id.success_fee_description)
	public TextView successFeeDescription;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@InjectPresenter
	public FollowSettingsPresenter presenter;

	private Unbinder unbinder;

	@OnClick(R.id.group_volume_fee)
	public void onVolumeFeeClicked() {
		showSoftKeyboard(volumeFee);
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
		return inflater.inflate(R.layout.fragment_follow_settings, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		if (getArguments() != null) {
			FollowSettingsModel model = getArguments().getParcelable(EXTRA_MODEL);
			if (model != null) {
				updateView(model);
				setTextListeners();
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

	private void updateView(FollowSettingsModel model) {
		stepGroup.setVisibility(model.isNeedStep() ? View.VISIBLE : View.GONE);
		stepNumber.setText(model.getStepNumber());
		stepTitle.setText(model.getStepTitle());

		successFeeLabel.setText(StringFormatUtil.capitalize(successFeeLabel.getText().toString()));

		confirmButton.setText(model.getButtonText());
		confirmButton.setEnabled(false);
	}

	private void setTextListeners() {
		RxTextView.textChanges(volumeFee)
				.subscribe(charSequence -> presenter.onVolumeFeeChanged(charSequence.toString()));
		RxTextView.textChanges(successFee)
				.subscribe(charSequence -> presenter.onSuccessFeeChanged(charSequence.toString()));
	}

	private void setFonts() {
		stepNumber.setTypeface(TypefaceUtil.semibold());
		stepTitle.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void updateVolumeFeeDescription(Double minVolumeFee, Double maxVolumeFee) {
		this.volumeFeeDescription.setText(String.format(Locale.getDefault(),
				getString(R.string.template_create_follow_volume_fee_description),
				StringFormatUtil.formatAmount(minVolumeFee, 0, 4),
				StringFormatUtil.formatAmount(maxVolumeFee, 0, 4)));
	}

	@Override
	public void updateSuccessFeeDescription(Double minSuccessFee, Double maxSuccessFee) {
		this.successFeeDescription.setText(String.format(Locale.getDefault(),
				getString(R.string.template_create_follow_success_fee_description),
				StringFormatUtil.formatAmount(minSuccessFee, 0, 4),
				StringFormatUtil.formatAmount(maxSuccessFee, 0, 4)));
	}

	@Override
	public void setVolumeFee(Double volumeFeeValue) {
		String volumeFeeText = StringFormatUtil.formatAmount(volumeFeeValue, 0, 4);
		if (volumeFeeValue == 0) {
			this.volumeFee.setText("");
		}
		else {
			this.volumeFee.setText(volumeFeeText);
			this.volumeFee.setSelection(volumeFeeText.length(), volumeFeeText.length());
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
			volumeFee.clearFocus();
			successFee.clearFocus();
			if (imm != null) {
				imm.hideSoftInputFromWindow(volumeFee.getWindowToken(), 0);
			}
		}
	}
}