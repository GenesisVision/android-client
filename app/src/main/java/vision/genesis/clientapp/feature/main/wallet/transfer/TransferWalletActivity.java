package vision.genesis.clientapp.feature.main.wallet.transfer;

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
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.model.WalletModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/02/2019.
 */

public class TransferWalletActivity extends BaseSwipeBackActivity implements TransferWalletView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, WalletModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), TransferWalletActivity.class);
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

	@BindView(R.id.icon_to)
	public SimpleDraweeView iconTo;

	@BindView(R.id.wallet_to)
	public TextView walletTo;

	@BindView(R.id.available_to)
	public TextView availableTo;

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

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@BindView(R.id.rate_progress_bar)
	public ProgressBar rateProgress;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	TransferWalletPresenter transferWalletPresenter;

	private WalletModel model;

	private List<WalletData> walletsTo = new ArrayList<>();

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_wallet)
	public void onWalletClicked() {
		SelectWalletBottomSheetFragment fragment = new SelectWalletBottomSheetFragment();
		fragment.setData(getString(R.string.select_wallet_currency), walletsTo);
		fragment.setListener(transferWalletPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@OnClick(R.id.group_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		transferWalletPresenter.onMaxClicked();
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		transferWalletPresenter.onConfirmClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_transfer_wallet);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			model = Objects.requireNonNull(getIntent().getExtras().getParcelable(EXTRA_MODEL));
			transferWalletPresenter.setModel(model);
		}

		setFonts();

		setTextListener();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		max.setTypeface(TypefaceUtil.semibold());
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> transferWalletPresenter.onAmountChanged(charSequence.toString()));
	}

	@Override
	public void setWalletInfo(WalletData wallet) {
		this.walletIcon.setImageURI(ImageUtils.getImageUri(wallet.getLogo()));
		this.walletName.setText(wallet.getTitle());
		this.walletBalance.setText(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.formatCurrencyAmount(wallet.getAvailable(), wallet.getCurrency().getValue()),
				wallet.getCurrency().getValue()));
		this.amountCurrency.setText(wallet.getCurrency().getValue());
	}

	@Override
	public void setWalletsTo(List<WalletData> walletsTo) {
		this.walletsTo = walletsTo;
	}

	@Override
	public void setWalletTo(WalletData walletTo) {
		this.iconTo.setImageURI(ImageUtils.getImageUri(walletTo.getLogo()));
		this.walletTo.setText(walletTo.getTitle());
		this.availableTo.setText(String.format(Locale.getDefault(), "%s %s %s",
				getString(R.string.available),
				StringFormatUtil.formatCurrencyAmount(walletTo.getAvailable(), walletTo.getCurrency().getValue()),
				walletTo.getCurrency().getValue()));
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
		rate.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
		finalAmountGroup.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
		rateProgress.setVisibility(!show ? View.INVISIBLE : View.VISIBLE);
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