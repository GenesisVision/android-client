package vision.genesis.clientapp.feature.main.program.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.InvestmentProgramDetails;
import io.swagger.client.model.TradeChart;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.ProgramInfoActivity;
import vision.genesis.clientapp.feature.main.program.ProgramInfoPagerAdapter;
import vision.genesis.clientapp.feature.main.program.invest.InvestProgramActivity;
import vision.genesis.clientapp.feature.main.program.requests.RequestsActivity;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramActivity;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.ui.AvailableTokensView;
import vision.genesis.clientapp.ui.PeriodLeftView;
import vision.genesis.clientapp.ui.ProgramDataView;
import vision.genesis.clientapp.ui.chart.ProfitDetailsChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

public class ProgramDetailsFragment extends BaseFragment implements ProgramDetailsView, ProgramInfoPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_PROGRAM_ID = "extra_program_id";

	public static ProgramDetailsFragment with(UUID programId) {
		ProgramDetailsFragment programDetailsFragment = new ProgramDetailsFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
		programDetailsFragment.setArguments(arguments);
		return programDetailsFragment;
	}

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.chart)
	public ProfitDetailsChartView chart;

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

	@BindView(R.id.tokens_availability_label)
	public TextView tokensAvailabilityLabel;

	@BindView(R.id.view_available_tokens)
	public AvailableTokensView availableTokensView;

	@BindView(R.id.group_buttons)
	public ViewGroup buttonsGroup;

	@BindView(R.id.button_invest)
	public View investButton;

	@BindView(R.id.button_withdraw)
	public View withdrawButton;

	@BindView(R.id.button_requests)
	public View requestsButton;

	@BindView(R.id.scrollview)
	public ScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	public ProgramDetailsPresenter programDetailsPresenter;

	private UUID programId;

	private InvestmentProgramDetails programDetails;

	private Unbinder unbinder;

	@OnClick(R.id.group_trades)
	public void onTradesClicked() {
//		if (programDetails != null && programDetails.getTradesCount() > 0)
//			TradesActivity.startWith(this, programDetails.getId());
	}

	@OnClick(R.id.button_invest)
	public void onInvestClicked() {
		if (programDetails == null)
			return;
		ProgramRequest request = new ProgramRequest();
		request.programId = programDetails.getId();
		request.programName = programDetails.getTitle();
		request.programCurrency = programDetails.getCurrency().toString();
		InvestProgramActivity.startWith(getActivity(), request);
	}

	@OnClick(R.id.button_withdraw)
	public void onWithdrawClicked() {
		if (programDetails == null)
			return;
		ProgramRequest request = new ProgramRequest();
		request.programId = programDetails.getId();
		request.programName = programDetails.getTitle();
		request.available = programDetails.getInvestedTokens();
		request.tokenPrice = programDetails.getToken().getInitialPrice();
		WithdrawProgramActivity.startWith(getActivity(), request);
	}

	@OnClick(R.id.button_requests)
	public void onRequestsClicked() {
		if (programDetails == null)
			return;
		RequestsActivity.startWith(getActivity(), programDetails.getId());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_program_details, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		programId = (UUID) getArguments().getSerializable(EXTRA_PROGRAM_ID);
		programDetailsPresenter.setProgramId(programId);

		setFonts();

		initRefreshLayout();

		chart.setTouchListener(new ProfitDetailsChartView.TouchListener()
		{
			@Override
			public void onTouch() {
				((ProgramInfoActivity) getActivity()).onChartTouch();
				refreshLayout.setEnabled(false);
			}

			@Override
			public void onStop() {
				((ProgramInfoActivity) getActivity()).onChartTouchEnd();
				refreshLayout.setEnabled(true);
			}
		});

		chart.setTimeFrameChangeListener(programDetailsPresenter::onChartTimeFrameChanged);
	}

	@Override
	public void onDestroyView() {
		if (chart != null)
			chart.onDestroy();
		if (programDataView != null)
			programDataView.onDestroy();
		if (periodLeftView != null)
			periodLeftView.onDestroy();

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
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

		tokensAvailabilityLabel.setTypeface(TypefaceUtil.bold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimary),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> programDetailsPresenter.onSwipeRefresh());
	}

	@Override
	public void setProgramDetails(InvestmentProgramDetails programDetails) {
		this.programDetails = programDetails;

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

		if (programDetails.getFreeTokens() == null) {
			availableTokensView.setVisibility(View.GONE);
		}
		else {
			availableTokensView.setVisibility(View.VISIBLE);
			availableTokensView.setData(programDetails.getFreeTokens().getTotal(),
					programDetails.getFreeTokens().getInvestorsTokens(),
					programDetails.getFreeTokens().getRequestsTokens());
		}

		investButton.setVisibility(programDetails.isIsInvestEnable() ? View.VISIBLE : View.GONE);
		withdrawButton.setVisibility(programDetails.isIsWithdrawEnable() ? View.VISIBLE : View.GONE);
		requestsButton.setVisibility(programDetails.isHasNewRequests() ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		if (refreshLayout != null)
			refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void setChartData(List<TradeChart> chart) {
		this.chart.setChart(chart);
	}


	@Override
	public void showInvestWithdrawButtons(boolean show) {
		buttonsGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void pagerShow() {
		if (programDetailsPresenter != null)
			programDetailsPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}
}