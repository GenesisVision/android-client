package vision.genesis.clientapp.feature.main.program.info.owner;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.CreateSignalProvider;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.SignalSubscription;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.TransferFundsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/11/2019.
 */

interface OwnerInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setDetails(ProgramFollowDetailsFull details);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void setInvestWithdrawInfo(String info);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showInvestWithdrawButtons();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setMinDepositText(String minDepositText);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showInvestProgramActivity(ProgramRequest request);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showWithdrawProgramActivity(ProgramRequest request);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showCopytrading(List<SignalSubscription> masters);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showRequestsBottomSheet();

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showLoginActivity();

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showCreateProgram(CreateProgramModel createProgramModel);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showCreateFollow(CreateProgramModel createProgramModel);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showEditPublicInfoActivity(UUID assetId, ProgramUpdate model);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showManageAccountActivity(TradingAccountDetailsModel model);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showManageProgramActivity(ProgramFollowDetailsFull details);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showEditFollowSettingsActivity(CreateSignalProvider model);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showUnfollowTradesActivity(UUID followId, UUID tradingAccountId, String followName, Boolean isExternal);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showCopytradingDetailsActivity(TradingAccountDetailsModel model);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showTransferFundsActivity(TransferFundsModel model);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showEditSubscriptionActivity(SubscriptionSettingsModel model, UUID followId, UUID tradingAccountId, Boolean external);
}