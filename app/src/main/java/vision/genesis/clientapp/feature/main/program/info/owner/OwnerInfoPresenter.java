package vision.genesis.clientapp.feature.main.program.info.owner;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.CreateSignalProvider;
import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.InternalTransferRequestType;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.SignalSubscription;
import io.swagger.client.model.SignalSubscriptionItemsViewModel;
import io.swagger.client.model.TradesDelay;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.TransferFundsModel;
import vision.genesis.clientapp.model.events.OnRequestCancelledEvent;
import vision.genesis.clientapp.model.events.ShowEditSubscriptionEvent;
import vision.genesis.clientapp.model.events.ShowUnfollowTradesEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/11/2019.
 */

@InjectViewState
public class OwnerInfoPresenter extends MvpPresenter<OwnerInfoView>
{
	@Inject
	public AuthManager authManager;

	@Inject
	public ProgramsManager programsManager;

	@Inject
	public FollowsManager followsManager;

	private Subscription detailsSubscription;

	private Subscription subscriptionsSubscription;

	private UUID assetId;

	private ProgramFollowDetailsFull details;

	private List<SignalSubscription> masters = new ArrayList<>();

	private boolean isActive = false;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);
		getDetails();
	}

	@Override
	public void onDestroy() {
		if (detailsSubscription != null) {
			detailsSubscription.unsubscribe();
		}
		if (subscriptionsSubscription != null) {
			subscriptionsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setDetails(ProgramFollowDetailsFull details) {
		this.assetId = details.getId();
		this.details = details;
	}

	void onResume() {
		isActive = true;
	}

	void onPause() {
		isActive = false;
	}

	void onShow() {
		getDetails();
	}

	void onEditPublicInfoClicked() {
		if (details != null) {
			ProgramUpdate model = new ProgramUpdate();
			model.setTitle(details.getPublicInfo().getTitle());
			model.setDescription(details.getPublicInfo().getDescription());
			model.setLogo(details.getPublicInfo().getLogo());
			ProgramDetailsFull programDetails = details.getProgramDetails();
			if (programDetails != null) {
				model.setEntryFee(programDetails.getManagementFeeCurrent());
				model.setSuccessFee(programDetails.getSuccessFeeCurrent());
				model.setInvestmentLimit(programDetails.getAvailableInvestmentLimit());
				model.setStopOutLevel(programDetails.getStopOutLevelCurrent());
				model.setTradesDelay(TradesDelay.fromValue(programDetails.getTradesDelay().getValue()));
			}
			getViewState().showEditPublicInfoActivity(assetId, model);
		}
	}

	void onManageAccountClicked() {
		TradingAccountDetailsModel model = new TradingAccountDetailsModel(
				assetId,
				details.getTradingAccountInfo().getId(),
				details.getPublicInfo().getTitle(),
				details.getBrokerDetails().getName(),
				details.getBrokerDetails().getLogoUrl(),
				details.getPublicInfo().getCreationDate(),
				details.getTradingAccountInfo().getLeverageMax(),
				details.getTradingAccountInfo().getCurrency().getValue(),
				details.getProgramDetails() != null
						? details.getProgramDetails().getPersonalDetails().getMigration()
						: null,
				details.getProgramDetails() != null);
		getViewState().showManageAccountActivity(model);
	}

	void onManageProgramClicked() {
		getViewState().showManageProgramActivity(details);
	}

	void onManageFollowClicked() {
		FollowDetailsFull followDetails = details.getFollowDetails();
		if (followDetails != null) {
			CreateSignalProvider model = new CreateSignalProvider();
			model.setId(assetId);
			model.setVolumeFee(followDetails.getSignalSettings().getSignalVolumeFee());
			model.setSuccessFee(followDetails.getSignalSettings().getSignalSuccessFee());
			getViewState().showEditFollowSettingsActivity(model);
		}
	}


	void onStatusClicked() {
		if (details != null && details.getProgramDetails() != null && details.getProgramDetails().getPersonalDetails() != null) {
			if (details.getProgramDetails().getPersonalDetails().getStatus().equals(AssetInvestmentStatus.INVESTING) ||
					(details.getProgramDetails().getPersonalDetails().getStatus().equals(AssetInvestmentStatus.WITHDRAWING))) {
				getViewState().showRequestsBottomSheet();
			}
		}
	}

	void onDepositProgramClicked() {
		if (details == null) {
			return;
		}

		ProgramDetailsFull programDetails = details.getProgramDetails();

		if (programDetails == null || programDetails.getAvailableInvestmentBase() == 0) {
			return;
		}

		ProgramRequest request = new ProgramRequest();

		request.setProgramId(details.getId());
		request.setProgramLogo(details.getPublicInfo().getLogoUrl());
		request.setProgramColor(details.getPublicInfo().getColor());
		request.setProgramCurrency(details.getTradingAccountInfo().getCurrency().getValue());
		request.setLevel(programDetails.getLevel());
		request.setLevelProgress(programDetails.getLevelProgress());
		request.setProgramName(details.getPublicInfo().getTitle());
		request.setManagerName(details.getOwner().getUsername());
		request.setAvailableInvestment(programDetails.getAvailableInvestmentBase());
		request.setManagementFee(programDetails.getManagementFeeCurrent());
		request.setBrokerType(details.getBrokerDetails().getType());

		getViewState().showInvestProgramActivity(request);
	}

	void onWithdrawProgramClicked() {
		if (details == null || details.getProgramDetails() == null) {
			return;
		}

		ProgramRequest request = new ProgramRequest();

		request.setProgramId(details.getId());
		request.setProgramLogo(details.getPublicInfo().getLogoUrl());
		request.setProgramColor(details.getPublicInfo().getColor());
		request.setProgramCurrency(details.getTradingAccountInfo().getCurrency().getValue());
		request.setLevel(details.getProgramDetails().getLevel());
		request.setLevelProgress(details.getProgramDetails().getLevelProgress());
		request.setProgramName(details.getPublicInfo().getTitle());
		request.setManagerName(details.getOwner().getUsername());
		request.setIsOwner(details.getPublicInfo().isIsOwnAsset());

		getViewState().showWithdrawProgramActivity(request);
	}

	void onWithdrawClicked() {
		TransferFundsModel model = TransferFundsModel.createFromFollow(details);
		model.setAssetType(InternalTransferRequestType.PUBLICTRADINGACCOUNT);
		model.setTransferDirection(TransferFundsModel.TransferDirection.WITHDRAW);
		getViewState().showTransferFundsActivity(model);
	}

	void onAddFundsClicked() {
		TransferFundsModel model = TransferFundsModel.createFromFollow(details);
		model.setAssetType(InternalTransferRequestType.PUBLICTRADINGACCOUNT);
		model.setTransferDirection(TransferFundsModel.TransferDirection.DEPOSIT);
		getViewState().showTransferFundsActivity(model);
	}

	void onCreateProgramClicked() {
		if (details != null && details.getTradingAccountInfo() != null) {
			getViewState().showCreateProgram(new CreateProgramModel(assetId,
					AssetType.FOLLOW,
					details.getBrokerDetails().getType(),
					details.getTradingAccountInfo().getBalance(),
					details.getTradingAccountInfo().getCurrency().getValue(),
					false));
		}
	}

	void onCreateFollowClicked() {
		if (details != null && details.getProgramDetails() != null) {
			getViewState().showCreateFollow(new CreateProgramModel(assetId,
					AssetType.PROGRAM,
					details.getBrokerDetails().getType(),
					details.getProgramDetails().getPersonalDetails().getInvested(),
					details.getTradingAccountInfo().getCurrency().getValue(),
					false));
		}
	}

	void onSubscriptionsDetailsClicked() {
		TradingAccountDetailsModel model = new TradingAccountDetailsModel(
				details.getTradingAccountInfo().getId(),
				details.getPublicInfo().getTitle(),
				details.getBrokerDetails().getLogoUrl());
		getViewState().showCopytradingDetailsActivity(model);

	}

	private void getDetails() {
		if (assetId != null && programsManager != null) {
			if (detailsSubscription != null) {
				detailsSubscription.unsubscribe();
			}
			detailsSubscription = programsManager.getProgramDetails(assetId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleDetailsSuccess,
							this::handleDetailsError);
		}
	}

	private void handleDetailsSuccess(ProgramFollowDetailsFull details) {
		detailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.details = details;

		getSubscriptions();

		getViewState().setDetails(details);
	}

	private void handleDetailsError(Throwable throwable) {
		detailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void getSubscriptions() {
		if (details != null && followsManager != null) {
			if (subscriptionsSubscription != null) {
				subscriptionsSubscription.unsubscribe();
			}
			subscriptionsSubscription = followsManager.getMastersForMyAccount(details.getTradingAccountInfo().getId(), false)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleSubscriptionsSuccess,
							this::handleSubscriptionsError);
		}
	}

	private void handleSubscriptionsSuccess(SignalSubscriptionItemsViewModel response) {
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
	public void onEventMainThread(ShowUnfollowTradesEvent event) {
		if (isActive) {
			getViewState().showUnfollowTradesActivity(event.getFollowId(), event.getTradingAccountId(), event.getFollowName(), event.isExternal());
		}
	}

	@Subscribe
	public void onEventMainThread(ShowEditSubscriptionEvent event) {
		if (isActive) {
			getViewState().showEditSubscriptionActivity(event.getModel(), event.getFollowId(), event.getTradingAccountId(), event.getExternal());
		}
	}

	@Subscribe
	public void onEventMainThread(OnRequestCancelledEvent event) {
		getDetails();
	}
}
