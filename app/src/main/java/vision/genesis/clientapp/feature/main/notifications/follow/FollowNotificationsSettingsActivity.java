package vision.genesis.clientapp.feature.main.notifications.follow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/07/2020.
 */

public class FollowNotificationsSettingsActivity extends BaseSwipeBackActivity implements FollowNotificationsSettingsView
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	private static final String EXTRA_PROGRAM_NAME = "extra_program_name";

	public static void startWith(Activity activity, UUID programId, String programName) {
		Intent intent = new Intent(activity.getApplicationContext(), FollowNotificationsSettingsActivity.class);
		intent.putExtra(EXTRA_PROGRAM_ID, programId);
		intent.putExtra(EXTRA_PROGRAM_NAME, programName);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.follow_name)
	public TextView followName;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.scrollview)
	public ScrollView scrollView;

	@BindView(R.id.label_general)
	public TextView labelGeneral;

	@BindView(R.id.switch_news)
	public SwitchCompat newsSwitch;

	@InjectPresenter
	FollowNotificationsSettingsPresenter presenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.switch_news)
	public void onNewsClicked() {
		presenter.onNewsClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_follow_notifications_settings);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID followId = (UUID) getIntent().getExtras().getSerializable(EXTRA_PROGRAM_ID);
			String followNameText = getIntent().getExtras().getString(EXTRA_PROGRAM_NAME);

			presenter.setFollowId(followId);
			this.followName.setText(followNameText);
		}
		else {
			Timber.e("Passed empty programId to %s", getClass().getSimpleName());
			onBackPressed();
		}
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		labelGeneral.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	protected void onResume() {
		super.onResume();

		presenter.onShow();
	}

	@Override
	public void setNewsChecked(Boolean checked) {
		newsSwitch.setChecked(checked);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			scrollView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}
