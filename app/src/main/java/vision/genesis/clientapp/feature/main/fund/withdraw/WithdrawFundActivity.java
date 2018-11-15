package vision.genesis.clientapp.feature.main.fund.withdraw;

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

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.fund.withdraw.confirm.ConfirmFundWithdrawBottomSheetFragment;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.DigitsInputFilter;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/10/2018.
 */

public class WithdrawFundActivity extends BaseSwipeBackActivity implements WithdrawFundView
{
	private static final String EXTRA_FUND_REQUEST = "extra_fund_request";

	public static void startWith(Activity activity, FundRequest request) {
		Intent intent = new Intent(activity.getApplicationContext(), WithdrawFundActivity.class);
		intent.putExtra(EXTRA_FUND_REQUEST, request);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.available_to_withdraw)
	public TextView availableToWithdraw;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.max)
	public TextView max;

	@BindView(R.id.estimated_amount)
	public TextView estimatedAmount;

	@BindView(R.id.exit_fee)
	public TextView exitFee;

	@BindView(R.id.button_continue)
	public PrimaryButton continueButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	WithdrawFundPresenter withdrawFundPresenter;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.available_to_withdraw)
	public void onAvailableClicked() {
		withdrawFundPresenter.onMaxClicked();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		withdrawFundPresenter.onMaxClicked();
	}

	@OnClick(R.id.button_continue)
	public void onContinueClicked() {
		withdrawFundPresenter.onContinueClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fund_withdraw);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			withdrawFundPresenter.setFundRequest(getIntent().getExtras().getParcelable(EXTRA_FUND_REQUEST));

			setFonts();

			setTextListener();
			amount.setFilters(new InputFilter[]{new DigitsInputFilter(3, 2, 1000)});
		}
		else {
			Timber.e("Passed empty request to WithdrawFundActivity");
			onBackPressed();
		}
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> withdrawFundPresenter.onAmountChanged(charSequence.toString()));
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		max.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setAvailableToWithdraw(Double availableToWithdraw) {
		this.availableToWithdraw.setText(String.format(Locale.getDefault(), "%s GVT",
				StringFormatUtil.formatCurrencyAmount(availableToWithdraw, CurrencyEnum.GVT.toString())));
	}

	@Override
	public void setAmount(String amountText) {
		this.amount.setText(amountText);
		this.amount.setSelection(amountText.length(), amountText.length());
	}

	@Override
	public void setEstimatedAmount(String estimatedAmountString) {
		this.estimatedAmount.setText(estimatedAmountString);
	}

	@Override
	public void setExitFee(String exitFeeString) {
		this.exitFee.setText(exitFeeString);
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
	public void showConfirmDialog(FundRequest fundRequest) {
		ConfirmFundWithdrawBottomSheetFragment bottomSheetDialog = new ConfirmFundWithdrawBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setData(fundRequest);
		bottomSheetDialog.setListener(withdrawFundPresenter);
	}

	private void showSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		amount.requestFocus();
		if (imm != null) {
			imm.showSoftInput(amount, 0);
		}
	}
}