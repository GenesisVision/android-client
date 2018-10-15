package vision.genesis.clientapp.feature.main.wallet.withdraw;

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
import io.swagger.client.model.WalletWithdrawalInfo;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.wallet.withdraw.confirm.ConfirmWalletWithdrawBottomSheetFragment;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.WithdrawalRequest;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/27/18.
 */

public class WithdrawWalletActivity extends BaseSwipeBackActivity implements WithdrawWalletView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), WithdrawWalletActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.available_in_wallet)
	public TextView availableInWallet;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.max)
	public TextView max;

	@BindView(R.id.wallet)
	public TextView wallet;

	@BindView(R.id.base_currency_amount)
	public TextView baseCurrencyAmount;

	@BindView(R.id.edittext_address)
	public EditText address;

	@BindView(R.id.scan_qr)
	public TextView scanQr;

	@BindView(R.id.address_error)
	public TextView addressError;

	@BindView(R.id.fee_amount)
	public TextView feeAmount;

	@BindView(R.id.button_continue)
	public PrimaryButton continueButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	WithdrawWalletPresenter withdrawWalletPresenter;

	private ArrayList<String> walletsOptions;

	private Integer selectedWalletsPosition = 0;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.available_in_wallet)
	public void onAvailableClicked() {
		withdrawWalletPresenter.onAvailableClicked();
	}

	@OnClick(R.id.group_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		withdrawWalletPresenter.onMaxClicked();
	}

	@OnClick(R.id.group_wallet)
	public void onWalletClicked() {
		SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
				getString(R.string.select_wallet_currency), walletsOptions, selectedWalletsPosition);
		fragment.setListener(withdrawWalletPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@OnClick(R.id.scan_qr)
	public void onScanQrClicked() {

	}

	@OnClick(R.id.button_continue)
	public void onContinueClicked() {
		withdrawWalletPresenter.onContinueClicked(address.getText().toString());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_withdraw_wallet);

		ButterKnife.bind(this);

		setFonts();

		setTextListener();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		max.setTypeface(TypefaceUtil.semibold());
		scanQr.setTypeface(TypefaceUtil.semibold());
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> withdrawWalletPresenter.onAmountChanged(charSequence.toString()));
		RxTextView.textChanges(address)
				.subscribe(charSequence -> showAddressNotValidError(false));
	}

	@Override
	public void setAvailableInWallet(Double availableInWallet) {
		this.availableInWallet.setText(String.format(Locale.getDefault(), "%s GVT",
				StringFormatUtil.formatCurrencyAmount(availableInWallet, CurrencyEnum.GVT.toString())));
	}

	@Override
	public void setWalletsOptions(ArrayList<String> walletsOptions) {
		this.walletsOptions = walletsOptions;
	}

	@Override
	public void setWalletInfo(WalletWithdrawalInfo selectedWallet, String walletName, Integer position) {
		this.wallet.setText(walletName);
		this.selectedWalletsPosition = position;
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
	public void setFeeAmount(String feeAmountText) {
		this.feeAmount.setText(feeAmountText);
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
	public void showConfirmDialog(WithdrawalRequest withdrawalRequest) {
		ConfirmWalletWithdrawBottomSheetFragment bottomSheetDialog = new ConfirmWalletWithdrawBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setData(withdrawalRequest);
		bottomSheetDialog.setListener(withdrawWalletPresenter);
	}

	@Override
	public void showAddressNotValidError(boolean show) {
		addressError.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	private void showSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		amount.requestFocus();
		if (imm != null) {
			imm.showSoftInput(amount, 0);
		}
	}
}