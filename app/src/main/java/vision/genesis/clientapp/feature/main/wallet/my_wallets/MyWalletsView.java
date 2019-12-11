package vision.genesis.clientapp.feature.main.wallet.my_wallets;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.WalletData;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

interface MyWalletsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setWallets(List<WalletData> wallets, String baseCurrency);

	void showSnackbarMessage(String message);
}
