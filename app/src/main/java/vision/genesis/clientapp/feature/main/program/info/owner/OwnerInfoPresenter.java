package vision.genesis.clientapp.feature.main.program.info.owner;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.AttachToSignalProviderInfo;
import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.ProgramDetailsFull;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.User;

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

	private Subscription reinvestSubscription;

	private UUID programId;

	private Boolean userLoggedOn;

	private ProgramDetailsFull programDetails;

	private FollowDetailsFull followDetails;

	private AttachToSignalProviderInfo signalsInfo;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToUser();
		getViewState().showProgress(true);
		getProgramDetails();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}

		if (programDetailsSubscription != null) {
			programDetailsSubscription.unsubscribe();
		}

		if (reinvestSubscription != null) {
			reinvestSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setDetails(ProgramDetailsFull programDetails, FollowDetailsFull followDetails) {
		this.programId = programDetails != null ? programDetails.getId() : followDetails.getId();
		this.programDetails = programDetails;
		this.followDetails = followDetails;
	}

	void onShow() {
		getProgramDetails();
	}

	void onEditPublicInfoClicked() {

	}

	void onManageAccountClicked() {

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

		getViewState().showWithdrawProgramActivity(request);
	}

	void onManageProgramClicked() {

	}

	void onManageFollowClicked() {

	}

	void onCreateFollowClicked() {
	}

	void onCreateProgramClicked() {
	}

	private void getProgramDetails() {
		if (programId != null && programsManager != null) {
			if (programDetailsSubscription != null) {
				programDetailsSubscription.unsubscribe();
			}
			programDetailsSubscription = programsManager.getProgramDetails(programId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleInvestmentProgramDetailsSuccess,
							this::handleInvestmentProgramDetailsError);
		}
	}

	private void handleInvestmentProgramDetailsSuccess(ProgramDetailsFull programDetails) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.programDetails = programDetails;

		getViewState().setProgramDetails(programDetails);

//		if (programDetails.isIsSignalProgram()) {
//			getSignalsInfo();
//		}
	}

	private void handleInvestmentProgramDetailsError(Throwable throwable) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
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
