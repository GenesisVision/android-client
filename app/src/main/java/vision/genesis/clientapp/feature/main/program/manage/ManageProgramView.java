package vision.genesis.clientapp.feature.main.program.manage;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.UUID;

import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramUpdate;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

interface ManageProgramView extends MvpView
{
	void updateView(ProgramDetailsFull model);

	void showChangeSettingsActivity(UUID programId, String currency, ProgramUpdate model);

	void showSnackbarMessage(String message);

	void showProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();
}