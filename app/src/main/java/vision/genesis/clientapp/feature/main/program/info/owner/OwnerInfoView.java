package vision.genesis.clientapp.feature.main.program.info.owner;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.UUID;

import io.swagger.client.model.CreateSignalProvider;
import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramUpdate;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

interface OwnerInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setProgramDetails(ProgramDetailsFull programDetails);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setFollowDetails(FollowDetailsFull followDetails);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showInvestWithdrawButtons();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showInvestProgramActivity(ProgramRequest request);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showWithdrawProgramActivity(ProgramRequest request);

	void showRequestsBottomSheet();

	void showSnackbarMessage(String message);

	void showLoginActivity();

	void showCreateProgram(CreateProgramModel createProgramModel);

	void showCreateFollow(CreateProgramModel createProgramModel);

	void showEditPublicInfoActivity(UUID assetId, ProgramUpdate model);

	void showManageAccountActivity(TradingAccountDetailsModel model);

	void showEditFollowSettingsActivity(CreateSignalProvider model);
}