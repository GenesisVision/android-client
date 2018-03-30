package vision.genesis.clientapp.feature.main.program.invest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.ui.AmountTextView;
import vision.genesis.clientapp.ui.NumericKeyboardView;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

public class InvestProgramActivity extends BaseSwipeBackActivity implements InvestProgramView
{
	private static final String EXTRA_REQUEST = "extra_request";

	public static void startWith(Activity activity, ProgramRequest request) {
		Intent intent = new Intent(activity, InvestProgramActivity.class);
		intent.putExtra(EXTRA_REQUEST, request);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.group_progress_bar)
	public ViewGroup progressBarGroup;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.balance_program_currency)
	public TextView balanceProgramCurrency;

	@BindView(R.id.balance_program_currency_currency)
	public TextView balanceProgramCurrencyCurrency;

	@BindView(R.id.balance_currency)
	public TextView balanceCurrency;

	@BindView(R.id.label_my_balance)
	public TextView myBalanceLabel;

	@BindView(R.id.textview_amount_hint)
	public TextView amountHintTextView;

	@BindView(R.id.textview_amount)
	public AmountTextView amountTextView;

	@BindView(R.id.amount_currency)
	public TextView amountCurrency;

	@BindView(R.id.amount_program_currency)
	public TextView amountProgramCurrency;

	@BindView(R.id.amount_program_currency_currency)
	public TextView amountProgramCurrencyCurrency;

	@BindView(R.id.label_enter_amount)
	public TextView enterAmountLabel;

	@BindView(R.id.button_invest)
	public View investButton;

	@BindView(R.id.keyboard)
	public NumericKeyboardView keyboard;

	@InjectPresenter
	InvestProgramPresenter investProgramPresenter;

	private ProgramRequest investRequest;

	@OnClick(R.id.button_invest)
	public void onInvestClicked() {
		investProgramPresenter.onInvestClicked();
	}

	@OnClick(R.id.balance)
	public void onBalanceClicked() {
		investProgramPresenter.onAvailableClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_invest);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			investRequest = getIntent().getExtras().getParcelable(EXTRA_REQUEST);
			investProgramPresenter.setInvestRequest(investRequest);

			initToolbar();
			initAmountTextView();
			setFonts();
			setProgramCurrency();
		}
		else {
			Timber.e("Passed empty request to InvestProgramActivity");
			onBackPressed();
		}
	}

	private void initToolbar() {
		toolbar.setWhite();
		toolbar.setTitle(getString(R.string.invest_to_program));
		toolbar.setSubtitle(investRequest.programName);
		toolbar.addLeftButton(R.drawable.back_arrow, () -> investProgramPresenter.onBackClicked());
	}

	private void initAmountTextView() {
		amountTextView.setKeyboard(keyboard);
		amountTextView.setMaxDecimalDigits(WalletManager.GVT_MAX_DECIMAL_POINT_DIGITS);
		amountTextView.setAmountChangeListener(new AmountTextView.AmountChangeListener()
		{
			@Override
			public void onAmountChanged(double newAmount) {
				investProgramPresenter.onAmountChanged(newAmount);
			}

			@Override
			public void onAmountCleared() {
				investProgramPresenter.onAmountCleared();
			}
		});
	}

	private void setFonts() {
		balance.setTypeface(TypefaceUtil.light(this));
		balanceProgramCurrency.setTypeface(TypefaceUtil.light(this));
		balanceCurrency.setTypeface(TypefaceUtil.bold(this));
		myBalanceLabel.setTypeface(TypefaceUtil.bold(this));
		enterAmountLabel.setTypeface(TypefaceUtil.bold(this));
		amountHintTextView.setTypeface(TypefaceUtil.light(this));
		amountCurrency.setTypeface(TypefaceUtil.bold(this));
		amountProgramCurrency.setTypeface(TypefaceUtil.light(this));

		balanceProgramCurrencyCurrency.setTypeface(TypefaceUtil.bold(this));
		amountProgramCurrencyCurrency.setTypeface(TypefaceUtil.bold(this));
	}

	private void setProgramCurrency() {
		balanceProgramCurrencyCurrency.setText(investRequest.programCurrency);
		amountProgramCurrencyCurrency.setText(investRequest.programCurrency);
	}

	@Override
	public void setInvestButtonEnabled(boolean enabled) {
		investButton.setEnabled(enabled);
	}

	@Override
	public void setAmount(double amount) {
		amountTextView.setText(String.valueOf(amount));
	}

	@Override
	public void setAvailable(double availableFunds) {
		balance.setText(StringFormatUtil.formatAmount(availableFunds, 0, 4));
	}

	@Override
	public void showAvailableProgress(boolean show) {

	}

	@Override
	public void setProgramCurrencyBalance(Double fiatBalance) {
		balanceProgramCurrency.setText(StringFormatUtil.formatAmount(fiatBalance, 2, 8));
	}

	@Override
	public void setProgramCurrencyAmount(Double fiatAmount) {
		amountProgramCurrency.setText(StringFormatUtil.formatAmount(fiatAmount, 2, 8));
	}

	@Override
	public void showAmountHint(boolean show) {
		amountHintTextView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		amountTextView.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void setKeyboardKeysEnabled(boolean enabled) {
		keyboard.disableAllKeysExceptBackspace(!enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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