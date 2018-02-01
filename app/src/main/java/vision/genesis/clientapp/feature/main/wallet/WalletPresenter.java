package vision.genesis.clientapp.feature.main.wallet;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.WalletManager;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class WalletPresenter extends MvpPresenter<WalletView>
{
	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	private Router localRouter;

	private Subscription balanceSubscription;

	public WalletPresenter(Router router) {
		this.localRouter = router;
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (balanceSubscription != null)
			balanceSubscription.unsubscribe();
	}

	void onResume() {
		updateBalance();
		getTransactions();
	}

	void onSwipeRefresh() {
		getTransactions();
	}

	void onLastListItemVisible() {
//		getTransactions(false);
	}

	private void updateBalance() {
		getViewState().showBalanceProgress();
		balanceSubscription = walletManager.getBalance()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleBalanceUpdateResponse,
						this::handleBalanceUpdateError);
	}

	private void handleBalanceUpdateResponse(Double balance) {
		getViewState().hideBalanceProgress();
		getViewState().setBalance(balance);
	}

	private void handleBalanceUpdateError(Throwable error) {
		getViewState().hideBalanceProgress();
	}

	private void getTransactions() {
		getViewState().showBalanceProgress();
		balanceSubscription = walletManager.getBalance()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetTransactionsResponse,
						this::handleGetTransactionsError);
	}

	private void handleGetTransactionsResponse(Double balance) {
		getViewState().hideTransactionsProgress();
		getViewState().setBalance(balance);
	}

	private void handleGetTransactionsError(Throwable error) {
		getViewState().hideTransactionsProgress();
	}
}
