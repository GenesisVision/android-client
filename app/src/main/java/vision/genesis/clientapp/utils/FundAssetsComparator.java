package vision.genesis.clientapp.utils;

import java.util.Comparator;

import io.swagger.client.model.FundAssetInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/10/2018.
 */
public class FundAssetsComparator implements Comparator<FundAssetInfo>
{
	@Override
	public int compare(FundAssetInfo asset1, FundAssetInfo asset2) {
		double diff = asset1.getTarget() - asset2.getTarget();

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