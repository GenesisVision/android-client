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
import io.swagger.client.model.ProgramFollowDetailsFull;
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

	private Subscription userSubscription;

	private Subscription detailsSubscription;

	private Subscription followDetailsSubscription;

	private UUID assetId;

	private Boolean userLoggedOn;

	private ProgramFollowDetailsFull details;

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

		if (detailsSubscription != null) {
			detailsSubscription.unsubscribe();
		}

		if (followDetailsSubscription != null) {
			followDetailsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setDetails(ProgramFollowDetailsFull details) {
		this.assetId = details.getId();
		this.details = details;
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
				model.setEntryFee(programDetails.getEntryFeeCurrent());
				model.setSuccessFee(programDetails.getSuccessFeeCurrent());
				model.setInvestmentLimit(programDetails.getAvailableInvestmentLimit());
				model.setStopOutLevel(programDetails.getStopOutLevelCurrent());
				model.setTradesDelay(ProgramUpdate.TradesDelayEnum.fromValue(programDetails.getTradesDelay().getValue()));
			}
			getViewState().showEditPublicInfoActivity(assetId, model);
		}
	}

	void onManageAccountClicked() {
		TradingAccountDetailsModel model = new TradingAccountDetailsModel(
				assetId,
				details.getPublicInfo().getTitle(),
				details.getBrokerDetails().getName(),
				details.getBrokerDetails().getLogo(),
				details.getPublicInfo().getCreationDate(),
				details.getTradingAccountInfo().getLeverageMax(),
				details.getTradingAccountInfo().getCurrency().getValue(),
				details.getProgramDetails() != null
						? details.getProgramDetails().getPersonalDetails().getMigration()
						: null);
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

	void onDepositClicked() {
		if (details == null) {
			return;
		}

		ProgramDetailsFull programDetails = details.getProgramDetails();

		if (programDetails == null || programDetails.getAvailableInvestmentBase() == 0) {
			return;
		}

		ProgramRequest request = new ProgramRequest();

		request.setProgramId(details.getId());
		request.setProgramLogo(details.getPublicInfo().getLogo());
		request.setProgramColor(details.getPublicInfo().getColor());
		request.setProgramCurrency(details.getTradingAccountInfo().getCurrency().getValue());
		request.setLevel(programDetails.getLevel());
		request.setLevelProgress(programDetails.getLevelProgress());
		request.setProgramName(details.getPublicInfo().getTitle());
		request.setManagerName(details.getOwner().getUsername());
		request.setAvailableInvestment(programDetails.getAvailableInvestmentBase());
		request.setEntryFee(programDetails.getEntryFeeCurrent());
		request.setBrokerType(details.getBrokerDetails().getType());

		getViewState().showInvestProgramActivity(request);
	}

	void onWithdrawClicked() {
		if (details == null || details.getProgramDetails() == null) {
			return;
		}

		ProgramRequest request = new ProgramRequest();

		request.setProgramId(details.getId());
		request.setProgramLogo(details.getPublicInfo().getLogo());
		request.setProgramColor(details.getPublicInfo().getColor());
		request.setProgramCurrency(details.getTradingAccountInfo().getCurrency().getValue());
		request.setLevel(details.getProgramDetails().getLevel());
		request.setLevelProgress(details.getProgramDetails().getLevelProgress());
		request.setProgramName(details.getPublicInfo().getTitle());
		request.setManagerName(details.getOwner().getUsername());
		request.setIsOwner(details.getPublicInfo().isIsOwnAsset());

		getViewState().showWithdrawProgramActivity(request);
	}

	void onCreateProgramClicked() {
		if (details != null && details.getTradingAccountInfo() != null) {
			getViewState().showCreateProgram(new CreateProgramModel(assetId,
					AssetType.FOLLOW,
					details.getBrokerDetails().getType(),
					details.getTradingAccountInfo().getBalance(),
					details.getTradingAccountInfo().getCurrency().getValue()));
		}
	}

	void onCreateFollowClicked() {
		if (details != null && details.getProgramDetails() != null) {
			getViewState().showCreateFollow(new CreateProgramModel(assetId,
					AssetType.PROGRAM,
					details.getBrokerDetails().getType(),
					details.getProgramDetails().getPersonalDetails().getInvested(),
					details.getTradingAccountInfo().getCurrency().getValue()));
		}
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

		getViewState().setDetails(details);
	}

	private void handleDetailsError(Throwable throwable) {
		detailsSubscription.unsubscribe();
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
