package vision.genesis.clientapp.feature.main.program.info;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.AttachToSignalProviderInfo;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.SignalSubscription;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SignalsManager;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
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
	public SignalsManager signalsManager;

	private Subscription userSubscription;

	private Subscription programDetailsSubscription;

	private Subscription signalsInfoSubscription;

	private Subscription reinvestSubscription;

	private UUID programId;

	private Boolean userLoggedOn;

	private ProgramDetailsFull programDetails;

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

		if (signalsInfoSubscription != null) {
			signalsInfoSubscription.unsubscribe();
		}

		if (reinvestSubscription != null) {
			reinvestSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setProgramId(UUID programId) {
		this.programId = programId;
		getProgramDetails();
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

		if (programDetails == null || programDetails.getAvailableInvestment() == 0) {
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
		request.setManagerName(programDetails.getManager().getUsername());

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
		request.setManagerName(programDetails.getManager().getUsername());

		getViewState().showWithdrawProgramActivity(request);
	}

	void onShowSubscriptionSettingsClicked(boolean isEdit) {
		if (!userLoggedOn) {
			getViewState().showLoginActivity();
			return;
		}

		if (signalsInfo != null) {
			SubscriptionSettingsModel model = new SubscriptionSettingsModel();

			SignalSubscription signalSubscription = programDetails.getPersonalDetails().getSignalSubscription();
			try {
				if (signalSubscription.isHasActiveSubscription()) {
					if (signalSubscription.getMode() != null) {
						model.setMode(signalSubscription.getMode().getValue());
					}
					if (signalSubscription.getPercent() != null) {
						model.setPercent(signalSubscription.getPercent());
					}
					if (signalSubscription.getOpenTolerancePercent() != null) {
						model.setOpenTolerancePercent(signalSubscription.getOpenTolerancePercent());
					}
					if (signalSubscription.getFixedVolume() != null) {
						model.setFixedVolume(signalSubscription.getFixedVolume());
					}
					if (signalSubscription.getFixedCurrency() != null) {
						model.setFixedCurrency(signalSubscription.getFixedCurrency().getValue());
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			model.setProgramName(programDetails.getTitle());
			model.setProgramId(programDetails.getId());
			model.setMinDepositCurrency(signalsInfo.getMinDepositCurrency().getValue());
			model.setMinDeposit(signalsInfo.getMinDeposit());

			if (!signalsInfo.isHasSignalAccount()) {
				getViewState().showCreateCopytradingAccountActivity(model);
			}
			else {
				getViewState().showSubscriptionSettings(model, isEdit);
			}
		}
	}

	void onUnfollowTradesClicked() {
		getViewState().showUnfollowTradesActivity(programId, programDetails.getTitle());
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

		if (programDetails.isIsSignalProgram()) {
			getSignalsInfo();
		}
	}

	private void handleInvestmentProgramDetailsError(Throwable throwable) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void getSignalsInfo() {
		if (programId != null && signalsManager != null) {
			signalsInfoSubscription = signalsManager.getSignalsInfo(programId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleSignalsInfoResponse,
							this::handleSignalsInfoError);
		}
	}

	private void handleSignalsInfoResponse(AttachToSignalProviderInfo response) {
		signalsInfoSubscription.unsubscribe();
		getViewState().showSignalsProgress(false);

		this.signalsInfo = response;
	}

	private void handleSignalsInfoError(Throwable throwable) {
		signalsInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
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
		getViewState().showSignalsProgress(false);
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}
}
