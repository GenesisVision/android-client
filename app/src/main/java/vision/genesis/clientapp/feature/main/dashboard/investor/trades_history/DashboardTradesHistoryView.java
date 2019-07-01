package vision.genesis.clientapp.feature.main.dashboard.investor.trades_history;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.SignalDetails;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

interface DashboardTradesHistoryView extends MvpView
{
	void showProgressBar(boolean show);

	void showEmpty(boolean show);

	void setSignals(List<SignalDetails> programs);

	void showSnackbarMessage(String message);
}
