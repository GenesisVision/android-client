package vision.genesis.clientapp.feature.common.timeframe_profit;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardProfits;
import io.swagger.client.model.DashboardTimeframeProfit;
import io.swagger.client.model.Timeframe;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/12/2019.
 */

public class TimeframeProfitView extends RelativeLayout
{
	public interface Listener
	{
		void onTimeframeSelected(Timeframe timeframe);
	}

	@BindView(R.id.selection)
	public View selection;

	@BindView(R.id.group_timeframes)
	public ViewGroup timeframesGroup;

	@BindView(R.id.group_day)
	public ViewGroup dayGroup;

	@BindView(R.id.group_week)
	public ViewGroup weekGroup;

	@BindView(R.id.group_month)
	public ViewGroup monthGroup;

	@BindView(R.id.day_value)
	public TextView dayValue;

	@BindView(R.id.day_label)
	public TextView dayLabel;

	@BindView(R.id.week_value)
	public TextView weekValue;

	@BindView(R.id.week_label)
	public TextView weekLabel;

	@BindView(R.id.month_value)
	public TextView monthValue;

	@BindView(R.id.month_label)
	public TextView monthLabel;

	private Unbinder unbinder;

	private Listener listener;

	private Timeframe selectedTimeframe = Timeframe.DAY;

	public TimeframeProfitView(Context context) {
		super(context);
		initView();
	}

	public TimeframeProfitView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TimeframeProfitView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.group_day)
	public void onDayClicked() {
		onClicked(Timeframe.DAY);
	}

	@OnClick(R.id.group_week)
	public void onWeekClicked() {
		onClicked(Timeframe.WEEK);
	}

	@OnClick(R.id.group_month)
	public void onMonthClicked() {
		onClicked(Timeframe.MONTH);
	}

	private void onClicked(Timeframe timeframe) {
		select(timeframe);
		if (listener != null) {
			listener.onTimeframeSelected(timeframe);
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_timeframe_profit, this);

		unbinder = ButterKnife.bind(this);

		setFonts();

		new Handler().postDelayed(() -> select(selectedTimeframe), 100);
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void setFonts() {
		dayValue.setTypeface(TypefaceUtil.semibold());
		weekValue.setTypeface(TypefaceUtil.semibold());
		monthValue.setTypeface(TypefaceUtil.semibold());

		dayLabel.setText(getContext().getString(R.string.day).toLowerCase());
		weekLabel.setText(getContext().getString(R.string.week).toLowerCase());
		monthLabel.setText(getContext().getString(R.string.month).toLowerCase());
	}

	public void setListener(Listener listener) {
		this.listener = listener;
		onClicked(selectedTimeframe);
	}

	public void setData(DashboardProfits data) {
		setChangePercent(dayValue, data.getDay());
		setChangePercent(weekValue, data.getWeek());
		setChangePercent(monthValue, data.getMonth());
	}

	private void setChangePercent(TextView view, DashboardTimeframeProfit model) {
		view.setText(String.format(Locale.getDefault(), "%s%%",
				StringFormatUtil.formatAmount(model.getProfitPercent(), 0, 2)));
		view.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				model.getProfitPercent() > 0
						? R.attr.colorGreen
						: model.getProfitPercent() < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}

	public void select(Timeframe timeframe) {
		this.selectedTimeframe = timeframe;
		moveSelection(timeframe);
	}

	private void moveSelection(Timeframe timeframe) {
		float timeframeWidth = timeframesGroup.getWidth() / 3;
		float newX;
		int index;
		switch (timeframe) {
			case WEEK:
				index = 1;
				break;
			case MONTH:
				index = 2;
				break;
			default:
			case DAY:
				index = 0;
				break;
		}
		newX = timeframesGroup.getX() + timeframeWidth * index + timeframeWidth / 2 - (float) selection.getWidth() / 2;

		ValueAnimator anim = ValueAnimator.ofFloat(selection.getX(), newX);
		ValueAnimator alphaAnim = ValueAnimator.ofFloat(selection.getAlpha(), 0.4f);

		anim.addUpdateListener(animation -> selection.setX((float) anim.getAnimatedValue()));
		alphaAnim.addUpdateListener(animation -> selection.setAlpha((float) alphaAnim.getAnimatedValue()));

		anim.setDuration(150);
		alphaAnim.setDuration(500);

		anim.setInterpolator(new AccelerateDecelerateInterpolator());
		alphaAnim.setInterpolator(new AccelerateDecelerateInterpolator());

		anim.start();
		alphaAnim.start();
	}
}
