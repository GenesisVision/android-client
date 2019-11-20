package vision.genesis.clientapp.utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/06/2018.
 */
public class Constants
{
	public static final int TOKENS_MAX_DECIMAL_POINT_DIGITS = 2;

	public static final int TWO_FACTOR_CODE_LENGTH = 6;

	public static final int PIN_CODE_LENGTH = 4;

	public static final int PIN_MAX_WRONG_ATTEMPTS = 5;

	public static final long MILLISECONDS_IN_BACKGROUND_TO_LOCK = 30 * 1000;

	public static final int MIN_PROGRAM_LEVEL_ENTRY_FEE = 3;

	public static final int MIN_LOGO_WIDTH = 300;

	public static final int MIN_LOGO_HEIGHT = 300;

	public static final int MIN_FUND_NAME_LENGTH = 4;

	public static final int MAX_FUND_NAME_LENGTH = 20;

	public static final int MIN_FUND_DESCRIPTION_LENGTH = 20;

	public static final int MAX_FUND_DESCRIPTION_LENGTH = 500;

	public static ArrayList<String> getCurrenciesForProgramsListFilter() {
		return new ArrayList<>(Arrays.asList("All", "ETH", "BTC", "USDT", "USD"));
	}

	public static ArrayList<String> getStatusesForDashboardProgramsListFilter() {
		return new ArrayList<>(Arrays.asList("All", "Active"));
	}
}
