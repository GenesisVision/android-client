package vision.genesis.clientapp.feature.main.wallet.transaction_details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.joda.time.DateTime;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.ProgramTransactionDetails;
import io.swagger.client.model.SignalFee;
import io.swagger.client.model.TransactionDetails;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.ButtonsView;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.ExternalAddressView;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.LabelView;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.ProgramView;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.RateView;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.StatusView;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.ValueView;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.WalletView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */

public class TransactionDetailsActivity extends BaseSwipeBackActivity implements TransactionDetailsView
{
	private static final String EXTRA_TRANSACTION_ID = "extra_transaction_id";

	private static final String EXTRA_TRANSACTION_TYPE = "extra_transaction_type";

	private static final String EXTRA_TRANSACTION_DATE = "extra_transaction_date";

	public static void startWith(Activity activity, UUID transactionId, String transactionType, DateTime transactionDate) {
		Intent intent = new Intent(activity.getApplicationContext(), TransactionDetailsActivity.class);
		intent.putExtra(EXTRA_TRANSACTION_ID, transactionId);
		intent.putExtra(EXTRA_TRANSACTION_TYPE, transactionType);
		intent.putExtra(EXTRA_TRANSACTION_DATE, transactionDate);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.type)
	public TextView type;

	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.content)
	public LinearLayout content;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	TransactionDetailsPresenter transactionDetailsPresenter;

	private TransactionDetails details;

	private ButtonsView buttonsView;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_transaction_details);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			transactionDetailsPresenter.setTransactionId((UUID) getIntent().getExtras().getSerializable(EXTRA_TRANSACTION_ID));
			setTransactionType(Objects.requireNonNull(getIntent().getExtras().getString(EXTRA_TRANSACTION_TYPE)));
			setDate((DateTime) getIntent().getExtras().getSerializable(EXTRA_TRANSACTION_DATE));

			setFonts();
		}
		else {
			Timber.e("Passed empty params to %s", getClass().getSimpleName());
			onBackPressed();
		}
	}

	private void setDate(DateTime date) {
		this.date.setText(DateTimeUtil.formatEventDateTime(date));
	}

	private void setTransactionType(String type) {
		if (type.equals(TransactionDetails.TypeEnum.SUBSCRIBESIGNAL.getValue()))
			this.type.setText(getString(R.string.paying_signal_fee));
		else if (type.equals(TransactionDetails.TypeEnum.PLATFORM.getValue()))
			this.type.setText(getString(R.string.platform_fee));
		else if (type.equals(TransactionDetails.TypeEnum.DEPOSITSIGNAL.getValue()))
			this.type.setText(getString(R.string.deposit_to_signal_account));
		else if (type.equals(TransactionDetails.TypeEnum.WITHDRAWALSIGNAL.getValue()))
			this.type.setText(getString(R.string.withdrawal_from_signal_account));
		else
			this.type.setText(type);
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setDetails(TransactionDetails details) {
		this.details = details;
		switch (details.getType()) {
			case INVESTING:
				if (details.getProgramDetails().getProgramType().equals(ProgramTransactionDetails.ProgramTypeEnum.PROGRAM))
					createProgramInvestingDetails();
				else if (details.getProgramDetails().getProgramType().equals(ProgramTransactionDetails.ProgramTypeEnum.FUND))
					createFundInvestingDetails();
				break;
			case WITHDRAWAL:
				if (details.getProgramDetails().getProgramType().equals(ProgramTransactionDetails.ProgramTypeEnum.PROGRAM))
					createProgramWithdrawalDetails();
				else if (details.getProgramDetails().getProgramType().equals(ProgramTransactionDetails.ProgramTypeEnum.FUND))
					createFundWithdrawalDetails();
				break;
			case EXTERNALWITHDRAWAL:
				createExternalWithdrawalDetails();
				break;
			case EXTERNALDEPOSIT:
				createExternalDepositDetails();
				break;
			case CONVERTING:
				createConvertingDetails();
				break;
			case CLOSE:
				createProgramCloseDetails();
				break;
			case PROFIT:
				createProgramProfitDetails();
				break;
			case SUBSCRIBESIGNAL:
				createSubscribeSignalProviderDetails();
				break;
			case DEPOSITSIGNAL:
				createDepositSignalDetails();
				break;
			case WITHDRAWALSIGNAL:
				createWithdrawalSignalDetails();
				break;
			case PLATFORM:
				createPlatformDetails();
				break;
			case PLATFORMFEE:
				createPlatformFeeDetails();
				break;
			case OPEN:
			case RECEIVESIGNAL:
			default:
				createUnsupportedTransactionType();
				break;
		}
		addEmptyView();
	}

	@Override
	public void setEmailResent() {
		if (buttonsView != null)
			buttonsView.onEmailResent();
	}

	private void createProgramInvestingDetails() {
		addWallet(getString(R.string.from), details.getCurrencyLogo(), details.getCurrencyName());
		addWrittenOffWallet();
		addProgramView(getString(R.string.to_the_program));
		addEntryFee();
		addGvCommission();
		addStatus();
		addInvestmentAmount();
	}

	private void createFundInvestingDetails() {
		addWallet(getString(R.string.from), details.getCurrencyLogo(), details.getCurrencyName());
		addWrittenOffWallet();
		addProgramView(getString(R.string.to_the_fund));
		addEntryFee();
		addGvCommission();
		addStatus();
		addInvestmentAmount();
	}

	private void createProgramWithdrawalDetails() {
		addProgramView(getString(R.string.from_the_program));
		addStatus();
		addWithdrawalAmount();
	}

	private void createFundWithdrawalDetails() {
		addProgramView(getString(R.string.from_the_fund));
		addExitFee();
		addStatus();
		addWithdrawalAmount();
	}

	private void createProgramCloseDetails() {
		addProgramView(getString(R.string.program));
		addAmount();
		addStatus();
	}

	private void createProgramProfitDetails() {
		addProgramView(getString(R.string.program));
		addSuccessFee();
		addGvCommission();
		addStatus();
		addAmount();
	}

	private void createSubscribeSignalProviderDetails() {
		addProgramView(getString(R.string.program));
		addSignalFees();
		addStatus();
		addAmount();
	}

	private void createDepositSignalDetails() {
		addWallet(getString(R.string.to), details.getCurrencyLogo(), details.getCurrencyName());
		addStatus();
		addAmount();
	}

	private void createWithdrawalSignalDetails() {
		addWallet(getString(R.string.from), details.getCurrencyLogo(), details.getCurrencyName());
		addStatus();
		addAmount();
	}

	private void createPlatformDetails() {
		addWallet(getString(R.string.from), details.getCurrencyLogo(), details.getCurrencyName());
		addStatus();
		addAmount();
	}

	private void createPlatformFeeDetails() {
		addWallet(getString(R.string.from), details.getCurrencyLogo(), details.getCurrencyName());
		addStatus();
		addAmount();
	}

	private void createConvertingDetails() {
		addWallet(getString(R.string.from), details.getCurrencyLogo(), details.getCurrencyName());
		addWrittenOffWallet();
		addWallet(getString(R.string.to), details.getConvertingDetails().getCurrencyToLogo(), details.getConvertingDetails().getCurrencyToName());
		addCreditedToWallet();
		addRate();
		addStatus();
	}

	private void createExternalWithdrawalDetails() {
		addWallet(getString(R.string.from), details.getCurrencyLogo(), details.getCurrencyName());
		addAmount();
		addExternalAddress(getString(R.string.to_external_address), details.getExternalTransactionDetails().getFromAddress());
		addStatus();
		if (details.getExternalTransactionDetails().isIsEnableActions())
			addButtons();
	}

	private void createExternalDepositDetails() {
		addExternalAddress(getString(R.string.from_external_address), details.getExternalTransactionDetails().getFromAddress());
		addAmount();
		addWallet(getString(R.string.to), details.getCurrencyLogo(), details.getCurrencyName());
		addStatus();
	}

	private void createUnsupportedTransactionType() {
		LabelView view = new LabelView(this);
		view.setData(getString(R.string.text_unsupported_transaction_type));
		addView(view);
	}

	private void addWallet(String label, String logo, String name) {
		WalletView view = new WalletView(this);
		view.setData(label, logo, name);
		addView(view);
	}

	private void addExternalAddress(String label, String address) {
		ExternalAddressView view = new ExternalAddressView(this);
		view.setData(label, address);
		addView(view);
	}

	private void addProgramView(String label) {
		ProgramView view = new ProgramView(this);
		view.setData(label,
				details.getProgramDetails().getLogo(),
				details.getProgramDetails().getColor(),
				details.getProgramDetails().getLevel(),
				details.getProgramDetails().getTitle(),
				details.getProgramDetails().getManagerName());
		addView(view);
	}

	private void addEntryFee() {
		ValueView view = new ValueView(this);
		String value = String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(details.getProgramDetails().getEntryFeePercent(), 0, 2),
				StringFormatUtil.getValueString(details.getProgramDetails().getEntryFee(), details.getCurrency().getValue()));
		view.setData(getString(R.string.entry_fee), value);
		addView(view);
	}

	private void addExitFee() {
		ValueView view = new ValueView(this);
		String value = String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(details.getProgramDetails().getExitFeePercent(), 0, 2),
				StringFormatUtil.getValueString(details.getProgramDetails().getExitFee(), details.getCurrency().getValue()));
		view.setData(getString(R.string.exit_fee), value);
		addView(view);
	}

	private void addSuccessFee() {
		ValueView view = new ValueView(this);
		String value = String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(details.getProgramDetails().getSuccessFeePercent(), 0, 2),
				StringFormatUtil.getValueString(details.getProgramDetails().getSuccessFee(), details.getProgramDetails().getSuccessFeeCurrency().getValue()));
		view.setData(StringFormatUtil.capitalize(getString(R.string.success_fee)), value);
		addView(view);
	}

	private void addGvCommission() {
		ValueView view = new ValueView(this);
		String value = String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(details.getGvCommissionPercent(), 0, 2),
				StringFormatUtil.getValueString(details.getGvCommission(), details.getGvCommissionCurrency().getValue()));
		view.setData(getString(R.string.gv_commission), value);
		addView(view);
	}

	private void addSignalFees() {
		for (SignalFee fee : details.getSignalFees()) {
			addSignalFee(fee);
		}
	}

	private void addSignalFee(SignalFee fee) {
		int titleResId = 0;
		String value = String.format(Locale.getDefault(), "%s%s",
				StringFormatUtil.formatCurrencyAmount(fee.getValue(), fee.getCurrency().getValue()),
				fee.getCurrency().getValue());
		if (fee.getType().equals(SignalFee.TypeEnum.MANAGERSIGNALMASTERVOLUMEFEE)) {
			titleResId = R.string.manager_volume_fee;
		}
		else if (fee.getType().equals(SignalFee.TypeEnum.MANAGERSIGNALMASTERSUCCESSFEE)) {
			titleResId = R.string.manager_success_fee;
		}
		else if (fee.getType().equals(SignalFee.TypeEnum.GVSIGNALSUCCESSFEE)) {
			titleResId = R.string.platform_success_fee;
		}
		ValueView view = new ValueView(this);
		view.setData(getString(titleResId), value);
		addView(view);
	}

	private void addInvestmentAmount() {
		ValueView view = new ValueView(this);
		Double amount = Math.abs(details.getAmount() - details.getAmount() *
				(details.getProgramDetails().getEntryFeePercent() + details.getGvCommissionPercent()) / 100);
		String value = StringFormatUtil.getValueString(amount, details.getCurrency().getValue());
		view.setData(getString(R.string.investment_amount), value);
		view.setValueParams(true, 20);
		addView(view);
	}

	private void addWithdrawalAmount() {
		ValueView view = new ValueView(this);
		String value = StringFormatUtil.getValueString(details.getAmount(), details.getCurrency().getValue());
		view.setData(getString(R.string.withdrawal_amount), value);
		view.setValueParams(true, 20);
		addView(view);
	}

	private void addAmount() {
		ValueView view = new ValueView(this);
		String value = StringFormatUtil.getValueString(details.getAmount(), details.getCurrency().getValue());
		view.setData(getString(R.string.amount), value);
		view.setValueParams(true, 20);
		addView(view);
	}

	private void addWrittenOffWallet() {
		ValueView view = new ValueView(this);
		String value = StringFormatUtil.getValueString(details.getAmount(), details.getCurrency().getValue());
		view.setData(getString(R.string.written_off_wallet), value);
		addView(view);
	}

	private void addCreditedToWallet() {
		ValueView view = new ValueView(this);
		String value = StringFormatUtil.getValueString(details.getConvertingDetails().getAmountTo(),
				details.getConvertingDetails().getCurrencyTo().getValue());
		view.setData(getString(R.string.credited_to_wallet), value);
		addView(view);
	}

	private void addRate() {
		RateView view = new RateView(this);
		view.setData(details);
		addView(view);
	}

	private void addStatus() {
		StatusView view = new StatusView(this);
		boolean needEmailConfirmation = false;
		if (details.getExternalTransactionDetails() != null)
			needEmailConfirmation = details.getExternalTransactionDetails().isIsEnableActions();
		view.setData(details.getStatus(), needEmailConfirmation);
		addView(view);
	}

	private void addButtons() {
		buttonsView = new ButtonsView(this);
		buttonsView.setListener(transactionDetailsPresenter);
		addView(buttonsView);
	}

	private void addEmptyView() {
		View view = new View(this);
		view.setMinimumHeight(50);
		addView(view);
	}

	private void addView(View view) {
		content.addView(view);
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
		lp.setMargins(0,
				(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
						GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics()),
				0,
				0);
		view.setLayoutParams(lp);
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
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}