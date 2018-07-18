package vision.genesis.clientapp.feature.auth.registration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class RegistrationActivity extends BaseSwipeBackActivity implements RegistrationView
{
	public static void startFrom(Activity activity) {
		activity.startActivity(new Intent(activity.getApplicationContext(), RegistrationActivity.class));
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.user_name)
	public EditText userName;

	@BindView(R.id.email)
	public EditText email;

	@BindView(R.id.password)
	public EditText password;

	@BindView(R.id.confirm_password)
	public EditText confirmPassword;

	@BindView(R.id.button_sign_up)
	public View signUpButton;

	@BindView(R.id.progress_bar)
	public View progressBar;

	@BindView(R.id.text_sign_in)
	public TextView signInText;

	@InjectPresenter
	RegistrationPresenter registrationPresenter;

	@OnEditorAction(R.id.confirm_password)
	protected boolean onConfirmPasswordEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			signUp();
		}
		return false;
	}

	@OnClick(R.id.button_sign_up)
	public void onSignUpClicked() {
		signUp();
	}

	@OnClick(R.id.button_sign_in)
	public void onSignInClicked() {
		registrationPresenter.onSignInClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_registration);

		ButterKnife.bind(this);

		initToolbar();

		userName.setVisibility(Constants.IS_INVESTOR ? View.GONE : View.VISIBLE);

		setFonts();
	}

	private void setFonts() {
		signInText.setTypeface(TypefaceUtil.bold());
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.sign_up));
		toolbar.addLeftButton(R.drawable.back_arrow, this::finishActivity);
	}

	private void signUp() {
		registrationPresenter.onSignUpClicked(
				userName.getText().toString(),
				email.getText().toString(),
				password.getText().toString(),
				confirmPassword.getText().toString());
	}

	@Override
	public void setUserNameError(String message) {
		userName.setError(message);
	}

	@Override
	public void setEmailError(String message) {
		email.setError(message);
	}

	@Override
	public void setPasswordError(String message) {
		password.setError(message);
	}

	@Override
	public void setConfirmPasswordError(String message) {
		confirmPassword.setError(message);
	}

	@Override
	public void clearErrors() {
		userName.setError(null);
		email.setError(null);
		password.setError(null);
		confirmPassword.setError(null);
	}

	@Override
	public void showProgress() {
		signUpButton.setVisibility(View.GONE);
		progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		progressBar.setVisibility(View.GONE);
		signUpButton.setVisibility(View.VISIBLE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbar);
	}

	@Override
	public void showLoginActivity() {
		LoginActivity.startFrom(this);
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
