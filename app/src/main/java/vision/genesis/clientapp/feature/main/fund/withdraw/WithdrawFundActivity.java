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
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.WalletData;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.withdraw.confirm.ConfirmFundWithdrawBottomSheetFragment;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.DigitsInputFilter;
import vision.genesis.clientapp.utils.ImageUtils;
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

	@BindView(R.id.fund_name)
	public TextView fundName;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.icon_to)
	public SimpleDraweeView iconTo;

	@BindView(R.id.wallet_to)
	public TextView walletTo;

	@BindView(R.id.group_invested)
	public ViewGroup investedGroup;

	@BindView(R.id.available_to_withdraw)
	public TextView availableToWithdraw;

	@BindView(R.id.group_amount)
	public ViewGroup amountGroup;

	@BindView(R.id.amount_to_withdraw_label)
	public TextView amountToWithdrawLabel;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.max)
	public TextView max;

	@BindView(R.id.estimated_amount)
	public TextView estimatedAmount;

	@BindView(R.id.group_commissions)
	public ViewGroup commissionsGroup;

	@BindView(R.id.exit_fee)
	public TextView exitFee;

	@BindView(R.id.withdrawal_amount)
	public TextView withdrawalAmount;

	@BindView(R.id.button_continue)
	public PrimaryButton continueButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.amount_progress_bar)
	public ProgressBar amountProgress;

	@InjectPresenter
	WithdrawFundPresenter withdrawFundPresenter;

	private List<WalletData> walletsTo;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_wallet)
	public void onWalletClicked() {
		SelectWalletBottomSheetFragment fragment = new SelectWalletBottomSheetFragment();
		fragment.setData(getString(R.string.select_wallet_currency), walletsTo);
		fragment.setListener(withdrawFundPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
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
			FundRequest request = getIntent().getExtras().getParcelable(EXTRA_FUND_REQUEST);
			if (request != null) {
				withdrawFundPresenter.setFundRequest(request);

				setFundName(request.getFundName());
				setFonts();

				setTextListener();
				amount.setFilters(new InputFilter[]{new DigitsInputFilter(3, 2, 1000)});
				return;
			}
		}
		Timber.e("Passed empty request to WithdrawFundActivity");
		onBackPressed();

	}

	private void setFundName(String fundName) {
		this.fundName.setText(fundName);
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
	public void setAvailableToWithdraw(String availableToWithdraw) {
		this.availableToWithdraw.setText(availableToWithdraw);
	}

	@Override
	public void setWalletsTo(List<WalletData> wallets) {
		this.walletsTo = wallets;
	}

	@Override
	public void setWalletTo(WalletData wallet) {
		this.iconTo.setImageURI(ImageUtils.getImageUri(wallet.getLogo()));
		this.walletTo.setText(wallet.getTitle());
	}

	@Override
	public void setMinWithdrawalAmount(Double minWithdrawalAmount) {
		amountToWithdrawLabel.setText(String.format(Locale.getDefault(), "%s (min %s%%)",
				getString(R.string.amount_to_withdraw),
				StringFormatUtil.formatAmount(minWithdrawalAmount, 0, 6)));
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
	public void setWithdrawalAmount(String withdrawalAmountText) {
		this.withdrawalAmount.setText(withdrawalAmountText);
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
	public void showAmountProgress(boolean show) {
		investedGroup.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
		amountGroup.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
		commissionsGroup.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
		amountProgress.setVisibility(!show ? View.INVISIBLE : View.VISIBLE);
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