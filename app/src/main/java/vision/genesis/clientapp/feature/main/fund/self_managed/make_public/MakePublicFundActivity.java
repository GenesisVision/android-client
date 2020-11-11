package vision.genesis.clientapp.feature.main.fund.self_managed.make_public;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import io.swagger.client.model.MakeSelfManagedFundPublicRequest;
import rx.Subscription;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/11/2020.
 */

public class MakePublicFundActivity extends BaseSwipeBackActivity implements MakePublicFundView
{
	private static final String EXTRA_REQUEST = "extra_request";

	public static void startWith(Activity activity, MakeSelfManagedFundPublicRequest request) {
		Intent intent = new Intent(activity.getApplicationContext(), MakePublicFundActivity.class);
		intent.putExtra(EXTRA_REQUEST, request);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.text_input_title)
	public TextInputLayout titleTextInput;

	@BindView(R.id.text_input_description)
	public TextInputLayout descriptionTextInput;

	@BindView(R.id.title)
	public EditText title;

	@BindView(R.id.description)
	public EditText description;

	@BindView(R.id.title_minimum)
	public TextView titleMinimum;

	@BindView(R.id.description_minimum)
	public TextView descriptionMinimum;

	@BindView(R.id.entry_fee)
	public EditText entryFee;

	@BindView(R.id.entry_fee_description)
	public TextView entryFeeDescription;

	@BindView(R.id.exit_fee)
	public EditText exitFee;

	@BindView(R.id.exit_fee_description)
	public TextView exitFeeDescription;

	@BindView(R.id.button_make_public_fund)
	public PrimaryButton makePublicFundButton;

	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	MakePublicFundPresenter presenter;

	private Subscription titleTextChangeSubscription;

	private Subscription descriptionTextChangeSubscription;

	private Subscription entryFeeTextChangeSubscription;

	private Subscription exitFeeTextChangeSubscription;

	@OnClick(R.id.group_entry_fee)
	public void onEntryFeeClicked() {
		showSoftKeyboard(entryFee);
	}

	@OnClick(R.id.group_exit_fee)
	public void onExitFeeClicked() {
		showSoftKeyboard(exitFee);
	}

	@OnFocusChange(R.id.title)
	void onTitleFocusChange(View view, boolean hasFocus) {
		if (!hasFocus) {
			presenter.onTitleFocusLost();
		}
	}

	@OnFocusChange(R.id.description)
	void onDescriptionFocusChange(View view, boolean hasFocus) {
		if (!hasFocus) {
			presenter.onDescriptionFocusLost();
		}
	}

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_make_public_fund)
	public void onMakePublicFundClicked() {
		hideSoftKeyboard();
		presenter.onMakePublicFundClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_public_fund);

		ButterKnife.bind(this);

		if (getIntent() != null && getIntent().getExtras() != null) {
			MakeSelfManagedFundPublicRequest request = getIntent().getExtras().getParcelable(EXTRA_REQUEST);
			if (request != null) {
				setLimits();
				setTextListeners();
				updateView(request);

				presenter.setData(request);

				return;
			}
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setLimits() {
		titleMinimum.setText(String.format(Locale.getDefault(), getString(R.string.template_minimum_symbols), Constants.MIN_ASSET_NAME_LENGTH));
		descriptionMinimum.setText(String.format(Locale.getDefault(), getString(R.string.template_minimum_symbols), Constants.MIN_ASSET_DESCRIPTION_LENGTH));
		titleTextInput.setCounterMaxLength(Constants.MAX_ASSET_NAME_LENGTH);
		descriptionTextInput.setCounterMaxLength(Constants.MAX_ASSET_DESCRIPTION_LENGTH);
	}

	private void setTextListeners() {
		titleTextChangeSubscription = RxTextView.textChanges(title)
				.subscribe(text -> presenter.onTitleChanged(text.toString()));
		descriptionTextChangeSubscription = RxTextView.textChanges(description)
				.subscribe(text -> presenter.onDescriptionChanged(text.toString()));
		entryFeeTextChangeSubscription = RxTextView.textChanges(entryFee)
				.subscribe(charSequence -> presenter.onEntryFeeChanged(charSequence.toString()));
		exitFeeTextChangeSubscription = RxTextView.textChanges(exitFee)
				.subscribe(charSequence -> presenter.onExitFeeChanged(charSequence.toString()));
	}

	private void updateView(MakeSelfManagedFundPublicRequest request) {
		this.title.setText(request.getTitle());
	}

	@Override
	public void onDestroy() {
		if (titleTextChangeSubscription != null) {
			titleTextChangeSubscription.unsubscribe();
		}
		if (descriptionTextChangeSubscription != null) {
			descriptionTextChangeSubscription.unsubscribe();
		}
		if (entryFeeTextChangeSubscription != null) {
			entryFeeTextChangeSubscription.unsubscribe();
		}
		if (exitFeeTextChangeSubscription != null) {
			exitFeeTextChangeSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	@Override
	public void showTitleError(String errorText) {
		this.title.setError(errorText);
	}

	@Override
	public void showDescriptionError(String errorText) {
		this.description.setError(errorText);
	}

	@Override
	public void cleanTitleError() {
		this.title.setError(null);
	}

	@Override
	public void cleanDescriptionError() {
		this.description.setError(null);
	}

	@Override
	public void updateEntryFeeDescription(Double managerMaxEntryFee) {
		this.entryFeeDescription.setText(String.format(Locale.getDefault(),
				getString(R.string.template_create_fund_entry_fee_description),
				StringFormatUtil.formatAmount(managerMaxEntryFee, 0, 4)));
	}

	@Override
	public void updateExitFeeDescription(Double managerMaxExitFee) {
		this.exitFeeDescription.setText(String.format(Locale.getDefault(),
				getString(R.string.template_create_fund_exit_fee_description),
				StringFormatUtil.formatAmount(managerMaxExitFee, 0, 4)));
	}

	@Override
	public void setEntryFee(Double entryFeeValue) {
		String entryFeeText = StringFormatUtil.formatAmount(entryFeeValue, 0, 4);
		if (entryFeeValue == 0) {
			this.entryFee.setText("");
		}
		else {
			this.entryFee.setText(entryFeeText);
			this.entryFee.setSelection(entryFeeText.length(), entryFeeText.length());
		}
	}

	@Override
	public void setExitFee(Double exitFeeValue) {
		String exitFeeText = StringFormatUtil.formatAmount(exitFeeValue, 0, 4);
		if (exitFeeValue == 0) {
			this.exitFee.setText("");
		}
		else {
			this.exitFee.setText(exitFeeText);
			this.exitFee.setSelection(exitFeeText.length(), exitFeeText.length());
		}
	}

	@Override
	public void setButtonEnabled(boolean enabled) {
		makePublicFundButton.setEnabled(enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}

	private void showSoftKeyboard(View view) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		view.requestFocus();
		if (imm != null) {
			imm.showSoftInput(view, 0);
		}
	}

	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		entryFee.clearFocus();
		exitFee.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(entryFee.getWindowToken(), 0);
		}
	}
}