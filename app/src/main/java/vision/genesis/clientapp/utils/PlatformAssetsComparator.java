package vision.genesis.clientapp.utils;

import java.util.Comparator;

import io.swagger.client.model.PlatformAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/09/2020.
 */
public class PlatformAssetsComparator implements Comparator<PlatformAsset>
{
	@Override
	public int compare(PlatformAsset asset1, PlatformAsset asset2) {
		return asset1.getName().compareToIgnoreCase(asset2.getName());
	}
}
