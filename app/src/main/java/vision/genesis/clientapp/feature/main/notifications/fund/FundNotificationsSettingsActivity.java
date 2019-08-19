package vision.genesis.clientapp.feature.main.notifications.fund;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.UUID;

import androidx.appcompat.widget.SwitchCompat;
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
 * Created by Vitaly on 30/10/2018.
 */

public class FundNotificationsSettingsActivity extends BaseSwipeBackActivity implements FundNotificationsSettingsView
{
	private static final String EXTRA_FUND_ID = "extra_fund_id";

	private static final String EXTRA_FUND_NAME = "extra_fund_name";

	public static void startWith(Activity activity, UUID fundId, String fundName) {
		Intent intent = new Intent(activity.getApplicationContext(), FundNotificationsSettingsActivity.class);
		intent.putExtra(EXTRA_FUND_ID, fundId);
		intent.putExtra(EXTRA_FUND_NAME, fundName);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.fund_name)
	public TextView fundName;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.scrollview)
	public ScrollView scrollView;

	@BindView(R.id.label_general)
	public TextView labelGeneral;

	@BindView(R.id.switch_news)
	public SwitchCompat newsSwitch;

	@BindView(R.id.switch_structure)
	public SwitchCompat structureSwitch;

	@InjectPresenter
	FundNotificationsSettingsPresenter fundNotificationsSettingsPresenter;

	private UUID fundId;

	private String fundNameText;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.switch_news)
	public void onNewsClicked() {
		fundNotificationsSettingsPresenter.onNewsClicked();
	}

	@OnClick(R.id.switch_structure)
	public void onStructureClicked() {
		fundNotificationsSettingsPresenter.onStructureClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fund_notifications_settings);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			this.fundId = (UUID) getIntent().getExtras().getSerializable(EXTRA_FUND_ID);
			this.fundNameText = getIntent().getExtras().getString(EXTRA_FUND_NAME);

			fundNotificationsSettingsPresenter.setFundId(fundId);
			this.fundName.setText(fundNameText);
		}
		else {
			Timber.e("Passed empty programId to FundNotificationsSettingsActivity");
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

		fundNotificationsSettingsPresenter.onShow();
	}

	@Override
	public void setNewsChecked(Boolean checked) {
		newsSwitch.setChecked(checked);
	}

	@Override
	public void setStructureChecked(Boolean checked) {
		structureSwitch.setChecked(checked);
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
