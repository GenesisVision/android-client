package vision.genesis.clientapp.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.joda.time.DateTime;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/04/2018.
 */
public class TimeValueFormatter implements IAxisValueFormatter
{
	@Override
	public String getFormattedValue(float value, AxisBase axis) {
		return DateTimeUtil.formatShortTime(new DateTime().withMillis((long) value));
	}
}
