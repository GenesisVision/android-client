package vision.genesis.clientapp.feature.main.fund.profit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.Currency;
import io.swagger.client.model.FundChartStatistic;
import io.swagger.client.model.SimpleChart;
import io.swagger.client.model.SimpleChartPoint;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.FundDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.fund.profit.glossary.FundStatisticsGlossaryBottomSheetDialog;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.chart.ProfitChartView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

public class FundProfitFragment extends BaseFragment implements FundProfitView, FundDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_FUND_ID = "extra_fund_id";

	public static FundProfitFragment with(UUID fundID) {
		FundProfitFragment fundProfitFragment = new FundProfitFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_FUND_ID, fundID);
		fundProfitFragment.setArguments(arguments);
		return fundProfitFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.profit_chart)
	public ProfitChartView absChart;

	@BindView(R.id.value_title)
	public TextView valueTitle;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.label_statistics)
	public TextView statisticsLabel;

	@BindView(R.id.start_day)
	public TextView startDay;

	@BindView(R.id.investors)
	public TextView investors;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.sharpe_ratio)
	public TextView sharpeRatio;

	@BindView(R.id.sortino_ratio)
	public TextView sortinoRatio;

	@BindView(R.id.calmar_ratio)
	public TextView calmarRatio;

	@BindView(R.id.max_drawdown)
	public TextView maxDrawdown;

	@BindView(R.id.profit_change)
	public TextView profitChange;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@InjectPresenter
	public FundProfitPresenter fundProfitPresenter;

	private UUID fundId;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK);

	@OnClick(R.id.glossary)
	public void onGlossaryClicked() {
		if (getActivity() != null) {
			FundStatisticsGlossaryBottomSheetDialog dialog = new FundStatisticsGlossaryBottomSheetDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
		}
	}

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		if (getActivity() != null) {
			DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setDateRange(dateRange);
			bottomSheetDialog.setListener(fundProfitPresenter);
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fund_profit, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		fundId = (UUID) getArguments().getSerializable(EXTRA_FUND_ID);
		fundProfitPresenter.setFundId(fundId);

		setFonts();

		absChart.setTouchListener(fundProfitPresenter);
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		valueTitle.setText(StringFormatUtil.capitalize(getString(R.string.value)));
	}

	@Override
	public void setAbsChart(List<SimpleChartPoint> chart) {
		absChart.setChartData(chart, dateRange);
	}

	@Override
	public void setPercentChart(List<SimpleChart> chart) {

	}

	@Override
	public void updateStatistics(FundChartStatistic statistic, Currency baseCurrency) {
		this.startDay.setText(DateTimeUtil.formatDate(statistic.getCreationDate()));
		this.balance.setText(StringFormatUtil.getValueString(statistic.getBalance(), baseCurrency.getValue()));
		this.investors.setText(String.valueOf(statistic.getInvestors()));


		this.sharpeRatio.setText(StringFormatUtil.formatAmount(statistic.getSharpeRatio(), 0, 4));
		this.sortinoRatio.setText(StringFormatUtil.formatAmount(statistic.getSortinoRatio(), 0, 4));
		this.calmarRatio.setText(StringFormatUtil.formatAmount(statistic.getCalmarRatio(), 0, 4));
		this.maxDrawdown.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(statistic.getMaxDrawdown(), 0, 4)));
		this.profitChange.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(statistic.getProfitPercent(), 0, 4)));
	}

	@Override
	public void setValue(boolean isNegative, String value) {
		this.value.setText(value);
		this.value.setTextColor(isNegative
				? ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed)
				: ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen));
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			scrollView.setVisibility(View.VISIBLE);
			dateRangeView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
		dateRangeView.setDateRange(dateRange);
	}

	@Override
	public void pagerShow() {
		if (fundProfitPresenter != null) {
			fundProfitPresenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
	}

	public void onOffsetChanged(int verticalOffset) {
		if (dateRangeView != null) {
			dateRangeView.setY(root.getHeight() - verticalOffset - dateRangeView.getHeight() - dateRangeMarginBottom);
		}
	}
}