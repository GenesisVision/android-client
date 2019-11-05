package vision.genesis.clientapp.feature.main.wallet.external_transactions;

import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.TransactionViewModel;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

interface ExternalTransactionsView extends MvpView
{
	void showProgress(boolean show);

	void setTransactions(List<TransactionViewModel> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void addTransactions(List<TransactionViewModel> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void setStatusCanceled(UUID transactionId);

	void showSnackbarMessage(String message);
}
