package vision.genesis.clientapp.feature.main.program.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentProgramDetails;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.program.details.description.ProgramDescriptionActivity;
import vision.genesis.clientapp.feature.main.program.history.ProgramHistoryActivity;
import vision.genesis.clientapp.feature.main.program.invest.InvestProgramActivity;
import vision.genesis.clientapp.feature.main.program.requests.RequestsActivity;
import vision.genesis.clientapp.feature.main.program.trades.TradesActivity;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramActivity;
import vision.genesis.clientapp.model.ProgramDescriptionModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.PeriodLeftView;
import vision.genesis.clientapp.ui.ProfitChartView;
import vision.genesis.clientapp.ui.ProgramDataView;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ProgramDetailsActivity extends BaseSwipeBackActivity implements ProgramDetailsView
{
	private static String EXTRA_PROGRAM_ID = "extra_program_id";

	public static void startWith(Activity activity, UUID programId) {
		Intent intent = new Intent(activity.getApplicationContext(), ProgramDetailsActivity.class);
		intent.putExtra(EXTRA_PROGRAM_ID, programId);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.program_logo)
	public AvatarView programLogo;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.chart)
	public ProfitChartView chart;

	@BindView(R.id.view_program_data)
	public ProgramDataView programDataView;

	@BindView(R.id.period_duration)
	public TextView periodDuration;

	@BindView(R.id.period_duration_days)
	public TextView periodDurationDays;

	@BindView(R.id.period_duration_label)
	public TextView periodDurationLabel;

	@BindView(R.id.view_period_left)
	public PeriodLeftView periodLeftView;

	@BindView(R.id.manager_share)
	public TextView managerShare;

	@BindView(R.id.manager_share_percent)
	public TextView managerSharePercent;

	@BindView(R.id.manager_share_label)
	public TextView managerShareLabel;

	@BindView(R.id.trades)
	public TextView trades;

	@BindView(R.id.trades_label)
	public TextView tradesLabel;

	@BindView(R.id.success_fee)
	public TextView successFee;

	@BindView(R.id.success_fee_percent)
	public TextView successFeePercent;

	@BindView(R.id.success_fee_label)
	public TextView successFeeLabel;

	@BindView(R.id.management_fee)
	public TextView managementFee;

	@BindView(R.id.management_fee_percent)
	public TextView managementFeePercent;

	@BindView(R.id.management_fee_label)
	public TextView managementFeeLabel;

	@BindView(R.id.group_buttons)
	public ViewGroup buttonsGroup;

	@BindView(R.id.button_invest)
	public View investButton;

	@BindView(R.id.button_withdraw)
	public View withdrawButton;

	@BindView(R.id.button_requests)
	public View requestsButton;

	@BindView(R.id.scrollview)
	public View scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	ProgramDetailsPresenter programDetailsPresenter;

	private InvestmentProgramDetails programDetails;

	@OnClick(R.id.group_trades)
	public void onTradesClicked() {
		if (programDetails != null && programDetails.getTradesCount() > 0)
			TradesActivity.startWith(this, programDetails.getId());
	}

	@OnClick(R.id.group_program_logo)
	public void onProgramLogoClicked() {
		ProgramDescriptionActivity.startWith(this, ProgramDescriptionModel.fromProgram(programDetails));
	}

	@OnClick(R.id.button_invest)
	public void onInvestClicked() {
		ProgramRequest request = new ProgramRequest();
		request.programId = programDetails.getId();
		request.programName = programDetails.getTitle();
		request.programCurrency = programDetails.getCurrency().toString();
		InvestProgramActivity.startWith(this, request);
	}

	@OnClick(R.id.button_withdraw)
	public void onWithdrawClicked() {
		ProgramRequest request = new ProgramRequest();
		request.programId = programDetails.getId();
		request.programName = programDetails.getTitle();
		request.available = programDetails.getInvestedTokens();
		request.tokenPrice = programDetails.getToken().getInitialPrice();
		WithdrawProgramActivity.startWith(this, request);
	}

	@OnClick(R.id.button_requests)
	public void onRequestsClicked() {
		RequestsActivity.startWith(this, programDetails.getId());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_details);

		ButterKnife.bind(this);

		initToolbar();
		initRefreshLayout();

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID programId = (UUID) getIntent().getExtras().getSerializable(EXTRA_PROGRAM_ID);
			programDetailsPresenter.setProgramId(programId);
		}
		else {
			Timber.e("Passed empty program to ProgramDetailsActivity");
			onBackPressed();
		}
	}

	@Override
	protected void onDestroy() {
		toolbar.onDestroy();
		chart.onDestroy();
		programDataView.onDestroy();
		periodLeftView.onDestroy();

		super.onDestroy();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.program_details));
		toolbar.addLeftButton(R.drawable.back_arrow, this::onBackPressed);
	}

	private void showHistoryButton() {
		toolbar.addRightButton(R.drawable.ic_history_icon, () -> ProgramHistoryActivity.startWith(this, programDetails.getId()));
		toolbar.setRightButtonPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics()));
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary),
				ContextCompat.getColor(getApplicationContext(), R.color.colorAccent),
				ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> programDetailsPresenter.onSwipeRefresh());
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.bold());

		periodDuration.setTypeface(TypefaceUtil.bold());
		periodDurationDays.setTypeface(TypefaceUtil.bold());
		periodDurationLabel.setTypeface(TypefaceUtil.bold());

		managerShare.setTypeface(TypefaceUtil.light());
		managerSharePercent.setTypeface(TypefaceUtil.light());
		managerShareLabel.setTypeface(TypefaceUtil.bold());

		trades.setTypeface(TypefaceUtil.light());
		tradesLabel.setTypeface(TypefaceUtil.bold());

		successFee.setTypeface(TypefaceUtil.light());
		successFeePercent.setTypeface(TypefaceUtil.light());
		successFeeLabel.setTypeface(TypefaceUtil.bold());

		managementFee.setTypeface(TypefaceUtil.light());
		managementFeePercent.setTypeface(TypefaceUtil.light());
		managementFeeLabel.setTypeface(TypefaceUtil.bold());
	}

	@Override
	protected void onResume() {
		super.onResume();
		programDetailsPresenter.onResume();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setProgram(InvestmentProgramDetails programDetails) {
		this.programDetails = programDetails;

		if (programDetails.isIsHistoryEnable())
			showHistoryButton();

		programLogo.setImage(programDetails.getLogo(), 200, 200);
		programLogo.setLevel(programDetails.getLevel());

		title.setText(programDetails.getTitle());
		managerName.setText(String.format(Locale.getDefault(), "%s %s", getResources().getString(R.string.by), programDetails.getManager().getUsername()));

		chart.setChart(programDetails.getChart());

		programDataView.setData(programDetails.getProfitTotal(),
				programDetails.getProfitAvgPercent(),
				programDetails.getBalance(),
				programDetails.getInvestorsCount(),
				programDetails.getCurrency().toString());

		periodDuration.setText(String.valueOf(programDetails.getPeriodDuration()));
		periodDurationDays.setText(getResources().getQuantityString(R.plurals.days, programDetails.getPeriodDuration()));

		if (programDetails.isIsEnabled())
			periodLeftView.setDateTo(programDetails.getStartOfPeriod(), programDetails.getEndOfPeriod());
		periodLeftView.setProgramClosed(!programDetails.isIsEnabled());

		double managerShareValue = 0;
		if (programDetails.getBalance() != 0)
			managerShareValue = programDetails.getOwnBalance() / programDetails.getBalance() * 100;
		managerShare.setText(StringFormatUtil.formatAmount(managerShareValue, 0, 2));
		trades.setText(StringFormatUtil.formatAmount(programDetails.getTradesCount(), 0, 0));

		successFee.setText(StringFormatUtil.formatAmount(programDetails.getFeeSuccess(), 0, 2));
		managementFee.setText(StringFormatUtil.formatAmount(programDetails.getFeeManagement(), 0, 2));

		investButton.setVisibility(programDetails.isIsInvestEnable() ? View.VISIBLE : View.GONE);
		withdrawButton.setVisibility(programDetails.isIsWithdrawEnable() ? View.VISIBLE : View.GONE);
		requestsButton.setVisibility(programDetails.isHasNewRequests() ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showInvestWithdrawButtons(boolean show) {
		buttonsGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		scrollView.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void setRefreshing(boolean show) {
		refreshLayout.setRefreshing(show);
	}
}
