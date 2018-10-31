package vision.genesis.clientapp.feature.two_factor.disable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import rx.Subscription;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

public class DisableTfaActivity extends BaseSwipeBackActivity implements DisableTfaView
{
	public static void startFrom(Activity activity) {
		Intent activityIntent = new Intent(activity, DisableTfaActivity.class);
		activity.startActivity(activityIntent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.password_input_layout)
	public TextInputLayout passwordInputLayout;

	@BindView(R.id.edit_text_password)
	public EditText password;

	@BindView(R.id.edit_text_code)
	public EditText code;

	@BindView(R.id.button_disable)
	public View disableButton;

	@BindView(R.id.group_progressbar)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	DisableTfaPresenter disableTfaPresenter;

	private Subscription passwordTextChangeSubscription;

	private Subscription codeTextChangeSubscription;

	@OnEditorAction(R.id.edit_text_code)
	protected boolean onCodeEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			disableTfaPresenter.onDisableClicked();
		}
		return false;
	}

	@OnClick(R.id.button_disable)
	public void onDisableButtonClicked() {
		disableTfaPresenter.onDisableClicked();
	}


	@OnClick(R.id.button_back)
	public void onBackButtonClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disable_two_factor);

		ButterKnife.bind(this);

		setFonts();

		disableButton.setEnabled(false);

		InputFilter[] filters = new InputFilter[1];
		filters[0] = new InputFilter.LengthFilter(Constants.TWO_FACTOR_CODE_LENGTH);
		code.setFilters(filters);
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		passwordInputLayout.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void onStart() {
		super.onStart();
		setTextListeners();
	}

	@Override
	public void onStop() {
		super.onStop();
		if (passwordTextChangeSubscription != null)
			passwordTextChangeSubscription.unsubscribe();
		if (codeTextChangeSubscription != null)
			codeTextChangeSubscription.unsubscribe();
	}

	private void setTextListeners() {
		passwordTextChangeSubscription = RxTextView.textChanges(password)
				.subscribe(text -> disableTfaPresenter.onPasswordChanged(text.toString()));
		codeTextChangeSubscription = RxTextView.textChanges(code)
				.subscribe(text -> disableTfaPresenter.onCodeChanged(text.toString()));
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void showProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		disableButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbar(String text) {
		Snackbar.make(progressBarGroup, text, Snackbar.LENGTH_LONG).show();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void showMessageDialog(int imageResourceId, String title, String message, boolean mustRead, MessageBottomSheetDialog.OnButtonClickListener listener) {
		MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
		dialog.show(getSupportFragmentManager(), dialog.getTag());
		dialog.setData(imageResourceId, title, message, mustRead, listener);
	}

	@Override
	public void setDisableButtonAvailability(boolean available) {
		disableButton.setEnabled(available);
	}
}