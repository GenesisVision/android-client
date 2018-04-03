package vision.genesis.clientapp.feature.auth.forgot_password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 4/2/18.
 */

public class ForgotPasswordActivity extends BaseSwipeBackActivity implements ForgotPasswordView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), ForgotPasswordActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.email)
	public EditText email;

	@BindView(R.id.group_progress_bar)
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_forgot_password);

		ButterKnife.bind(this);

		initToolbar();
		setFonts();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.forgot_password));
		toolbar.addLeftButton(R.drawable.back_arrow, this::onBackPressed);
	}

	private void setFonts() {
//		whoopsLabel.setTypeface(TypefaceUtil.bold(this));
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbar);
	}

	@Override
	public void setEmailError(String error) {
		email.setError(error);
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