package vision.genesis.clientapp.feature.main.program.withdraw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.program.withdraw.confirm.ConfirmProgramWithdrawBottomSheetFragment;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.DigitsInputFilter;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

public class WithdrawProgramActivity extends BaseSwipeBackActivity implements WithdrawProgramView
{
	private static final String EXTRA_PROGRAM_REQUEST = "extra_program_request";

	public static void startWith(Activity activity, ProgramRequest request) {
		Intent intent = new Intent(activity.getApplicationContext(), WithdrawProgramActivity.class);
		intent.putExtra(EXTRA_PROGRAM_REQUEST, request);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.available_to_withdraw)
	public TextView availableToWithdraw;

	@BindView(R.id.group_amount_to_withdraw)
	public ViewGroup amountToWithdrawGroup;

	@BindView(R.id.group_amount)
	public ViewGroup amountGroup;

	@BindView(R.id.group_percent_amount)
	public ViewGroup percentAmountGroup;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.edittext_percent_amount)
	public EditText percentAmount;

	@BindView(R.id.currency)
	public TextView currency;

	@BindView(R.id.max)
	public TextView max;

	@BindView(R.id.max_percent)
	public TextView maxPercent;

	@BindView(R.id.withdraw_all_switch)
	public SwitchCompat withdrawAllSwitch;

	@BindView(R.id.program_withdraw_all_info)
	public TextView programWithdrawAllInfo;

	@BindView(R.id.base_currency_amount)
	public TextView baseCurrencyAmount;

	@BindView(R.id.remaining_investment)
	public TextView remainingInvestment;

	@BindView(R.id.payout_date)
	public TextView payoutDate;

	@BindView(R.id.button_continue)
	public PrimaryButton continueButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	WithdrawProgramPresenter presenter;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_amount)
	public void onAmountClicked() {
		if (amount.isEnabled()) {
			showSoftKeyboard(amount);
		}
	}

	@OnClick(R.id.group_percent_amount)
	public void onPercentAmountClicked() {
		if (percentAmount.isEnabled()) {
			showSoftKeyboard(percentAmount);
		}
	}

	@OnClick(R.id.available_to_withdraw)
	public void onAvailableClicked() {
		presenter.onAvailableToWithdrawClicked();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		presenter.onAvailableToWithdrawClicked();
	}

	@OnClick(R.id.max_percent)
	public void onMaxPercentClicked() {
		presenter.onAvailableToWithdrawClicked();
	}

	@OnClick(R.id.button_continue)
	public void onContinueClicked() {
		presenter.onContinueClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_withdraw);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null) {
			ProgramRequest request = getIntent().getExtras().getParcelable(EXTRA_PROGRAM_REQUEST);
			if (request != null) {
				presenter.setProgramRequest(request);

				setListeners();
				percentAmount.setFilters(new InputFilter[]{new DigitsInputFilter(3, 2, 1000)});

				updateView(request);

				return;
			}
		}
		Timber.e("Passed empty request to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void updateView(ProgramRequest request) {
		programName.setText(request.getProgramName());
		withdrawAllSwitch.setVisibility(request.isOwner() ? View.GONE : View.VISIBLE);
		max.setVisibility(request.isOwner() ? View.VISIBLE : View.GONE);
		maxPercent.setVisibility(request.isOwner() ? View.VISIBLE : View.GONE);

		amountGroup.setVisibility(request.isExchangeProgram() ? View.GONE : View.VISIBLE);
		percentAmountGroup.setVisibility(request.isExchangeProgram() ? View.VISIBLE : View.GONE);
	}


	private void setListeners() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> presenter.onAmountChanged(charSequence.toString()));
		RxTextView.textChanges(percentAmount)
				.subscribe(charSequence -> presenter.onPercentAmountChanged(charSequence.toString()));

		withdrawAllSwitch.setOnCheckedChangeListener((view, checked) -> {
			presenter.onWithdrawAllCheckedChanged(checked);
			programWithdrawAllInfo.setVisibility(checked ? View.VISIBLE : View.GONE);
		});
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setAvailableToWithdraw(String availableToWithdrawText) {
		this.availableToWithdraw.setText(availableToWithdrawText);
	}

	@Override
	public void setAmount(String amountText) {
		this.amount.setText(amountText);
		this.amount.setSelection(amountText.length(), amountText.length());
	}

	@Override
	public void setPercentAmount(String percentAmountText) {
		this.percentAmount.setText(percentAmountText);
		this.percentAmount.setSelection(percentAmountText.length(), percentAmountText.length());
	}

	@Override
	public void setAmountBase(String amountBaseString) {
		this.baseCurrencyAmount.setText(amountBaseString);
	}

	@Override
	public void setCurrency(String programCurrency) {
		this.currency.setText(programCurrency);
	}

	@Override
	public void setRemainingInvestment(String remainingInvestmentString) {
		this.remainingInvestment.setText(remainingInvestmentString);
	}

	@Override
	public void setPayoutDate(String payoutDate) {
		this.payoutDate.setText(payoutDate);
	}

	@Override
	public void setContinueButtonEnabled(boolean enabled) {
		continueButton.setEnabled(enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			content.setVisibility(View.VISIBLE);
		}
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

	@Override
	public void showConfirmDialog(ProgramRequest programRequest) {
		ConfirmProgramWithdrawBottomSheetFragment bottomSheetDialog = new ConfirmProgramWithdrawBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setData(programRequest);
		bottomSheetDialog.setListener(presenter);
	}

	@Override
	public void setAmountEnabled(boolean enabled) {
		amountToWithdrawGroup.setAlpha(enabled ? 1 : 0.5f);
		amount.setEnabled(enabled);
		percentAmount.setEnabled(enabled);
	}

	@Override
	public void setWithdrawAllChecked(boolean checked) {
		withdrawAllSwitch.setChecked(checked);
	}

	private void showSoftKeyboard(EditText view) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		view.requestFocus();
		if (imm != null) {
			imm.showSoftInput(view, 0);
		}
	}
}