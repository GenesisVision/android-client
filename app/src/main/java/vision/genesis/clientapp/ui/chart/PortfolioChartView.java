package vision.genesis.clientapp.ui.chart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
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

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.LimitLine;
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
import io.swagger.client.model.ChartProgramDetails;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeValueFormatter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/08/2018.
 */

public class PortfolioChartView extends RelativeLayout
{
	public interface TouchListener
	{
		void onTouch();

		void onStop();
	}

	@BindView(R.id.chart)
	public CombinedChart chart;

	@BindView(R.id.highlight_circle)
	public View highlightCircle;

	@BindView(R.id.chart_progress_bar)
	public ProgressBar progressBar;

	private Unbinder unbinder;

	private int lineColor = R.color.colorAccent;

	private int highlightColor = R.color.colorMedium;

	private TouchListener touchListener;

	private IAxisValueFormatter xAxisValueFormatter = new DateTimeValueFormatter();

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
		chart.setHardwareAccelerationEnabled(false);
		chart.getDescription().setEnabled(false);
		chart.setDrawGridBackground(false);
		chart.setDragEnabled(false);
		chart.setTouchEnabled(true);
		chart.setDoubleTapToZoomEnabled(false);
		chart.getXAxis().setEnabled(false);
		chart.getLegend().setEnabled(false);
		chart.getAxisLeft().setEnabled(true);
		chart.getAxisRight().setEnabled(false);
		chart.setDrawBorders(false);
		chart.setAutoScaleMinMaxEnabled(true);
		chart.setNoDataText(GenesisVisionApplication.INSTANCE.getResources().getString(R.string.not_enough_data));
		chart.setNoDataTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		chart.setViewPortOffsets(0f, 0f, 0f, 0f);

		YAxis yAxis = chart.getAxisLeft();
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

		chart.setOnTouchListener((v, me) -> {
			v.getParent().requestDisallowInterceptTouchEvent(true);
			if (me.getAction() == MotionEvent.ACTION_DOWN || me.getAction() == MotionEvent.ACTION_MOVE) {
				showHighlight(chart.getHighlightByTouchPoint(me.getX(), me.getY()));
				if (touchListener != null)
					touchListener.onTouch();
			}
			else if (me.getAction() == MotionEvent.ACTION_UP || me.getAction() == MotionEvent.ACTION_CANCEL) {
				hideHighlight();
				if (touchListener != null)
					touchListener.onStop();
				v.getParent().requestDisallowInterceptTouchEvent(false);
			}
			return true;
		});
	}

	public void setChart(List<ChartProgramDetails> charts) {
		showProgress(false);

		if (charts.size() <= 1) {
			chart.clear();
			return;
		}

		List<Entry> lineEntries = new ArrayList<>();
		for (ChartProgramDetails chart : charts) {
			lineEntries.add(new Entry(chart.getDate().getMillis(), chart.getValue().floatValue()));
		}

//		chart.setData(getLineData(lineEntries));
		chart.invalidate();
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
		dataSet.setLineWidth(1.5f);
		dataSet.setDrawHorizontalHighlightIndicator(false);
		dataSet.setHighLightColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, highlightColor));
		dataSet.setHighlightLineWidth(1.5f);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
			dataSet.setFillDrawable(ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.chart_background_gradient));
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