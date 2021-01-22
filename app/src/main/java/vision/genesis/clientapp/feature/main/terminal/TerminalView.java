package vision.genesis.clientapp.feature.main.terminal;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.ProfileFullViewModel;
import vision.genesis.clientapp.model.CurrencyEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

interface TerminalView extends MvpView
{
	void updateProfile(ProfileFullViewModel profile);

	void setBaseCurrency(CurrencyEnum baseCurrency);

	void showDialogMessage(String message);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
