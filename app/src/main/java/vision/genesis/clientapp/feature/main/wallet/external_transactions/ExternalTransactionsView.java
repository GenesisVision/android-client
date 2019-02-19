package vision.genesis.clientapp.feature.main.wallet.external_transactions;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.MultiWalletExternalTransaction;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

interface ExternalTransactionsView extends MvpView
{
	void showProgress(boolean show);

	void setTransactions(List<MultiWalletExternalTransaction> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void addTransactions(List<MultiWalletExternalTransaction> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void showSnackbarMessage(String message);
}
