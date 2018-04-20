package vision.genesis.clientapp.ui.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/04/2018.
 */
public class CustomLineChart extends LineChart
{
	public CustomLineChart(Context context) {
		super(context);
	}

	public CustomLineChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomLineChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}
}
