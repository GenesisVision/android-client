package vision.genesis.clientapp.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/04/2018.
 */
public class TimeValueFormatter implements IAxisValueFormatter
{
	public static List<String> getValues(DateRange dateRange) {
		List<String> values = new ArrayList<>();

		DateTime value = dateRange.getFrom().withMinuteOfHour(0).withSecondOfMinute(0);
		while (value.isBefore(dateRange.getTo())) {
			if (value.getHourOfDay() % 6 == 0) {
				values.add(DateTimeUtil.formatShortTime(value));
			}
			value = value.plusHours(1);
		}

		return values;
	}

	@Override
	public String getFormattedValue(float value, AxisBase axis) {
		return DateTimeUtil.formatShortTime(new DateTime().withMillis((long) value * 1000 * 60));
	}
}
