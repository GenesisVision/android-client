package vision.genesis.clientapp.feature.auth.login;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface LoginView extends MvpView
{
	void setEmailError(String message);

	void setPasswordError(String message);

	void clearErrors();

	void showProgress();

	void hideProgress();

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showRegistrationActivity();

	void finishActivity();
}
