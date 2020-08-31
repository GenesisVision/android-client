package vision.genesis.clientapp.feature.main.unregistered.dashboard;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/08/2020.
 */

interface UnregisteredDashboardView extends MvpView
{
	void setQuote(String quote, String author);

	void showSnackbarMessage(String message);
}
