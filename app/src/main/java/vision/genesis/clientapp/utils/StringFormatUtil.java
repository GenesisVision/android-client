package vision.genesis.clientapp.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import vision.genesis.clientapp.model.ShortenedAmount;

/**
 * GenesisVision
 * Created by Vitaly on 3/5/18.
 */

public class StringFormatUtil
{
	public static String formatAmount(double amountValue) {
		return formatAmount(amountValue, 2, 8);
	}

	public static String formatAmount(double amountValue, int minFraction, int maxFraction) {
		BigDecimal decimal = BigDecimal.valueOf(amountValue);
		DecimalFormat df = new DecimalFormat("0.########");
		df.setMinimumFractionDigits(minFraction);
		df.setMaximumFractionDigits(maxFraction);
		df.setGroupingUsed(true);
		df.setGroupingSize(3);
		df.setRoundingMode(RoundingMode.DOWN);
		return df.format(decimal);
	}

	public static ShortenedAmount getShortenedAmount(double amountValue) {
		ShortenedAmount shortenedAmount = new ShortenedAmount();
		if (Math.abs(amountValue) > 1000000) {
			shortenedAmount.amount = formatAmount(amountValue / 1000000, 0, 1);
			shortenedAmount.modifier = "M";
		}
		else if (Math.abs(amountValue) > 10000) {
			shortenedAmount.amount = formatAmount(amountValue / 1000, 0, 1);
			shortenedAmount.modifier = "K";
		}
		else if (Math.abs(amountValue) < 1) {
			shortenedAmount.amount = formatAmount(amountValue, 0, 2);
		}
		else {
			shortenedAmount.amount = formatAmount(amountValue, 0, 0);
		}

		return shortenedAmount;
	}
}
