package vision.genesis.clientapp.feature.main.wallet.buy_with_card;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/09/2020.
 */

interface BuyWithCardView extends MvpView
{
	void showSnackbarMessage(String message);

	void finishActivity();
}
