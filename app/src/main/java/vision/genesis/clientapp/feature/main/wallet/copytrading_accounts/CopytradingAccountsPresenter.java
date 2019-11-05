package vision.genesis.clientapp.feature.main.wallet.copytrading_accounts;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.CopyTradingAccountInfo;
import io.swagger.client.model.CopyTradingAccountsList;
import rx.Subscription;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SignalsManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2019.
 */

@InjectViewState
public class CopytradingAccountsPresenter extends MvpPresenter<CopytradingAccountsView>
{
	@Inject
	public Context context;

	@Inject
	public SignalsManager signalsManager;

	private Subscription accountsSubscription;

	private List<CopyTradingAccountInfo> accounts = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);

		getCopytradingAccounts();
	}

	@Override
	public void onDestroy() {
		if (accountsSubscription != null) {
			accountsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onShow() {
		getCopytradingAccounts();
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getCopytradingAccounts();
	}

	private void getCopytradingAccounts() {
//		if (signalsManager != null) {
//			if (accountsSubscription != null) {
//				accountsSubscription.unsubscribe();
//			}
//			accountsSubscription = signalsManager.getAccounts()
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribeOn(Schedulers.io())
//					.subscribe(this::handleGetAccountsResponse,
//							this::handleGetAccountsError);
//		}
	}

	private void handleGetAccountsResponse(CopyTradingAccountsList response) {
		accountsSubscription.unsubscribe();
		getViewState().showProgress(false);

		accounts.clear();
		accounts.addAll(response.getAccounts());

		getViewState().setAccounts(accounts);
	}

	private void handleGetAccountsError(Throwable error) {
		accountsSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}
}
