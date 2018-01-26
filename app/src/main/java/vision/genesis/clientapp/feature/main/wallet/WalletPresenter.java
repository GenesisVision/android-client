package vision.genesis.clientapp.feature.main.wallet;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.ProfileManager;

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
	public ProfileManager profileManager;

	private Subscription balanceSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		updateBalance();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (balanceSubscription != null)
			balanceSubscription.unsubscribe();
	}

	void onResume() {
		updateBalance();
	}

	private void updateBalance() {
		getViewState().showBalanceProgress();
		balanceSubscription = profileManager.getBalance()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
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
}
