package vision.genesis.clientapp.feature.main.terminal;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import io.swagger.client.model.ExchangeAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

interface TerminalView extends MvpView
{
	void setSelectedSymbol(String symbol);

	void showProgressBar(boolean show);

	void showSnackbarMessage(String message);

	void showSelectAccount(ArrayList<ExchangeAsset> accounts);

	void setSelectedAccount(ExchangeAsset account, int selectedAccountPosition);

	void showAccountArrow(boolean show);
}
