package vision.genesis.clientapp.feature.main.program.invest;

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

import org.joda.time.DateTime;

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
import vision.genesis.clientapp.feature.main.program.invest.confirm.ConfirmProgramInvestBottomSheetFragment;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

public class InvestProgramActivity extends BaseSwipeBackActivity implements InvestProgramView
{
	private static final String EXTRA_PROGRAM_REQUEST = "extra_program_request";

	public static void startWith(Activity activity, ProgramRequest programRequest) {
		Intent intent = new Intent(activity.getApplicationContext(), InvestProgramActivity.class);
		intent.putExtra(EXTRA_PROGRAM_REQUEST, programRequest);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.group_available_to_invest)
	public ViewGroup availableToInvestGroup;

	@BindView(R.id.available_to_invest)
	public TextView availableToInvest;

	@BindView(R.id.group_amount)
	public ViewGroup amountGroup;

	@BindView(R.id.amount_to_invest_label)
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

	@BindView(R.id.request_info)
	public TextView requestInfo;

	@BindView(R.id.group_commissions)
	public ViewGroup commissionsGroup;

	@BindView(R.id.management_fee)
	public TextView managementFee;

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
	InvestProgramPresenter investProgramPresenter;

	private List<WalletData> walletsFrom;

	private ProgramRequest request;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_wallet)
	public void onWalletClicked() {
		SelectWalletBottomSheetFragment fragment = new SelectWalletBottomSheetFragment();
		fragment.setData(getString(R.string.select_wallet), walletsFrom);
		fragment.setListener(investProgramPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@OnClick(R.id.group_edittext_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		investProgramPresenter.onMaxClicked();
	}

	@OnClick(R.id.button_continue)
	public void onContinueClicked() {
		investProgramPresenter.onContinueClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_invest);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			request = getIntent().getExtras().getParcelable(EXTRA_PROGRAM_REQUEST);
			if (request != null) {
				investProgramPresenter.setProgramRequest(request);

				updateView(request);
				setFonts();

				setTextListener();
				return;
			}
		}
		Timber.e("Passed empty request to InvestProgramActivity");
		onBackPressed();
	}

	private void updateView(ProgramRequest request) {
		this.programName.setText(request.getProgramName());
		if (request.isOwner()) {
			this.title.setText(getString(R.string.deposit));
			this.availableToInvestGroup.setVisibility(View.GONE);
			this.commissionsGroup.setVisibility(View.GONE);
		}
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> investProgramPresenter.onAmountChanged(charSequence.toString()));
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		max.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setAvailableToInvest(String availableToInvest) {
		this.availableToInvest.setText(availableToInvest);
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
				wallet.getCurrency().getValue().equals(request.getProgramCurrency())
						? View.GONE
						: View.VISIBLE);
	}

	@Override
	public void setMinInvestmentAmount(Double minInvestmentAmount) {
		amountToInvestLabel.setText(String.format(Locale.getDefault(), "%s (min %s)",
				getString(request.isOwner() ? R.string.amount_to_deposit : R.string.amount_to_invest),
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
	public void setRequestInfo(DateTime periodEnds) {
		this.requestInfo.setVisibility(View.VISIBLE);
		this.requestInfo.setText(String.format(Locale.getDefault(), getString(R.string.request_info_exchange_template), DateTimeUtil.formatRequestInfoDateTime(periodEnds)));
	}

	@Override
	public void setManagementFee(String managementFeeText) {
		this.managementFee.setText(managementFeeText);
	}

	@Override
	public void setGvCommission(String gvCommissionString) {
		this.gvCommission.setText(gvCommissionString);
	}

	@Override
	public void setInvestmentAmount(String investmentAmountText) {
		this.investmentAmount.setText(investmentAmountText);
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
		amountProgress.setVisibility(!show ? View.INVISIBLE : View.VISIBLE);
		if (request != null && !request.isOwner()) {
			commissionsGroup.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
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
	public void showConfirmDialog(ProgramRequest programRequest) {
		ConfirmProgramInvestBottomSheetFragment bottomSheetDialog = new ConfirmProgramInvestBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setData(programRequest);
		bottomSheetDialog.setListener(investProgramPresenter);
	}

	private void showSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		amount.requestFocus();
		if (imm != null) {
			imm.showSoftInput(amount, 0);
		}
	}
}