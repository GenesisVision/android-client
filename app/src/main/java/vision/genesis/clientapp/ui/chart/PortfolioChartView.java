package vision.genesis.clientapp.ui.chart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.AssetsValue;
import io.swagger.client.model.ChartSimple;
import io.swagger.client.model.DashboardChartValue;
import io.swagger.client.model.ValueChartBar;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.utils.DateValueFormatter;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TimeValueFormatter;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/08/2018.
 */

public class PortfolioChartView extends RelativeLayout
{
	public interface TouchListener
	{
		void onTouch(int lineIndex, int barIndex);
	}

	@BindView(R.id.chart)
	public CombinedChart chart;

	@BindView(R.id.highlight_circle)
	public View highlightCircle;

	@BindView(R.id.min_value)
	public TextView minValue;

	@BindView(R.id.max_value)
	public TextView maxValue;

	@BindView(R.id.chart_progress_bar)
	public ProgressBar progressBar;

	private Unbinder unbinder;

	private int lineColor = R.color.colorAccent;

	private int highlightColor = R.color.colorMedium;

	private TouchListener touchListener;

	private IAxisValueFormatter xAxisDateValueFormatter = new DateValueFormatter();

	private IAxisValueFormatter xAxisTimeValueFormatter = new TimeValueFormatter();

	private DashboardChartValue chartData;

	private DateRange dateRange;

	private BarDataSet selectedBarDataSet;

	public PortfolioChartView(Context context) {
		super(context);
		initView();
	}

