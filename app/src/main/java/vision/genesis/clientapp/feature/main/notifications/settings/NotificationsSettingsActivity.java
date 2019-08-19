package vision.genesis.clientapp.feature.main.notifications.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
import io.swagger.client.model.FundNotificationSettingList;
import io.swagger.client.model.ProgramNotificationSettingList;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.notifications.fund.FundNotificationsSettingsActivity;
import vision.genesis.clientapp.feature.main.notifications.program.ProgramNotificationsSettingsActivity;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/10/2018.
 */

public class NotificationsSettingsActivity extends BaseSwipeBackActivity implements NotificationsSettingsView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), NotificationsSettingsActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.scrollview)
	public ScrollView scrollView;

	@BindView(R.id.switch_news)
	public SwitchCompat newsSwitch;

	@BindView(R.id.switch_emergency)
	public SwitchCompat emergencySwitch;

	@BindView(R.id.group_programs)
	public ViewGroup programsGroup;

	@BindView(R.id.label_programs)
	public TextView labelPrograms;

	@BindView(R.id.programs_recycler_view)
	public RecyclerView programsRecyclerView;

	@BindView(R.id.group_funds)
	public ViewGroup fundsGroup;

	@BindView(R.id.label_funds)
	public TextView labelFunds;

	@BindView(R.id.funds_recycler_view)
	public RecyclerView fundsRecyclerView;

	@InjectPresenter
	NotificationsSettingsPresenter notificationsSettingsPresenter;

	private ProgramsNotificationsListAdapter programsListAdapter;

	private FundsNotificationsListAdapter fundsListAdapter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.switch_news)
	public void onNewsClicked() {
		notificationsSettingsPresenter.onNewsClicked();
	}

	@OnClick(R.id.switch_emergency)
	public void onEmergencyClicked() {
		notificationsSettingsPresenter.onEmergencyClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_notifications_settings);

		ButterKnife.bind(this);

		setFonts();

		initRecyclerViews();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		labelPrograms.setTypeface(TypefaceUtil.semibold());
		labelFunds.setTypeface(TypefaceUtil.semibold());
	}

	private void initRecyclerViews() {
		LinearLayoutManager programsLayoutManager = new LinearLayoutManager(this);
		programsRecyclerView.setLayoutManager(programsLayoutManager);
		programsListAdapter = new ProgramsNotificationsListAdapter();
		programsRecyclerView.setAdapter(programsListAdapter);

		LinearLayoutManager fundsLayoutManager = new LinearLayoutManager(this);
		fundsRecyclerView.setLayoutManager(fundsLayoutManager);
		fundsListAdapter = new FundsNotificationsListAdapter();
		fundsRecyclerView.setAdapter(fundsListAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		notificationsSettingsPresenter.onShow();
	}

	@Override
	public void setNewsChecked(Boolean checked) {
		newsSwitch.setChecked(checked);
	}

	@Override
	public void setEmergencyChecked(Boolean checked) {
		emergencySwitch.setChecked(checked);
	}

	@Override
	public void setProgramsSettings(List<ProgramNotificationSettingList> settings) {
		programsGroup.setVisibility(settings.isEmpty() ? View.GONE : View.VISIBLE);
		programsListAdapter.setSettings(settings);
	}

	@Override
	public void setFundsSettings(List<FundNotificationSettingList> settings) {
		fundsGroup.setVisibility(settings.isEmpty() ? View.GONE : View.VISIBLE);
		fundsListAdapter.setSettings(settings);
	}

	@Override
	public void showProgramNotificationsSettings(UUID programId, String programName) {
		ProgramNotificationsSettingsActivity.startWith(this, programId, programName);
	}

	@Override
	public void showFundNotificationsSettings(UUID fundId, String fundName) {
		FundNotificationsSettingsActivity.startWith(this, fundId, fundName);
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
