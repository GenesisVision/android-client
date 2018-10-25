package vision.genesis.clientapp.feature.main.fund.invest;

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
import vision.genesis.clientapp.feature.main.fund.invest.confirm.ConfirmFundInvestBottomSheetFragment;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

public class InvestFundActivity extends BaseSwipeBackActivity implements InvestFundView
{
	private static final String EXTRA_FUND_REQUEST = "extra_fund_request";

	public static void startWith(Activity activity, FundRequest fundRequest) {
		Intent intent = new Intent(activity.getApplicationContext(), InvestFundActivity.class);
		intent.putExtra(EXTRA_FUND_REQUEST, fundRequest);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.available_in_wallet)
	public TextView availableInWallet;

	@BindView(R.id.label_amount_to_invest)
	public TextView amountToInvestLabel;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.max)
	public TextView max;

	@BindView(R.id.base_currency_amount)
	public TextView baseCurrencyAmount;

	@BindView(R.id.entry_fee)
	public TextView entryFee;

	@BindView(R.id.gv_commission)
	public TextView gvCommission;

	@BindView(R.id.investment_amount)
	public TextView investmentAmount;

	@BindView(R.id.button_continue)
	public PrimaryButton continueButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	InvestFundPresenter investFundPresenter;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.available_in_wallet)
	public void onAvailableClicked() {
		investFundPresenter.onMaxClicked();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		investFundPresenter.onMaxClicked();
	}

	@OnClick(R.id.button_continue)
	public void onContinueClicked() {
		investFundPresenter.onContinueClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fund_invest);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			investFundPresenter.setFundRequest(getIntent().getExtras().getParcelable(EXTRA_FUND_REQUEST));

			setFonts();

			setTextListener();
		}
		else {
			Timber.e("Passed empty request to InvestFundActivity");
			onBackPressed();
		}
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> investFundPresenter.onAmountChanged(charSequence.toString()));
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		max.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setAvailableInWallet(Double availableToInvest) {
		this.availableInWallet.setText(String.format(Locale.getDefault(), "%s GVT",
				StringFormatUtil.formatCurrencyAmount(availableToInvest, CurrencyEnum.GVT.toString())));
	}

	@Override
	public void setMinInvestmentAmount(Double minInvestmentAmount) {
		amountToInvestLabel.setText(String.format(Locale.getDefault(), "%s (min %s)",
				getString(R.string.amount_to_invest),
				StringFormatUtil.formatAmount(minInvestmentAmount, 0, 6)));
	}

	@Override
	public void setAmount(String amountText) {
		this.amount.setText(amountText);
		this.amount.setSelection(amountText.length(), amountText.length());
	}

	@Override
	public void setAmountBase(String amountBaseString) {
		this.baseCurrencyAmount.setText(amountBaseString);
	}

	@Override
	public void setEntryFee(String entryFeeText) {
		this.entryFee.setText(entryFeeText);
	}

	@Override
	public void setGvCommission(String gvCommission) {
		this.gvCommission.setText(gvCommission);
	}

	@Override
	public void setInvestmentAmount(String investmentAmount) {
		this.investmentAmount.setText(investmentAmount);
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
		ConfirmFundInvestBottomSheetFragment bottomSheetDialog = new ConfirmFundInvestBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setData(fundRequest);
		bottomSheetDialog.setListener(investFundPresenter);
	}

	private void showSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		amount.requestFocus();
		if (imm != null) {
			imm.showSoftInput(amount, 0);
		}
	}
}