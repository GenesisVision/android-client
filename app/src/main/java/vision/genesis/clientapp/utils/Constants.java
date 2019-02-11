package vision.genesis.clientapp.utils;

import vision.genesis.clientapp.BuildConfig;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/06/2018.
 */
public class Constants
{
	public static final boolean IS_INVESTOR = BuildConfig.FLAVOR.equals("investor");

	public static final int TOKENS_MAX_DECIMAL_POINT_DIGITS = 2;

	public static final int TWO_FACTOR_CODE_LENGTH = 6;

	public static final int PIN_CODE_LENGTH = 4;

	public static final int PIN_MAX_WRONG_ATTEMPTS = 5;

	public static final long MILLISECONDS_IN_BACKGROUND_TO_LOCK = 30 * 1000;

	public static final int MIN_PROGRAM_LEVEL_ENTRY_FEE = 3;
}
