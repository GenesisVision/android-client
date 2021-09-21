package vision.genesis.clientapp.feature.main.terminal.chart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.EntryXComparator;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.BinanceKlineInterval;
import io.swagger.client.model.BinanceRawKline;
import io.swagger.client.model.BinanceRawKlineItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.terminal.binance_socket.KlineModel;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.chart.ProfitChartView;
import vision.genesis.clientapp.utils.DateValueFormatter;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TimeValueFormatter;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/02/2021.
 */

public class TerminalChartView extends RelativeLayout
{
	public interface TouchListener
	{
		void onTouch(float index);

		void onStop();
	}

	@Inject
	public TerminalManager terminalManager;

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.chart)
	public CombinedChart chart;

	@BindView(R.id.highlight_circle)
	public View highlightCircle;

	@BindView(R.id.highlight_line)
	public View highlightLine;

	@BindView(R.id.highlight_area)
	public View highlightArea;

	@BindView(R.id.min_value)
	public TextView minValue;

	@BindView(R.id.max_value)
	public TextView maxValue;

	public Subscription getKlineDataSubscription;

	public Subscription klineUpdateSubscription;

	private int lineColor = R.attr.colorGreen;

	private int highlightColor = R.attr.colorAccent;

	private ProfitChartView.TouchListener touchListener;

	private IAxisValueFormatter xAxisDateValueFormatter = new DateValueFormatter();

	private IAxisValueFormatter xAxisTimeValueFormatter = new TimeValueFormatter();

	private Unbinder unbinder;

	private String symbol = "";

	private BinanceKlineInterval interval = BinanceKlineInterval.ONEMINUTE;

	private DateTime startTime;

	private DateTime endTime;

	private Integer limit;

	private ArrayList<KlineModel> klines = new ArrayList<>();

	private int colorTextPrimary;

	private int colorGreen;

	private int colorRed;

	public TerminalChartView(Context context) {
		super(context);
		initView();
	}

	public TerminalChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TerminalChartView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_terminal_chart, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		colorTextPrimary = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary);
		colorGreen = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen);
		colorRed = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed);

		initChart();

		onSymbolChanged();
	}

	public void onResume() {
		if (klineUpdateSubscription == null || klineUpdateSubscription.isUnsubscribed()) {
			subscribeToKlineUpdates();
		}
	}

	public void onDestroy() {
		if (getKlineDataSubscription != null) {
			getKlineDataSubscription.unsubscribe();
		}
		if (klineUpdateSubscription != null) {
			klineUpdateSubscription.unsubscribe();
		}
		chart.invalidate();

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
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

	public void setTouchListener(ProfitChartView.TouchListener touchListener) {
		this.touchListener = touchListener;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;

		onSymbolChanged();
	}

	private void onSymbolChanged() {
		if (symbol != null && terminalManager != null) {
			getKlineData();
		}
	}

	private void getKlineData() {
		if (symbol != null && terminalManager != null) {
			if (getKlineDataSubscription != null) {
				getKlineDataSubscription.unsubscribe();
			}
			getKlineDataSubscription = terminalManager.getKlineData(symbol, interval, startTime, endTime, limit)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetKlineDataSuccess,
							this::handleGetKlineDataError);
		}
	}

	private void handleGetKlineDataSuccess(BinanceRawKlineItemsViewModel response) {
		getKlineDataSubscription.unsubscribe();
		showProgress(false);

		this.klines = new ArrayList<>();
		for (BinanceRawKline item : response.getItems()) {
			this.klines.add(KlineModel.fromBinanceRawKline(item));
		}
		updateChart();
	}

	private void handleGetKlineDataError(Throwable throwable) {
		getKlineDataSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, this::showToast);
	}

	private void subscribeToKlineUpdates() {
		if (terminalManager != null && symbol != null) {
			if (klineUpdateSubscription != null) {
				klineUpdateSubscription.unsubscribe();
			}
			klineUpdateSubscription = terminalManager.getKlineSubject(symbol, interval)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleKlinesUpdate, this::onKlinesUpdateError);
		}
	}

	private void handleKlinesUpdate(KlineModel kline) {
		if (klines != null && !klines.isEmpty()) {
			if (klines.get(klines.size() - 1).getOpenTime().equals(kline.getOpenTime())) {
				klines.set(klines.size() - 1, kline);
			}
			else {
				klines.add(kline);
			}
//			updateChart();
		}
	}

	private void onKlinesUpdateError(Throwable throwable) {

	}

	private void showProgress(boolean show) {
		if (!show) {
			this.root.setVisibility(View.VISIBLE);
		}
	}

	private void showToast(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
	}

	@SuppressLint("ClickableViewAccessibility")
	private void initChart() {
//		chart.setHardwareAccelerationEnabled(false);
		chart.getDescription().setEnabled(false);
		chart.setDrawGridBackground(false);
		chart.setDragEnabled(false);
		chart.setTouchEnabled(true);
		chart.setDoubleTapToZoomEnabled(true);
		chart.setPinchZoom(true);
		chart.getXAxis().setEnabled(true);
		chart.getLegend().setEnabled(false);
		chart.getAxisRight().setEnabled(true);
		chart.getAxisLeft().setEnabled(false);
		chart.setDrawBorders(false);
		chart.setAutoScaleMinMaxEnabled(false);
		chart.setNoDataText(GenesisVisionApplication.INSTANCE.getResources().getString(R.string.not_enough_data));
		chart.setNoDataTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		chart.setNoDataTextTypeface(TypefaceUtil.regular());
		chart.setViewPortOffsets(0f, 0f, 0f,
				TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
						GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics()));

		chart.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.CANDLE});

		YAxis yAxis = chart.getAxisRight();
		yAxis.setDrawLabels(false);
		yAxis.setDrawAxisLine(false);
		yAxis.setDrawGridLines(true);

		XAxis xAxis = chart.getXAxis();
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
		xAxis.setAvoidFirstLastClipping(true);
		xAxis.setGranularity(1f);
		xAxis.setDrawGridLines(true);
		xAxis.setTypeface(TypefaceUtil.regular());
		xAxis.setDrawAxisLine(false);
		xAxis.setYOffset(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -2,
				GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics()));
		xAxis.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));

