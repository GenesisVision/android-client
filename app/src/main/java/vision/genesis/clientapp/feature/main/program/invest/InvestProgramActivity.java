package vision.genesis.clientapp.feature.main.program.invest;

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

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.program.invest.confirm.ConfirmProgramInvestBottomSheetFragment;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

public class InvestProgramActivity extends BaseSwipeBackActivity implements InvestProgramView
{
	private static final String EXTRA_PROGRAM_REQUEST = "extra_program_request";

	public static void startWith(Activity activity, ProgramRequest programRequest) {
		Intent intent = new Intent(activity.getApplicationContext(), InvestProgramActivity.class);
		intent.putExtra(EXTRA_PROGRAM_REQUEST, programRequest);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.available_to_invest)
	public TextView availableToInvest;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.max)
	public TextView max;

	@BindView(R.id.base_currency_amount)
	public TextView baseCurrencyAmount;

	@BindView(R.id.entry_fee)
	public TextView entryFee;

	@BindView(R.id.amount_due)
	public TextView amountDue;

	@BindView(R.id.button_continue)
	public PrimaryButton continueButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	InvestProgramPresenter investProgramPresenter;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.available_to_invest)
	public void onAvailvalbeClicked() {
		investProgramPresenter.onMaxClicked();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		investProgramPresenter.onMaxClicked();
	}

	@OnClick(R.id.button_continue)
	public void onContinueClicked() {
		investProgramPresenter.onContinueClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_invest);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			investProgramPresenter.setProgramRequest(getIntent().getExtras().getParcelable(EXTRA_PROGRAM_REQUEST));

			setFonts();

			setTextListener();
		}
		else {
			Timber.e("Passed empty request to InvestProgramActivity");
			onBackPressed();
		}
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> investProgramPresenter.onAmountChanged(charSequence.toString()));
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		max.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setAvailableToInvest(Double availableToInvest) {
		this.availableToInvest.setText(String.format(Locale.getDefault(), "%s GVT", StringFormatUtil.formatCurrencyAmount(availableToInvest, CurrencyEnum.GVT.toString())));
	}

	@Override
	public void setAmount(String amountText) {
		this.amount.setText(amountText);
		this.amount.setSelection(amountText.length(), amountText.length());
	}

	@Override
	public void setEntryFee(String entryFeeText) {
		this.entryFee.setText(entryFeeText);
	}

	@Override
	public void setAmountDue(String amountDueText) {
		this.amountDue.setText(amountDueText);
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
		ConfirmProgramInvestBottomSheetFragment bottomSheetDialog = new ConfirmProgramInvestBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setData(programRequest);
		bottomSheetDialog.setListener(investProgramPresenter);
	}

	private void showSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		amount.requestFocus();
		if (imm != null) {
			imm.showSoftInput(amount, 0);
		}
	}
}