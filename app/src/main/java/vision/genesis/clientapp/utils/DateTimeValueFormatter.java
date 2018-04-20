package vision.genesis.clientapp.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.joda.time.DateTime;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/04/2018.
 */
public class DateTimeValueFormatter implements IAxisValueFormatter
{
	@Override
	public String getFormattedValue(float value, AxisBase axis) {
		return DateTimeUtil.formatShortDateTime(new DateTime().withMillis((long) value));
	}
}