//		chart.setOnTouchListener((v, me) -> {
//			v.getParent().requestDisallowInterceptTouchEvent(true);
//			if (me.getAction() == MotionEvent.ACTION_DOWN || me.getAction() == MotionEvent.ACTION_MOVE) {
//				Highlight highlight = chart.getHighlightByTouchPoint(me.getX(), me.getY());
//				if (highlight != null) {
//					showHighlight(highlight);
//					if (touchListener != null) {
//						touchListener.onTouch(highlight.getY());
//					}
//				}
//			}
//			else if (me.getAction() == MotionEvent.ACTION_UP || me.getAction() == MotionEvent.ACTION_CANCEL) {
//				hideHighlight();
//				if (touchListener != null) {
//					touchListener.onStop();
//				}
//				v.getParent().requestDisallowInterceptTouchEvent(false);
//			}
//			return true;
//		});
	}

	private void updateChart() {
		setChartData(klines);
	}

	private void setChartData(ArrayList<KlineModel> chart) {
		this.root.setVisibility(View.VISIBLE);

		if (chart.size() <= 1) {
			this.chart.clear();
			return;
		}

		updateXAxis(interval);

		float min = chart.get(0).getLow().floatValue();
		float max = chart.get(0).getHigh().floatValue();

		List<CandleEntry> candleEntries = new ArrayList<>();

//		for (SimpleChartPoint point : chart) {
//			candleEntries.add(new Entry(point.getDate() / 1000 / 60, point.getValue().floatValue()));
//			if (min > point.getValue().floatValue()) {
//				min = point.getValue().floatValue();
//			}
//			if (max < point.getValue().floatValue()) {
//				max = point.getValue().floatValue();
//			}
//		}

		for (KlineModel kline : chart) {
			candleEntries.add(new CandleEntry(kline.getOpenTime().getMillis() / 1000 / 60,
					kline.getHigh().floatValue(), kline.getLow().floatValue(),
					kline.getOpen().floatValue(), kline.getClose().floatValue()));

			if (min > kline.getLow().floatValue()) {
				min = kline.getLow().floatValue();
			}
			if (max < kline.getHigh().floatValue()) {
				max = kline.getHigh().floatValue();
			}
		}

		minValue.setText(StringFormatUtil.formatAmount(min, 2, 4));
		maxValue.setText(StringFormatUtil.formatAmount(max, 2, 4));

		setLimitLines(min, max);

//		chart.getAxisLeft().setAxisMaximum(max);
//		chart.getAxisLeft().setAxisMinimum(min);

		CombinedData combinedData = new CombinedData();
		combinedData.setData(getCandleData(candleEntries));
		this.chart.setData(combinedData);
		this.chart.invalidate();

		this.chart.setMaxVisibleValueCount(40);
//		this.chart.getViewPortHandler().
	}

	private void updateXAxis(BinanceKlineInterval interval) {
		XAxis xAxis = chart.getXAxis();
//		if (!dateRange.getSelectedRange().equals(DateRange.DateRangeEnum.ALL_TIME)) {
//			xAxis.setAxisMinimum(dateRange.getFrom().getMillis() / 1000 / 60);
//		}
//		else {
		xAxis.resetAxisMinimum();
//		}

		switch (interval) {
			case ONEMINUTE:
			case THREEMINUTES:
			case FIVEMINUTES:
			case FIFTEENMINUTES:
			case THIRTYMINUTES:
			case ONEHOUR:
			case TWOHOUR:
			case FOURHOUR:
				xAxis.setValueFormatter(xAxisTimeValueFormatter);
				xAxis.setLabelCount(6, true);
				break;
			case SIXHOUR:
			case EIGHTHOUR:
			case TWELVEHOUR:
			case ONEDAY:
			case THREEDAY:
			case ONEWEEK:
			case ONEMONTH:
				xAxis.setValueFormatter(xAxisDateValueFormatter);
				xAxis.setLabelCount(6, true);
				break;
		}
	}

	private CandleData getCandleData(List<CandleEntry> data) {
		Collections.sort(data, new EntryXComparator());
		CandleData candleData = new CandleData();

		candleData.addDataSet(createCandleDataSet(data));

		return candleData;
	}

	private CandleDataSet createCandleDataSet(List<CandleEntry> data) {
		CandleDataSet dataSet = new CandleDataSet(data, "Data Set");

		dataSet.setDrawIcons(false);
		dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
		dataSet.setShadowColorSameAsCandle(true);
		dataSet.setShadowWidth(4f);
		dataSet.setDecreasingColor(colorRed);
		dataSet.setDecreasingPaintStyle(Paint.Style.FILL);
		dataSet.setIncreasingColor(colorGreen);
		dataSet.setIncreasingPaintStyle(Paint.Style.FILL);
		dataSet.setNeutralColor(colorTextPrimary);

		dataSet.setDrawHorizontalHighlightIndicator(true);
		dataSet.setHighLightColor(ThemeUtil.getColorByAttrId(getContext(), highlightColor));
		dataSet.setHighlightLineWidth(0.7f);

		return dataSet;
	}


	//	private BarData getBarData(List<BarEntry> data) {
