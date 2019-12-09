package vision.genesis.clientapp.feature.main.wallet.transfer_funds;

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
import vision.genesis.clientapp.model.TransferFundsModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/02/2019.
 */

public class TransferFundsActivity extends BaseSwipeBackActivity implements TransferWalletView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, TransferFundsModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), TransferFundsActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.first_icon)
	public SimpleDraweeView firstIcon;

	@BindView(R.id.first_name)
	public TextView firstName;

	@BindView(R.id.first_balance)
	public TextView firstBalance;

	@BindView(R.id.label_second)
	public TextView labelSecond;

	@BindView(R.id.second_icon)
	public SimpleDraweeView secondIcon;

	@BindView(R.id.second_name)
	public TextView secondName;

	@BindView(R.id.second_balance)
	public TextView secondBalance;

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

	@BindView(R.id.converting_info)
	public TextView convertingInfo;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.rate_progress_bar)
	public ProgressBar rateProgress;

	@BindView(R.id.button_progress_bar)
	public ProgressBar buttonProgress;

	@BindView(R.id.arrow_down)
	public View arrowDown;

	@BindView(R.id.arrow_up)
	public View arrowUp;

	@InjectPresenter
	TransferFundsPresenter presenter;

	private List<WalletData> wallets = new ArrayList<>();

	private TransferFundsModel model;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_second_asset)
	public void onSecondAssetClicked() {
		SelectWalletBottomSheetFragment fragment = new SelectWalletBottomSheetFragment();
		fragment.setData(getString(R.string.select_wallet), wallets);
		fragment.setListener(presenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@OnClick(R.id.group_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		presenter.onMaxClicked();
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		presenter.onConfirmClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_transfer_funds);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			if (model != null) {
				presenter.setModel(model);

				updateView();

				setFonts();
				setTextListener();

				return;
			}
		}
		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		max.setTypeface(TypefaceUtil.semibold());
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> presenter.onAmountChanged(charSequence.toString()));
	}

	private void updateView() {
		this.firstIcon.setImageURI(ImageUtils.getImageUri(model.getLogo()));
		this.firstName.setText(model.getTitle());
		this.firstBalance.setText(StringFormatUtil.getValueString(model.getAvailable(), model.getCurrency()));
		this.arrowDown.setVisibility(model.getTransferDirection().equals(TransferFundsModel.TransferDirection.WITHDRAW) ? View.VISIBLE : View.GONE);
		this.arrowUp.setVisibility(model.getTransferDirection().equals(TransferFundsModel.TransferDirection.DEPOSIT) ? View.VISIBLE : View.GONE);
		this.labelSecond.setText(getString(model.getTransferDirection().equals(TransferFundsModel.TransferDirection.WITHDRAW)
				? R.string.to
				: R.string.from));

		if (model.getTransferDirection().equals(TransferFundsModel.TransferDirection.WITHDRAW)) {
			this.amountCurrency.setText(model.getCurrency());
		}
	}

	@Override
	public void setWallets(List<WalletData> wallets) {
		this.wallets = wallets;
	}

	@Override
	public void setWallet(WalletData wallet) {
		this.secondIcon.setImageURI(ImageUtils.getImageUri(wallet.getLogo()));
		this.secondName.setText(wallet.getTitle());
		this.secondBalance.setText(String.format(Locale.getDefault(), "%s %s %s",
				getString(R.string.available),
				StringFormatUtil.formatCurrencyAmount(wallet.getAvailable(), wallet.getCurrency().getValue()),
				wallet.getCurrency().getValue()));
		if (model.getTransferDirection().equals(TransferFundsModel.TransferDirection.DEPOSIT)) {
			this.amountCurrency.setText(wallet.getCurrency().getValue());
		}
		this.rate.setVisibility(!model.getCurrency().equals(wallet.getCurrency().getValue()) ? View.VISIBLE : View.INVISIBLE);
		this.convertingInfo.setVisibility(!model.getCurrency().equals(wallet.getCurrency().getValue()) ? View.VISIBLE : View.INVISIBLE);
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