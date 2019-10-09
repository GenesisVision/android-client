package vision.genesis.clientapp.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Locale;

/**
 * GenesisVision
 * Created by Vitaly on 2/1/18.
 */

public class DateTimeUtil
{
	private static DateTimeFormatter dateFormatter = DateTimeFormat.longDate();

	private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.shortDateTime();

	private static DateTimeFormatter dateShortFormatter = DateTimeFormat.forPattern("dd MMM YYYY");

	private static DateTimeFormatter dateExtraShortFormatter = DateTimeFormat.forPattern("dd.MM");

	private static DateTimeFormatter dateRequestFormatter = DateTimeFormat.forPattern("dd MMM");

	private static DateTimeFormatter timeShortFormatter = DateTimeFormat.forPattern("HH:mm");

	private static DateTimeFormatter dateTimeShortFormatter = DateTimeFormat.forPattern("dd.MM.YY HH:mm");

	private static DateTimeFormatter eventDateTimeFormatter = DateTimeFormat.forPattern("dd MMM YYYY, KK:mmaa");

	public static String formatDate(DateTime dateTime) {
		return dateFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static String formatShortDate(DateTime dateTime) {
		return dateShortFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static String formatExtraShortDate(DateTime dateTime) {
		return dateExtraShortFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static String formatRequestDate(DateTime dateTime) {
		return dateRequestFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static String formatShortTime(DateTime dateTime) {
		return timeShortFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static String formatShortDateTime(DateTime dateTime) {
		return dateTimeShortFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static String formatDateTime(DateTime dateTime) {
		return dateTimeFormatter.withLocale(Locale.UK).print(dateTime);
	}

	public static String formatEventDateTime(DateTime dateTime) {
		return eventDateTimeFormatter.withLocale(Locale.US).print(dateTime);
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

	public static String getHumanReadablePeriod(long milliseconds) {
		PeriodFormatter formatter = new PeriodFormatterBuilder()
				.appendDays()
				.appendSuffix("d")
				.appendSeparator(" ")
				.appendHours()
				.appendSuffix("h")
				.appendSeparator(" ")
				.appendMinutes()
				.appendSuffix("m")
				.toFormatter();
		return formatter.print(new Period(milliseconds).normalizedStandard(PeriodType.dayTime()));
	}
}
