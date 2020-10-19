package vision.genesis.clientapp.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import io.swagger.client.model.Currency;
import io.swagger.client.model.SocialViewMode;
import io.swagger.client.model.TradesDelay;
import vision.genesis.clientapp.BuildConfig;
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
		else if (Math.abs(amountValue) < 10) {
			shortenedAmount.amount = formatAmount(amountValue, 0, 4);
		}
		else if (Math.abs(amountValue) >= 10 && Math.abs(amountValue) < 1000) {
			shortenedAmount.amount = formatAmount(amountValue, 0, 2);
		}
		else {
			shortenedAmount.amount = formatAmount(amountValue, 0, 0);
		}

		return shortenedAmount;
	}

	public static int getCurrencyMaxFraction(String currency) {
		if (currency.equals(CurrencyEnum.USD.toString())) {
			return 2;
		}
		if (currency.equals(CurrencyEnum.USDT.toString())) {
			return 8;
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
		if (isNegative) {
			text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.transactionRed)),
					0, value.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		}
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
		if (currency.equals(Currency.USD.getValue())) {
			return String.format(Locale.getDefault(), "%s $", StringFormatUtil.formatCurrencyAmount(baseValue, currency));
		}
		return String.format(Locale.getDefault(), "%s %s", StringFormatUtil.formatCurrencyAmount(baseValue, currency), currency);
	}

	public static String getPercentString(Double percent) {
		return String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(percent), 0, 2);
	}

	public static String getChangePercentString(Double first, Double last) {
		return first == 0 ? "" : String.format(Locale.getDefault(), "%s%%",
				StringFormatUtil.formatAmount(Math.abs(first != 0 ? 100 / first * (first - last) : 0), 0, 2));
	}

	public static String getChangeValueString(Double changeValue) {
		return String.format(Locale.getDefault(), "%s%s GVT", changeValue > 0 ? "+" : "",
				StringFormatUtil.formatCurrencyAmount(changeValue, CurrencyEnum.GVT.getValue()));
	}

	public static String capitalize(String string) {
		if (string != null && !string.isEmpty()) {
			if (string.length() > 1) {
				return Character.toUpperCase(string.charAt(0)) + string.substring(1).toLowerCase();
			}
			else {
				return string.toUpperCase();
			}
		}
		else {
			return "";
		}
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

	public static String getTradesDelayString(TradesDelay tradesDelay) {
		switch (tradesDelay) {
			case FIVEMINUTES:
				return String.format(Locale.getDefault(), "%d %s", 5, GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.minutes, 5));
			case FIFTEENMINUTES:
				return String.format(Locale.getDefault(), "%d %s", 15, GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.minutes, 15));
			case THIRTYMINUTES:
				return String.format(Locale.getDefault(), "%d %s", 30, GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.minutes, 30));
			case ONEHOUR:
				return String.format(Locale.getDefault(), "%d %s", 1, GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.hours, 1));
			case SIXHOURS:
				return String.format(Locale.getDefault(), "%d %s", 6, GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.hours, 6));
			default:
			case NONE:
				return GenesisVisionApplication.INSTANCE.getString(R.string.no_delay);
		}
	}

	public static ArrayList<String> getTradesDelayOptions() {
		ArrayList<String> options = new ArrayList<>();
		for (TradesDelay value : TradesDelay.values()) {
			options.add(getTradesDelayString(value));
		}

		return options;
	}

	public static String getAssetSocialTag(String name, String type) {
		return String.format(Locale.getDefault(), "%s (%s)", name, type.toLowerCase());
	}

	public static int getSocialAssetTagStartPos(String text, int pos) {
		int i = pos;
		int start = pos;
		while (i > 0) {
			if (!Pattern.compile("[@a-zA-Z0-9_-]").matcher(text.subSequence(i - 1, i)).find()) {
				break;
			}
			if (text.charAt(i - 1) == '@') {
				start = i;
				break;
			}
			i--;
		}
		return start;
	}

	public static int getSocialAssetTagEndPos(String text, int pos) {
		int i = pos;
		int end = pos;
		while (i < text.length() && i > 0) {
			if (!Pattern.compile("[a-zA-Z0-9_-]").matcher(text.subSequence(i - 1, i)).find()) {
				end = i;
				break;
			}
			i++;
		}

		return end;
	}

	public static ArrayList<String> getSocialViewModeOptions() {
		ArrayList<String> options = new ArrayList<>();
		for (SocialViewMode value : SocialViewMode.values()) {
			options.add(getSocialViewModeString(value));
		}

		return options;
	}

	public static String getSocialViewModeString(SocialViewMode socialViewMode) {
		switch (socialViewMode) {
			case ALLUSERS:
				return GenesisVisionApplication.INSTANCE.getResources().getString(R.string.all_users);
			case ONLYME:
				return GenesisVisionApplication.INSTANCE.getResources().getString(R.string.only_me);
			default:
				return "";
		}
	}

	public static String getPostUrl(String url) {
		return BuildConfig.WEB_ADDRESS.concat("posts/".concat(url));
	}

	public static String getCommentUrl(String url) {
		return BuildConfig.WEB_ADDRESS.concat("posts/".concat(url));
	}
}
