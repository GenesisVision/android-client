package vision.genesis.clientapp.feature.main.fund.structure;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Collections;
import java.util.List;

import io.swagger.client.model.FundAssetInfo;
import vision.genesis.clientapp.utils.FundAssetsComparator;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

@InjectViewState
public class FundStructurePresenter extends MvpPresenter<FundStructureView>
{
	void setAssets(List<FundAssetInfo> assets) {
		Collections.sort(assets, new FundAssetsComparator());
		Collections.reverse(assets);
		getViewState().setAssetsToAdapter(assets);
	}
}
