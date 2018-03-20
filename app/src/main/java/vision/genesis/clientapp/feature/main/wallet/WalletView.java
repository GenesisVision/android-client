package vision.genesis.clientapp.feature.main.wallet;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.TransactionsFilter;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface WalletView extends MvpView
{
	void setBalance(double balance);

	void setFiatBalance(double balance);

	void setTransactionsFilterType(TransactionsFilter.TypeEnum type);

	void showBalanceProgress();

	void hideBalanceProgress();

	void showSnackbarMessage(String message);
}
