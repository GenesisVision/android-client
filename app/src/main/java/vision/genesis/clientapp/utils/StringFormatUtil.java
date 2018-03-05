package vision.genesis.clientapp.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * GenesisVision
 * Created by Vitaly on 3/5/18.
 */

public class StringFormatUtil
{
	public static String formatAmount(double amountValue) {
		DecimalFormat df = new DecimalFormat("0.########");
		df.setMinimumFractionDigits(2);
		df.setGroupingUsed(true);
		df.setGroupingSize(3);
		df.setRoundingMode(RoundingMode.DOWN);
		return df.format(amountValue);
	}
}
