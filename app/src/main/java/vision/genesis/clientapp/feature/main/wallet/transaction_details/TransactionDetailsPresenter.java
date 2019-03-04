package vision.genesis.clientapp.feature.main.wallet.transaction_details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.TransactionDetails;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */

@InjectViewState
public class TransactionDetailsPresenter extends MvpPresenter<TransactionDetailsView>
{
	@Inject
	public WalletManager walletManager;

	private UUID transactionId;

	private Subscription detailsSubscription;

	private TransactionDetails transactionDetails;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);

		getTransactionDetails();
	}

	@Override
	public void onDestroy() {
		if (detailsSubscription != null)
			detailsSubscription.unsubscribe();

		super.onDestroy();
	}

	void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
		getTransactionDetails();
	}

	private void getTransactionDetails() {
		if (walletManager != null && transactionId != null) {
			if (detailsSubscription != null)
				detailsSubscription.unsubscribe();
			detailsSubscription = walletManager.getTransaction(transactionId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWalletUpdateSuccess,
							this::handleWalletUpdateError);
		}
	}

	private void handleWalletUpdateSuccess(TransactionDetails response) {
		getViewState().showProgress(false);

		transactionDetails = response;

		getViewState().setDetails(transactionDetails);
	}

	private void handleWalletUpdateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}
}
