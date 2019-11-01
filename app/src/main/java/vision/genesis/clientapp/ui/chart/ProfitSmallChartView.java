package vision.genesis.clientapp.ui.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.swagger.client.model.SimpleChartPoint;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/2/18.
 */

public class ProfitSmallChartView extends com.github.mikephil.charting.charts.LineChart
{
	static {
		Utils.init(GenesisVisionApplication.INSTANCE);
	}

//	public static LineData getPreparedEquityChart(List<ChartByDate> equityChart) {
//		List<Entry> lineEntries = new ArrayList<>();
//
//		for (ChartByDate chart : equityChart) {
//			lineEntries.add(new Entry(chart.getDate().getMillis(), chart.getValue().floatValue()));
//		}
//
//		return getLineData(lineEntries);
//	}

	public ProfitSmallChartView(Context context) {
		super(context);
		initView();
	}

	public ProfitSmallChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ProfitSmallChartView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
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
		boolean isProfitable = data.get(0).getY() <= data.get(data.size() - 1).getY();
		dataSet.setColor(ThemeUtil.getColorByAttrId(getContext(), isProfitable ? R.attr.colorGreen : R.attr.colorRed));
		dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		dataSet.setLineWidth(1.5f);

		return dataSet;
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
		this.setNoDataText(GenesisVisionApplication.INSTANCE.getResources().getString(R.string.no_chart));
		this.setNoDataTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		this.setViewPortOffsets(0f, 0f, 0f, 0f);

		YAxis yAxis = this.getAxisLeft();
		yAxis.setDrawLabels(false);
		yAxis.setDrawAxisLine(false);
		yAxis.setDrawGridLines(false);

		LimitLine ll = new LimitLine(0f, "");
		ll.setLineColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		ll.setLineWidth(1f);
		int lineLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics());
		int spaceLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics());
		ll.enableDashedLine(lineLength, spaceLength, 0);

		yAxis.setDrawLimitLinesBehindData(true);
		yAxis.addLimitLine(ll);

		this.setHardwareAccelerationEnabled(false);
	}

	public void setEquityChart(LineData data) {
		if (data.getEntryCount() <= 1) {
			this.clear();
			return;
		}

		this.setData(data);
		this.invalidate();
	}

//	public void setEquityChart(List<ChartByDate> charts) {
//		if (charts.size() <= 1) {
//			this.clear();
//			return;
//		}
//		List<Entry> lineEntries = new ArrayList<>();
//
//		for (ChartByDate chart : charts) {
//			lineEntries.add(new Entry(chart.getDate().getMillis(), chart.getValue().floatValue()));
//		}
//
//		this.setProgramRequest(getLineData(lineEntries));
//		this.invalidate();
//	}

//	public void setChart(List<Chart> charts) {
//		if (charts.size() <= 1) {
//			this.clear();
//			return;
//		}
//		List<Entry> lineEntries = new ArrayList<>();
//		float index = 0;
//		for (Chart chart : charts) {
//			lineEntries.add(new Entry(index, chart.getTotalProfit().floatValue()));
//			index++;
//		}
//
//		this.setProgramRequest(getLineData(lineEntries));
//		this.invalidate();
//	}

	public void setChart(List<SimpleChartPoint> chart) {
		if (chart == null || chart.size() <= 1) {
			this.clear();
			return;
		}
		List<Entry> lineEntries = new ArrayList<>();
		float index = 0;
		for (SimpleChartPoint point : chart) {
			lineEntries.add(new Entry(index, point.getValue().floatValue()));
			index++;
		}

		this.setData(getLineData(lineEntries));
		this.invalidate();
	}

	public void onDestroy() {
		this.invalidate();
	}
}