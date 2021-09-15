package vision.genesis.clientapp.utils;

import android.util.Pair;

import java.util.Comparator;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 09/02/2021.
 */
public class DepthPairsPriceComparator implements Comparator<Pair<Double, Double>>
{
	@Override
	public int compare(Pair<Double, Double> pair1, Pair<Double, Double> pair2) {
		double diff = pair1.first - pair2.first;

		if (diff == 0f) {
			return 0;
		}
		else {
			if (diff > 0f) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
}
