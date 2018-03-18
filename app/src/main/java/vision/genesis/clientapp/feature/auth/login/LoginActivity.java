package vision.genesis.clientapp.feature.auth.login;

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
import vision.genesis.clientapp.feature.auth.registration.RegistrationActivity;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class LoginActivity extends BaseSwipeBackActivity implements LoginView
{
	public static void startFrom(Activity activity) {
		Intent intent = new Intent(activity, LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.email)
	public EditText email;

	@BindView(R.id.password)
	public EditText password;

	@BindView(R.id.group_buttons)
	public View buttonsGroup;

	@BindView(R.id.group_progressbar)
	public View progressbarGroup;

	@BindView(R.id.sign_up_label)
	public TextView signUpLabel;

	@BindView(R.id.text_sign_up)
	public TextView signUpText;

	@InjectPresenter
	LoginPresenter loginPresenter;

	@OnEditorAction(R.id.password)
	protected boolean onConfirmPasswordEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			loginPresenter.onSignInClicked(email.getText().toString(), password.getText().toString());
		}
		return false;
	}

	@OnClick(R.id.button_sign_up)
	public void onSignUpClicked() {
		loginPresenter.onSignUpClicked();
	}

	@OnClick(R.id.button_sign_in)
	public void onSignInClicked() {
		loginPresenter.onSignInClicked(email.getText().toString(), password.getText().toString());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		ButterKnife.bind(this);

		initToolbar();

		setFonts();
	}

	private void setFonts() {
		signUpLabel.setTypeface(TypefaceUtil.regular(this));
		signUpText.setTypeface(TypefaceUtil.bold(this));
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.sign_in));
		toolbar.addLeftButton(R.drawable.back_arrow, this::finishActivity);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
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
		buttonsGroup.setVisibility(View.GONE);
		progressbarGroup.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		progressbarGroup.setVisibility(View.GONE);
		buttonsGroup.setVisibility(View.VISIBLE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbar);
	}

	@Override
	public void showRegistrationActivity() {
		RegistrationActivity.startFrom(this);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}
