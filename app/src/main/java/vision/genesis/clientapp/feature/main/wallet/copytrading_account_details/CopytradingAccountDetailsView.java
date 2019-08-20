package vision.genesis.clientapp.feature.main.wallet.copytrading_account_details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import vision.genesis.clientapp.model.CopytradingAccountModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2019.
 */

interface CopytradingAccountDetailsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setAccountData(CopytradingAccountModel data);

	void showProgress(boolean show);

	void setRefreshing(boolean refreshing);

	void showSnackbarMessage(String message);

	void setOpenTradesCount(Integer transactionsCount);

	void setTradesHistoryCount(Integer depositsWithdrawalsCount);

	void setTradingLogCount(Integer eventsCount);
}
