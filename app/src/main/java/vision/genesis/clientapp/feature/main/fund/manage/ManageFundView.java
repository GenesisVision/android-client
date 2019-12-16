package vision.genesis.clientapp.feature.main.fund.manage;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.UUID;

import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.ProgramUpdate;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

interface ManageFundView extends MvpView
{
	void updateView(FundDetailsFull fundDetails);

	void showChangeSettingsActivity(UUID fundId, ProgramUpdate model);

	void showSnackbarMessage(String message);

	void showProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();
}