	public PortfolioChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PortfolioChartView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_portfolio_chart, this);

		unbinder = ButterKnife.bind(this);

		initChart();
	}

	@Nullable
	@Override
	protected Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable("superState", super.onSaveInstanceState());
		return bundle;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle) // implicit null check
		{
			Bundle bundle = (Bundle) state;
			state = bundle.getParcelable("superState");
		}
		super.onRestoreInstanceState(state);
	}

	public void setTouchListener(TouchListener touchListener) {
		this.touchListener = touchListener;
	}

	@SuppressLint("ClickableViewAccessibility")
	private void initChart() {
//		chart.setHardwareAccelerationEnabled(false);
		chart.getDescription().setEnabled(false);
		chart.setDrawGridBackground(false);
		chart.setDragEnabled(false);
		chart.setTouchEnabled(true);
		chart.setDoubleTapToZoomEnabled(false);
		chart.getXAxis().setEnabled(true);
		chart.getLegend().setEnabled(false);
		chart.getAxisLeft().setEnabled(false);
		chart.getAxisRight().setEnabled(false);
		chart.setDrawBorders(false);
		chart.setAutoScaleMinMaxEnabled(true);
		chart.setNoDataText(GenesisVisionApplication.INSTANCE.getResources().getString(R.string.not_enough_data));
		chart.setNoDataTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		chart.setNoDataTextTypeface(TypefaceUtil.regular());
//		chart.setViewPortOffsets(0f, 0f, 0f,
//				TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
//						GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics()));

		chart.setDrawOrder(new DrawOrder[]{
				DrawOrder.BAR, DrawOrder.LINE
		});

		YAxis yAxis = chart.getAxisLeft();
		yAxis.setDrawLabels(false);
		yAxis.setDrawAxisLine(false);
		yAxis.setDrawGridLines(false);

		XAxis xAxis = chart.getXAxis();
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
		xAxis.setAvoidFirstLastClipping(true);
		xAxis.setDrawGridLines(false);
		xAxis.setTypeface(TypefaceUtil.regular());
		xAxis.setDrawAxisLine(false);
		xAxis.setYOffset(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -2,
				GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics()));
		xAxis.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));

		chart.setOnTouchListener((v, me) -> {
			v.getParent().requestDisallowInterceptTouchEvent(true);
			if (me.getAction() == MotionEvent.ACTION_DOWN || me.getAction() == MotionEvent.ACTION_MOVE) {
				Highlight highlight = chart.getHighlightByTouchPoint(me.getX(), me.getY());
				Entry entry = chart.getEntryByTouchPoint(me.getX(), me.getY());
				if (highlight != null) {
					showHighlight(highlight);
					if (touchListener != null) {
						int barIndex = findBarSetIndex(highlight.getX());
						Timber.d("TEST_CHART %d", barIndex);
						touchListener.onTouch(chart.getLineData().getDataSetByIndex(0).getEntryIndex(entry), barIndex);
						selectBarByIndex(barIndex);
					}
				}
			}
//			else if (me.getAction() == MotionEvent.ACTION_UP || me.getAction() == MotionEvent.ACTION_CANCEL) {
//				hideHighlight();
//				if (touchListener != null)
//					touchListener.onStop();
//				v.getParent().requestDisallowInterceptTouchEvent(false);
//			}
			return true;
		});
	}

	private void selectBarByIndex(int index) {
		BarDataSet barDataSet = (BarDataSet) chart.getCombinedData().getBarData().getDataSetByIndex(index);
		barDataSet.setColors(getResources().getIntArray(R.array.assetsColors));
		if (selectedBarDataSet != null && !selectedBarDataSet.equals(barDataSet))
			selectedBarDataSet.setColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));
		selectedBarDataSet = barDataSet;
		chart.invalidate();
	}

	private void deselectBarDataSet() {
		if (selectedBarDataSet != null)
			selectedBarDataSet.setColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));
	}

	private int findBarSetIndex(float date) {
		float datesGap = (dateRange.getTo().getMillis() - dateRange.getFrom().getMillis()) / 2 / 1000 / 60;
		if (chartData.getInvestedProgramsInfo().size() >= 2)
			datesGap = (chartData.getInvestedProgramsInfo().get(1).getDate().getMillis() - chartData.getInvestedProgramsInfo().get(0).getDate().getMillis()) / 1000 / 60;
		int index = 0;
		for (ValueChartBar valueChartBar : chartData.getInvestedProgramsInfo()) {
			if (date >= valueChartBar.getDate().getMillis() / 1000 / 60 - datesGap
					&& date < valueChartBar.getDate().getMillis() / 1000 / 60 + datesGap)
				break;
			index++;
		}
		return index;
	}

	public void setChart(DashboardChartValue chartData, DateRange dateRange) {
		showProgress(false);
		this.chartData = chartData;
		this.dateRange = dateRange;

		if (chartData.getBalanceChart().size() <= 1) {
			chart.clear();
			return;
		}

		updateXAxis(dateRange);

		float min = chartData.getBalanceChart().get(0).getValue().floatValue();
		float max = chartData.getBalanceChart().get(0).getValue().floatValue();

		List<Entry> lineEntries = new ArrayList<>();

		int index = 0;
		for (ChartSimple chart : chartData.getBalanceChart()) {
			lineEntries.add(new Entry((int) (chart.getDate().getMillis() / 1000 / 60), chart.getValue().floatValue()));
//			lineEntries.add(new Entry(index, chart.getValue().floatValue()));
//			lineEntries.add(new Entry(index, Math.abs(chart.getValue().floatValue())));
			if (min > chart.getValue().floatValue())
				min = chart.getValue().floatValue();
			if (max < chart.getValue().floatValue())
				max = chart.getValue().floatValue();
			index++;
		}

		List<BarEntry> barEntries = new ArrayList<>();
		index = 0;
		for (ValueChartBar bar : chartData.getInvestedProgramsInfo()) {
			List<Float> vals = new ArrayList<>();
			for (AssetsValue assetsValue : bar.getTopAssets()) {
				vals.add(assetsValue.getValue().floatValue());
			}
			if (bar.getOtherAssetsValue() != null && bar.getOtherAssetsValue().getValue() != null)
				vals.add(bar.getOtherAssetsValue().getValue().floatValue());
//			Collections.reverse(vals);
//			barEntries.add(new BarEntry((int) (bar.getDate().getMillis() / 1000 / 60), bar.getValue().floatValue()));
			float[] valsArray = new float[vals.size()];
			for (int i = 0; i < vals.size(); i++) {
				valsArray[i] = vals.get(i);
			}
			barEntries.add(new BarEntry((int) (bar.getDate().getMillis() / 1000 / 60), valsArray));
//			barEntries.add(new BarEntry(index, bar.getValue().floatValue()));
			index++;
		}

		minValue.setText(StringFormatUtil.formatAmount(min, 2, 4));
		maxValue.setText(StringFormatUtil.formatAmount(max, 2, 4));

		setLimitLines(min, max);

//		chart.getAxisLeft().setAxisMaximum(max);
//		chart.getAxisLeft().setAxisMinimum(min);

		CombinedData combinedData = new CombinedData();
		combinedData.setData(getLineData(lineEntries));
		combinedData.setData(getBarData(barEntries, dateRange));
		chart.setData(combinedData);
		chart.invalidate();
	}

	private void updateXAxis(DateRange dateRange) {
		XAxis xAxis = chart.getXAxis();
//		if (!dateRange.getSelectedRange().equals(DateRange.DateRangeEnum.ALL_TIME)) {
//			xAxis.setAxisMinimum(dateRange.getFrom().getMillis() / 1000 / 60);
//		}
//		else {
		xAxis.resetAxisMinimum();
//		}
		xAxis.setAxisMaximum(dateRange.getTo().getMillis() / 1000 / 60);
		switch (dateRange.getSelectedRange()) {
			case DAY:
				xAxis.setValueFormatter(xAxisTimeValueFormatter);
				xAxis.setLabelCount(6, true);
				break;
			case WEEK:
				xAxis.setValueFormatter(xAxisDateValueFormatter);
				xAxis.setLabelCount(7, true);
				break;
			case MONTH:
				xAxis.setValueFormatter(xAxisDateValueFormatter);
				xAxis.setLabelCount(4, true);
				break;
			case YEAR:
				xAxis.setValueFormatter(xAxisDateValueFormatter);
				xAxis.setLabelCount(6, true);
				break;
			case ALL_TIME:
				xAxis.setValueFormatter(xAxisDateValueFormatter);
				xAxis.setLabelCount(6, true);
				break;
			case CUSTOM:
				xAxis.setValueFormatter(xAxisDateValueFormatter);
				xAxis.setLabelCount(6, true);
				break;
		}
	}

	private void setLimitLines(float min, float max) {
		YAxis yAxis = chart.getAxisLeft();
		yAxis.setDrawLimitLinesBehindData(true);

		yAxis.removeAllLimitLines();
//		yAxis.addLimitLine(createLimitLine(0f));
		yAxis.addLimitLine(createLimitLine(min));
		yAxis.addLimitLine(createLimitLine(max));
	}

	private LimitLine createLimitLine(float limit) {
		LimitLine ll = new LimitLine(limit, "");
		ll.setLineColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorDelimiterLight));
		ll.setLineWidth(1f);
		int lineLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics());
		int spaceLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics());
		ll.enableDashedLine(lineLength, spaceLength, 0);
		return ll;
	}

	private LineData getLineData(List<Entry> data) {
		Collections.sort(data, new EntryXComparator());
		LineData lineData = new LineData();

		lineData.addDataSet(createLineDataSet(data));

		return lineData;
	}

	private LineDataSet createLineDataSet(List<Entry> data) {
		LineDataSet dataSet = new LineDataSet(data, "");

		dataSet.setLabel("");
		dataSet.setDrawValues(false);
		dataSet.setDrawCircles(false);
		dataSet.setColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, lineColor));
