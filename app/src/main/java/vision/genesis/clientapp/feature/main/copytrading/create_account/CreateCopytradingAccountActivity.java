package vision.genesis.clientapp.feature.main.copytrading.create_account;

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
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/06/2019.
 */

public class CreateCopytradingAccountActivity extends BaseSwipeBackActivity implements CreateCopytradingAccountView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, SubscriptionSettingsModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), CreateCopytradingAccountActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.account_currency)
	public TextView accountCurrencyText;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.group_amount)
	public ViewGroup amountGroup;

	@BindView(R.id.amount_to_deposit_label)
	public TextView amountToDepositLabel;

	@BindView(R.id.icon_from)
	public SimpleDraweeView iconFrom;

	@BindView(R.id.wallet_from)
	public TextView walletFrom;

	@BindView(R.id.available_from)
	public TextView availableFrom;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.amount_currency)
	public TextView amountCurrency;

	@BindView(R.id.max)
	public TextView max;

	@BindView(R.id.base_currency_amount)
	public TextView baseCurrencyAmount;

	@BindView(R.id.button_next)
	public PrimaryButton nextButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.amount_progress_bar)
	public ProgressBar amountProgress;

	@InjectPresenter
	CreateCopytradingAccountPresenter createCopytradingAccountPresenter;

	private SubscriptionSettingsModel model;

	private List<WalletData> walletsFrom;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_wallet)
	public void onWalletClicked() {
		SelectWalletBottomSheetFragment fragment = new SelectWalletBottomSheetFragment();
		fragment.setData(getString(R.string.select_wallet), walletsFrom);
		fragment.setListener(createCopytradingAccountPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@OnClick(R.id.amount_to_deposit_label)
	public void onAmountToDepositLabelClicked() {
		createCopytradingAccountPresenter.onAmountToDepositLabelClicked();
	}

	@OnClick(R.id.group_edittext_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		createCopytradingAccountPresenter.onMaxClicked();
	}

	@OnClick(R.id.button_next)
	public void onNextButtonClicked() {
		createCopytradingAccountPresenter.onNextClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_create_copytrading_account);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			if (model != null) {
				createCopytradingAccountPresenter.setModel(model);

				setAccountCurrency(model.getMinDepositCurrency());
				setFonts();

				setTextListener();
				return;
			}
		}
		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setAccountCurrency(String accountCurrency) {
		this.accountCurrencyText.setText(accountCurrency);
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> createCopytradingAccountPresenter.onAmountChanged(charSequence.toString()));
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		max.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setWalletsFrom(List<WalletData> wallets) {
		this.walletsFrom = wallets;
	}

	@Override
	public void setWalletFrom(WalletData wallet) {
		this.iconFrom.setImageURI(ImageUtils.getImageUri(wallet.getLogoUrl()));
		this.walletFrom.setText(wallet.getTitle());
		this.availableFrom.setText(String.format(Locale.getDefault(), "%s %s",
				getString(R.string.available_in_wallet),
				StringFormatUtil.getValueString(wallet.getAvailable(), wallet.getCurrency().getValue())));
		this.amountCurrency.setText(wallet.getCurrency().getValue());
		this.baseCurrencyAmount.setVisibility(
				wallet.getCurrency().getValue().equals(model.getMinDepositCurrency())
						? View.GONE
						: View.VISIBLE);
	}

	@Override
	public void setMinDepositAmount(Double minDepositAmount, String currency) {
		amountToDepositLabel.setText(String.format(Locale.getDefault(), "%s (min %s)",
				getString(R.string.amount_to_deposit), StringFormatUtil.formatCurrencyAmount(minDepositAmount, currency)));
	}

	@Override
	public void setAmount(String amountText) {
		this.amount.setText(amountText);
		this.amount.setSelection(this.amount.getText().length(), this.amount.getText().length());
	}

	@Override
	public void setAmountBase(String amountBaseString) {
		this.baseCurrencyAmount.setText(amountBaseString);
	}

	@Override
	public void setNextButtonEnabled(boolean enabled) {
		nextButton.setEnabled(enabled);
	}

	@Override
	public void showSubscriptionSettings(SubscriptionSettingsModel model) {
//		SubscriptionSettingsFragment.startWith(this, model, false);
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
		amountGroup.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
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

	private void showSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		amount.requestFocus();
		if (imm != null) {
			imm.showSoftInput(amount, 0);
		}
	}
}