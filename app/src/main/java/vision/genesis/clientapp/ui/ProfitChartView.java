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

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 2/2/18.
 */

public class ProfitChartView extends RelativeLayout
{
	@BindView(R.id.line_chart)
	public LineChart chart;

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

		YAxis yAxis = chart.getAxisLeft();
		yAxis.setDrawLabels(false);
		yAxis.setDrawAxisLine(false);
		yAxis.setDrawGridLines(false);
	}

	public void setData(List<Entry> data) {
		Collections.sort(data, new EntryXComparator());
		LineData lineData = new LineData();

		lineData.addDataSet(createDataSet(data));

		chart.setData(lineData);
		chart.invalidate();
		chart.animateX(1200, input -> (1 - (1 - input) * (1 - input)));
	}

	private LineDataSet createDataSet(List<Entry> data) {
		LineDataSet dataSet = new LineDataSet(data, "");

		dataSet.setLabel("");
		dataSet.setDrawValues(false);
		dataSet.setDrawCircles(false);
		dataSet.setColor(ContextCompat.getColor(getContext(), R.color.grey400));
		dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		dataSet.setLineWidth(1f);

		dataSet.setFillColor(ContextCompat.getColor(getContext(), R.color.grey300));
		dataSet.setDrawFilled(true);

		return dataSet;
	}
}