package vision.genesis.clientapp.feature.main.manager.info;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.ManagerProfileDetails;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

interface ManagerInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setManagerDetails(ManagerProfileDetails managerDetails);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);
}