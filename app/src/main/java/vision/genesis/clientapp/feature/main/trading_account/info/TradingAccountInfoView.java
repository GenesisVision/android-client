package vision.genesis.clientapp.feature.main.trading_account.info;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.PrivateTradingAccountFull;
import io.swagger.client.model.SignalSubscription;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.TransferFundsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

interface TradingAccountInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setAccountDetails(PrivateTradingAccountFull accountDetails);

	void showCopytrading(List<SignalSubscription> masters);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void showCreateProgram(CreateProgramModel createProgramModel);

	void showCreateFollow(CreateProgramModel createProgramModel);

	void showSnackbarMessage(String message);

	void showLoginActivity();

	void showTransferFundsActivity(TransferFundsModel model);

	void showCopytradingDetailsActivity(TradingAccountDetailsModel model);

	void showProfilePublicInfoActivity();
}