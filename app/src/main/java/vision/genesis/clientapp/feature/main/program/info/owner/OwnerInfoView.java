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
import vision.genesis.clientapp.model.TradingAccountDetailsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/11/2019.
 */

interface OwnerInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setDetails(ProgramFollowDetailsFull details);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showInvestWithdrawButtons();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showInvestProgramActivity(ProgramRequest request);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showWithdrawProgramActivity(ProgramRequest request);

	void showCopytrading(List<SignalSubscription> masters);

	void showRequestsBottomSheet();

	void showSnackbarMessage(String message);

	void showLoginActivity();

	void showCreateProgram(CreateProgramModel createProgramModel);

	void showCreateFollow(CreateProgramModel createProgramModel);

	void showEditPublicInfoActivity(UUID assetId, ProgramUpdate model);

	void showManageAccountActivity(TradingAccountDetailsModel model);

	void showManageProgramActivity(ProgramFollowDetailsFull details);

	void showEditFollowSettingsActivity(CreateSignalProvider model);

	void showUnfollowTradesActivity(UUID followId, UUID tradingAccountId, String followName);

	void showCopytradingDetailsActivity(TradingAccountDetailsModel model);
}