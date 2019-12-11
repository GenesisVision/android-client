package vision.genesis.clientapp.feature.main.program.info.owner;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.CreateSignalProvider;
import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramUpdate;
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
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
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

	private Subscription userSubscription;

	private Subscription programDetailsSubscription;

	private Subscription followDetailsSubscription;

	private UUID assetId;

	private Boolean userLoggedOn;

	private ProgramDetailsFull programDetails;

	private FollowDetailsFull followDetails;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToUser();
		getViewState().showProgress(true);
		getDetails();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}

		if (programDetailsSubscription != null) {
			programDetailsSubscription.unsubscribe();
		}

		if (followDetailsSubscription != null) {
			followDetailsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setDetails(ProgramDetailsFull programDetails, FollowDetailsFull followDetails) {
		this.assetId = programDetails != null ? programDetails.getId() : followDetails.getId();
		this.programDetails = programDetails;
		this.followDetails = followDetails;
	}

	void onShow() {
		getDetails();
	}

	void onEditPublicInfoClicked() {
		ProgramUpdate model = new ProgramUpdate();
		model.setTitle(programDetails != null ? programDetails.getTitle() : followDetails.getTitle());
		model.setDescription(programDetails != null ? programDetails.getDescription() : followDetails.getDescription());
		model.setLogo(programDetails != null ? programDetails.getLogo() : followDetails.getLogo());
		if (programDetails != null) {
			model.setEntryFee(programDetails.getEntryFeeCurrent());
			model.setSuccessFee(programDetails.getSuccessFeeCurrent());
			model.setInvestmentLimit(programDetails.getAvailableInvestmentLimit());
			model.setStopOutLevel(programDetails.getStopOutLevelCurrent());
			model.setTradesDelay(ProgramUpdate.TradesDelayEnum.fromValue(programDetails.getTradesDelay().getValue()));
		}
		getViewState().showEditPublicInfoActivity(assetId, model);
	}

	void onManageAccountClicked() {
		TradingAccountDetailsModel model = new TradingAccountDetailsModel(
				assetId,
				programDetails.getTitle(),
				programDetails.getBrokerDetails().getName(),
				programDetails.getBrokerDetails().getLogo(),
				programDetails.getCreationDate(),
				programDetails.getLeverageMax(),
				programDetails.getCurrency().getValue(),
				programDetails.getPersonalDetails().getMigration()
		);
		getViewState().showManageAccountActivity(model);
	}

	void onManageProgramClicked() {
		getViewState().showManageProgramActivity(programDetails);
	}

	void onManageFollowClicked() {
		if (followDetails != null) {
			CreateSignalProvider model = new CreateSignalProvider();
			model.setAssetId(assetId);
			model.setVolumeFee(followDetails.getSignalSettings().getSignalVolumeFee());
			model.setSuccessFee(followDetails.getSignalSettings().getSignalSuccessFee());
			getViewState().showEditFollowSettingsActivity(model);
		}
	}


	void onStatusClicked() {
		if (programDetails != null && programDetails.getPersonalDetails() != null) {
			if (programDetails.getPersonalDetails().getStatus().equals(AssetInvestmentStatus.INVESTING) ||
					(programDetails.getPersonalDetails().getStatus().equals(AssetInvestmentStatus.WITHDRAWING))) {
				getViewState().showRequestsBottomSheet();
			}
		}
	}

	void onDepositClicked() {
		if (!userLoggedOn) {
			getViewState().showLoginActivity();
			return;
		}

		if (programDetails == null || programDetails.getAvailableInvestmentBase() == 0) {
			return;
		}

		ProgramRequest request = new ProgramRequest();

		request.setProgramId(programDetails.getId());
		request.setProgramLogo(programDetails.getLogo());
		request.setProgramColor(programDetails.getColor());
		request.setProgramCurrency(programDetails.getCurrency().getValue());
		request.setLevel(programDetails.getLevel());
		request.setLevelProgress(programDetails.getLevelProgress());
		request.setProgramName(programDetails.getTitle());
		request.setManagerName(programDetails.getOwner().getUsername());
		request.setAvailableInvestment(programDetails.getAvailableInvestmentBase());
		request.setEntryFee(programDetails.getEntryFeeCurrent());
		request.setBrokerType(programDetails.getBrokerDetails().getType());

		getViewState().showInvestProgramActivity(request);
	}

	void onWithdrawClicked() {
		if (!userLoggedOn) {
			getViewState().showLoginActivity();
			return;
		}

		if (programDetails == null) {
			return;
		}

		ProgramRequest request = new ProgramRequest();

		request.setProgramId(programDetails.getId());
		request.setProgramLogo(programDetails.getLogo());
		request.setProgramColor(programDetails.getColor());
		request.setProgramCurrency(programDetails.getCurrency().getValue());
		request.setLevel(programDetails.getLevel());
		request.setLevelProgress(programDetails.getLevelProgress());
		request.setProgramName(programDetails.getTitle());
		request.setManagerName(programDetails.getOwner().getUsername());
		request.setIsOwner(programDetails.getPersonalDetails().isIsOwnAsset());

		getViewState().showWithdrawProgramActivity(request);
	}

	void onCreateProgramClicked() {
		if (followDetails != null) {
			getViewState().showCreateProgram(new CreateProgramModel(assetId,
					AssetType.FOLLOW,
					followDetails.getBrokerDetails().getType(),
//			followDetails.getBalance(),
					0.0,
					followDetails.getCurrency().getValue()));
		}
	}

	void onCreateFollowClicked() {
		if (programDetails != null) {
			getViewState().showCreateFollow(new CreateProgramModel(assetId,
					AssetType.PROGRAM,
					programDetails.getBrokerDetails().getType(),
					programDetails.getPersonalDetails().getInvested(),
					programDetails.getCurrency().getValue()));
		}
	}

	private void getDetails() {
		if (programDetails != null) {
			getProgramDetails();
		}
		if (followDetails != null) {
			getFollowDetails();
		}
	}

	private void getProgramDetails() {
		if (assetId != null && programsManager != null) {
			if (programDetailsSubscription != null) {
				programDetailsSubscription.unsubscribe();
			}
			programDetailsSubscription = programsManager.getProgramDetails(assetId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleProgramDetailsSuccess,
							this::handleProgramDetailsError);
		}
	}

	private void handleProgramDetailsSuccess(ProgramDetailsFull programDetails) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.programDetails = programDetails;

		getViewState().setProgramDetails(programDetails);
	}

	private void handleProgramDetailsError(Throwable throwable) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void getFollowDetails() {
		if (assetId != null && followsManager != null) {
			if (followDetailsSubscription != null) {
				followDetailsSubscription.unsubscribe();
			}
			followDetailsSubscription = followsManager.getFollowDetails(assetId.toString())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleFollowDetailsSuccess,
							this::handleFollowDetailsError);
		}
	}

	private void handleFollowDetailsSuccess(FollowDetailsFull followDetails) {
		followDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.followDetails = followDetails;

		getViewState().setFollowDetails(followDetails);
	}

	private void handleFollowDetailsError(Throwable throwable) {
		followDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated, this::handleUserError);
	}

	private void userUpdated(User user) {
		if (user == null) {
			userLoggedOff();
		}
		else {
			userLoggedOn();
		}
	}

	private void userLoggedOn() {
		userLoggedOn = true;
		getViewState().showInvestWithdrawButtons();
	}

	private void userLoggedOff() {
		userLoggedOn = false;
		getViewState().showInvestWithdrawButtons();
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}
}
