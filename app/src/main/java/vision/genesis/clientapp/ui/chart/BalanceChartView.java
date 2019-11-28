package vision.genesis.clientapp.ui.chart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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
import io.swagger.client.model.BalanceChartPoint;
import io.swagger.client.model.SimpleChartPoint;
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
 * Created by Vitaly on 19/10/2018.
 */

public class BalanceChartView extends RelativeLayout
{
	public interface TouchListener
	{
		void onTouch(float index);

		void onStop();
	}

	@BindView(R.id.chart)
	public LineChart chart;

	@BindView(R.id.highlight_circle)
	public View highlightCircle;

	@BindView(R.id.min_value)
	public TextView minValue;

	@BindView(R.id.max_value)
	public TextView maxValue;

	@BindView(R.id.chart_progress_bar)
	public ProgressBar progressBar;

	private Unbinder unbinder;

	private int managerColor = R.color.balanceManagerColor;

	private int investorsColor = R.color.balanceInvestorsColor;

	private int profitColor = R.color.balanceProfitColor;

	private int highlightColor = R.attr.colorAccent;

	private TouchListener touchListener;

	private IAxisValueFormatter xAxisDateValueFormatter = new DateValueFormatter();

	private IAxisValueFormatter xAxisTimeValueFormatter = new TimeValueFormatter();

	public BalanceChartView(Context context) {
		super(context);
		initView();
	}

	public BalanceChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public BalanceChartView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_balance_chart, this);

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
		chart.setAutoScaleMinMaxEnabled(false);
		chart.setNoDataText(GenesisVisionApplication.INSTANCE.getResources().getString(R.string.not_enough_data));
		chart.setNoDataTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		chart.setNoDataTextTypeface(TypefaceUtil.regular());
		chart.setViewPortOffsets(0f, 0f, 0f,
				TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
						GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics()));

		YAxis yAxis = chart.getAxisLeft();
		yAxis.setDrawLabels(false);
		yAxis.setDrawAxisLine(false);
		yAxis.setDrawGridLines(false);

		XAxis xAxis = chart.getXAxis();
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
		xAxis.setAvoidFirstLastClipping(true);
		xAxis.setGranularity(1f);
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
				if (highlight != null) {
					showHighlight(highlight);
					if (touchListener != null) {
						touchListener.onTouch(highlight.getY());
					}
				}
			}
			else if (me.getAction() == MotionEvent.ACTION_UP || me.getAction() == MotionEvent.ACTION_CANCEL) {
				hideHighlight();
				if (touchListener != null) {
					touchListener.onStop();
				}
				v.getParent().requestDisallowInterceptTouchEvent(false);
			}
			return true;
		});
	}

