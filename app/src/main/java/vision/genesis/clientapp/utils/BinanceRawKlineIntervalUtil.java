package vision.genesis.clientapp.utils;

import io.swagger.client.model.BinanceRawKlineInterval;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/02/2021.
 */
public class BinanceRawKlineIntervalUtil
{
	public static String toShortString(BinanceRawKlineInterval interval) {
		switch (interval) {
			case ONEMINUTE:
				return "1m";
			case THREEMINUTES:
				return "3m";
			case FIVEMINUTES:
				return "5m";
			case FIFTEENMINUTES:
				return "15m";
			case THIRTYMINUTES:
				return "30m";
			case ONEHOUR:
				return "1h";
			case TWOHOUR:
				return "2h";
			case FOURHOUR:
				return "4h";
			case SIXHOUR:
				return "6h";
			case EIGHTHOUR:
				return "8h";
			case TWELVEHOUR:
				return "12h";
			default:
			case ONEDAY:
				return "1d";
			case THREEDAY:
				return "3d";
			case ONEWEEK:
				return "1w";
			case ONEMONTH:
				return "1M";
		}
	}
}
