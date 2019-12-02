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
import vision.genesis.clientapp.model.ProgramSettingsModel;
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

	public static FollowSettingsFragment with(ProgramSettingsModel model) {
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

	@BindView(R.id.entry_fee)
	public EditText entryFee;

	@BindView(R.id.entry_fee_description)
	public TextView entryFeeDescription;

	@BindView(R.id.success_fee)
	public EditText successFee;

	@BindView(R.id.success_fee_description)
	public TextView successFeeDescription;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@InjectPresenter
	public FollowSettingsPresenter presenter;

	private Unbinder unbinder;

	@OnClick(R.id.group_entry_fee)
	public void onEntryFeeClicked() {
		showSoftKeyboard(entryFee);
	}

	@OnClick(R.id.group_success_fee)
	public void onSuccessFeeClicked() {
		showSoftKeyboard(successFee);
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		hideSoftKeyboard();
		presenter.onConfirmClicked();
//		EventBus.getDefault().post(new OnProgramSettingsConfirmEvent());
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
			ProgramSettingsModel model = getArguments().getParcelable(EXTRA_MODEL);
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

	private void updateView(ProgramSettingsModel model) {
		stepGroup.setVisibility(model.isNeedStep() ? View.VISIBLE : View.GONE);
		stepNumber.setText(model.getStepNumber());
		stepTitle.setText(model.getStepTitle());

		confirmButton.setText(model.getButtonText());
		confirmButton.setEnabled(false);
	}

	private void setTextListeners() {
		RxTextView.textChanges(entryFee)
				.subscribe(charSequence -> presenter.onEntryFeeChanged(charSequence.toString()));
		RxTextView.textChanges(successFee)
				.subscribe(charSequence -> presenter.onSuccessFeeChanged(charSequence.toString()));
	}

	private void setFonts() {
		stepNumber.setTypeface(TypefaceUtil.semibold());
		stepTitle.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void updateEntryFeeDescription(Double managerMaxEntryFee) {
		this.entryFeeDescription.setText(String.format(Locale.getDefault(),
				getString(R.string.template_create_fund_entry_fee_description),
				StringFormatUtil.formatAmount(managerMaxEntryFee, 0, 4)));
	}

	@Override
	public void updateSuccessFeeDescription(Double managerMaxExitFee) {
		this.successFeeDescription.setText(String.format(Locale.getDefault(),
				getString(R.string.template_create_fund_exit_fee_description),
				StringFormatUtil.formatAmount(managerMaxExitFee, 0, 4)));
	}

	@Override
	public void setEntryFee(Double entryFeeValue) {
		String entryFeeText = StringFormatUtil.formatAmount(entryFeeValue, 0, 4);
		if (entryFeeValue == 0) {
			this.entryFee.setText("");
		}
		else {
			this.entryFee.setText(entryFeeText);
			this.entryFee.setSelection(entryFeeText.length(), entryFeeText.length());
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
			entryFee.clearFocus();
			successFee.clearFocus();
			if (imm != null) {
				imm.hideSoftInputFromWindow(entryFee.getWindowToken(), 0);
			}
		}
	}
}