//	public void setProgramChartData(List<ProgramBalanceChartElement> balanceChart, DateRange dateRange) {
//		if (balanceChart.size() <= 1) {
//			chart.clear();
//			return;
//		}
//
//		float min = 0;
//		float max = 0;
//
//		List<Entry> managerEntries = new ArrayList<>();
//		List<Entry> investorsEntries = new ArrayList<>();
//		List<Entry> profitEntries = new ArrayList<>();
//
//		for (ProgramBalanceChartElement element : balanceChart) {
//			float managerValue = element.getManagerFunds().floatValue();
//			float investorsValue = managerValue + element.getInvestorsFunds().floatValue();
//			float profitValue = investorsValue + element.getProfit().floatValue();
//
//			managerEntries.add(new Entry(element.getDate().getMillis() / 1000 / 60, managerValue));
//			investorsEntries.add(new Entry(element.getDate().getMillis() / 1000 / 60, investorsValue));
//			profitEntries.add(new Entry(element.getDate().getMillis() / 1000 / 60, profitValue));
//
//			if (min > profitValue)
//				min = profitValue;
//			if (max < profitValue)
//				max = profitValue;
//		}
//
//		setChartData(managerEntries, investorsEntries, profitEntries, min, max, dateRange);
//	}

	public void setChart(List<BalanceChartPoint> balanceChart, DateRange dateRange) {
		if (balanceChart.size() <= 1) {
			chart.clear();
			return;
		}

		float min = 0;
		float max = 0;

		List<Entry> managerEntries = new ArrayList<>();
		List<Entry> investorsEntries = new ArrayList<>();
		List<Entry> profitEntries = new ArrayList<>();

		for (BalanceChartPoint element : balanceChart) {
			float managerValue = element.getManagerFunds().floatValue();
			float investorsValue = managerValue + element.getInvestorsFunds().floatValue();

			managerEntries.add(new Entry(element.getDate() / 1000 / 60, managerValue));
			investorsEntries.add(new Entry(element.getDate() / 1000 / 60, investorsValue));
			profitEntries.add(new Entry(element.getDate() / 1000 / 60, investorsValue));

			if (min > investorsValue) {
				min = investorsValue;
			}
			if (max < investorsValue) {
				max = investorsValue;
			}
		}

		setChartData(managerEntries, investorsEntries, min, max, dateRange);
	}

	public void setSimpleChart(List<SimpleChartPoint> balanceChart, DateRange dateRange) {
		if (balanceChart.size() <= 1) {
			chart.clear();
			return;
		}

		float min = 0;
		float max = 0;

		List<Entry> managerEntries = new ArrayList<>();
		List<Entry> investorsEntries = new ArrayList<>();

		for (SimpleChartPoint element : balanceChart) {
			float managerValue = element.getValue().floatValue();
			float investorsValue = 0f;

			managerEntries.add(new Entry(element.getDate() / 1000 / 60, managerValue));
			investorsEntries.add(new Entry(element.getDate() / 1000 / 60, investorsValue));

			if (min > investorsValue) {
				min = investorsValue;
			}
			if (max < investorsValue) {
				max = investorsValue;
			}
		}

		setChartData(managerEntries, investorsEntries, min, max, dateRange);
	}

	private void setChartData(List<Entry> managerEntries, List<Entry> investorsEntries, float min, float max, DateRange dateRange) {
		updateXAxis(dateRange);

		minValue.setText(StringFormatUtil.formatAmount(min, 2, 4));
		maxValue.setText(StringFormatUtil.formatAmount(max, 2, 4));

		setLimitLines(min, max);

		chart.setData(getLineData(managerEntries, investorsEntries));
		chart.getXAxis().setAxisMaximum(chart.getData().getXMax() + 1f);
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
		yAxis.setAxisMinimum(min);
		yAxis.setAxisMaximum(max);

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

	private LineData getLineData(List<Entry> managerEntries, List<Entry> investorsEntries) {
		Collections.sort(managerEntries, new EntryXComparator());
		Collections.sort(investorsEntries, new EntryXComparator());

		LineData lineData = new LineData();

		lineData.addDataSet(createLineDataSet(investorsEntries, investorsColor, false));
		lineData.addDataSet(createLineDataSet(managerEntries, managerColor, false));

		return lineData;
	}

	private LineDataSet createLineDataSet(List<Entry> data, int color, boolean highlightEnabled) {
		LineDataSet dataSet = new LineDataSet(data, "");

		dataSet.setLabel("");
		dataSet.setDrawValues(false);
		dataSet.setDrawCircles(false);
		dataSet.setColor(ContextCompat.getColor(getContext(), color));
//		dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		dataSet.setLineWidth(1.2f);

		dataSet.setHighlightEnabled(highlightEnabled);
		dataSet.setDrawHorizontalHighlightIndicator(false);
		dataSet.setHighLightColor(ThemeUtil.getColorByAttrId(getContext(), highlightColor));
		dataSet.setHighlightLineWidth(1.5f);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
			dataSet.setFillDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), color)));
			dataSet.setDrawFilled(true);
		}

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
		if (highlight == null) {
			return;
		}
		highlightCircle.setVisibility(View.VISIBLE);
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
	}
}