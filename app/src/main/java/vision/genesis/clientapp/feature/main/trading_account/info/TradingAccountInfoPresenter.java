package vision.genesis.clientapp.feature.main.trading_account.info;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.PrivateTradingAccountFull;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.TradingAccountManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

@InjectViewState
public class TradingAccountInfoPresenter extends MvpPresenter<TradingAccountInfoView>
{
	@Inject
	public AuthManager authManager;

	@Inject
	public TradingAccountManager tradingAccountManager;

	private Subscription accountDetailsSubscription;

	private UUID accountId;

	private PrivateTradingAccountFull accountDetails;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getAccountDetails();
	}

	@Override
	public void onDestroy() {
		if (accountDetailsSubscription != null) {
			accountDetailsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setDetails(PrivateTradingAccountFull accountDetails) {
		this.accountId = accountDetails.getId();
		this.accountDetails = accountDetails;
	}

	void onShow() {
		getAccountDetails();
	}


	void onManageAccountClicked() {

	}

	void onTransferClicked() {

	}

	void onCreateFollowClicked() {
	}

	void onCreateProgramClicked() {
	}

	private void getAccountDetails() {
		if (accountId != null && tradingAccountManager != null) {
			if (accountDetailsSubscription != null) {
				accountDetailsSubscription.unsubscribe();
			}
			accountDetailsSubscription = tradingAccountManager.getAccountDetails(accountId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleAccountDetailsSuccess,
							this::handleAccountDetailsError);
		}
	}

	private void handleAccountDetailsSuccess(PrivateTradingAccountFull accountDetails) {
		accountDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.accountDetails = accountDetails;

		getViewState().setAccountDetails(accountDetails);
	}

	private void handleAccountDetailsError(Throwable throwable) {
		accountDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
