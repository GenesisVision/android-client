package vision.genesis.clientapp.feature.main.program.invest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

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

	@BindView(R.id.text_available_funds)
	public TextView availableFundsText;

	@BindView(R.id.textview_amount)
	public AmountTextView amountTextView;

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

	@OnClick(R.id.group_available)
	public void onAvailableCLicked() {
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
		}
		else {
			Timber.e("Passed empty request to InvestProgramActivity");
			onBackPressed();
		}
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.invest_to_program));
		toolbar.setSubtitle(investRequest.programName);
		toolbar.addLeftButton(R.drawable.back_arrow, () -> investProgramPresenter.onBackClicked());
	}

	private void initListeners() {
		amountTextView.setKeyboard(keyboard);
		amountTextView.setAmountChangeListener(newAmount -> investProgramPresenter.onAmountChanged(newAmount));
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
		availableFundsText.setText(StringFormatUtil.formatAmount((availableFunds)));
	}

	@Override
	public void showAvailableProgress(boolean show) {

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