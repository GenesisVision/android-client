package vision.genesis.clientapp.feature.main.fund.add_asset;

import android.util.Pair;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.PlatformAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/10/2019.
 */

interface AddAssetView extends MvpView
{
	void setAssets(ArrayList<Pair<String, List<PlatformAsset>>> assets);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
