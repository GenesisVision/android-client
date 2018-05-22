package vision.genesis.clientapp.utils;

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
}
