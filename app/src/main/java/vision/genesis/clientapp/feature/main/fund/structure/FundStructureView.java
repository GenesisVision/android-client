package vision.genesis.clientapp.feature.main.fund.structure;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.FundAssetInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

interface FundStructureView extends MvpView
{
	void setAssetsToAdapter(List<FundAssetInfo> assets);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);
}