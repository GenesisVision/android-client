package vision.genesis.clientapp.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

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

	public static String formatCurrencyAmount(double amountValue, String currency) {
		return formatAmount(amountValue, 2, getCurrencyMaxFraction(currency));
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
		if (Math.abs(amountValue) >= 1000000) {
			shortenedAmount.amount = formatAmount(amountValue / 1000000, 0, 1);
			shortenedAmount.modifier = "M";
		}
		else if (Math.abs(amountValue) >= 1000) {
			shortenedAmount.amount = formatAmount(amountValue / 1000, 0, 1);
			shortenedAmount.modifier = "k";
		}
		else if (Math.abs(amountValue) < 1) {
			shortenedAmount.amount = formatAmount(amountValue, 0, 2);
		}
		else {
			shortenedAmount.amount = formatAmount(amountValue, 0, 0);
		}

		return shortenedAmount;
	}

	public static int getCurrencyMaxFraction(String currency) {
		if (currency.equals(CurrencyEnum.USD.toString()) ||
				currency.equals(CurrencyEnum.EUR.toString())) {
			return 2;
		}
		if (currency.equals(CurrencyEnum.GVT.toString())) {
			return 8;
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

	public static String getGvtValueString(Double gvtValue) {
		return String.format(Locale.getDefault(), "%s GVT", StringFormatUtil.formatCurrencyAmount(gvtValue, CurrencyEnum.GVT.getValue()));
	}

	public static String getValueString(Double baseValue, String currency) {
		return String.format(Locale.getDefault(), "%s %s", StringFormatUtil.formatCurrencyAmount(baseValue, currency), currency);
	}

	public static String getChangePercentString(Double first, Double last) {
		return first == 0 ? "∞" : String.format(Locale.getDefault(), "%s%%",
				StringFormatUtil.formatAmount(Math.abs(first != 0 ? 100 / first * (first - last) : 0), 0, 2));
	}

	public static String getChangeValueString(Double changeValue) {
		return String.format(Locale.getDefault(), "%s%s GVT", changeValue > 0 ? "+" : "",
				StringFormatUtil.formatCurrencyAmount(changeValue, CurrencyEnum.GVT.getValue()));
	}

	public static String capitalize(String string) {
		if (string != null && !string.isEmpty()) {
			if (string.length() > 1)
				return Character.toUpperCase(string.charAt(0)) + string.substring(1).toLowerCase();
			else
				return string.toUpperCase();
		}
		else
			return "";
	}

	public static String getApproxSymbolIfNeeded(Double amount) {
		return amount != 0 ? "≈" : "";
	}

	public static void setClickableSpan(Context context, TextView textView, String completeString, String clickablePart, String url, boolean needUnderline) {
		int startPosition = completeString.indexOf(clickablePart);
		int endPosition = completeString.lastIndexOf(clickablePart) + clickablePart.length();

		final SpannableString spannable = new SpannableString(completeString);
		spannable.setSpan(new ClickableSpan()
		                  {
			                  @Override
			                  public void onClick(View view) {
				                  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				                  context.startActivity(browserIntent);
			                  }

			                  @Override
			                  public void updateDrawState(TextPaint ds) {
				                  super.updateDrawState(ds);
				                  int linkColor = ContextCompat.getColor(context, R.color.accent);
				                  ds.setColor(linkColor);
				                  ds.setUnderlineText(needUnderline);
			                  }
		                  },
				startPosition,
				endPosition,
				Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		textView.setText(spannable);
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
