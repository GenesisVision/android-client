package vision.genesis.clientapp.feature.main.coin;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.AssetInfo;
import io.swagger.client.model.CoinsAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/10/2021.
 */

interface CoinDetailsView extends MvpView
{
	void setAssetInfo(AssetInfo response);

	void setInvestment(CoinsAsset coin);

	void setCoinInfo(CoinsAsset coinsAsset);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}