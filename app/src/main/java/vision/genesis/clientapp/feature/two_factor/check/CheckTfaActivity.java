package vision.genesis.clientapp.feature.two_factor.check;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.NumericKeyboardView;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

public class CheckTfaActivity extends BaseSwipeBackActivity implements CheckTfaView
{
	private static final String EXTRA_ACTION = "extra_action";

	public static void startWith(Activity activity, String action) {
		Intent intent = new Intent(activity.getApplicationContext(), CheckTfaActivity.class);
		intent.putExtra(EXTRA_ACTION, action);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.label_about_to_perform)
	public TextView aboutToPerformLabel;

	@BindView(R.id.action)
	public TextView action;

	@BindView(R.id.label_confirm_this_action)
	public TextView confirmThisActionLabel;

	@BindView(R.id.code_1)
	public TextView code1;

	@BindView(R.id.code_2)
	public TextView code2;

	@BindView(R.id.code_3)
	public TextView code3;

	@BindView(R.id.code_4)
	public TextView code4;

	@BindView(R.id.code_5)
	public TextView code5;

	@BindView(R.id.code_6)
	public TextView code6;

	@BindView(R.id.button_confirm)
	public View confirmButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.keyboard)
	public NumericKeyboardView keyboard;

	@InjectPresenter
	CheckTfaPresenter checkTfaPresenter;

	@OnCheckedChanged(R.id.checkbox_recovery_code)
	public void onRecoveryCodeCheckedChanged(CompoundButton button, boolean checked) {
		checkTfaPresenter.setUseRecoveryCode(checked);
		button.setTextColor(checked
				? ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimaryDark)
				: ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.grey300));
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		checkTfaPresenter.onConfirmClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_check_tfa);

		ButterKnife.bind(this);

		confirmButton.setEnabled(false);
		setCode("");

		if (getIntent().getExtras() != null) {
			action.setText(getIntent().getExtras().getString(EXTRA_ACTION));

			initToolbar();
			initKeyboardListener();
			setFonts();
		}
		else {
			Timber.e("Passed empty request to InvestProgramActivity");
			onBackPressed();
		}
	}

	@Override
	protected void onDestroy() {
		toolbar.onDestroy();
		keyboard.onDestroy();

		super.onDestroy();
	}

	private void initToolbar() {
		toolbar.setWhite();
		toolbar.setTitle(getString(R.string.two_factor_authentication));
		toolbar.addLeftButton(R.drawable.back_arrow, () -> checkTfaPresenter.onBackClicked());
	}

	private void initKeyboardListener() {
		keyboard.disableDecimal();
		keyboard.setListener(new NumericKeyboardView.InputListener()
		{
			@Override
			public void onNumber(String number) {
				checkTfaPresenter.onNumber(number);
			}

			@Override
			public void onDecimal() {

			}

			@Override
			public void onBackspace() {
				checkTfaPresenter.onBackspace();
			}

			@Override
			public void onLongBackspace() {
				checkTfaPresenter.onLongBackspace();
			}
		});
	}

	private void setFonts() {
		code1.setTypeface(TypefaceUtil.light());
		code2.setTypeface(TypefaceUtil.light());
		code3.setTypeface(TypefaceUtil.light());
		code4.setTypeface(TypefaceUtil.light());
		code5.setTypeface(TypefaceUtil.light());
		code6.setTypeface(TypefaceUtil.light());
	}

	@Override
	public void setCode(String code) {
		code1.setText("_");
		code2.setText("_");
		code3.setText("_");
		code4.setText("_");
		code5.setText("_");
		code6.setText("_");
		if (code.length() > 0)
			code1.setText(String.valueOf(code.charAt(0)));
		if (code.length() > 1)
			code2.setText(String.valueOf(code.charAt(1)));
		if (code.length() > 2)
			code3.setText(String.valueOf(code.charAt(2)));
		if (code.length() > 3)
			code4.setText(String.valueOf(code.charAt(3)));
		if (code.length() > 4)
			code5.setText(String.valueOf(code.charAt(4)));
		if (code.length() > 5)
			code6.setText(String.valueOf(code.charAt(5)));
	}

	@Override
	public void setConfirmButtonEnabled(boolean enabled) {
		confirmButton.setEnabled(enabled);
	}

	@Override
	public void setKeyboardKeysEnabled(boolean enabled) {
		keyboard.disableAllKeysExceptBackspace(!enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		confirmButton.setVisibility(!show ? View.VISIBLE : View.GONE);
		if (show)
			keyboard.disableKeyboard(true);
		else
			keyboard.disableAllKeysExceptBackspace(true);
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
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}