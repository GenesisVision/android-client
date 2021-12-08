package vision.genesis.clientapp.feature.main.wallet.transactions;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.TransactionViewModel;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

interface TransactionsView extends MvpView
{
	void setTransactions(List<TransactionViewModel> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void addTransactions(List<TransactionViewModel> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void setTypeOptions(ArrayList<String> typeOptions);

	void setType(String type, Integer position);

	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

	void showSnackbarMessage(String message);
}
