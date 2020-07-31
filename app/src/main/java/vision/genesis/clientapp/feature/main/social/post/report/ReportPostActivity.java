package vision.genesis.clientapp.feature.main.social.post.report;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypedValueFormatter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/07/2020.
 */

public class ReportPostActivity extends BaseSwipeBackActivity implements ReportPostView
{
	private static final String EXTRA_POST_ID = "extra_post_id";

	public static void startWith(Activity activity, UUID postId) {
		Intent intent = new Intent(activity.getApplicationContext(), ReportPostActivity.class);
		intent.putExtra(EXTRA_POST_ID, postId);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.group_reasons)
	public RadioGroup reasonsGroup;

	@BindView(R.id.description)
	public TextInputEditText description;

	@BindView(R.id.progress_bar_button)
	public ProgressBar progressBarButton;

	@BindView(R.id.button_send)
	public PrimaryButton sendButton;

	@InjectPresenter
	ReportPostPresenter presenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_send)
	public void onSendClicked() {
		presenter.onSendClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_post);

		ButterKnife.bind(this);

		setButtonEnabled(false);
		initReasons();
		setTextListener();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID postId = (UUID) getIntent().getExtras().getSerializable(EXTRA_POST_ID);
			if (postId != null) {
				presenter.setData(postId);
				return;
			}
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void initReasons() {
		reasonsGroup.removeAllViews();
		for (String reason : getResources().getStringArray(R.array.report_reasons)) {
			RadioButton newRadioButton = new RadioButton(this);
			newRadioButton.setText(reason);
			newRadioButton.setTextColor(ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary));
			newRadioButton.setOnClickListener(view -> presenter.onReasonClicked(((RadioButton) view).getText().toString()));
			reasonsGroup.addView(newRadioButton);

			RadioGroup.LayoutParams lp = (RadioGroup.LayoutParams) newRadioButton.getLayoutParams();
			lp.setMargins(0, TypedValueFormatter.toDp(8), 0, 0);
			newRadioButton.setLayoutParams(lp);
		}
	}

	private void setTextListener() {
		RxTextView.textChanges(description)
				.subscribe(charSequence -> presenter.onDescriptionChanged(charSequence.toString()));
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setButtonEnabled(boolean enabled) {
		sendButton.setEnabled(enabled);
	}

	@Override
	public void showButtonProgress(boolean show) {
		progressBarButton.setVisibility(show ? View.VISIBLE : View.GONE);
		sendButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, reasonsGroup);
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}