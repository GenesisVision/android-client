package vision.genesis.clientapp.ui.chart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.ChartSimple;
import io.swagger.client.model.DashboardChartValue;
import io.swagger.client.model.ValueChartBar;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateValueFormatter;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/08/2018.
 */

public class PortfolioChartView extends RelativeLayout
{
	public interface TouchListener
	{
		void onTouch(int index);

		void onStop();
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

	private IAxisValueFormatter xAxisValueFormatter = new DateValueFormatter();

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
		chart.getAxisLeft().setEnabled(true);
		chart.getAxisRight().setEnabled(false);
		chart.setDrawBorders(false);
		chart.setAutoScaleMinMaxEnabled(true);
		chart.setNoDataText(GenesisVisionApplication.INSTANCE.getResources().getString(R.string.not_enough_data));
		chart.setNoDataTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		chart.setViewPortOffsets(0f, 0f, 0f, 0f);

		chart.setDrawOrder(new DrawOrder[]{
				DrawOrder.BAR, DrawOrder.LINE
		});

		YAxis yAxis = chart.getAxisLeft();
		yAxis.setDrawLabels(false);
		yAxis.setDrawAxisLine(false);
		yAxis.setDrawGridLines(false);

		XAxis xAxis = chart.getXAxis();
		xAxis.setValueFormatter(xAxisValueFormatter);
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		xAxis.setAvoidFirstLastClipping(true);
		xAxis.setAxisMinimum(0f);
		xAxis.setGranularity(1f);
		xAxis.setDrawGridLines(false);
		xAxis.setTypeface(TypefaceUtil.medium());
		xAxis.setDrawAxisLine(false);
//		xAxis.setYOffset(-6f);
//		xAxis.setLabelCount(3);
		xAxis.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));

		chart.setOnTouchListener((v, me) -> {
			v.getParent().requestDisallowInterceptTouchEvent(true);
			if (me.getAction() == MotionEvent.ACTION_DOWN || me.getAction() == MotionEvent.ACTION_MOVE) {
				Highlight highlight = chart.getHighlightByTouchPoint(me.getX(), me.getY());
				showHighlight(highlight);
				if (touchListener != null) {
					float index = highlight.getX();
					Timber.d("TEST_CHART %f", index);
					touchListener.onTouch((int) index);
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

	public void setChart(DashboardChartValue chartData) {
		showProgress(false);

		if (chartData.getChart().size() <= 1) {
			chart.clear();
			return;
		}

		float min = chartData.getChart().get(0).getValue().floatValue();
		float max = chartData.getChart().get(0).getValue().floatValue();

		List<Entry> lineEntries = new ArrayList<>();

		int index = 0;
		for (ChartSimple chart : chartData.getChart()) {
//			lineEntries.add(new Entry(chart.getDate().getMillis(), chart.getValue().floatValue()));
			lineEntries.add(new Entry(index, chart.getValue().floatValue()));
//			lineEntries.add(new Entry(index, Math.abs(chart.getValue().floatValue())));
			if (min > chart.getValue().floatValue())
				min = chart.getValue().floatValue();
			if (max < chart.getValue().floatValue())
				max = chart.getValue().floatValue();
			index++;
			if (index == 7)
				break;
		}

		List<BarEntry> barEntries = new ArrayList<>();
		index = 0;
		for (ValueChartBar bar : chartData.getBars()) {
//			barEntries.add(new BarEntry(bar.getDate().getMillis(), bar.getValue().floatValue(), bar.getAssets()));
			barEntries.add(new BarEntry(index, bar.getValue().floatValue()));
			index++;
			if (index == 7)
				break;
		}

		minValue.setText(StringFormatUtil.formatAmount(min, 2, 4));
		maxValue.setText(StringFormatUtil.formatAmount(max, 2, 4));

		setLimitLines(min, max);

//		chart.getAxisLeft().setAxisMaximum(max);
//		chart.getAxisLeft().setAxisMinimum(min);

		CombinedData combinedData = new CombinedData();
		combinedData.setData(getLineData(lineEntries));
		combinedData.setData(getBarData(barEntries));
		chart.setData(combinedData);
		chart.invalidate();
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
//			dataSet.setFillDrawable(ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.chart_background_gradient));
//			dataSet.setDrawFilled(true);
//		}

		return dataSet;
	}

	private BarData getBarData(List<BarEntry> data) {
		Collections.sort(data, new EntryXComparator());
		BarData barData = new BarData();

		barData.addDataSet(createBarDataSet(data));
		barData.setBarWidth(0.07f);
		barData.setHighlightEnabled(false);

		return barData;
	}

	private BarDataSet createBarDataSet(List<BarEntry> data) {
		BarDataSet dataSet = new BarDataSet(data, "");

		dataSet.setLabel("");
		dataSet.setDrawValues(false);
		dataSet.setColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));
//		dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//		dataSet.setHighLightColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, highlightColor));

//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//			dataSet.setFillDrawable(ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.chart_background_gradient));
//			dataSet.setDrawFilled(true);
//		}

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

	private void hideHighlight() {
		highlightCircle.setVisibility(View.INVISIBLE);
		chart.highlightValue(null, false);
	}

	private void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		chart.setVisibility(!show ? View.VISIBLE : View.GONE);
	}
}