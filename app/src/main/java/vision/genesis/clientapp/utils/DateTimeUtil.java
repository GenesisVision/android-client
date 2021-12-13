package vision.genesis.clientapp.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Seconds;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Locale;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;

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

	private static DateTimeFormatter requestInfoDateTimeFormatter = DateTimeFormat.forPattern("KK:mmaa 'on' MMMM dd");

	private static DateTimeFormatter tradeTimeFormatter = DateTimeFormat.forPattern("HH:mm:ss");

	private static DateTimeFormatter dateTimeFileFormatter = DateTimeFormat.forPattern("dd-MM-YY_HH-mm");

	private static DateTimeFormatter timerFormatter = DateTimeFormat.forPattern("mm:ss");

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

	public static String formatDateTimeFile(DateTime dateTime) {
		return dateTimeFileFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static String formatTimer(int seconds) {
		return timerFormatter.withLocale(Locale.US).print(seconds * 1000L);
	}

	public static String formatEventDateTime(DateTime dateTime) {
//		DateTime nowMidnight = DateTime.now().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
		if (DateTimeComparator.getDateOnlyInstance().compare(DateTime.now(), dateTime) == 0) {
			return String.format(Locale.getDefault(), "%s, %s",
					GenesisVisionApplication.INSTANCE.getString(R.string.today),
					timeShortFormatter.withLocale(Locale.US).print(dateTime));
		}
		else if (DateTimeComparator.getDateOnlyInstance().compare(DateTime.now().minusDays(1), dateTime) == 0) {
			return String.format(Locale.getDefault(), "%s, %s",
					GenesisVisionApplication.INSTANCE.getString(R.string.yesterday),
					timeShortFormatter.withLocale(Locale.US).print(dateTime));
		}
		else {
			return eventDateTimeFormatter.withLocale(Locale.US).print(dateTime);
		}
	}

	public static String formatAgeDateTime(DateTime dateTime) {
		int years = Math.abs(DateTimeUtil.getYearsToDate(dateTime));
		if (years > 0) {
			return (String.format(Locale.getDefault(), "%d %s", years, GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.years, years)));
		}
		int months = Math.abs(DateTimeUtil.getMonthsToDate(dateTime));
		if (months > 0) {
			return (String.format(Locale.getDefault(), "%d %s", months, GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.months, months)));
		}
		int days = Math.abs(DateTimeUtil.getDaysToDate(dateTime));
		if (days > 0) {
			return (String.format(Locale.getDefault(), "%d %s", days, GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.days, days)));
		}
		else {
			return GenesisVisionApplication.INSTANCE.getResources().getString(R.string.today);
		}
	}

	public static String formatRequestInfoDateTime(DateTime dateTime) {
		return requestInfoDateTimeFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static String formatTradeTime(DateTime dateTime) {
		return tradeTimeFormatter.withLocale(Locale.US).print(dateTime);
	}

	public static int getYearsToDate(DateTime date) {
		return Years.yearsBetween(DateTime.now(), date).getYears();
	}

	public static int getMonthsToDate(DateTime date) {
		return Months.monthsBetween(DateTime.now(), date).getMonths();
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
