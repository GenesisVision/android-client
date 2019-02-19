package vision.genesis.clientapp.feature.main.wallet.transactions;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.MultiWalletTransaction;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

interface TransactionsView extends MvpView
{
	void showProgress(boolean show);

	void setTransactions(List<MultiWalletTransaction> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void addTransactions(List<MultiWalletTransaction> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void showSnackbarMessage(String message);
}
