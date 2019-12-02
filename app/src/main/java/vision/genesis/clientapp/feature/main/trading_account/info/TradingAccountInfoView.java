package vision.genesis.clientapp.feature.main.trading_account.info;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.PrivateTradingAccountFull;
import vision.genesis.clientapp.model.CreateProgramModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

interface TradingAccountInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setAccountDetails(PrivateTradingAccountFull accountDetails);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void showCreateProgram(CreateProgramModel createProgramModel);

	void showSnackbarMessage(String message);

	void showLoginActivity();
}