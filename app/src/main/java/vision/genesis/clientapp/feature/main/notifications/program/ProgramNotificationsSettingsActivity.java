package vision.genesis.clientapp.feature.main.notifications.program;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.NotificationSettingViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.notifications.create.CreateCustomNotificationSettingActivity;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/10/2018.
 */

public class ProgramNotificationsSettingsActivity extends BaseSwipeBackActivity implements ProgramNotificationsSettingsView
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	private static final String EXTRA_PROGRAM_NAME = "extra_program_name";

	public static void startWith(Activity activity, UUID programId, String programName) {
		Intent intent = new Intent(activity.getApplicationContext(), ProgramNotificationsSettingsActivity.class);
		intent.putExtra(EXTRA_PROGRAM_ID, programId);
		intent.putExtra(EXTRA_PROGRAM_NAME, programName);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.scrollview)
	public ScrollView scrollView;

	@BindView(R.id.label_general)
	public TextView labelGeneral;

	@BindView(R.id.switch_news)
	public SwitchCompat newsSwitch;

	@BindView(R.id.switch_period)
	public SwitchCompat periodSwitch;

	@BindView(R.id.label_custom)
	public TextView labelCustom;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.button_create_notification)
	public PrimaryButton createNotificationButton;

	@InjectPresenter
	ProgramNotificationsSettingsPresenter programNotificationsSettingsPresenter;

	private CustomNotificationsListAdapter customListAdapter;

	private UUID programId;

	private String programNameText;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.switch_news)
	public void onNewsClicked() {
		programNotificationsSettingsPresenter.onNewsClicked();
	}

	@OnClick(R.id.switch_period)
	public void onPeriodClicked() {
		programNotificationsSettingsPresenter.onPeriodClicked();
	}

	@OnClick(R.id.button_create_notification)
	public void onCreateNotificationClicked() {
		CreateCustomNotificationSettingActivity.startWith(this, programId, programNameText);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_notifications_settings);

		ButterKnife.bind(this);

		createNotificationButton.setEmpty();

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			this.programId = (UUID) getIntent().getExtras().getSerializable(EXTRA_PROGRAM_ID);
			this.programNameText = getIntent().getExtras().getString(EXTRA_PROGRAM_NAME);

			programNotificationsSettingsPresenter.setProgramId(programId);
			this.programName.setText(programNameText);

			initRecyclerView();
		}
		else {
			Timber.e("Passed empty programId to ProgramNotificationsSettingsActivity");
			onBackPressed();
		}
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		labelGeneral.setTypeface(TypefaceUtil.semibold());
		labelCustom.setTypeface(TypefaceUtil.semibold());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		customListAdapter = new CustomNotificationsListAdapter();
		recyclerView.setAdapter(customListAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();

		programNotificationsSettingsPresenter.onShow();
	}

	@Override
	public void setNewsChecked(Boolean checked) {
		newsSwitch.setChecked(checked);
	}

	@Override
	public void setPeriodChecked(Boolean checked) {
		periodSwitch.setChecked(checked);
	}

	@Override
	public void setCustomSettings(List<NotificationSettingViewModel> settings) {
		customListAdapter.setSettings(settings);
	}

	@Override
	public void setCustomSettingEnabled(UUID settingId, Boolean enabled) {
		customListAdapter.setEnabled(settingId, enabled);
	}

	@Override
	public void deleteSetting(UUID settingId) {
		customListAdapter.deleteSetting(settingId);
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
