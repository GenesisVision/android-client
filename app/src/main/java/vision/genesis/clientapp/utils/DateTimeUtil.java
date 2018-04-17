package vision.genesis.clientapp.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

/**
 * GenesisVision
 * Created by Vitaly on 2/1/18.
 */

public class DateTimeUtil
{
	private static DateTimeFormatter dateFormatter = DateTimeFormat.longDate();

	private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.shortDateTime();

	private static DateTimeFormatter dateShortFormatter = DateTimeFormat.forPattern("MM-dd");

	private static DateTimeFormatter timeShortFormatter = DateTimeFormat.forPattern("HH:mm");

	public static String formatDate(DateTime dateTime) {
		return dateFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static String formatShortDate(DateTime dateTime) {
		return dateShortFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static String formatShortTime(DateTime dateTime) {
		return timeShortFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static String formatDateTime(DateTime dateTime) {
		return dateTimeFormatter.withLocale(Locale.UK).print(dateTime);
	}

	public static int getDaysToDate(DateTime date) {
		return Days.daysBetween(DateTime.now(), date).getDays();
	}

	public static int getHoursToDate(DateTime date) {
		return Hours.hoursBetween(DateTime.now(), date).getHours();
	}

	public static int getMinutesToDate(DateTime date) {
		return Minutes.minutesBetween(DateTime.now(), date).getMinutes();
	}

	public static int getSecondsToDate(DateTime date) {
		return Seconds.secondsBetween(DateTime.now(), date).getSeconds();
	}
}
