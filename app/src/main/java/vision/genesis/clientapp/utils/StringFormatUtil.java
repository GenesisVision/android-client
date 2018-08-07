package vision.genesis.clientapp.utils;

import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.CurrencyEnum;
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
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
		DecimalFormat df = new DecimalFormat("0.########", dfs);
		df.setMinimumFractionDigits(minFraction);
		df.setMaximumFractionDigits(maxFraction);
		df.setGroupingUsed(true);
		df.setGroupingSize(3);
		df.setRoundingMode(RoundingMode.DOWN);
		return df.format(decimal);
	}

	public static String formatAmountWithoutGrouping(double amountValue) {
		BigDecimal decimal = BigDecimal.valueOf(amountValue);
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
		dfs.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("0.########", dfs);
		df.setMinimumFractionDigits(0);
		df.setMaximumFractionDigits(8);
		df.setGroupingUsed(false);
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

	public static int getCurrencyMaxFraction(String programCurrency) {
		if (programCurrency.equals(CurrencyEnum.USD.toString()) ||
				programCurrency.equals(CurrencyEnum.EUR.toString())) {
			return 2;
		}
		if (programCurrency.equals(CurrencyEnum.GVT.toString())) {
			return 4;
		}
		else {
			return 8;
		}
	}

	public static SpannableString getDecimalSpannable(String value) {
		boolean isNegative = value.startsWith("-");
		final SpannableString text = new SpannableString(value);
		if (isNegative)
			text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.transactionRed)),
					0, value.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		if (value.contains(".")) {
			text.setSpan(new RelativeSizeSpan(0.7f), value.indexOf(".") + 1, value.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.black38)),
					value.indexOf("."), value.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		}
		return text;
	}
}