//		Collections.sort(data, new EntryXComparator());
//		BarData barData = new BarData();
//
//		barData.addDataSet(createBarDataSet(data));
//		barData.setBarWidth(0.07f);
//		barData.setHighlightEnabled(false);
//
//		return barData;
//	}
//
//	private BarDataSet createBarDataSet(List<BarEntry> data) {
//		BarDataSet dataSet = new BarDataSet(data, "");
//
//		dataSet.setLabel("");
//		dataSet.setDrawValues(false);
//		dataSet.setColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));
////		dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
////		dataSet.setHighLightColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, highlightColor));
//
////		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
////			dataSet.setFillDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.chart_background_gradient));
////			dataSet.setDrawFilled(true);
////		}
//
//		return dataSet;
//	}

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

	private void showHighlight(Highlight highlight) {
		if (highlight == null) {
			return;
		}
		highlightCircle.setVisibility(View.VISIBLE);
		highlightLine.setVisibility(View.VISIBLE);
		highlightArea.setVisibility(View.VISIBLE);
//		chart.highlightValue(highlight, false);
		moveHighlight(highlight);
	}

	private void moveHighlight(Highlight highlight) {
		float x = highlight.getXPx();
		float y = highlight.getYPx() + chart.getY();

		highlightCircle.setX(x - highlightCircle.getWidth() / 2);
		highlightCircle.setY(y - highlightCircle.getHeight() / 2);
		highlightLine.setX(x);
		ViewGroup.LayoutParams lp = highlightArea.getLayoutParams();
		lp.width = (int) x;
		highlightArea.setLayoutParams(lp);
	}

	public void hideHighlight() {
		highlightCircle.setVisibility(View.INVISIBLE);
		highlightLine.setVisibility(View.INVISIBLE);
		highlightArea.setVisibility(View.INVISIBLE);
		chart.highlightValue(null, false);
	}
}
