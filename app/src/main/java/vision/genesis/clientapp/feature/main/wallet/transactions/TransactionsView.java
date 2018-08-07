package vision.genesis.clientapp.feature.main.wallet.transactions;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVision
 * Created by Vitaly on 3/5/18.
 */

interface TransactionsView extends MvpView
{
	void setRefreshing(boolean refreshing);

//	void setTransactions(List<WalletTransaction> transactions);
//
//	void addTransactions(List<WalletTransaction> transactions);

	void showSnackbarMessage(String message);
}
