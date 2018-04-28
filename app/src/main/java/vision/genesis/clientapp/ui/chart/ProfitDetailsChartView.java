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
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.TradeChart;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.ChartZoomEnum;
import vision.genesis.clientapp.utils.DateTimeValueFormatter;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/04/2018.
 */

public class ProfitDetailsChartView extends RelativeLayout
{
	public interface TouchListener
	{
		void onTouch();

		void onStop();
	}

	public interface TimeFrameChangeListener
	{
		void onChange(String newTimeFrame);
	}

	@BindView(R.id.group_change)
	public ViewGroup changeGroup;

	@BindView(R.id.text_change)
	public TextView changeText;

	@BindView(R.id.text_change_time_frame)
	public TextView changeTimeFrameText;

	@BindView(R.id.line_chart)
	public CustomLineChart chart;

	@BindView(R.id.highlight_box)
	public ViewGroup highlightBox;

	@BindView(R.id.highlight_circle)
	public View highlightCircle;

	@BindView(R.id.value_text)
	public TextView valueText;

	@BindView(R.id.date_text)
	public TextView dateText;

	@BindView(R.id.view_chart_time_frame_selector)
	public ChartTimeFrameSelectorView chartTimeFrameSelectorView;

	@BindView(R.id.chart_progress_bar)
	public ProgressBar progressBar;

	private Unbinder unbinder;

	private int lineColor = R.color.colorPrimary;

	private int highlightColor = R.color.colorPrimaryDark;

	private TouchListener touchListener;

	private TimeFrameChangeListener timeFrameChangeListener;

	private IAxisValueFormatter xAxisValueFormatter = new DateTimeValueFormatter();

	private ChartZoomEnum currentZoom = ChartZoomEnum.ZOOM_ALL;

	public ProfitDetailsChartView(Context context) {
		super(context);
		initView();
	}

	public ProfitDetailsChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ProfitDetailsChartView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_profit_details_chart, this);

		unbinder = ButterKnife.bind(this);

		initChart();

		chartTimeFrameSelectorView.setTimeFrameChangeListener(newTimeFrame -> {
			onTimeFrameChanged(newTimeFrame);
			if (timeFrameChangeListener != null) {
				showProgress(true);
				timeFrameChangeListener.onChange(newTimeFrame.toString());
			}
		});

		chartTimeFrameSelectorView.selectZoom(currentZoom);
	}

	@Nullable
	@Override
	protected Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable("superState", super.onSaveInstanceState());
		bundle.putSerializable("selectedZoom", currentZoom);
		return bundle;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle) // implicit null check
		{
			Bundle bundle = (Bundle) state;
			currentZoom = (ChartZoomEnum) bundle.getSerializable("selectedZoom");
			chartTimeFrameSelectorView.selectZoom(currentZoom);
			state = bundle.getParcelable("superState");
		}
		super.onRestoreInstanceState(state);
	}

	public void setTouchListener(TouchListener touchListener) {
		this.touchListener = touchListener;
	}

	public void setTimeFrameChangeListener(TimeFrameChangeListener timeFrameChangeListener) {
		this.timeFrameChangeListener = timeFrameChangeListener;
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
		chart.setNoDataTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimaryDark));
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

	public void setChart(List<TradeChart> charts) {
		showProgress(false);

		if (charts.size() <= 1) {
			chart.clear();
			return;
		}

		List<Entry> lineEntries = new ArrayList<>();
		for (TradeChart chart : charts) {
			lineEntries.add(new Entry(chart.getDate().getMillis(), chart.getProfit().floatValue()));
		}

		chart.setData(getLineData(lineEntries));
		chart.invalidate();

		updateChangeText(charts);
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

		dataSet.setFillDrawable(ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.chart_background_gradient));
		dataSet.setDrawFilled(true);

		return dataSet;
	}

	private void updateChangeText(List<TradeChart> charts) {
		double first = charts.get(0).getProfit();
		double last = charts.get(charts.size() - 1).getProfit();

		double changeValue = last - first;
		String directionSymbol = "";
		String percentSign = "";
		int textColorResId;
		if (changeValue > 0) {
			directionSymbol = "\u2191";
			percentSign = "+";
			textColorResId = R.color.transactionGreen;
		}
		else if (changeValue < 0) {
			directionSymbol = "\u2193";
			percentSign = "-";
			textColorResId = R.color.transactionRed;
		}
		else {
			textColorResId = R.color.colorPrimaryDark;
		}
		String changeValueString = String.format(Locale.getDefault(),
				"%s%s%%", directionSymbol, StringFormatUtil.formatAmount(Math.abs(changeValue), 0, 2));

		String changePercentString = "";
		if (first != 0) {
			changePercentString = String.format(Locale.getDefault(), " (%s%s%%)", percentSign,
					StringFormatUtil.formatAmount(Math.abs(100 / first * (first - last)), 0, 2));
		}

		changeText.setText(changeValueString.concat(changePercentString));
		changeText.setTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, textColorResId));
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
		highlightBox.setVisibility(View.VISIBLE);
		highlightCircle.setVisibility(View.VISIBLE);
		chart.highlightValue(highlight, false);
		valueText.setText(String.format(Locale.getDefault(), "%s %%", StringFormatUtil.formatAmount(highlight.getY(), 0, 2)));
		dateText.setText(xAxisValueFormatter.getFormattedValue(highlight.getX(), null));
		moveHighlightBox(highlight);
		moveHighlightCircle(highlight);
	}

	private void moveHighlightBox(Highlight highlight) {
		float x = highlight.getXPx() - highlightBox.getWidth() / 2;
		if (x < 0)
			x = 0;
		if (x > this.getWidth() - highlightBox.getWidth())
			x = this.getWidth() - highlightBox.getWidth();

		highlightBox.setX(x);
	}

	private void moveHighlightCircle(Highlight highlight) {
		float x = highlight.getXPx() - highlightCircle.getWidth() / 2;
		float y = highlight.getYPx() - highlightCircle.getHeight() / 2 + chart.getY();

		highlightCircle.setX(x);
		highlightCircle.setY(y);
	}

	private void hideHighlight() {
		highlightBox.setVisibility(View.INVISIBLE);
		highlightCircle.setVisibility(View.INVISIBLE);
		chart.highlightValue(null, false);
	}

	private void onTimeFrameChanged(ChartZoomEnum newTimeFrame) {
//		if (newTimeFrame.equals(ChartZoomEnum.ZOOM_1D))
//			xAxisValueFormatter = new DateTimeValueFormatter();
//		else
//			xAxisValueFormatter = new DateValueFormatter();

		currentZoom = newTimeFrame;
		updateChangeTimeFrameText(newTimeFrame);
	}

	private void updateChangeTimeFrameText(ChartZoomEnum newTimeFrame) {
		int stringResource;
		switch (newTimeFrame) {
			case ZOOM_1D:
				stringResource = R.string.one_day;
				break;
			case ZOOM_1W:
				stringResource = R.string.one_week;
				break;
			case ZOOM_1M:
				stringResource = R.string.one_month;
				break;
			case ZOOM_3M:
				stringResource = R.string.three_months;
				break;
			case ZOOM_6M:
				stringResource = R.string.six_months;
				break;
			case ZOOM_1Y:
				stringResource = R.string.one_year;
				break;
			case ZOOM_ALL:
				stringResource = R.string.all_time;
				break;
			default:
				stringResource = R.string.all_time;
				break;
		}
		changeTimeFrameText.setText(getResources().getString(stringResource).toUpperCase());
	}

	private void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		chart.setVisibility(!show ? View.VISIBLE : View.GONE);
		changeGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}
}