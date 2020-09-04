package vision.genesis.clientapp.utils;

import android.util.Pair;

import java.util.Comparator;
import java.util.List;

import io.swagger.client.model.PlatformAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/09/2020.
 */
public class PlatformAssetsPairsComparator implements Comparator<Pair<String, List<PlatformAsset>>>
{
	@Override
	public int compare(Pair<String, List<PlatformAsset>> pair1, Pair<String, List<PlatformAsset>> pair2) {
		return pair1.first.compareToIgnoreCase(pair2.first);
	}
}
