package vision.genesis.clientapp.feature.main.terminal.market_watch.activity;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/01/2022.
 */

public interface MarketWatchView extends MvpView
{
	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
