package vision.genesis.clientapp.feature.main.profile.change_password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
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
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 4/2/18.
 */

public class ChangePasswordActivity extends BaseSwipeBackActivity implements ChangePasswordView
{
	public static void startWith(Fragment fragment, int requestCode) {
		Intent intent = new Intent(fragment.getContext(), ChangePasswordActivity.class);
		fragment.startActivityForResult(intent, requestCode);
		if (fragment.getActivity() != null)
			fragment.getActivity().overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.old_password)
	public EditText oldPassword;

	@BindView(R.id.new_password)
	public EditText newPassword;

	@BindView(R.id.confirm_password)
	public EditText confirmPassword;

	@BindView(R.id.group_progress_bar)
	public View progressBarGroup;

	@InjectPresenter
	public ChangePasswordPresenter changePasswordPresenter;

	@OnEditorAction(R.id.confirm_password)
	protected boolean onConfirmPasswordEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			onChangePasswordClicked();
		}
		return false;
	}

	@OnClick(R.id.button_change_password)
	public void onChangePasswordButtonClicked() {
		onChangePasswordClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_change_password);

		ButterKnife.bind(this);

		initToolbar();
		setInputFilters();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.change_password));
		toolbar.addLeftButton(R.drawable.back_arrow, this::onBackPressed);
	}

	private void setInputFilters() {
		InputFilter filter = (source, start, end, dest, dstart, dend) -> {
			for (int i = start; i < end; i++) {
				if (Character.isWhitespace(source.charAt(i))) {
					return "";
				}
			}
			return null;
		};
		oldPassword.setFilters(new InputFilter[]{filter});
		newPassword.setFilters(new InputFilter[]{filter});
		confirmPassword.setFilters(new InputFilter[]{filter});
	}

	private void onChangePasswordClicked() {
		changePasswordPresenter.onChangePasswordClicked(
				oldPassword.getText().toString(),
				newPassword.getText().toString(),
				confirmPassword.getText().toString());
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
	public void finishActivity(int resultCode) {
		setResult(resultCode);
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void clearErrors() {
		oldPassword.setError(null);
		newPassword.setError(null);
		confirmPassword.setError(null);
	}

	@Override
	public void setOldPasswordError(String error) {
		oldPassword.setError(error);
	}

	@Override
	public void setNewPasswordError(String error) {
		newPassword.setError(error);
	}

	@Override
	public void setConfirmPasswordError(String error) {
		confirmPassword.setError(error);
	}

	@Override
	public void onBackPressed() {
		finishActivity(Activity.RESULT_CANCELED);
	}
}