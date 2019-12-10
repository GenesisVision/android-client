package vision.genesis.clientapp.feature.main.trading_account.change_password;

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

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

public class ChangeTradingAccountPasswordActivity extends BaseSwipeBackActivity implements ChangeTradingAccountPasswordView
{
	private static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	public static void startFrom(Activity activity, UUID accountId) {
		Intent intent = new Intent(activity.getApplicationContext(), ChangeTradingAccountPasswordActivity.class);
		intent.putExtra(EXTRA_ACCOUNT_ID, accountId);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.new_password_input_layout)
	public TextInputLayout newPasswordInputLayout;

	@BindView(R.id.repeat_password_input_layout)
	public TextInputLayout repeatPasswordInputLayout;

	@BindView(R.id.new_password)
	public EditText newPassword;

	@BindView(R.id.repeat_password)
	public EditText repeatPassword;

	@BindView(R.id.button_change_password)
	public View changePasswordButton;

	@BindView(R.id.group_progressbar)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	public ChangeTradingAccountPasswordPresenter presenter;

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

		setContentView(R.layout.activity_change_trading_account_password);

		ButterKnife.bind(this);

		setFonts();
		setInputFilters();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID accountId = (UUID) getIntent().getExtras().getSerializable(EXTRA_ACCOUNT_ID);

			presenter.setData(accountId);
			return;
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
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
		newPassword.setFilters(new InputFilter[]{filter});
		repeatPassword.setFilters(new InputFilter[]{filter});
	}

	private void onChangePasswordClicked() {
		presenter.onChangePasswordClicked(
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
		newPassword.setError(null);
		repeatPassword.setError(null);
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