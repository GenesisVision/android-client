package vision.genesis.clientapp.feature.main.wallet.copytrading_accounts;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.CopyTradingAccountInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2019.
 */

interface CopytradingAccountsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setAccounts(List<CopyTradingAccountInfo> wallets);

	void showSnackbarMessage(String message);
}
