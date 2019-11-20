package vision.genesis.clientapp.feature.main.fund.create.fees;

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

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.NewFundRequest;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.events.OnCreateFundNextButtonClickedEvent;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

public class CreateFundFeesFragment extends BaseFragment implements CreateFundFeesView
{
	@BindView(R.id.step_number)
	public TextView stepNumber;

	@BindView(R.id.step_title)
	public TextView stepTitle;

	@BindView(R.id.entry_fee)
	public EditText entryFee;

	@BindView(R.id.entry_fee_description)
	public TextView entryFeeDescription;

	@BindView(R.id.exit_fee)
	public EditText exitFee;

	@BindView(R.id.exit_fee_description)
	public TextView exitFeeDescription;

	@BindView(R.id.button_next)
	public PrimaryButton nextButton;

	@InjectPresenter
	public CreateFundFeesPresenter presenter;

	private Unbinder unbinder;

	private NewFundRequest request;

	@OnClick(R.id.group_entry_fee)
	public void onEntryFeeClicked() {
		showSoftKeyboard(entryFee);
	}

	@OnClick(R.id.group_exit_fee)
	public void onExitFeeClicked() {
		showSoftKeyboard(exitFee);
	}

	@OnClick(R.id.button_next)
	public void onNextClicked() {
		hideSoftKeyboard();
		EventBus.getDefault().post(new OnCreateFundNextButtonClickedEvent());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_create_fund_fees, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		nextButton.setText(String.format(Locale.getDefault(), "%s (3/4)", getString(R.string.next)));

		setTextListeners();

		if (request != null) {
			presenter.setRequest(request);
		}
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setTextListeners() {
		RxTextView.textChanges(entryFee)
				.subscribe(charSequence -> presenter.onEntryFeeChanged(charSequence.toString()));
		RxTextView.textChanges(exitFee)
				.subscribe(charSequence -> presenter.onExitFeeChanged(charSequence.toString()));
	}

	private void setFonts() {
		stepNumber.setTypeface(TypefaceUtil.semibold());
		stepTitle.setTypeface(TypefaceUtil.semibold());
	}

	public void setRequest(NewFundRequest request) {
		this.request = request;
		if (presenter != null) {
			presenter.setRequest(request);
		}
	}

	@Override
	public void updateEntryFeeDescription(Double managerMaxEntryFee) {
		this.entryFeeDescription.setText(String.format(Locale.getDefault(),
				getString(R.string.template_create_fund_entry_fee_description),
				StringFormatUtil.formatAmount(managerMaxEntryFee, 0, 4)));
	}

	@Override
	public void updateExitFeeDescription(Double managerMaxExitFee) {
		this.exitFeeDescription.setText(String.format(Locale.getDefault(),
				getString(R.string.template_create_fund_exit_fee_description),
				StringFormatUtil.formatAmount(managerMaxExitFee, 0, 4)));
	}

	@Override
	public void setEntryFeeText(Double entryFeeValue) {
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
	public void setExitFeeText(Double exitFeeValue) {
		String exitFeeText = StringFormatUtil.formatAmount(exitFeeValue, 0, 4);
		if (exitFeeValue == 0) {
			this.exitFee.setText("");
		}
		else {
			this.exitFee.setText(exitFeeText);
			this.exitFee.setSelection(exitFeeText.length(), exitFeeText.length());
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, stepNumber);
	}

	@Override
	public void setNextButtonEnabled(boolean enabled) {
		nextButton.setEnabled(enabled);
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
			exitFee.clearFocus();
			if (imm != null) {
				imm.hideSoftInputFromWindow(entryFee.getWindowToken(), 0);
			}
		}
	}
}