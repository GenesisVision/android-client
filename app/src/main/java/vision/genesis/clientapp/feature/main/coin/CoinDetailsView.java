package vision.genesis.clientapp.feature.main.coin;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.AssetInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/10/2021.
 */

interface CoinDetailsView extends MvpView
{
	void setAssetInfo(AssetInfo response);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}