package vision.genesis.clientapp.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.Chart;
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 2/2/18.
 */

public class ProfitChartView extends RelativeLayout
{
	@BindView(R.id.combined_chart)
	public CombinedChart chart;

	private int fillColor = R.color.grey300;

	private int lineColor = R.color.colorPrimary;

	public ProfitChartView(Context context) {
		super(context);
		initView();
	}

	public ProfitChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ProfitChartView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_profit_chart, this);

		ButterKnife.bind(this);

		chart.getDescription().setEnabled(false);
		chart.setDrawGridBackground(false);
		chart.setDragEnabled(false);
		chart.setTouchEnabled(false);
		chart.getXAxis().setEnabled(false);
		chart.getLegend().setEnabled(false);
		chart.getAxisLeft().setEnabled(true);
		chart.getAxisRight().setEnabled(false);
		chart.setDrawBorders(false);
		chart.setAutoScaleMinMaxEnabled(true);
		chart.setNoDataText(getContext().getResources().getString(R.string.not_enough_data));
		chart.setNoDataTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

		YAxis yAxis = chart.getAxisLeft();
		yAxis.setDrawLabels(false);
		yAxis.setDrawAxisLine(false);
		yAxis.setDrawGridLines(false);

		LimitLine ll = new LimitLine(0f, "");
		ll.setLineColor(ContextCompat.getColor(getContext(), R.color.grey400));
		ll.setLineWidth(1f);
		int lineLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getContext().getResources().getDisplayMetrics());
		int spaceLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, getContext().getResources().getDisplayMetrics());
		ll.enableDashedLine(lineLength, spaceLength, 0);

		yAxis.setDrawLimitLinesBehindData(true);
		yAxis.addLimitLine(ll);

		chart.setHardwareAccelerationEnabled(false);
	}

	public void showDetails() {
		chart.getAxisRight().setEnabled(true);
		chart.setDragEnabled(true);
		chart.setTouchEnabled(true);
		chart.setHighlightPerTapEnabled(false);
		chart.setHighlightPerDragEnabled(false);
		chart.setPinchZoom(true);
		chart.setViewPortOffsets(0f, 0f, 140f, 0f);
		chart.invalidate();

		fillColor = R.color.colorPrimary;
		lineColor = R.color.colorPrimary;
	}

	public void setDataDouble(List<Double> data) {
//		List<Entry> entries = new ArrayList<>();
//		float index = 0;
//		for (Double value : data) {
//			entries.add(new Entry(index, value.floatValue()));
//			index++;
//		}
//		setLineData(entries);
	}

	public void setChart(List<Chart> charts) {
		if (charts.isEmpty()) {
			chart.clear();
			return;
		}
		List<Entry> lineEntries = new ArrayList<>();
//		List<BarEntry> barEntries = new ArrayList<>();
		float index = 0;
		for (Chart chart : charts) {
			lineEntries.add(new Entry(index, chart.getTotalProfit().floatValue()));
//			barEntries.add(new BarEntry(index, new float[]{
//					chart.getManagerFund().floatValue() + chart.getInvestorFund().floatValue(),
//					chart.getLoss().floatValue(),
//					chart.getProfit().floatValue()}));
			index++;
		}

		CombinedData data = new CombinedData();
		data.setData(getLineData(lineEntries));
//		data.setData(getBarData(barEntries));

		chart.setData(data);
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
		dataSet.setColor(ContextCompat.getColor(getContext(), lineColor));
		dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		dataSet.setLineWidth(3f);

		return dataSet;
	}

	private BarData getBarData(List<BarEntry> data) {
		Collections.sort(data, new EntryXComparator());
		BarData barData = new BarData();

		barData.addDataSet(createBarDataSet(data));

		return barData;
	}

	private BarDataSet createBarDataSet(List<BarEntry> data) {
		BarDataSet dataSet = new BarDataSet(data, "");

		dataSet.setLabel("");
		dataSet.setDrawValues(false);
		dataSet.setColor(ContextCompat.getColor(getContext(), lineColor));

		dataSet.setColors(ContextCompat.getColor(getContext(), R.color.colorPrimary),
				ContextCompat.getColor(getContext(), R.color.transactionRed),
				ContextCompat.getColor(getContext(), R.color.transactionGreen));

		return dataSet;
	}
}