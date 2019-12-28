package vision.genesis.clientapp.feature.auth.registration;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ThemeUtil;
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

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.button_sign_in)
	public TextView signInButton;

	@BindView(R.id.email)
	public EditText email;

	@BindView(R.id.password)
	public EditText password;

	@BindView(R.id.password_input_layout)
	public TextInputLayout passwordInputLayout;

	@BindView(R.id.confirm_password)
	public EditText confirmPassword;

	@BindView(R.id.confirm_password_input_layout)
	public TextInputLayout confirmPasswordInputLayout;

	@BindView(R.id.button_sign_up)
	public PrimaryButton signUpButton;

	@BindView(R.id.group_progressbar)
	public View progressbarGroup;

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

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@OnClick(R.id.privacy_policy)
	public void onPrivacyPolicyClicked() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_policy_address)));
		startActivity(browserIntent);
	}

	@OnClick(R.id.terms_conditions)
	public void onTermsConditionsClicked() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.term_conditions_address)));
		startActivity(browserIntent);
	}

	@OnCheckedChanged(R.id.checkbox_accept_privacy_policy)
	public void onAcceptPrivacyPolicyCheckedChanged(CompoundButton button, boolean checked) {
		registrationPresenter.onAcceptPrivacyPolicyCheckedChanged(checked);
	}

	@OnCheckedChanged(R.id.checkbox_accept_terms_conditions)
	public void onAcceptTermsConditionsCheckedChanged(CompoundButton button, boolean checked) {
		registrationPresenter.onAcceptTermsConditionsCheckedChanged(checked);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_registration);

		ButterKnife.bind(this);

//		userName.setVisibility(Constants.IS_INVESTOR ? View.GONE : View.VISIBLE);

		setFonts();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.bold());
		signInButton.setTypeface(TypefaceUtil.bold());
		passwordInputLayout.setTypeface(TypefaceUtil.regular());
		confirmPasswordInputLayout.setTypeface(TypefaceUtil.regular());
	}

	private void signUp() {
		registrationPresenter.onSignUpClicked(
//				userName.getText().toString(),
				email.getText().toString(),
				password.getText().toString(),
				confirmPassword.getText().toString());
	}

	@Override
	public void setUserNameError(String message) {
//		userName.setError(message);
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
//		userName.setError(null);
		email.setError(null);
		password.setError(null);
		confirmPassword.setError(null);
	}

	@Override
	public void showProgress(boolean show) {
		progressbarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		signUpButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void showLoginActivity() {
		LoginActivity.startFrom(this);
	}

	@Override
	public void showMessageDialog(int imageResourceId, String title, String message, boolean mustRead, MessageBottomSheetDialog.OnButtonClickListener listener) {
		MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
		dialog.show(getSupportFragmentManager(), dialog.getTag());
		dialog.setData(imageResourceId, title, message, mustRead, listener);
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

	@Override
	public void setSignUpButtonEnabled(boolean enabled) {
		signUpButton.setEnabled(enabled);
	}
}
