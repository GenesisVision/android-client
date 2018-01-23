package vision.genesis.clientapp.feature.auth.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class RegistrationFragment extends BaseFragment implements RegistrationView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

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

	@InjectPresenter
	RegistrationPresenter registrationPresenter;

	@OnClick(R.id.button_sign_up)
	public void onSignUpClicked() {
		registrationPresenter.onSignUpClicked(email.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_registration, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.registration));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> registrationPresenter.onBackClicked());
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
}
