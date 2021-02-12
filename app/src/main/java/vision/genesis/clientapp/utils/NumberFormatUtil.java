package vision.genesis.clientapp.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/05/2018.
 */
public class NumberFormatUtil
{
	public static Double integerToDouble(Integer integerValue) {
		return integerValue != null ? (double) integerValue : null;
	}

	public static Integer doubleToInteger(Double doubleValue) {
		return doubleValue != null ? doubleValue.intValue() : null;
	}

	public static Double roundDouble(double number, int maxFraction) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(maxFraction, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static Double multipleDouble(double number, double multiplier) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.multiply(new BigDecimal(multiplier));
		return bd.setScale(8, RoundingMode.HALF_UP).doubleValue();
	}
}
