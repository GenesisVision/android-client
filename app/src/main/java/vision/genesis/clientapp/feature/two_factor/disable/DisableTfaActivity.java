package vision.genesis.clientapp.feature.two_factor.disable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import rx.Subscription;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.message.MessageActivity;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

public class DisableTfaActivity extends BaseSwipeBackActivity implements DisableTfaView
{
	public static void startFrom(Context context) {
		Intent activityIntent = new Intent(context, DisableTfaActivity.class);
		context.startActivity(activityIntent);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.version)
	public TextView version;

	@BindView(R.id.edit_text_password)
	public EditText password;

	@BindView(R.id.edit_text_code)
	public EditText code;

	@BindView(R.id.button_disable)
	public View disableButton;

	@BindView(R.id.group_progress_bar)
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disable_two_factor);

		ButterKnife.bind(this);

		initToolbar();

		version.setText(String.format(Locale.getDefault(), "%s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));

		disableButton.setEnabled(false);

		InputFilter[] filters = new InputFilter[1];
		filters[0] = new InputFilter.LengthFilter(Constants.TWO_FACTOR_CODE_LENGTH);
		code.setFilters(filters);
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

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.disable_two_factor));
		toolbar.addLeftButton(R.drawable.back_arrow, this::onBackPressed);
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	public void showProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbar(String text) {
		Snackbar.make(progressBarGroup, text, Snackbar.LENGTH_LONG).show();
	}

	@Override
	public void finishWithSuccess() {
		MessageActivity.startWith(this, getString(R.string.tfa_disable_success), R.drawable.ic_email_confirmed_icon, false);
		finish();
	}

	@Override
	public void setDisableButtonAvailability(boolean available) {
		disableButton.setEnabled(available);
	}
}