package vision.genesis.clientapp.feature.main.fund.info.owner;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.UUID;

import io.swagger.client.model.FundChartStatistic;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.MakeSelfManagedFundPublicRequest;
import io.swagger.client.model.ProgramUpdate;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.FundRequest;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/12/2019.
 */

interface FundOwnerInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setFundDetails(FundDetailsFull fundDetails, CurrencyEnum baseCurrency);

	void updateStatistics(FundChartStatistic statistic, String baseCurrency);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showInvestWithdrawButtons();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showInvestFundActivity(FundRequest request);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showWithdrawFundActivity(FundRequest request);

	void showLoginActivity();

	void showRequestsBottomSheet();

	void showEditPublicInfoActivity(UUID assetId, ProgramUpdate model, Boolean showDescription);

	void showManageFundActivity(FundDetailsFull fundDetails);

	void showMakePublicFundActivity(MakeSelfManagedFundPublicRequest request);
}