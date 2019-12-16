package vision.genesis.clientapp.feature.main.fund.manage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.ProgramUpdate;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.fund.change_settings.ChangeFundSettingsActivity;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/12/2019.
 */

public class ManageFundActivity extends BaseSwipeBackActivity implements ManageFundView
{
	private static String EXTRA_DETAILS = "extra_details";

	public static void startFrom(Activity activity, FundDetailsFull fundDetails) {
		Intent intent = new Intent(activity.getApplicationContext(), ManageFundActivity.class);
		intent.putExtra(EXTRA_DETAILS, fundDetails);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.label_entry_fee)
	public TextView labelEntryFee;

	@BindView(R.id.label_exit_fee)
	public TextView labelExitFee;

	@BindView(R.id.entry_fee)
	public TextView entryFee;

	@BindView(R.id.exit_fee)
	public TextView exitFee;

	@BindView(R.id.label_danger_zone)
	public TextView labelDangerZone;

	@BindView(R.id.switch_danger_zone)
	public SwitchCompat dangerZoneSwitch;

	@BindView(R.id.group_danger_zone)
	public ViewGroup groupDangerZone;

	@BindView(R.id.button_close_fund)
	public PrimaryButton closeFundButton;


	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;


	@InjectPresenter
	ManageFundPresenter presenter;

	private FundDetailsFull fundDetails;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_change_settings)
	public void onChangeSettingsClicked() {
		presenter.onChangeSettingsClicked();
	}

	@OnClick(R.id.button_close_fund)
	public void onCloseFundClicked() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(getString(R.string.close_fund));
		builder.setMessage(getString(R.string.close_fund_alert_message));
		builder.setPositiveButton(getString(R.string.confirm), (dialog, which) -> {
			presenter.onCloseFundClicked();
			dialog.dismiss();
		});
		builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
		builder.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_manage_fund);

		ButterKnife.bind(this);

		setFonts();

		closeFundButton.setRed();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			fundDetails = getIntent().getExtras().getParcelable(EXTRA_DETAILS);
			if (fundDetails != null) {
				setListener();
				updateView(fundDetails);
				presenter.setData(fundDetails);
				return;
			}
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setListener() {
		dangerZoneSwitch.setOnCheckedChangeListener((view, checked) -> {
			groupDangerZone.setVisibility(checked ? View.VISIBLE : View.GONE);
		});
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());

		entryFee.setTypeface(TypefaceUtil.semibold());
		exitFee.setTypeface(TypefaceUtil.semibold());
		labelDangerZone.setTypeface(TypefaceUtil.semibold());

		labelEntryFee.setText(labelEntryFee.getText().toString().toLowerCase());
		labelExitFee.setText(labelExitFee.getText().toString().toLowerCase());
	}

	@Override
	public void updateView(FundDetailsFull details) {
		entryFee.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(details.getEntryFeeSelected(), 0, 4)));
		exitFee.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(details.getExitFeeSelected(), 0, 4)));
	}

	@Override
	public void showChangeSettingsActivity(UUID fundId, ProgramUpdate model) {
		ChangeFundSettingsActivity.startFrom(this, fundId, model);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void showProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

}
