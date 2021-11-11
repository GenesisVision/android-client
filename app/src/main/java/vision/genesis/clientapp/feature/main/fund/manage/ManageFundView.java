package vision.genesis.clientapp.feature.main.fund.manage;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.FundAssetInfo;
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

	void showReallocateFundActivity(UUID fundId, String reallocationInfo, List<FundAssetInfo> assets);

	void showSnackbarMessage(String message);

	void showProgress(boolean show);

	void showCheckTfaActivity();

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();
}