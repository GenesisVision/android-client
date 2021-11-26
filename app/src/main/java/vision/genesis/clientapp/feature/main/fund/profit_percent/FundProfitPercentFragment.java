package vision.genesis.clientapp.feature.main.fund.profit_percent;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.FundAssetPartWithIcon;
import io.swagger.client.model.FundChartStatistic;
import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.SimpleChart;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.FundDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.fund.profit_percent.glossary.FundStatisticsGlossaryBottomSheetDialog;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.ChartAssetView;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.FundAssetProfitView;
import vision.genesis.clientapp.ui.chart.ProfitChartView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypedValueFormatter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/11/2021.
 */

public class FundProfitPercentFragment extends BaseFragment implements FundProfitPercentView, FundDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_FUND_ID = "extra_fund_id";

	public static FundProfitPercentFragment with(UUID fundID) {
		FundProfitPercentFragment fragment = new FundProfitPercentFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_FUND_ID, fundID);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.currencies_flex_box)
	public FlexboxLayout currenciesFlexBox;

	@BindView(R.id.group_line)
	public LinearLayout lineGroup;

	@BindView(R.id.assets_flex_box)
	public FlexboxLayout assetsFlexBox;

	@BindView(R.id.profit_chart)
	public ProfitChartView profitChart;

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
	public FundProfitPercentPresenter presenter;

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
			bottomSheetDialog.setListener(presenter);
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fund_profit_percent, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		fundId = (UUID) getArguments().getSerializable(EXTRA_FUND_ID);
		presenter.setFundId(fundId);

		profitChart.setTouchListener(presenter);
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
	public void setPercentChart(List<SimpleChart> chart) {
		profitChart.setMultipleChartData(chart, dateRange);
	}

	@Override
	public void setAssets(List<FundAssetPartWithIcon> assets) {
		lineGroup.removeAllViews();
		assetsFlexBox.removeAllViews();
		for (FundAssetPartWithIcon asset : assets) {
			if (asset.getPercent() > 0) {
				addAsset(asset);
			}
		}
	}

	private void addAsset(FundAssetPartWithIcon asset) {
		addAssetLine(asset);
		addAssetView(asset);
	}

	private void addAssetLine(FundAssetPartWithIcon asset) {
		View view = new View(getContext());
		try {
			view.setBackgroundColor(Color.parseColor(asset.getColor()));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			view.setBackgroundColor(Color.parseColor("#00bdaf"));
		}
		lineGroup.addView(view);
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
		lp.weight = asset.getPercent().floatValue();
		lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
		lp.width = 0;
		view.setLayoutParams(lp);
	}

	private void addAssetView(FundAssetPartWithIcon asset) {
		FundAssetProfitView view = new FundAssetProfitView(getContext());
		view.setAsset(asset);
		assetsFlexBox.addView(view);
		FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
		lp.setMargins(0, 0, TypedValueFormatter.toDp(10), TypedValueFormatter.toDp(4));
		view.setLayoutParams(lp);
	}

	@Override
	public void setCurrencies(List<PlatformCurrencyInfo> selectedCurrencies, boolean showAddButton) {
		currenciesFlexBox.removeAllViews();
		for (int i = 0; i < selectedCurrencies.size(); i++) {
			ChartAssetView view = new ChartAssetView(getContext());
			view.setAsset(selectedCurrencies.get(i));
			view.setRemoveEnabled(i > 0);
			int position = i;
			view.setListener(new ChartAssetView.Listener()
			{
				@Override
				public void onAssetClicked(PlatformCurrencyInfo asset) {
					presenter.onAssetClicked(asset, position);
				}

				@Override
				public void onRemoveAssetClicked(PlatformCurrencyInfo asset) {
					presenter.onRemoveAssetClicked(asset);
				}
			});
			currenciesFlexBox.addView(view);
			FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
			lp.setMargins(0, 0, TypedValueFormatter.toDp(10), TypedValueFormatter.toDp(4));
			view.setLayoutParams(lp);
		}

		if (showAddButton) {
			showAddButton();
		}
	}

	@Override
	public void showAddCurrenciesList(ArrayList<String> optionsList) {
		if (getActivity() != null) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.currency), optionsList, -1);
			fragment.setListener((position, text) -> presenter.onAddCurrencySelected(text));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@Override
	public void showReplaceCurrenciesList(ArrayList<String> optionsList, PlatformCurrencyInfo assetToReplace) {
		if (getActivity() != null) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.currency), optionsList, -1);
			fragment.setListener((position, text) -> presenter.onReplaceCurrencySelected(text, assetToReplace));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@Override
	public void showChangeBaseCurrenciesList(ArrayList<String> optionsList) {
		if (getActivity() != null) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.currency), optionsList, -1);
			fragment.setListener((position, text) -> presenter.onChangeBaseCurrencySelected(text));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	private void showAddButton() {
		ImageView view = new ImageView(getContext());
		view.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_add_circle_black_24dp));
		view.setOnClickListener((view1) -> presenter.onAddAssetClicked());

		currenciesFlexBox.addView(view);
		FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
		lp.setWidth(TypedValueFormatter.toDp(28));
		lp.setHeight(TypedValueFormatter.toDp(28));
		lp.setMargins(0, TypedValueFormatter.toDp(10), 0, 0);
		view.setLayoutParams(lp);
	}

	@Override
	public void setValue(boolean isNegative, String value) {
		this.value.setText(value);
		this.value.setTextColor(isNegative
				? ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed)
				: ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen));
	}

	@Override
	public void updateStatistics(FundChartStatistic statistic, String baseCurrency) {
		this.startDay.setText(DateTimeUtil.formatDate(statistic.getCreationDate()));
		this.balance.setText(StringFormatUtil.getValueString(statistic.getBalance(), baseCurrency));
		this.investors.setText(String.valueOf(statistic.getInvestors()));


		this.sharpeRatio.setText(StringFormatUtil.formatAmount(statistic.getSharpeRatio(), 0, 4));
		this.sortinoRatio.setText(StringFormatUtil.formatAmount(statistic.getSortinoRatio(), 0, 4));
		this.calmarRatio.setText(StringFormatUtil.formatAmount(statistic.getCalmarRatio(), 0, 4));
		this.maxDrawdown.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(statistic.getMaxDrawdown(), 0, 4)));
		this.profitChange.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(statistic.getProfitPercent(), 0, 4)));
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