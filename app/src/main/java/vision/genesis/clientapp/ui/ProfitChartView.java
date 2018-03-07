package vision.genesis.clientapp.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
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
	@BindView(R.id.line_chart)
	public LineChart chart;

	private int fillColor = R.color.grey300;

	private int lineColor = R.color.grey400;

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
		chart.getAxisLeft().setEnabled(false);
		chart.getAxisRight().setEnabled(false);
		chart.setDrawBorders(false);
		chart.setAutoScaleMinMaxEnabled(true);
		chart.setNoDataText(getContext().getResources().getString(R.string.not_enough_data));
		chart.setNoDataTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

		YAxis yAxis = chart.getAxisLeft();
		yAxis.setDrawLabels(false);
		yAxis.setDrawAxisLine(false);
		yAxis.setDrawGridLines(false);
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
		List<Entry> entries = new ArrayList<>();
		float index = 0;
		for (Double value : data) {
			entries.add(new Entry(index, value.floatValue()));
			index++;
		}
		setData(entries);
	}

	public void setChart(List<Chart> charts) {
		List<Entry> entries = new ArrayList<>();
		float index = 0;
		for (Chart chart : charts) {
			entries.add(new Entry(index, chart.getTotalProfit().floatValue()));
			index++;
		}
		setData(entries);
	}

	public void setData(List<Entry> data) {
		if (data.size() == 0) {
			chart.clear();
			return;
		}
		Collections.sort(data, new EntryXComparator());
		LineData lineData = new LineData();

		lineData.addDataSet(createDataSet(data));

		chart.setData(lineData);
		chart.invalidate();
//		chart.animateX(1200, input -> (1 - (1 - input) * (1 - input)));
	}

	private LineDataSet createDataSet(List<Entry> data) {
		LineDataSet dataSet = new LineDataSet(data, "");

		dataSet.setLabel("");
		dataSet.setDrawValues(false);
		dataSet.setDrawCircles(false);
		dataSet.setColor(ContextCompat.getColor(getContext(), lineColor));
		dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		dataSet.setLineWidth(1f);

		dataSet.setFillColor(ContextCompat.getColor(getContext(), fillColor));
		dataSet.setDrawFilled(true);

		return dataSet;
	}
}