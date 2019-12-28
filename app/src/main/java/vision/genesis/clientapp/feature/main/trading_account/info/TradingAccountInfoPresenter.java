package vision.genesis.clientapp.feature.main.trading_account.info;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetType;
import io.swagger.client.model.InternalTransferRequestType;
import io.swagger.client.model.ItemsViewModelSignalSubscription;
import io.swagger.client.model.PrivateTradingAccountFull;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.SignalSubscription;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.TradingAccountManager;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.TransferFundsModel;
import vision.genesis.clientapp.model.events.OnProfilePublicInfoFilledEvent;
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

	@Inject
	public FollowsManager followsManager;

	@Inject
	public ProfileManager profileManager;

	private Subscription profileSubscription;

	private Subscription accountDetailsSubscription;

	private Subscription subscriptionsSubscription;

	private UUID accountId;

	private PrivateTradingAccountFull accountDetails;

	private List<SignalSubscription> masters = new ArrayList<>();

	private boolean isUsernameFilled = false;

	private boolean isWaitingFillProfileToCreateProgram = false;

	private boolean isWaitingFillProfileToCreateFollow = false;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getProfile();
		getAccountDetails();
		getSubscriptions();
	}

	@Override
	public void onDestroy() {
		if (profileSubscription != null) {
			profileSubscription.unsubscribe();
		}
		if (accountDetailsSubscription != null) {
			accountDetailsSubscription.unsubscribe();
		}
		if (subscriptionsSubscription != null) {
			subscriptionsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setDetails(PrivateTradingAccountFull accountDetails) {
		this.accountId = accountDetails.getId();
		this.accountDetails = accountDetails;
	}

	void onShow() {
		getAccountDetails();
		getSubscriptions();
	}

	void onManageAccountClicked() {
//		TradingAccountDetailsModel model = new TradingAccountDetailsModel(
//				accountId,
//				accountDetails.getTitle(),
//				accountDetails.getBrokerDetails().getLogo(),
//				accountDetails.getCreationDate(),
//				accountDetails.getLeverage(),
//				accountDetails.getCurrency(),
//				accountDetails.getP
//
//		);
//		getViewState().showManageAccountActivity(model);
	}

	void onWithdrawClicked() {
		TransferFundsModel model = TransferFundsModel.createFrom(accountDetails);
		model.setAssetType(InternalTransferRequestType.PRIVATETRADINGACCOUNT);
		model.setTransferDirection(TransferFundsModel.TransferDirection.WITHDRAW);
		getViewState().showTransferFundsActivity(model);
	}

	void onAddFundsClicked() {
		TransferFundsModel model = TransferFundsModel.createFrom(accountDetails);
		model.setAssetType(InternalTransferRequestType.PRIVATETRADINGACCOUNT);
		model.setTransferDirection(TransferFundsModel.TransferDirection.DEPOSIT);
		getViewState().showTransferFundsActivity(model);
	}

	void onCreateProgramClicked() {
		if (isUsernameFilled) {
			showCreateProgram();
		}
		else {
			isWaitingFillProfileToCreateProgram = true;
			getViewState().showProfilePublicInfoActivity();
		}
	}

	void onCreateFollowClicked() {
		if (isUsernameFilled) {
			showCreateFollow();
		}
		else {
			isWaitingFillProfileToCreateFollow = true;
			getViewState().showProfilePublicInfoActivity();
		}
	}

	void onSubscriptionsDetailsClicked() {
		TradingAccountDetailsModel model = new TradingAccountDetailsModel(
				accountDetails.getId(),
				accountDetails.getPublicInfo().getTitle(),
				accountDetails.getBrokerDetails().getLogo());
		getViewState().showCopytradingDetailsActivity(model);

	}

	private void showCreateProgram() {
		getViewState().showCreateProgram(new CreateProgramModel(accountDetails.getId(),
				AssetType.NONE,
				accountDetails.getBrokerDetails().getType(),
				accountDetails.getTradingAccountInfo().getBalance(),
				accountDetails.getTradingAccountInfo().getCurrency().getValue(),
				false));
	}

	private void showCreateFollow() {
		getViewState().showCreateFollow(new CreateProgramModel(accountDetails.getId(),
				AssetType.NONE,
				accountDetails.getBrokerDetails().getType(),
				accountDetails.getTradingAccountInfo().getBalance(),
				null,
				accountDetails.getTradingAccountInfo().isIsExternal()));
	}

	private void getProfile() {
		profileSubscription = profileManager.getProfileFull(true)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProfileSuccess);

	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		this.isUsernameFilled = profile.getUserName() != null && !profile.getUserName().isEmpty();
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

	private void getSubscriptions() {
		if (accountId != null && followsManager != null) {
			if (subscriptionsSubscription != null) {
				subscriptionsSubscription.unsubscribe();
			}
			subscriptionsSubscription = followsManager.getMastersForMyAccount(accountId, false)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleSubscriptionsSuccess,
							this::handleSubscriptionsError);
		}
	}

	private void handleSubscriptionsSuccess(ItemsViewModelSignalSubscription response) {
		subscriptionsSubscription.unsubscribe();

		this.masters = response.getItems();

		if (!masters.isEmpty()) {
			getViewState().showCopytrading(masters);
		}
	}

	private void handleSubscriptionsError(Throwable throwable) {
		subscriptionsSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	@Subscribe
	public void onEventMainThread(OnProfilePublicInfoFilledEvent event) {
		if (isWaitingFillProfileToCreateProgram) {
			isWaitingFillProfileToCreateProgram = false;
			showCreateProgram();
		}
		else if (isWaitingFillProfileToCreateFollow) {
			isWaitingFillProfileToCreateFollow = false;
			showCreateFollow();
		}
	}
}
