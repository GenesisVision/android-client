package vision.genesis.clientapp.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.swagger.client.model.Chart;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 2/2/18.
 */

public class ProfitChartView extends com.github.mikephil.charting.charts.LineChart
{
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
		this.getDescription().setEnabled(false);
		this.setDrawGridBackground(false);
		this.setDragEnabled(false);
		this.setTouchEnabled(false);
		this.getXAxis().setEnabled(false);
		this.getLegend().setEnabled(false);
		this.getAxisLeft().setEnabled(true);
		this.getAxisRight().setEnabled(false);
		this.setDrawBorders(false);
		this.setAutoScaleMinMaxEnabled(true);
		this.setNoDataText(GenesisVisionApplication.INSTANCE.getResources().getString(R.string.not_enough_data));
		this.setNoDataTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimaryDark));

		YAxis yAxis = this.getAxisLeft();
		yAxis.setDrawLabels(false);
		yAxis.setDrawAxisLine(false);
		yAxis.setDrawGridLines(false);

		LimitLine ll = new LimitLine(0f, "");
		ll.setLineColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.grey400));
		ll.setLineWidth(1f);
		int lineLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics());
		int spaceLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics());
		ll.enableDashedLine(lineLength, spaceLength, 0);

		yAxis.setDrawLimitLinesBehindData(true);
		yAxis.addLimitLine(ll);

		this.setHardwareAccelerationEnabled(false);
	}

	public void showDetails() {
		this.getAxisRight().setEnabled(true);
		this.setDragEnabled(true);
		this.setTouchEnabled(true);
		this.setHighlightPerTapEnabled(false);
		this.setHighlightPerDragEnabled(false);
		this.setPinchZoom(true);
		this.setViewPortOffsets(0f, 0f, 140f, 0f);
		this.invalidate();

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
		if (charts.size() <= 1) {
			this.clear();
			return;
		}
		List<Entry> lineEntries = new ArrayList<>();
		float index = 0;
		for (Chart chart : charts) {
			lineEntries.add(new Entry(index, chart.getTotalProfit().floatValue()));
			index++;
		}

		this.setData(getLineData(lineEntries));
		this.invalidate();
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
		dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		dataSet.setLineWidth(3f);

		return dataSet;
	}

	public void onDestroy() {
		this.invalidate();
	}
}