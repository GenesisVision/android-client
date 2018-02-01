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
	private static DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");

	public static String formatDate(DateTime dateTime) {
		return dateFormatter.print(dateTime);
	}
}
