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
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.WalletWithdrawalInfo;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.wallet.withdraw.confirm.ConfirmWalletWithdrawBottomSheetFragment;
import vision.genesis.clientapp.model.WalletModel;
import vision.genesis.clientapp.model.WithdrawalRequest;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/27/18.
 */

public class WithdrawWalletActivity extends BaseSwipeBackActivity implements WithdrawWalletView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, WalletModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), WithdrawWalletActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.wallet_icon)
	public SimpleDraweeView walletIcon;

	@BindView(R.id.wallet_name)
	public TextView walletName;

	@BindView(R.id.wallet_balance)
	public TextView walletBalance;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.max)
	public TextView max;

	@BindView(R.id.edittext_address)
	public EditText address;

	@BindView(R.id.scan_qr)
	public TextView scanQr;

	@BindView(R.id.address_error)
	public TextView addressError;

	@BindView(R.id.fee_amount)
	public TextView feeAmount;

	@BindView(R.id.label_final_amount)
	public TextView finalAmountLabel;

	@BindView(R.id.final_amount)
	public TextView finalAmount;

	@BindView(R.id.button_continue)
	public PrimaryButton continueButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	WithdrawWalletPresenter withdrawWalletPresenter;

	private WalletModel model;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		withdrawWalletPresenter.onMaxClicked();
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

		if (getIntent().getExtras() != null) {
			model = Objects.requireNonNull(getIntent().getExtras().getParcelable(EXTRA_MODEL));
			withdrawWalletPresenter.setModel(model);
		}

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
	public void setWalletInfo(WalletWithdrawalInfo wallet) {
		this.walletIcon.setImageURI(ImageUtils.getImageUri(wallet.getLogo()));
		this.walletName.setText(wallet.getDescription());
		this.walletBalance.setText(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.formatCurrencyAmount(wallet.getAvailableToWithdrawal(), wallet.getCurrency().getValue()),
				wallet.getCurrency().getValue()));
	}

	@Override
	public void setAmount(String amountText) {
		this.amount.setText(amountText);
		this.amount.setSelection(amountText.length(), amountText.length());
	}

	@Override
	public void setFeeAmount(String feeAmountText) {
		this.feeAmount.setText(feeAmountText);
	}

	@Override
	public void setFinalAmountLabel(String finalAmountLabelString) {
		this.finalAmountLabel.setText(finalAmountLabelString);
	}

	@Override
	public void setFinalAmount(String finalAmountString) {
		this.finalAmount.setText(finalAmountString);
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