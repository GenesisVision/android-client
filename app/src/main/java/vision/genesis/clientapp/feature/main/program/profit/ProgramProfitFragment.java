package vision.genesis.clientapp.feature.main.program.profit;

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

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.Currency;
import io.swagger.client.model.ProgramChartStatistic;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.SimpleChart;
import io.swagger.client.model.SimpleChartPoint;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.program.profit.glossary.ProgramStatisticsGlossaryBottomSheetDialog;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.chart.ProfitChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2018.
 */

public class ProgramProfitFragment extends BaseFragment implements ProgramProfitView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_PROGRAM_DETAILS = "extra_program_details";

	public static ProgramProfitFragment with(ProgramFollowDetailsFull details) {
		ProgramProfitFragment programProfitFragment = new ProgramProfitFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_PROGRAM_DETAILS, details);
		programProfitFragment.setArguments(arguments);
		return programProfitFragment;
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

	@BindView(R.id.amount_title)
	public TextView amountTitle;

	@BindView(R.id.amount_value)
	public TextView amountValue;

	@BindView(R.id.amount_value_secondary)
	public TextView amountValueSecondary;

	@BindView(R.id.group_change)
	public ViewGroup changeGroup;

	@BindView(R.id.change_value)
	public TextView changeValue;

	@BindView(R.id.label_statistics)
	public TextView statisticsLabel;

	@BindView(R.id.equity)
	public TextView equity;

	@BindView(R.id.group_investors)
	public ViewGroup investorsGroup;

	@BindView(R.id.investors)
	public TextView investors;

	@BindView(R.id.group_subscribers)
	public ViewGroup subscribersGroup;

	@BindView(R.id.subscribers)
	public TextView subscribers;

	@BindView(R.id.success_trades)
	public TextView successTrades;

	@BindView(R.id.profit_factor)
	public TextView profitFactor;

	@BindView(R.id.sharpe_ratio)
	public TextView sharpeRatio;

	@BindView(R.id.sortino_ratio)
	public TextView sortinoRatio;

	@BindView(R.id.calmar_ratio)
	public TextView calmarRatio;

	@BindView(R.id.trades)
	public TextView trades;

	@BindView(R.id.max_drawdown)
	public TextView maxDrawdown;

	@BindView(R.id.trading_volume)
	public TextView tradingVolume;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@InjectPresenter
	public ProgramProfitPresenter presenter;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK);

	private ProgramFollowDetailsFull details;

	private ProgramChartStatistic statistic = null;

	@OnClick(R.id.glossary)
	public void onGlossaryClicked() {
		if (getActivity() != null && statistic != null) {
			ProgramStatisticsGlossaryBottomSheetDialog dialog = new ProgramStatisticsGlossaryBottomSheetDialog(
					statistic.getInvestors() != null, statistic.getSubscribers() != null);
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
		}
	}

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		if (getActivity() != null) {
			DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setDateRange(dateRange);
			bottomSheetDialog.setListener(presenter);
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_program_profit, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		if (getArguments() != null) {
			details = getArguments().getParcelable(EXTRA_PROGRAM_DETAILS);
			if (details != null) {
				presenter.setData(details);

				absChart.setTouchListener(presenter);
				return;
			}
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
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
		amountTitle.setText(StringFormatUtil.capitalize(getString(R.string.value)));
		amountValue.setTypeface(TypefaceUtil.semibold());
		changeValue.setTypeface(TypefaceUtil.semibold());
		amountValueSecondary.setTypeface(TypefaceUtil.medium());
	}

	@Override
	public void setAbsChart(List<SimpleChartPoint> chart) {
		absChart.setChartData(chart, dateRange);
	}

	@Override
	public void setPercentChart(List<SimpleChart> chart) {

	}

	@Override
	public void updateStatistics(ProgramChartStatistic statistic, Currency baseCurrency) {
		this.statistic = statistic;
		if (statistic.getSubscribers() != null) {
			this.subscribersGroup.setVisibility(View.VISIBLE);
			this.subscribers.setText(String.valueOf(statistic.getSubscribers()));
		}
		if (statistic.getInvestors() != null) {
			this.investorsGroup.setVisibility(View.VISIBLE);
			this.investors.setText(String.valueOf(statistic.getInvestors()));
		}
		this.equity.setText(StringFormatUtil.getValueString(statistic.getBalance(), baseCurrency.getValue()));

		this.trades.setText(String.valueOf(statistic.getTrades()));
		this.successTrades.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(statistic.getSuccessTradesPercent(), 0, 4)));
		this.profitFactor.setText(statistic.getProfitFactor() == null ? "-" : StringFormatUtil.formatAmount(statistic.getProfitFactor(), 0, 4));
		this.sharpeRatio.setText(StringFormatUtil.formatAmount(statistic.getSharpeRatio(), 0, 4));
		this.sortinoRatio.setText(StringFormatUtil.formatAmount(statistic.getSortinoRatio(), 0, 4));
		this.calmarRatio.setText(StringFormatUtil.formatAmount(statistic.getCalmarRatio(), 0, 4));
		this.maxDrawdown.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(statistic.getMaxDrawdown(), 0, 4)));
		this.tradingVolume.setText(StringFormatUtil.getValueString(statistic.getTradingVolume(), baseCurrency.getValue()));
	}

	@Override
	public void setValue(Boolean isNegative, String value) {
		this.amountValue.setTextColor(isNegative
				? ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed)
				: ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen));

		this.amountValue.setText(value);
	}

	@Override
	public void setChange(Boolean isChangeNegative, String changePercent) {
		this.changeValue.setTextColor(isChangeNegative
				? ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed)
				: ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen));

		this.changeValue.setText(changePercent);
	}

	@Override
	public void setChangeVisibility(boolean visible) {
		this.changeGroup.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
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
		if (presenter != null) {
			presenter.onShow();
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