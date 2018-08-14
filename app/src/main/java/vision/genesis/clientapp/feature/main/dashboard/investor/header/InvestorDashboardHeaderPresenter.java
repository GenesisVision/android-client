package vision.genesis.clientapp.feature.main.dashboard.investor.header;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.InvestorDashboardManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/08/2018.
 */

@InjectViewState
public class InvestorDashboardHeaderPresenter extends MvpPresenter<InvestorDashboardHeaderView>
{
	@Inject
	public Context context;

	@Inject
	public InvestorDashboardManager investorDashboardManager;

	private Subscription dataSubscription;

	private String type;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

//		getViewState().setRefreshing(true);
		getData();
	}

	@Override
	public void onDestroy() {
		if (dataSubscription != null)
			dataSubscription.unsubscribe();

		super.onDestroy();
	}

	void setType(String type) {
		this.type = type;
//		getViewState().setRefreshing(true);
		getData();
	}

	void onShow() {
		getData();
	}

	private void getData() {
		if (type != null) {
			if (dataSubscription != null)
				dataSubscription.unsubscribe();
//		transactionsSubscription = walletManager.getData(filter)
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribeOn(Schedulers.io())
//				.subscribe(this::handleGetTransactionsResponse,
//						this::handleGetTransactionsError);
		}
	}

//	private void handleGetTransactionsResponse(WalletTransactionsViewModel model) {
//		transactionsSubscription.unsubscribe();
//
//		getViewState().setRefreshing(false);
//
//		List<WalletTransaction> transactions = model.getData();
//
//		if (skip == 0)
//			getViewState().setTransactions(transactions);
//		else
//			getViewState().addTransactions(transactions);
//	}

	private void handleGetTransactionsError(Throwable error) {
		dataSubscription.unsubscribe();

//		getViewState().setRefreshing(false);
	}

}
