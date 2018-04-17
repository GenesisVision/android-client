package vision.genesis.clientapp.feature.main.program.chart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.joda.time.DateTime;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.ChartZoomEnum;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.DateValueFormatter;
import vision.genesis.clientapp.utils.TimeValueFormatter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/04/2018.
 */

public class ChartActivity extends MvpAppCompatActivity implements ChartView
{
	private static String EXTRA_PROGRAM_ID = "extra_program_id";

	private static String EXTRA_PROGRAM_NAME = "extra_program_name";

	private static String EXTRA_MANAGER_NAME = "extra_manager_name";

	public static void startWith(Activity activity, UUID programId, String programName, String managerName) {
		Intent intent = new Intent(activity.getApplicationContext(), ChartActivity.class);
		intent.putExtra(EXTRA_PROGRAM_ID, programId);
		intent.putExtra(EXTRA_PROGRAM_NAME, programName);
		intent.putExtra(EXTRA_MANAGER_NAME, managerName);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.fragment_fade_in, R.anim.hold);
	}

	@BindView(R.id.line_chart)
	public LineChart chart;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.group_selected_value)
	public ViewGroup selectedValueGroup;

	@BindView(R.id.selected_date)
	public TextView selectedDateText;

	@BindView(R.id.selected_value)
	public TextView selectedValueText;

	@BindView(R.id.highlight_value_x)
	public TextView highlightValueX;

	@BindView(R.id.highlight_value_y)
	public TextView highlightValueY;

	@BindView(R.id.zoom_five_min)
	public TextView zoomFiveMin;

	@BindView(R.id.zoom_one_hour)
	public TextView zoomOneHour;

	@BindView(R.id.zoom_one_day)
	public TextView zoomOneDay;

	@BindView(R.id.zoom_one_week)
	public TextView zoomOneWeek;

	@BindView(R.id.zoom_five_min_underline)
	public View zoomFiveMinUnderline;

	@BindView(R.id.zoom_one_hour_underline)
	public View zoomOneHourUnderline;

	@BindView(R.id.zoom_one_day_underline)
	public View zoomOneDayUnderline;

	@BindView(R.id.zoom_one_week_underline)
	public View zoomOneWeekUnderline;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	ChartPresenter chartPresenter;

	private int gridColor = R.color.grey100;

	private IAxisValueFormatter xAxisValueFormatter = new TimeValueFormatter();

	private Highlight currentHighlight;

	@OnClick(R.id.group_zoom_five_min)
	public void onZoom5minClicked() {
		chartPresenter.onZoomClicked(ChartZoomEnum.ZOOM_5MIN);
	}

	@OnClick(R.id.group_zoom_one_hour)
	public void onZoom1hClicked() {
		chartPresenter.onZoomClicked(ChartZoomEnum.ZOOM_1H);
	}

	@OnClick(R.id.group_zoom_one_day)
	public void onZoom1dClicked() {
		chartPresenter.onZoomClicked(ChartZoomEnum.ZOOM_1D);
	}

	@OnClick(R.id.group_zoom_one_week)
	public void onZoom1wClicked() {
		chartPresenter.onZoomClicked(ChartZoomEnum.ZOOM_1W);
	}

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_chart);

		ButterKnife.bind(this);

		setFonts();

		Bundle extras = getIntent().getExtras();

		if (extras != null && !extras.isEmpty()) {
			programName.setText(extras.getString(EXTRA_PROGRAM_NAME));
			managerName.setText(extras.getString(EXTRA_MANAGER_NAME));

			initChart();

			UUID programId = (UUID) extras.getSerializable(EXTRA_PROGRAM_ID);
			chartPresenter.setProgramId(programId);
		}
		else {
			Timber.e("Passed empty program to ChartActivity");
			onBackPressed();
		}
	}

	private void setFonts() {
	}

	private void initChart() {
		chart.getDescription().setEnabled(false);
		chart.setGridBackgroundColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, gridColor));
		chart.setBorderColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, gridColor));
		chart.setDrawBorders(false);
		chart.getLegend().setEnabled(false);
		chart.getAxisRight().setEnabled(true);
		chart.setDragEnabled(true);
		chart.setTouchEnabled(true);
		chart.setPinchZoom(true);
		chart.setHighlightPerTapEnabled(true);
		chart.setHighlightPerDragEnabled(true);
		chart.setNoDataText("");

		XAxis xAxis = chart.getXAxis();
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

		YAxis yAxis = chart.getAxisLeft();

		LimitLine ll = new LimitLine(0f, "");
		ll.setLineColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.grey400));
		ll.setLineWidth(2f);

		yAxis.setDrawLimitLinesBehindData(true);
		yAxis.addLimitLine(ll);

		chart.setHardwareAccelerationEnabled(false);

		chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
		{
			@Override
			public void onValueSelected(Entry entry, Highlight highlight) {
				showSelectedValue(entry, highlight);
			}

			@Override
			public void onNothingSelected() {
				showSelectedValue(null, null);
			}
		});

		chart.setOnDragListener((v, event) -> {
			if (currentHighlight != null)
				updateHighlightValuesPositions();
			return false;
		});
	}

	private void showSelectedValue(Entry selectedValue, Highlight highlight) {
		if (selectedValue == null) {
			currentHighlight = null;
			highlightValueX.setVisibility(View.INVISIBLE);
			highlightValueY.setVisibility(View.INVISIBLE);

			selectedValueGroup.setVisibility(View.INVISIBLE);
		}
		else {
			currentHighlight = highlight;

			highlightValueX.setText(xAxisValueFormatter.getFormattedValue(selectedValue.getX(), null));
			highlightValueY.setText(String.valueOf(selectedValue.getY()));

			selectedDateText.setText(DateTimeUtil.formatDateTime(new DateTime().withMillis((long) selectedValue.getX())));
			selectedValueText.setText(String.valueOf(selectedValue.getY()));

			updateHighlightValuesPositions();

			highlightValueX.setVisibility(View.VISIBLE);
			highlightValueY.setVisibility(View.VISIBLE);

			selectedValueGroup.setVisibility(View.VISIBLE);
		}
	}

	private void updateHighlightValuesPositions() {
		if (currentHighlight != null) {
			Timber.d("HIGHLIGHT_TEST %f, %f", currentHighlight.getXPx() - highlightValueX.getWidth() / 2,
					currentHighlight.getYPx() - highlightValueY.getHeight() / 2);
			highlightValueX.setX(currentHighlight.getXPx() - highlightValueX.getWidth() / 2);
			highlightValueY.setY(currentHighlight.getYPx() - highlightValueY.getHeight() / 2);
		}
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}


	@Override
	public void setChartData(LineData data) {
		chart.setData(data);
		chart.invalidate();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.fragment_fade_out);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void selectZoom(ChartZoomEnum currentZoom) {
		setCurrentZoomButtonSelection(currentZoom);
		setChartZoom(currentZoom);
	}

	private void setCurrentZoomButtonSelection(ChartZoomEnum currentZoom) {
		deselectAllZoomButtons();
		switch (currentZoom) {
			case ZOOM_5MIN:
				selectZoomButton(zoomFiveMin, zoomFiveMinUnderline);
				break;
			case ZOOM_1H:
				selectZoomButton(zoomOneHour, zoomOneHourUnderline);
				break;
			case ZOOM_1D:
				selectZoomButton(zoomOneDay, zoomOneDayUnderline);
				break;
			case ZOOM_1W:
				selectZoomButton(zoomOneWeek, zoomOneWeekUnderline);
				break;
		}
	}

	private void deselectAllZoomButtons() {
		deselectZoomButton(zoomFiveMin, zoomFiveMinUnderline);
		deselectZoomButton(zoomOneHour, zoomOneHourUnderline);
		deselectZoomButton(zoomOneDay, zoomOneDayUnderline);
		deselectZoomButton(zoomOneWeek, zoomOneWeekUnderline);
	}

	private void selectZoomButton(TextView zoomButton, View underline) {
		zoomButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
		underline.setVisibility(View.VISIBLE);
	}

	private void deselectZoomButton(TextView zoomButton, View underline) {
		zoomButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
		underline.setVisibility(View.GONE);
	}

	private void setChartZoom(ChartZoomEnum currentZoom) {
		XAxis xAxis = chart.getXAxis();

		switch (currentZoom) {
			case ZOOM_5MIN:
				xAxisValueFormatter = new TimeValueFormatter();
				xAxis.setGranularity(1000 * 60 * 5);
				break;
			case ZOOM_1H:
				xAxisValueFormatter = new TimeValueFormatter();
				xAxis.setGranularity(1000 * 60 * 60);
				break;
			case ZOOM_1D:
				xAxisValueFormatter = new DateValueFormatter();
				xAxis.setGranularity(1000 * 60 * 60 * 24);
				break;
			case ZOOM_1W:
				xAxisValueFormatter = new DateValueFormatter();
				xAxis.setGranularity(1000 * 60 * 60 * 24 * 7);
				break;
		}
		xAxis.setValueFormatter(xAxisValueFormatter);
	}
}
