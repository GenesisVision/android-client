package vision.genesis.clientapp.feature.main.trading_account.add_demo_funds;

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
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/01/2020.
 */

public class AddDemoFundsActivity extends BaseSwipeBackActivity implements AddDemoFundsView
{
	private static final String EXTRA_PROGRAM_REQUEST = "extra_program_request";

	public static void startWith(Activity activity, ProgramRequest programRequest) {
		Intent intent = new Intent(activity.getApplicationContext(), AddDemoFundsActivity.class);
		intent.putExtra(EXTRA_PROGRAM_REQUEST, programRequest);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.account_name)
	public TextView accountName;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.group_amount)
	public ViewGroup amountGroup;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.amount_currency)
	public TextView amountCurrency;

	@BindView(R.id.button_add_funds)
	public PrimaryButton addFundsButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	AddDemoFundsPresenter presenter;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_edittext_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.button_add_funds)
	public void onAddFundsClicked() {
		presenter.onAddFundsClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_demo_funds);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			ProgramRequest request = getIntent().getExtras().getParcelable(EXTRA_PROGRAM_REQUEST);
			if (request != null) {
				presenter.setRequest(request);

				updateView(request);
				setTextListener();
				return;
			}
		}
		Timber.e("Passed empty request to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void updateView(ProgramRequest request) {
		this.accountName.setText(request.getProgramName());
		this.amountCurrency.setText(request.getProgramCurrency());
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> presenter.onAmountChanged(charSequence.toString()));
	}

	@Override
	public void setAmount(String amountText) {
		this.amount.setText(amountText);
		this.amount.setSelection(amountText.length(), amountText.length());
	}

	@Override
	public void setAddFundsButtonEnabled(boolean enabled) {
		addFundsButton.setEnabled(enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		addFundsButton.setVisibility(!show ? View.VISIBLE : View.GONE);
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