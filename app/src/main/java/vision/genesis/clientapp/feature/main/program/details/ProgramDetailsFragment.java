package vision.genesis.clientapp.feature.main.program.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.InvestmentProgramDetails;
import io.swagger.client.model.TradeChart;
import io.swagger.client.model.WalletTransaction;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.ProgramInfoActivity;
import vision.genesis.clientapp.feature.main.program.ProgramInfoPagerAdapter;
import vision.genesis.clientapp.feature.main.program.invest.InvestProgramActivity;
import vision.genesis.clientapp.feature.main.program.requests.RequestsActivity;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramActivity;
import vision.genesis.clientapp.feature.main.tooltip.TooltipActivity;
import vision.genesis.clientapp.model.InvestmentProgramExtended;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.TooltipModel;
import vision.genesis.clientapp.model.events.ShowTradesEvent;
import vision.genesis.clientapp.ui.PeriodLeftView;
import vision.genesis.clientapp.ui.ProgramDataView;
import vision.genesis.clientapp.ui.chart.ProfitDetailsChartView;
import vision.genesis.clientapp.utils.Constants;
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

	@BindView(R.id.group_tournament)
	public ViewGroup tournamentGroup;

	@BindView(R.id.round)
	public TextView round;

	@BindView(R.id.round_label)
	public TextView roundLabel;

	@BindView(R.id.place)
	public TextView place;

	@BindView(R.id.place_label)
	public TextView placeLabel;

	@BindView(R.id.chart)
	public ProfitDetailsChartView chart;

	@BindView(R.id.view_program_data)
	public ProgramDataView programDataView;

	@BindView(R.id.period_duration)
	public TextView periodDuration;

	@BindView(R.id.group_period_duration)
	public ViewGroup periodDurationGroup;

	@BindView(R.id.period_duration_days)
	public TextView periodDurationDays;

	@BindView(R.id.period_duration_label)
	public TextView periodDurationLabel;

	@BindView(R.id.view_period_left)
	public PeriodLeftView periodLeftView;

	@BindView(R.id.group_manager_share)
	public ViewGroup managerShareGroup;

	@BindView(R.id.manager_share)
	public TextView managerShare;

	@BindView(R.id.manager_share_percent)
	public TextView managerSharePercent;

	@BindView(R.id.manager_share_label)
	public TextView managerShareLabel;

	@BindView(R.id.group_trades)
	public ViewGroup tradesGroup;

	@BindView(R.id.trades)
	public TextView trades;

	@BindView(R.id.trades_label)
	public TextView tradesLabel;

	@BindView(R.id.group_success_fee)
	public ViewGroup successFeeGroup;

	@BindView(R.id.success_fee)
	public TextView successFee;

	@BindView(R.id.success_fee_percent)
	public TextView successFeePercent;

	@BindView(R.id.success_fee_label)
	public TextView successFeeLabel;

	@BindView(R.id.group_management_fee)
	public ViewGroup managementFeeGroup;

	@BindView(R.id.management_fee)
	public TextView managementFee;

	@BindView(R.id.management_fee_percent)
	public TextView managementFeePercent;

	@BindView(R.id.management_fee_label)
	public TextView managementFeeLabel;

	@BindView(R.id.card_my_tokens)
	public View myTokensCard;

	@BindView(R.id.my_tokens)
	public TextView myTokens;

	@BindView(R.id.my_tokens_fiat)
	public TextView myTokensFiat;

	@BindView(R.id.my_tokens_label)
	public TextView myTokensLabel;

	@BindView(R.id.my_profit)
	public TextView myProfit;

	@BindView(R.id.profit_currency)
	public TextView profitCurrency;

	@BindView(R.id.my_profit_label)
	public TextView myProfitLabel;

	@BindView(R.id.card_available)
	public CardView availableToInvestCard;

	@BindView(R.id.text_available_to_invest)
	public TextView availableToInvestText;

	@BindView(R.id.available_currency)
	public TextView availableCurrency;

	@BindView(R.id.label_available_to_invest)
	public TextView availableToInvestLabel;

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

	@OnClick(R.id.trades)
	public void onTradesClicked() {
		EventBus.getDefault().post(new ShowTradesEvent());
	}

	@OnClick(R.id.tooltip_tournament)
	public void onTooltipTournamentClicked() {
		showTooltip(tournamentGroup, R.string.tooltip_tournament);
	}

	@OnClick(R.id.tooltip_equity_chart)
	public void onTooltipEquityChartClicked() {
		showTooltip(chart, R.string.tooltip_equity_chart);
	}

	@OnClick(R.id.tooltip_program_data)
	public void onTooltipProgramDataClicked() {
		showTooltip(programDataView, R.string.tooltip_program_data);
	}

	@OnClick(R.id.tooltip_period_duration)
	public void onTooltipPeriodDurationClicked() {
		showTooltip(periodDurationGroup, R.string.tooltip_period_duration);
	}

	@OnClick(R.id.tooltip_manager_share)
	public void onTooltipManagerShareClicked() {
		showTooltip(managerShareGroup, R.string.tooltip_managers_funds_share);
	}

	@OnClick(R.id.tooltip_trades)
	public void onTooltipTradesClicked() {
		showTooltip(tradesGroup, R.string.tooltip_trades);
	}

	@OnClick(R.id.tooltip_success_fee)
	public void onTooltipSuccessFeeClicked() {
		showTooltip(successFeeGroup, R.string.tooltip_success_fee);
	}

	@OnClick(R.id.tooltip_management_fee)
	public void onTooltipManagementFeeClicked() {
		showTooltip(managementFeeGroup, R.string.tooltip_management_fee);
	}

	@OnClick(R.id.tooltip_my_tokens)
	public void onTooltipMyTokensClicked() {
		showTooltip(myTokensCard, R.string.tooltip_my_tokens);
	}

	@OnClick(R.id.tooltip_my_profit)
	public void onTooltipMyProfitClicked() {
		showTooltip(myTokensCard, R.string.tooltip_my_profit);
	}

	@OnClick(R.id.tooltip_available_tokens)
	public void onTooltipAvailableTokensClicked() {
		showTooltip(availableToInvestCard, R.string.tooltip_available_to_invest);
	}

	private void showTooltip(View view, int tooltipTextResId) {
		int[] viewLocation = new int[2];
		view.getLocationInWindow(viewLocation);
		float viewX = viewLocation[0];
		float viewY = viewLocation[1];

		TooltipModel tooltipModel = new TooltipModel(
				viewX + view.getWidth() / 2,
				viewY,
				viewY + view.getHeight(),
				getString(tooltipTextResId));

		if (getActivity() != null)
			TooltipActivity.startWith(getActivity(), tooltipModel);
	}

	@OnClick(R.id.button_invest)
	public void onInvestClicked() {
		if (programDetails == null || getActivity() == null)
			return;
		if (programDetails.getAvailableInvestment() == 0) {
			showInvestmentNotAvailableDialog();
			return;
		}
		ProgramRequest request = new ProgramRequest();
		request.programId = programDetails.getId();
		request.programName = programDetails.getTitle();
		request.programCurrency = programDetails.getCurrency().toString();
		request.available = programDetails.getAvailableInvestment();
		InvestProgramActivity.startWith(getActivity(), request);
	}

	@OnClick(R.id.button_withdraw)
	public void onWithdrawClicked() {
		if (programDetails == null || getActivity() == null)
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
		if (programDetails == null || getActivity() == null)
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
				if (getActivity() != null)
					((ProgramInfoActivity) getActivity()).onChartTouch();
				refreshLayout.setEnabled(false);
			}

			@Override
			public void onStop() {
				if (getActivity() != null)
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
		round.setTypeface(TypefaceUtil.light());
		roundLabel.setTypeface(TypefaceUtil.bold());

		place.setTypeface(TypefaceUtil.light());
		placeLabel.setTypeface(TypefaceUtil.bold());

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

		myTokens.setTypeface(TypefaceUtil.light());
		myTokensFiat.setTypeface(TypefaceUtil.bold());
		myTokensLabel.setTypeface(TypefaceUtil.bold());

		myProfit.setTypeface(TypefaceUtil.light());
		profitCurrency.setTypeface(TypefaceUtil.bold());
		myProfitLabel.setTypeface(TypefaceUtil.bold());

		availableToInvestLabel.setTypeface(TypefaceUtil.bold());
		availableToInvestText.setTypeface(TypefaceUtil.light());
		availableCurrency.setTypeface(TypefaceUtil.bold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> programDetailsPresenter.onSwipeRefresh());
	}

	@Override
	public void setProgramDetails(InvestmentProgramDetails programDetails) {
		this.programDetails = programDetails;

		if (programDetails.isIsTournament()) {
			tournamentGroup.setVisibility(View.VISIBLE);
			round.setText(String.valueOf(programDetails.getRoundNumber()));
			place.setText(String.valueOf(programDetails.getPlace()));
		}
		else {
			tournamentGroup.setVisibility(View.GONE);
		}

		programDataView.setData(new InvestmentProgramExtended(programDetails));

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

		if (programDetails.isIsHistoryEnable()) {
			myTokensCard.setVisibility(View.VISIBLE);

			myTokens.setText(StringFormatUtil.formatAmount(programDetails.getInvestedTokens(), 0, Constants.TOKENS_MAX_DECIMAL_POINT_DIGITS));
			double tokensFiatValue = programDetails.getInvestedTokens() * programDetails.getToken().getInitialPrice();
			myTokensFiat.setText(String.format(Locale.getDefault(), "($%.2f)", tokensFiatValue));

			myProfit.setText(StringFormatUtil.formatAmount(programDetails.getProfitFromProgram(), 0,
					StringFormatUtil.getCurrencyMaxFraction(WalletTransaction.CurrencyEnum.GVT.toString())));
		}
		else {
			myTokensCard.setVisibility(View.GONE);
		}

		availableToInvestText.setText(StringFormatUtil.formatAmount(programDetails.getAvailableInvestment(), 0,
				StringFormatUtil.getCurrencyMaxFraction(WalletTransaction.CurrencyEnum.GVT.toString())));

		investButton.setVisibility(programDetails.isIsInvestEnable() ? View.VISIBLE : View.GONE);
		withdrawButton.setVisibility(programDetails.isIsWithdrawEnable() ? View.VISIBLE : View.GONE);
		requestsButton.setVisibility(programDetails.isHasNewRequests() ? View.VISIBLE : View.GONE);

		scrollView.setVisibility(View.VISIBLE);
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
	}

	@Override
	public void pagerShow() {
		if (programDetailsPresenter != null)
			programDetailsPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}

	private void showInvestmentNotAvailableDialog() {
		showMessageDialog(getString(R.string.no_available_tokens_to_purchase));
	}
}