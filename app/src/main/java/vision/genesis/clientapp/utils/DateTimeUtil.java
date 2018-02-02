package vision.genesis.clientapp.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * GenesisVision
 * Created by Vitaly on 2/1/18.
 */

public class DateTimeUtil
{
	private static DateTimeFormatter dateFormatter = DateTimeFormat.shortDate();

	private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.shortDateTime();

	public static String formatDate(DateTime dateTime) {
		return dateFormatter.print(dateTime);
	}

	public static String formatDateTime(DateTime dateTime) {
		return dateTimeFormatter.print(dateTime);
	}
}
