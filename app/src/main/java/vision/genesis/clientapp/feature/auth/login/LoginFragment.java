package vision.genesis.clientapp.feature.auth.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class LoginFragment extends BaseFragment implements LoginView
{
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

	@InjectPresenter
	LoginPresenter loginPresenter;

	@OnEditorAction(R.id.password)
	protected boolean onConfirmPasswordEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			loginPresenter.onSignInClicked(email.getText().toString(), password.getText().toString());
		}
		return false;
	}

	@OnClick(R.id.create_account)
	public void onCreateAccountClicked() {
		loginPresenter.onCreateAccountClicked();
	}

	@OnClick(R.id.button_sign_in)
	public void onSignInClicked() {
		loginPresenter.onSignInClicked(email.getText().toString(), password.getText().toString());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_login, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.login));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> loginPresenter.onBackClicked());
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
}