//		dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		dataSet.setLineWidth(1.2f);
		dataSet.setDrawHorizontalHighlightIndicator(false);
		dataSet.setHighLightColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, highlightColor));
		dataSet.setHighlightLineWidth(1.5f);

//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//			dataSet.setFillDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.chart_background_gradient));
//			dataSet.setDrawFilled(true);
//		}

		return dataSet;
	}

	private BarData getBarData(List<BarEntry> data, DateRange dateRange) {
		Collections.sort(data, new EntryXComparator());
		BarData barData = new BarData();

		for (BarEntry entry : data) {
			List<BarEntry> dataList = new ArrayList<>();
			dataList.add(entry);
			barData.addDataSet(createBarDataSet(dataList));
		}
		if (!data.isEmpty())
			barData.setBarWidth((dateRange.getTo().getMillis() / 1000 / 60 - data.get(0).getX()) / data.size() * 0.2f);
		barData.setHighlightEnabled(false);

		return barData;
	}

	private BarDataSet createBarDataSet(List<BarEntry> data) {
		Collections.reverse(data);
		BarDataSet dataSet = new BarDataSet(data, "");

		dataSet.setLabel("");
		dataSet.setDrawValues(false);
		dataSet.setColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));

		return dataSet;
	}

	public void onDestroy() {
		chart.invalidate();

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void showHighlight(Highlight highlight) {
		if (highlight == null)
			return;
		highlightCircle.setVisibility(View.VISIBLE);
//		chart.highlightValue(highlight, false);
		moveHighlightCircle(highlight);
	}

	private void moveHighlightCircle(Highlight highlight) {
		float x = highlight.getXPx() - highlightCircle.getWidth() / 2;
		float y = highlight.getYPx() - highlightCircle.getHeight() / 2 + chart.getY();

		highlightCircle.setX(x);
		highlightCircle.setY(y);
	}

	public void hideHighlight() {
		highlightCircle.setVisibility(View.INVISIBLE);
		chart.highlightValue(null, false);
		deselectBarDataSet();
	}

	private void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		chart.setVisibility(!show ? View.VISIBLE : View.GONE);
	}
}