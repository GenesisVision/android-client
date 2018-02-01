package vision.genesis.clientapp.feature.main.wallet;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.WalletTransaction;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface WalletView extends MvpView
{
	void setBalance(double balance);

	void showBalanceProgress();

	void hideBalanceProgress();

	void setRefreshing(boolean refreshing);

	void setTransactions(List<WalletTransaction> transactions);

	void addTransactions(List<WalletTransaction> transactions);

	void showSnackbarMessage(String message);
}
