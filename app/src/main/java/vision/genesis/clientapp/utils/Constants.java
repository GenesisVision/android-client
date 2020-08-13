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

	public static final int MIN_ASSET_NAME_LENGTH = 4;

	public static final int MAX_ASSET_NAME_LENGTH = 20;

	public static final int MIN_ASSET_DESCRIPTION_LENGTH = 20;

	public static final int MAX_ASSET_DESCRIPTION_LENGTH = 500;

	public static final double MAX_STOP_OUT_LEVEL = 100.0;

	public static final double MIN_STOP_OUT_LEVEL = 10.0;

	public static final int MIN_USER_NAME_LENGTH = 4;

	public static final int MAX_USER_NAME_LENGTH = 20;

	public static final int MIN_USER_ABOUT_LENGTH = 20;

	public static final int MAX_USER_ABOUT_LENGTH = 500;

	public static final int MIN_IMAGES_COUNT_CREATE_POST = 10;

	public static final int MIN_IMAGES_COUNT_ADD_COMMENT = 1;

	public static final int MAX_SOCIAL_TAGS_FILTER = 16;

	public static ArrayList<String> getCurrenciesForProgramsListFilter() {
		return new ArrayList<>(Arrays.asList("All", "ETH", "BTC", "USDT", "USD"));
	}

	public static ArrayList<String> getStatusesForDashboardProgramsListFilter() {
		return new ArrayList<>(Arrays.asList("All", "Active"));
	}
}
