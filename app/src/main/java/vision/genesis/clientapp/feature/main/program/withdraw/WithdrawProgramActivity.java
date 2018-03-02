package vision.genesis.clientapp.feature.main.program.withdraw;

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
import vision.genesis.clientapp.model.ProgramWithdrawalRequest;
import vision.genesis.clientapp.ui.AmountTextView;
import vision.genesis.clientapp.ui.NumericKeyboardView;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

public class WithdrawProgramActivity extends BaseSwipeBackActivity implements WithdrawProgramView
{
	private static final String EXTRA_REQUEST = "extra_request";

	public static void startWith(Activity activity, ProgramWithdrawalRequest request) {
		Intent intent = new Intent(activity, WithdrawProgramActivity.class);
		intent.putExtra(EXTRA_REQUEST, request);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.text_available_funds)
	public TextView availableFundsText;

	@BindView(R.id.textview_amount)
	public AmountTextView amountTextView;

	@BindView(R.id.button_withdraw)
	public View withdrawButton;

	@BindView(R.id.keyboard)
	public NumericKeyboardView keyboard;

	@InjectPresenter
	WithdrawProgramPresenter withdrawProgramPresenter;

	private ProgramWithdrawalRequest withdrawalRequest;

	@OnClick(R.id.button_withdraw)
	public void onWithdrawClicked() {
		withdrawProgramPresenter.onWithdrawClicked();
	}

	@OnClick(R.id.group_available)
	public void onAvailableCLicked() {
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
			initListeners();
		}
		else {
			Timber.e("Passed empty request to WithdrawProgramActivity");
			onBackPressed();
		}
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.withdraw_from_program));
		toolbar.setSubtitle(withdrawalRequest.programName);
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> withdrawProgramPresenter.onBackClicked());
	}

	private void initListeners() {
		amountTextView.setKeyboard(keyboard);
		amountTextView.setAmountChangeListener(newAmount -> withdrawProgramPresenter.onAmountChanged(newAmount));
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
		availableFundsText.setText(String.format(Locale.getDefault(), "$%s", String.valueOf(availableFunds)));
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