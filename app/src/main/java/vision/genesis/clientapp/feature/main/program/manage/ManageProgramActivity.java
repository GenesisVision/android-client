package vision.genesis.clientapp.feature.main.program.manage;

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
import io.swagger.client.model.ProgramDailyPeriodDetails;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramType;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.TradesDelay;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.program.change_settings.ChangeProgramSettingsActivity;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

public class ManageProgramActivity extends BaseSwipeBackActivity implements ManageProgramView
{
	private static String EXTRA_DETAILS = "extra_details";

	public static void startFrom(Activity activity, ProgramFollowDetailsFull programDetails) {
		Intent intent = new Intent(activity.getApplicationContext(), ManageProgramActivity.class);
		intent.putExtra(EXTRA_DETAILS, programDetails);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.label_processing)
	public TextView labelProcessing;

	@BindView(R.id.label_investment_limit)
	public TextView labelInvestmentLimit;

	@BindView(R.id.label_trades_delay)
	public TextView labelTradesDelay;

	@BindView(R.id.label_stop_out)
	public TextView labelStopOut;

	@BindView(R.id.label_management_fee)
	public TextView labelManagementFee;

	@BindView(R.id.label_success_fee)
	public TextView labelSuccessFee;


	@BindView(R.id.processing)
	public TextView processing;

	@BindView(R.id.investment_limit)
	public TextView investmentLimit;

	@BindView(R.id.trades_delay)
	public TextView tradesDelay;

	@BindView(R.id.stop_out)
	public TextView stopOut;

	@BindView(R.id.management_fee)
	public TextView managementFee;

	@BindView(R.id.success_fee)
	public TextView successFee;


	@BindView(R.id.group_processing)
	public ViewGroup groupProcessing;

	@BindView(R.id.group_stop_out)
	public ViewGroup groupStopOut;


	@BindView(R.id.label_danger_zone)
	public TextView labelDangerZone;

	@BindView(R.id.switch_danger_zone)
	public SwitchCompat dangerZoneSwitch;

	@BindView(R.id.group_danger_zone)
	public ViewGroup groupDangerZone;

	@BindView(R.id.group_close_period)
	public ViewGroup groupClosePeriod;

	@BindView(R.id.close_program_info)
	public TextView closeProgramInfo;

	@BindView(R.id.button_close_program)
	public PrimaryButton closeProgramButton;


	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;


	@InjectPresenter
	ManageProgramPresenter presenter;

	private ProgramFollowDetailsFull details;

	private String currency;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_change_settings)
	public void onChangeSettingsClicked() {
		presenter.onChangeSettingsClicked();
	}

	@OnClick(R.id.button_close_period)
	public void onClosePeriodClicked() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(getString(R.string.close_period));
		builder.setMessage(getString(R.string.close_period_alert_message));
		builder.setPositiveButton(getString(R.string.confirm), (dialog, which) -> {
			presenter.onClosePeriodClicked();
			dialog.dismiss();
		});
		builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
		builder.show();
	}

	@OnClick(R.id.button_close_program)
	public void onCloseProgramClicked() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(getString(R.string.close_program));
		builder.setMessage(getString(details.getProgramDetails().getType().equals(ProgramType.DAILYPERIOD)
				? R.string.close_exchange_program_alert_message : R.string.close_program_alert_message));
		builder.setPositiveButton(getString(R.string.confirm), (dialog, which) -> {
			presenter.onCloseProgramClicked();
			dialog.dismiss();
		});
		builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
		builder.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_manage_program);

		ButterKnife.bind(this);

		setFonts();

		closeProgramButton.setRed();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			details = getIntent().getExtras().getParcelable(EXTRA_DETAILS);
			if (details != null) {
				currency = details.getTradingAccountInfo().getCurrency().getValue();
				setListener();
				updateView(details.getProgramDetails());
				presenter.setData(details);
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

		investmentLimit.setTypeface(TypefaceUtil.semibold());
		tradesDelay.setTypeface(TypefaceUtil.semibold());
		stopOut.setTypeface(TypefaceUtil.semibold());
		managementFee.setTypeface(TypefaceUtil.semibold());
		successFee.setTypeface(TypefaceUtil.semibold());
		labelDangerZone.setTypeface(TypefaceUtil.semibold());

		labelProcessing.setText(labelProcessing.getText().toString().toLowerCase());
		labelInvestmentLimit.setText(labelInvestmentLimit.getText().toString().toLowerCase());
		labelTradesDelay.setText(labelTradesDelay.getText().toString().toLowerCase());
		labelStopOut.setText(labelStopOut.getText().toString().toLowerCase());
		labelManagementFee.setText(labelManagementFee.getText().toString().toLowerCase());
		labelSuccessFee.setText(labelSuccessFee.getText().toString().toLowerCase());
	}

	@Override
	public void updateView(ProgramDetailsFull programDetails) {
		investmentLimit.setText(programDetails.getAvailableInvestmentLimit() == null
				? getString(R.string.no_limit)
				: StringFormatUtil.getValueString(programDetails.getAvailableInvestmentLimit(), currency));
		tradesDelay.setText(programDetails.getTradesDelay() == null || programDetails.getTradesDelay().equals(TradesDelay.NONE)
				? getString(R.string.no_delay)
				: StringFormatUtil.getTradesDelayString(programDetails.getTradesDelay()));

		stopOut.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(programDetails.getStopOutLevelSelected(), 0, 4)));
		managementFee.setText(String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(programDetails.getManagementFeeSelected(), 0, 4),
				getString(R.string.annual)));
		successFee.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(programDetails.getSuccessFeeSelected(), 0, 4)));

		if (programDetails.getType().equals(ProgramType.DAILYPERIOD)) {
			groupProcessing.setVisibility(View.VISIBLE);
			groupStopOut.setVisibility(View.GONE);
			groupClosePeriod.setVisibility(View.GONE);

			if (programDetails.getDailyPeriodDetails() != null) {
				processing.setText(getProcessingText(programDetails.getDailyPeriodDetails()));
			}

			closeProgramInfo.setText(getString(R.string.close_exchange_program_info));
		}
	}

	private String getProcessingText(ProgramDailyPeriodDetails dailyPeriodDetails) {
		if (dailyPeriodDetails.isIsProcessingRealTime()) {
			return getString(R.string.real_time);
		}
		else {
			return (dailyPeriodDetails.getHourProcessing() < 10 ? "0" : "").concat(String.valueOf(dailyPeriodDetails.getHourProcessing()).concat(":00"));
		}
	}

	@Override
	public void showChangeSettingsActivity(UUID programId, String currency, ProgramUpdate model, Boolean isExchange) {
		ChangeProgramSettingsActivity.startFrom(this, programId, currency, model, isExchange);
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
