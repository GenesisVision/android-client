package vision.genesis.clientapp.feature.main.program.withdraw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

public class WithdrawProgramActivity extends BaseSwipeBackActivity implements WithdrawProgramView
{
	private static final String EXTRA_REQUEST = "extra_request";

	public static void startWith(Activity activity, ProgramRequest request) {
		Intent intent = new Intent(activity.getApplicationContext(), WithdrawProgramActivity.class);
		intent.putExtra(EXTRA_REQUEST, request);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.group_progress_bar)
	public ViewGroup progressBarGroup;

	@BindView(R.id.available_tokens)
	public TextView availableTokens;

	@BindView(R.id.balance_currency)
	public TextView balanceCurrency;

	@BindView(R.id.label_my_balance)
	public TextView myBalanceLabel;

	@BindView(R.id.balance_fiat)
	public TextView balanceFiat;

	@BindView(R.id.textview_amount_hint)
	public TextView amountHintTextView;

	@BindView(R.id.textview_amount)
	public AmountTextView amountTextView;

	@BindView(R.id.amount_currency)
	public TextView amountCurrency;

	@BindView(R.id.label_enter_amount)
	public TextView enterAmountLabel;

	@BindView(R.id.amount_fiat)
	public TextView amountFiat;

	@BindView(R.id.button_withdraw)
	public View withdrawButton;

	@BindView(R.id.keyboard)
	public NumericKeyboardView keyboard;

	@InjectPresenter
	WithdrawProgramPresenter withdrawProgramPresenter;

	private ProgramRequest withdrawalRequest;

	@OnClick(R.id.button_withdraw)
	public void onWithdrawClicked() {
		withdrawProgramPresenter.onWithdrawClicked();
	}

	@OnClick(R.id.group_available)
	public void onAvailableClicked() {
		withdrawProgramPresenter.onAvailableClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_withdraw);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			withdrawalRequest = getIntent().getExtras().getParcelable(EXTRA_REQUEST);
			withdrawProgramPresenter.setWithdrawalRequest(withdrawalRequest);

			initToolbar();
			initAmountTextView();
			setFonts();
		}
		else {
			Timber.e("Passed empty request to WithdrawProgramActivity");
			onBackPressed();
		}
	}

	@Override
	protected void onDestroy() {
		toolbar.onDestroy();
		amountTextView.onDestroy();
		keyboard.onDestroy();

		super.onDestroy();
	}

	private void initToolbar() {
		toolbar.setWhite();
		toolbar.setTitle(getString(R.string.withdraw_from_program));
//		toolbar.setSubtitle(withdrawalRequest.programName);
		toolbar.addLeftButton(R.drawable.back_arrow, () -> withdrawProgramPresenter.onBackClicked());
	}

	private void initAmountTextView() {
		amountTextView.setKeyboard(keyboard);
		amountTextView.setMaxDecimalDigits(Constants.TOKENS_MAX_DECIMAL_POINT_DIGITS);
		amountTextView.setAmountChangeListener(new AmountTextView.AmountChangeListener()
		{
			@Override
			public void onAmountChanged(double newAmount) {
				withdrawProgramPresenter.onAmountChanged(newAmount);
			}

			@Override
			public void onAmountCleared() {
				withdrawProgramPresenter.onAmountCleared();
			}
		});
	}

	private void setFonts() {
		availableTokens.setTypeface(TypefaceUtil.light());
		balanceFiat.setTypeface(TypefaceUtil.light());
		myBalanceLabel.setTypeface(TypefaceUtil.bold());
		enterAmountLabel.setTypeface(TypefaceUtil.bold());
		amountHintTextView.setTypeface(TypefaceUtil.light());
		amountFiat.setTypeface(TypefaceUtil.light());

		balanceCurrency.setTypeface(TypefaceUtil.bold());
		amountCurrency.setTypeface(TypefaceUtil.bold());
	}

	@Override
	public void setWithdrawButtonEnabled(boolean enabled) {
		withdrawButton.setEnabled(enabled);
	}

	@Override
	public void setAmount(double amount) {
		amountTextView.setText(String.valueOf(amount));
	}

	@Override
	public void setAvailable(double availableFunds) {
		availableTokens.setText(StringFormatUtil.formatAmount(availableFunds, 0, Constants.TOKENS_MAX_DECIMAL_POINT_DIGITS));
	}

	@Override
	public void showToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void setFiatBalance(Double fiatBalance) {
		balanceFiat.setText(String.format(Locale.getDefault(), "$%s", StringFormatUtil.formatAmount(fiatBalance, 2, 2)));
	}

	@Override
	public void setFiatAmount(Double fiatAmount) {
		amountFiat.setText(String.format(Locale.getDefault(), "$%s", StringFormatUtil.formatAmount(fiatAmount, 2, 2)));
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
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}