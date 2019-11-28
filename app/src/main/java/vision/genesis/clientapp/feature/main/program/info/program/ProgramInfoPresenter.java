package vision.genesis.clientapp.feature.main.program.info.program;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetInvestmentStatus;
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
import vision.genesis.clientapp.model.events.OnProgramReinvestChangedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

@InjectViewState
public class ProgramInfoPresenter extends MvpPresenter<ProgramInfoView>
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

	void setProgramDetails(ProgramDetailsFull programDetails) {
		this.programId = programDetails.getId();
		this.programDetails = programDetails;
	}

	void onShow() {
		getProgramDetails();
	}

	void onStatusClicked() {
		if (programDetails != null && programDetails.getPersonalDetails() != null) {
			if (programDetails.getPersonalDetails().getStatus().equals(AssetInvestmentStatus.INVESTING) ||
					(programDetails.getPersonalDetails().getStatus().equals(AssetInvestmentStatus.WITHDRAWING))) {
				getViewState().showRequestsBottomSheet();
			}
		}
	}

	void onReinvestClicked() {
		setReinvest(!programDetails.getPersonalDetails().isIsReinvest());
	}

	void onInvestClicked() {
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

	private void getProgramDetails() {
		if (programId != null && programsManager != null) {
			if (programDetailsSubscription != null) {
				programDetailsSubscription.unsubscribe();
			}
			programDetailsSubscription = programsManager.getProgramDetails(programId)
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
	}

	private void setReinvest(boolean reinvest) {
		if (programId != null && programsManager != null) {
			if (reinvestSubscription != null) {
				reinvestSubscription.unsubscribe();
			}
			reinvestSubscription = programsManager.setReinvest(programId, reinvest)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleReinvestSuccess(reinvest),
							this::handleReinvestError);
		}
	}

	private void handleReinvestSuccess(Boolean reinvest) {
		reinvestSubscription.unsubscribe();
		programDetails.getPersonalDetails().setIsReinvest(reinvest);

		EventBus.getDefault().post(new OnProgramReinvestChangedEvent(programId, reinvest));
	}

	private void handleReinvestError(Throwable throwable) {
		reinvestSubscription.unsubscribe();
		getViewState().setReinvest(programDetails.getPersonalDetails().isIsReinvest());

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
