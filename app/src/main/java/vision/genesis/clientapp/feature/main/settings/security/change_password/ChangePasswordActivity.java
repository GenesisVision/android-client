package vision.genesis.clientapp.feature.main.settings.security.change_password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 4/2/18.
 */

public class ChangePasswordActivity extends BaseSwipeBackActivity implements ChangePasswordView
{
	public static void startFrom(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), ChangePasswordActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.old_password_input_layout)
	public TextInputLayout oldPasswordInputLayout;

	@BindView(R.id.new_password_input_layout)
	public TextInputLayout newPasswordInputLayout;

	@BindView(R.id.repeat_password_input_layout)
	public TextInputLayout repeatPasswordInputLayout;

	@BindView(R.id.old_password)
	public EditText oldPassword;

	@BindView(R.id.new_password)
	public EditText newPassword;

	@BindView(R.id.repeat_password)
	public EditText repeatPassword;

	@BindView(R.id.button_change_password)
	public View changePasswordButton;

	@BindView(R.id.group_progressbar)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	public ChangePasswordPresenter changePasswordPresenter;

	@OnEditorAction(R.id.repeat_password)
	protected boolean onRepeatPasswordEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			onChangePasswordClicked();
		}
		return false;
	}

	@OnClick(R.id.button_change_password)
	public void onChangePasswordButtonClicked() {
		onChangePasswordClicked();
	}


	@OnClick(R.id.button_back)
	public void onBackButtonClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_change_password);

		ButterKnife.bind(this);

		setFonts();
		setInputFilters();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		oldPasswordInputLayout.setTypeface(TypefaceUtil.regular());
		newPasswordInputLayout.setTypeface(TypefaceUtil.regular());
		repeatPasswordInputLayout.setTypeface(TypefaceUtil.regular());
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
		repeatPassword.setFilters(new InputFilter[]{filter});
	}

	private void onChangePasswordClicked() {
		changePasswordPresenter.onChangePasswordClicked(
				oldPassword.getText().toString(),
				newPassword.getText().toString(),
				repeatPassword.getText().toString());
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		changePasswordButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void clearErrors() {
		oldPassword.setError(null);
		newPassword.setError(null);
		repeatPassword.setError(null);
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
	public void setRepeatPasswordError(String error) {
		repeatPassword.setError(error);
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
}