package vision.genesis.clientapp.feature.main.program.invest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
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

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.balance_fiat)
	public TextView balanceFiat;

	@BindView(R.id.balance_currency)
	public TextView balanceCurrency;

	@BindView(R.id.label_my_balance)
	public TextView myBalanceLabel;

	@BindView(R.id.textview_amount)
	public AmountTextView amountTextView;

	@BindView(R.id.amount_currency)
	public TextView amountCurrency;

	@BindView(R.id.amount_fiat)
	public TextView amountFiat;

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
			initListeners();
			setFonts();
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

	private void initListeners() {
		amountTextView.setKeyboard(keyboard);
		amountTextView.setAmountChangeListener(newAmount -> investProgramPresenter.onAmountChanged(newAmount));
	}

	private void setFonts() {
		balance.setTypeface(TypefaceUtil.light(this));
		balanceCurrency.setTypeface(TypefaceUtil.bold(this));
		myBalanceLabel.setTypeface(TypefaceUtil.bold(this));
		enterAmountLabel.setTypeface(TypefaceUtil.bold(this));
		amountCurrency.setTypeface(TypefaceUtil.bold(this));
		amountFiat.setTypeface(TypefaceUtil.light(this));
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
		balance.setText(StringFormatUtil.formatAmount(availableFunds, 0, 8));
	}

	@Override
	public void showAvailableProgress(boolean show) {

	}

	@Override
	public void setFiatBalance(Double fiatBalance) {
		balanceFiat.setText(String.format(Locale.getDefault(), "$%s", StringFormatUtil.formatAmount(fiatBalance, 2, 4)));
	}

	@Override
	public void setFiatAmount(Double fiatAmount) {
		amountFiat.setText(String.format(Locale.getDefault(), "$%s", StringFormatUtil.formatAmount(fiatAmount, 2, 4)));
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