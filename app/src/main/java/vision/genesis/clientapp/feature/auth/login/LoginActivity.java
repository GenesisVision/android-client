package vision.genesis.clientapp.feature.auth.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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
import vision.genesis.clientapp.feature.auth.forgot_password.ForgotPasswordActivity;
import vision.genesis.clientapp.feature.auth.registration.RegistrationActivity;
import vision.genesis.clientapp.feature.two_factor.check.CheckTfaActivity;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class LoginActivity extends BaseSwipeBackActivity implements LoginView
{
	public static void startFrom(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.button_sign_up)
	public TextView signUpButton;

	@BindView(R.id.email)
	public EditText email;

	@BindView(R.id.password_input_layout)
	public TextInputLayout passwordInputLayout;

	@BindView(R.id.password)
	public EditText password;

	@BindView(R.id.forgot_password)
	public TextView forgotPassword;

	@BindView(R.id.button_sign_in)
	public PrimaryButton signInButton;

	@BindView(R.id.group_progressbar)
	public View progressbarGroup;

	@InjectPresenter
	LoginPresenter loginPresenter;

	@OnEditorAction(R.id.password)
	protected boolean onPasswordEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			loginPresenter.onSignInClicked(email.getText().toString(), password.getText().toString());
		}
		return false;
	}

	@OnClick(R.id.forgot_password)
	public void onForgotPasswordClicked() {
		ForgotPasswordActivity.startWith(this);
	}

	@OnClick(R.id.button_sign_up)
	public void onSignUpClicked() {
		loginPresenter.onSignUpClicked();
	}

	@OnClick(R.id.button_sign_in)
	public void onSignInClicked() {
		loginPresenter.onSignInClicked(email.getText().toString(), password.getText().toString());
	}

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity(true);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		ButterKnife.bind(this);

		setFonts();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		signUpButton.setTypeface(TypefaceUtil.semibold());
		forgotPassword.setTypeface(TypefaceUtil.semibold());
		passwordInputLayout.setTypeface(TypefaceUtil.regular());
	}

	@Override
	public void onBackPressed() {
		finishActivity(true);
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
	public void clearErrors() {
		email.setError(null);
		password.setError(null);
	}

	@Override
	public void showProgress() {
		signInButton.setVisibility(View.INVISIBLE);
		progressbarGroup.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		signInButton.setVisibility(View.VISIBLE);
		progressbarGroup.setVisibility(View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void showRegistrationActivity() {
		RegistrationActivity.startFrom(this);
	}

	@Override
	public void finishActivity(boolean withAnimation) {
		finish();
		if (withAnimation)
			overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
		else
			overridePendingTransition(R.anim.hold, R.anim.hold);
	}

	@Override
	public void startCheckTfaActivity(String action) {
		CheckTfaActivity.startWith(this, action);
	}
}
