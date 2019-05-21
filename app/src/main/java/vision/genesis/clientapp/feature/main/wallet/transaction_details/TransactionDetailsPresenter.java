package vision.genesis.clientapp.feature.main.wallet.transaction_details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.TransactionDetails;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.views.ButtonsView;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.events.OnTransactionCanceledEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */

@InjectViewState
public class TransactionDetailsPresenter extends MvpPresenter<TransactionDetailsView> implements ButtonsView.ButtonsActionListener
{
	@Inject
	public WalletManager walletManager;

	private UUID transactionId;

	private Subscription detailsSubscription;

	private Subscription cancelSubscription;

	private Subscription resendEmailSubscription;

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
		if (cancelSubscription != null)
			cancelSubscription.unsubscribe();
		if (resendEmailSubscription != null)
			resendEmailSubscription.unsubscribe();

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

	private void cancelWithdrawRequest() {
		if (walletManager != null && transactionId != null) {
			if (cancelSubscription != null)
				cancelSubscription.unsubscribe();
			cancelSubscription = walletManager.cancelWithdrawRequest(transactionId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleCancelSuccess,
							this::handleCancelError);
		}
	}

	private void handleCancelSuccess(Void response) {
		cancelSubscription.unsubscribe();
		EventBus.getDefault().post(new OnTransactionCanceledEvent(transactionId));
		getViewState().finishActivity();
	}

	private void handleCancelError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void resendEmail() {
		if (walletManager != null && transactionId != null) {
			if (resendEmailSubscription != null)
				resendEmailSubscription.unsubscribe();
			resendEmailSubscription = walletManager.resendConfirmationEmail(transactionId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleResendEmailSuccess,
							this::handleResendEmailError);
		}
	}

	private void handleResendEmailSuccess(Void response) {
		resendEmailSubscription.unsubscribe();

		getViewState().setEmailResent();
	}

	private void handleResendEmailError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onCancelClicked() {
		cancelWithdrawRequest();
	}

	@Override
	public void onResendEmailClicked() {
		resendEmail();
	}
}
