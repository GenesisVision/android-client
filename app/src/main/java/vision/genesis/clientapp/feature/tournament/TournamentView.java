package vision.genesis.clientapp.feature.tournament;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/05/2018.
 */

interface TournamentView extends MvpView
{
	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();

	void showToast(String message);

	void showSnackbarMessage(String message);

	void showProgress(boolean show);
}