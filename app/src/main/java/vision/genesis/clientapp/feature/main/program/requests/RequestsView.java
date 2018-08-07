package vision.genesis.clientapp.feature.main.program.requests;

import com.arellomobile.mvp.MvpView;



/**
 * GenesisVision
 * Created by Vitaly on 3/1/18.
 */

interface RequestsView extends MvpView
{
//	void setRequests(List<InvestmentProgramRequest> requests);

	void setRefreshing(boolean refreshing);

	void finishActivity();

	void showSnackbarMessage(String message);
}
