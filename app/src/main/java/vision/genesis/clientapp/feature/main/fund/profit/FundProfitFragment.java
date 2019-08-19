package vision.genesis.clientapp.feature.main.fund.profit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ChartSimple;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.FundDetailsPagerAdapter;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.chart.ProfitChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

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
	public ProfitChartView profitChart;

	@BindView(R.id.value_title)
	public TextView valueTitle;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.label_statistics)
	public TextView statisticsLabel;

	@BindView(R.id.rebalances)
	public TextView rebalances;

	@BindView(R.id.sharpe_ratio)
	public TextView sharpeRatio;

	@BindView(R.id.sortino_ratio)
	public TextView sortinoRatio;

	@BindView(R.id.calmar_ratio)
	public TextView calmarRatio;

	@BindView(R.id.max_drawdown)
	public TextView maxDrawdown;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@InjectPresenter
	public FundProfitPresenter fundProfitPresenter;

	private UUID fundId;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK);

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

		profitChart.setTouchListener(fundProfitPresenter);
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
		value.setTypeface(TypefaceUtil.semibold());

		statisticsLabel.setTypeface(TypefaceUtil.semibold());
		rebalances.setTypeface(TypefaceUtil.semibold());
		sharpeRatio.setTypeface(TypefaceUtil.semibold());
		sortinoRatio.setTypeface(TypefaceUtil.semibold());
		calmarRatio.setTypeface(TypefaceUtil.semibold());
		maxDrawdown.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setChartData(List<ChartSimple> equityChart) {
		profitChart.setFundChartData(equityChart, dateRange);
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
	public void setStatisticsData(Integer rebalances, Double sharpeRatio, Double sortinoRatio, Double calmarRatio, Double maxDrawdown) {
		this.rebalances.setText(String.valueOf(rebalances));
		this.sharpeRatio.setText(StringFormatUtil.formatAmount(sharpeRatio, 0, 4));
		this.sortinoRatio.setText(StringFormatUtil.formatAmount(sortinoRatio, 0, 4));
		this.calmarRatio.setText(StringFormatUtil.formatAmount(calmarRatio, 0, 4));
		this.maxDrawdown.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(maxDrawdown, 0, 4)));
	}

	@Override
	public void pagerShow() {
		if (fundProfitPresenter != null)
			fundProfitPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}

	public void onOffsetChanged(int verticalOffset) {
		if (dateRangeView != null)
			dateRangeView.setY(root.getHeight() - verticalOffset - dateRangeView.getHeight() - dateRangeMarginBottom);
	}
}