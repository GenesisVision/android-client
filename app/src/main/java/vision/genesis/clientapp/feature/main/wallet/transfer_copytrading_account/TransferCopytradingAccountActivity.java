package vision.genesis.clientapp.feature.main.wallet.transfer_copytrading_account;

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

import java.util.ArrayList;
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
import vision.genesis.clientapp.model.CopytradingAccountModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/02/2019.
 */

public class TransferCopytradingAccountActivity extends BaseSwipeBackActivity implements TransferCopytradingAccountView
{
	public static final String OPERATION_DEPOSIT = "operation_deposit";

	public static final String OPERATION_WITHDRAW = "operation_withdraw";

	private static final String EXTRA_MODEL = "extra_model";

	private static final String EXTRA_OPERATION = "extra_operation";

	public static void startWith(Activity activity, CopytradingAccountModel model, String operation) {
		Intent intent = new Intent(activity.getApplicationContext(), TransferCopytradingAccountActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.putExtra(EXTRA_OPERATION, operation);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.arrow_up)
	public View arrowUp;

	@BindView(R.id.arrow_down)
	public View arrowDown;

	@BindView(R.id.account_icon)
	public SimpleDraweeView accountIcon;

	@BindView(R.id.account_name)
	public TextView accountName;

	@BindView(R.id.account_balance)
	public TextView accountBalance;

	@BindView(R.id.label_from_to)
	public TextView labelFromTo;

	@BindView(R.id.icon_from_to)
	public SimpleDraweeView iconFromTo;

	@BindView(R.id.wallet_from_to)
	public TextView walletFromTo;

	@BindView(R.id.available_from_to)
	public TextView availableFromTo;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.amount_currency)
	public TextView amountCurrency;

	@BindView(R.id.max)
	public TextView max;

	@BindView(R.id.rate)
	public TextView rate;

	@BindView(R.id.group_final_amount)
	public ViewGroup finalAmountGroup;

	@BindView(R.id.final_amount)
	public TextView finalAmount;

	@BindView(R.id.text_funds_converted)
	public TextView fundsConvertedText;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.rate_progress_bar)
	public ProgressBar rateProgress;

	@BindView(R.id.button_progress_bar)
	public ProgressBar buttonProgress;

	@InjectPresenter
	TransferCopytradingAccountPresenter transferCopytradingAccountPresenter;

	private List<WalletData> walletsTo = new ArrayList<>();

	private CopytradingAccountModel model;

	private String operation;

	private boolean showRate = true;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_wallet)
	public void onWalletClicked() {
		SelectWalletBottomSheetFragment fragment = new SelectWalletBottomSheetFragment();
		fragment.setData(getString(R.string.select_wallet), walletsTo);
		fragment.setListener(transferCopytradingAccountPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@OnClick(R.id.group_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		transferCopytradingAccountPresenter.onMaxClicked();
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		transferCopytradingAccountPresenter.onConfirmClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_transfer_copytrading_account);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			operation = getIntent().getExtras().getString(EXTRA_OPERATION);
			if (model != null && operation != null) {
				transferCopytradingAccountPresenter.setData(model, operation);

				setAccountInfo(model);
				updateFields(model, operation);
				setFonts();
				setTextListener();
				return;
			}
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();

	}

	private void updateFields(CopytradingAccountModel account, String operation) {
		switch (operation) {
			case OPERATION_DEPOSIT:
				title.setText(getString(R.string.add_funds));
				arrowUp.setVisibility(View.VISIBLE);
				arrowDown.setVisibility(View.GONE);
				labelFromTo.setText(getString(R.string.from));
				break;
			case OPERATION_WITHDRAW:
				title.setText(getString(R.string.withdraw));
				arrowUp.setVisibility(View.GONE);
				arrowDown.setVisibility(View.VISIBLE);
				labelFromTo.setText(getString(R.string.to));
				this.amountCurrency.setText(account.getCurrency());
				break;
		}
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		max.setTypeface(TypefaceUtil.semibold());
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> transferCopytradingAccountPresenter.onAmountChanged(charSequence.toString()));
	}

	public void setAccountInfo(CopytradingAccountModel account) {
		this.accountIcon.setImageURI(ImageUtils.getImageUri(account.getLogo()));
		this.accountName.setText(account.getTitle());
		this.accountBalance.setText(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.formatCurrencyAmount(account.getAvailable(), account.getCurrency()),
				account.getCurrency()));
	}

	@Override
	public void setWallets(List<WalletData> walletsTo) {
		this.walletsTo = walletsTo;
	}

	@Override
	public void setSelectedWallet(WalletData wallet) {
		this.iconFromTo.setImageURI(ImageUtils.getImageUri(wallet.getLogoUrl()));
		this.walletFromTo.setText(wallet.getTitle());
		this.availableFromTo.setText(String.format(Locale.getDefault(), "%s %s %s",
				getString(R.string.available),
				StringFormatUtil.formatCurrencyAmount(wallet.getAvailable(), wallet.getCurrency().getValue()),
				wallet.getCurrency().getValue()));
		if (operation.equals(OPERATION_DEPOSIT)) {
			this.amountCurrency.setText(wallet.getCurrency().getValue());
		}
		showRate = !wallet.getCurrency().getValue().equals(model.getCurrency());
		fundsConvertedText.setVisibility(showRate ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void setAmount(String amountText) {
		this.amount.setText(amountText);
		this.amount.setSelection(amountText.length(), amountText.length());
	}

	@Override
	public void setRate(String rate) {
		this.rate.setText(rate);
	}

	@Override
	public void setFinalAmount(String finalAmountString) {
		this.finalAmount.setText(finalAmountString);
	}

	@Override
	public void setConfirmButtonEnabled(boolean enabled) {
		confirmButton.setEnabled(enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			content.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showRateProgress(boolean show) {
		if (show) {
			rate.setVisibility(View.INVISIBLE);
		}
		else if (showRate) {
			rate.setVisibility(View.VISIBLE);
		}
		finalAmountGroup.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
		rateProgress.setVisibility(!show ? View.INVISIBLE : View.VISIBLE);
	}

	@Override
	public void showButtonProgress(boolean show) {
		buttonProgress.setVisibility(show ? View.VISIBLE : View.GONE);
		confirmButton.setVisibility(!show ? View.VISIBLE : View.GONE);
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

	private void showSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		amount.requestFocus();
		if (imm != null) {
			imm.showSoftInput(amount, 0);
		}
	}
}