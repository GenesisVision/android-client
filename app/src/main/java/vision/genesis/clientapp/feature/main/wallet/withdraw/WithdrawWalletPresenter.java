package vision.genesis.clientapp.feature.main.wallet.withdraw;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.ui.AmountEditText;
import vision.genesis.clientapp.utils.EthAddressValidator;

/**
 * GenesisVision
 * Created by Vitaly on 2/27/18.
 */

@InjectViewState
public class WithdrawWalletPresenter extends MvpPresenter<WithdrawWalletView> implements AmountEditText.AmountChangeListener
{
	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	private Subscription getWalletAddressSubscription;

	private double amount = 0;

	private String walletAddress = "";

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (getWalletAddressSubscription != null)
			getWalletAddressSubscription.unsubscribe();

		super.onDestroy();
	}

	void onBackClicked() {
		getViewState().finishActivity();
	}

	void onWalletAddressChanged(String address) {
		this.walletAddress = address;
		checkWithdrawButtonEnabled();
	}

	void onWithdrawButtonClicked() {
	}

	private void checkWithdrawButtonEnabled() {
		getViewState().setWithdrawButtonEnabled(amount > 0 && EthAddressValidator.isValid(walletAddress));
	}

	private void getWalletAddress() {
		getViewState().showProgress(true);
//		getWalletAddressSubscription = walletManager.getWalletAddress()
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribeOn(Schedulers.io())
//				.subscribe(this::handleGetWalletAddressSuccess,
//						this::handleGetWalletAddressError);
	}

//	private void handleGetWalletAddressSuccess(WalletAddressViewModel response) {
//		getViewState().showProgress(false);
//
//	}

	private void handleGetWalletAddressError(Throwable throwable) {
		getViewState().finishActivity();
	}

	@Override
	public void onAmountChanged(double amount) {
		this.amount = amount;
		checkWithdrawButtonEnabled();
	}
}
