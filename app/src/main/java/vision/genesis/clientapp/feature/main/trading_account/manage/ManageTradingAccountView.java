package vision.genesis.clientapp.feature.main.trading_account.manage;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import vision.genesis.clientapp.model.TradingAccountDetailsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

interface ManageTradingAccountView extends MvpView
{
	void updateView(TradingAccountDetailsModel model);

	void showChangeBrokerActivity(TradingAccountDetailsModel model);

	void showChangePasswordActivity(TradingAccountDetailsModel model);

	void showSnackbarMessage(String message);

	void showCancelProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();
}