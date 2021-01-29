package vision.genesis.clientapp.feature.main.terminal;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

interface TerminalView extends MvpView
{
	void setSelectedSymbol(String symbol);

	void showProgressBar(boolean show);

	void showSnackbarMessage(String message);
}
