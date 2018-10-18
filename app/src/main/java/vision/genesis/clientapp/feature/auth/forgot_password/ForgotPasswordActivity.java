package vision.genesis.clientapp.feature.auth.forgot_password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 4/2/18.
 */

public class ForgotPasswordActivity extends BaseSwipeBackActivity implements ForgotPasswordView
{
	private static final String EXTRA_EMAIL = "extra_email";

	public static void startWith(Activity activity, String email) {
		Intent intent = new Intent(activity.getApplicationContext(), ForgotPasswordActivity.class);
		intent.putExtra(EXTRA_EMAIL, email);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.email)
	public EditText email;

	@BindView(R.id.button_reset_password)
	public PrimaryButton resetPasswordButton;

	@BindView(R.id.group_progressbar)
	public View progressBarGroup;

	@InjectPresenter
	public ForgotPasswordPresenter forgotPasswordPresenter;

	@OnEditorAction(R.id.email)
	protected boolean onEmailEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			forgotPasswordPresenter.onResetPasswordClicked(email.getText().toString());
		}
		return false;
	}

	@OnClick(R.id.button_reset_password)
	public void onResetPasswordClicked() {
		forgotPasswordPresenter.onResetPasswordClicked(email.getText().toString());
	}

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_forgot_password);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null)
			email.setText(getIntent().getExtras().getString(EXTRA_EMAIL, ""));

		setFonts();

		setTextListener();
	}

	private void setTextListener() {
		RxTextView.textChanges(email)
				.subscribe(charSequence -> forgotPasswordPresenter.onEmailChanged(charSequence.toString()));
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		resetPasswordButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void setEmailError(String error) {
		email.setError(error);
	}

	@Override
	public void setButtonEnabled(boolean enabled) {
		resetPasswordButton.setEnabled(enabled);
	}

	@Override
	public void showMessageDialog(int imageResourceId, String title, String message, boolean mustRead, MessageBottomSheetDialog.OnButtonClickListener listener) {
		MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
		dialog.show(getSupportFragmentManager(), dialog.getTag());
		dialog.setData(imageResourceId, title, message, mustRead, listener);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}
}