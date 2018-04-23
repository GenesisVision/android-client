package vision.genesis.clientapp.ui.chart;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.ChartZoomEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/04/2018.
 */

public class ChartTimeFrameSelectorView extends RelativeLayout
{
	public interface TimeFrameChangeListener
	{
		void onChange(ChartZoomEnum newTimeFrame);
	}

	@BindView(R.id.zoom_one_day)
	public TextView zoomOneDay;

	@BindView(R.id.zoom_one_week)
	public TextView zoomOneWeek;

	@BindView(R.id.zoom_one_month)
	public TextView zoomOneMonth;

	@BindView(R.id.zoom_three_months)
	public TextView zoomThreeMonths;

	@BindView(R.id.zoom_six_months)
	public TextView zoomSixMonths;

	@BindView(R.id.zoom_one_year)
	public TextView zoomOneYear;

	@BindView(R.id.zoom_all)
	public TextView zoomAll;


	@BindView(R.id.zoom_one_day_underline)
	public View zoomOneDayUnderline;

	@BindView(R.id.zoom_one_week_underline)
	public View zoomOneWeekUnderline;

	@BindView(R.id.zoom_one_month_underline)
	public View zoomOneMonthUnderline;

	@BindView(R.id.zoom_three_months_underline)
	public View zoomThreeMonthsUnderline;

	@BindView(R.id.zoom_six_months_underline)
	public View zoomSixMonthsUnderline;

	@BindView(R.id.zoom_one_year_underline)
	public View zoomOneYearUnderline;

	@BindView(R.id.zoom_all_underline)
	public View zoomAllUnderline;

	private Unbinder unbinder;

	private TimeFrameChangeListener timeFrameChangeListener;

	public ChartTimeFrameSelectorView(Context context) {
		super(context);
		initView();
	}

	public ChartTimeFrameSelectorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ChartTimeFrameSelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.group_zoom_one_day)
	public void onZoomOneDayClicked() {
		selectZoom(ChartZoomEnum.ZOOM_1D);
	}

	@OnClick(R.id.group_zoom_one_week)
	public void onZoomOneWeekClicked() {
		selectZoom(ChartZoomEnum.ZOOM_1W);
	}

	@OnClick(R.id.group_zoom_one_month)
	public void onZoomOneMonthClicked() {
		selectZoom(ChartZoomEnum.ZOOM_1M);
	}

	@OnClick(R.id.group_zoom_three_months)
	public void onZoomThreeMonthsClicked() {
		selectZoom(ChartZoomEnum.ZOOM_3M);
	}

	@OnClick(R.id.group_zoom_six_months)
	public void onZoomSizMonthsClicked() {
		selectZoom(ChartZoomEnum.ZOOM_6M);
	}

	@OnClick(R.id.group_zoom_one_year)
	public void onZoomOneYearClicked() {
		selectZoom(ChartZoomEnum.ZOOM_1Y);
	}

	@OnClick(R.id.group_zoom_all)
	public void onZoomAllClicked() {
		selectZoom(ChartZoomEnum.ZOOM_ALL);
	}

	private void initView() {
		inflate(getContext(), R.layout.view_chart_time_frame_selector, this);

		unbinder = ButterKnife.bind(this);
	}

	public void setTimeFrameChangeListener(TimeFrameChangeListener timeFrameChangeListener) {
		this.timeFrameChangeListener = timeFrameChangeListener;
	}

	public void selectZoom(ChartZoomEnum zoomClicked) {
		setCurrentZoomButtonSelection(zoomClicked);
		if (timeFrameChangeListener != null)
			timeFrameChangeListener.onChange(zoomClicked);
	}

	private void setCurrentZoomButtonSelection(ChartZoomEnum currentZoom) {
		deselectAllZoomButtons();
		switch (currentZoom) {
			case ZOOM_1D:
				selectZoomButton(zoomOneDay, zoomOneDayUnderline);
				break;
			case ZOOM_1W:
				selectZoomButton(zoomOneWeek, zoomOneWeekUnderline);
				break;
			case ZOOM_1M:
				selectZoomButton(zoomOneMonth, zoomOneMonthUnderline);
				break;
			case ZOOM_3M:
				selectZoomButton(zoomThreeMonths, zoomThreeMonthsUnderline);
				break;
			case ZOOM_6M:
				selectZoomButton(zoomSixMonths, zoomSixMonthsUnderline);
				break;
			case ZOOM_1Y:
				selectZoomButton(zoomOneYear, zoomOneYearUnderline);
				break;
			case ZOOM_ALL:
				selectZoomButton(zoomAll, zoomAllUnderline);
				break;
		}
	}

	private void deselectAllZoomButtons() {
		deselectZoomButton(zoomOneDay, zoomOneDayUnderline);
		deselectZoomButton(zoomOneWeek, zoomOneWeekUnderline);
		deselectZoomButton(zoomOneMonth, zoomOneMonthUnderline);
		deselectZoomButton(zoomThreeMonths, zoomThreeMonthsUnderline);
		deselectZoomButton(zoomSixMonths, zoomSixMonthsUnderline);
		deselectZoomButton(zoomOneYear, zoomOneYearUnderline);
		deselectZoomButton(zoomAll, zoomAllUnderline);
	}

	private void selectZoomButton(TextView zoomButton, View underline) {
		zoomButton.setTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimary));
		underline.setVisibility(View.VISIBLE);
	}

	private void deselectZoomButton(TextView zoomButton, View underline) {
		zoomButton.setTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.grey400));
		underline.setVisibility(View.GONE);
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}
}