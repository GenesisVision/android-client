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
import vision.genesis.clientapp.feature.main.fund.invest.confirm.ConfirmFundInvestBottomSheetFragment;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ImageUtils;
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

	@BindView(R.id.fund_name)
	public TextView fundName;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.group_amount)
	public ViewGroup amountGroup;

	@BindView(R.id.label_amount_to_invest)
	public TextView amountToInvestLabel;

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

	@BindView(R.id.entry_fee)
	public TextView entryFee;

	@BindView(R.id.group_commissions)
	public ViewGroup commissionsGroup;

	@BindView(R.id.gv_commission)
	public TextView gvCommission;

	@BindView(R.id.investment_amount)
	public TextView investmentAmount;

	@BindView(R.id.button_continue)
	public PrimaryButton continueButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.amount_progress_bar)
	public ProgressBar amountProgress;

	@InjectPresenter
	InvestFundPresenter investFundPresenter;

	private List<WalletData> walletsFrom;

	private FundRequest request;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_wallet)
	public void onWalletClicked() {
		SelectWalletBottomSheetFragment fragment = new SelectWalletBottomSheetFragment();
		fragment.setData(getString(R.string.select_wallet_currency), walletsFrom);
		fragment.setListener(investFundPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@OnClick(R.id.group_edittext_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
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
			request = getIntent().getExtras().getParcelable(EXTRA_FUND_REQUEST);
			if (request != null) {
				investFundPresenter.setFundRequest(request);

				setFundName(request.getFundName());

				setFonts();

				setTextListener();
				return;
			}
		}
		Timber.e("Passed empty request to InvestFundActivity");
		onBackPressed();

	}

	private void setFundName(String fundName) {
		this.fundName.setText(fundName);
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
	public void setWalletsFrom(List<WalletData> wallets) {
		this.walletsFrom = wallets;
	}

	@Override
	public void setWalletFrom(WalletData wallet, CurrencyEnum baseCurrency) {
		this.iconFrom.setImageURI(ImageUtils.getImageUri(wallet.getLogo()));
		this.walletFrom.setText(wallet.getTitle());
		this.availableFrom.setText(String.format(Locale.getDefault(), "%s %s",
				getString(R.string.available_in_wallet),
				StringFormatUtil.getValueString(wallet.getAvailable(), wallet.getCurrency().getValue())));
		this.amountCurrency.setText(wallet.getCurrency().getValue());
		this.baseCurrencyAmount.setVisibility(
				wallet.getCurrency().getValue().equals(baseCurrency.getValue())
						? View.GONE
						: View.VISIBLE);
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
	public void showAmountProgress(boolean show) {
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