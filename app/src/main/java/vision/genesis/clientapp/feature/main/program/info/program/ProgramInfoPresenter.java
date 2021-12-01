package vision.genesis.clientapp.feature.main.program.info.program;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.NotificationSettingConditionType;
import io.swagger.client.model.NotificationType;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramType;
import io.swagger.client.model.ProgramWithdrawInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.managers.NotificationsManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.OnProgramReinvestChangedEvent;
import vision.genesis.clientapp.model.events.OnRequestCancelledEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

@InjectViewState
public class ProgramInfoPresenter extends MvpPresenter<ProgramInfoView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public NotificationsManager notificationsManager;

	@Inject
	public ProgramsManager programsManager;

	@Inject
	public FollowsManager followsManager;

	private Subscription userSubscription;

	private Subscription programDetailsSubscription;

	private Subscription withdrawInfoSubscription;

	private Subscription reinvestSubscription;

	private Subscription ignoreSoSubscription;

	private Subscription createNotificationSubscription;

	private UUID programId;

	private Boolean userLoggedOn;

	private ProgramFollowDetailsFull details;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

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
		if (withdrawInfoSubscription != null) {
			withdrawInfoSubscription.unsubscribe();
		}
		if (reinvestSubscription != null) {
			reinvestSubscription.unsubscribe();
		}
		if (ignoreSoSubscription != null) {
			ignoreSoSubscription.unsubscribe();
		}
		if (createNotificationSubscription != null) {
			createNotificationSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setDetails(ProgramFollowDetailsFull details) {
		this.programId = details.getId();
		this.details = details;
	}

	void onShow() {
		getProgramDetails();
	}

	void onStatusClicked() {
		if (details != null && details.getProgramDetails().getPersonalDetails() != null) {
			if (details.getProgramDetails().getPersonalDetails().getStatus().equals(AssetInvestmentStatus.INVESTING) ||
					(details.getProgramDetails().getPersonalDetails().getStatus().equals(AssetInvestmentStatus.WITHDRAWING))) {
				getViewState().showRequestsBottomSheet();
			}
		}
	}

	void onReinvestClicked() {
		setReinvest(!details.getProgramDetails().getPersonalDetails().isIsReinvest());
	}

	void onIgnoreSoClicked() {
		setIgnoreSo(!details.getProgramDetails().getPersonalDetails().isIsAutoJoin());
	}

	void onNotifyClicked() {
		if (programId != null && notificationsManager != null) {
			if (createNotificationSubscription != null) {
				createNotificationSubscription.unsubscribe();
			}
			createNotificationSubscription = notificationsManager.addNotificationSetting(programId, null,
					NotificationType.PROGRAMCONDITION.getValue(), NotificationSettingConditionType.AVAILABLETOINVEST.getValue(), 0.1)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleCreateNotificationSuccess,
							this::handleCreateCustomNotificationError);
		}
	}

	private void handleCreateNotificationSuccess(UUID response) {
		createNotificationSubscription.unsubscribe();
	}

	private void handleCreateCustomNotificationError(Throwable throwable) {
		createNotificationSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	void onInvestClicked() {
		if (!userLoggedOn) {
			getViewState().showLoginActivity();
			return;
		}

		if (details == null || details.getProgramDetails().getAvailableInvestmentBase() == 0) {
			return;
		}

		ProgramRequest request = new ProgramRequest();

		request.setProgramId(details.getId());
		request.setProgramLogo(details.getPublicInfo().getLogoUrl());
		request.setProgramColor(details.getPublicInfo().getColor());
		request.setProgramName(details.getPublicInfo().getTitle());
		request.setProgramCurrency(details.getTradingAccountInfo().getCurrency().getValue());
		request.setManagerName(details.getOwner().getUsername());
		request.setLevel(details.getProgramDetails().getLevel());
		request.setLevelProgress(details.getProgramDetails().getLevelProgress());
		request.setAvailableInvestment(details.getProgramDetails().getAvailableInvestmentBase());
		request.setManagementFee(details.getProgramDetails().getPersonalDetails() != null && details.getProgramDetails().getPersonalDetails().getManagementFeePersonal() != null
				? details.getProgramDetails().getPersonalDetails().getManagementFeePersonal()
				: details.getProgramDetails().getManagementFeeCurrent());
		request.setBrokerType(details.getBrokerDetails().getType());
		request.setIsExchangeProgram(details.getProgramDetails().getType().equals(ProgramType.DAILYPERIOD));
		request.setIsProcessingRealTime(details.getProgramDetails().getDailyPeriodDetails() != null
				&& details.getProgramDetails().getDailyPeriodDetails().isIsProcessingRealTime());

		getViewState().showInvestProgramActivity(request);
	}

	void onWithdrawClicked() {
		if (!userLoggedOn) {
			getViewState().showLoginActivity();
			return;
		}

		if (details == null) {
			return;
		}

		ProgramRequest request = new ProgramRequest();

		request.setProgramId(details.getId());
		request.setProgramLogo(details.getPublicInfo().getLogoUrl());
		request.setProgramColor(details.getPublicInfo().getColor());
		request.setProgramName(details.getPublicInfo().getTitle());
		request.setProgramCurrency(details.getTradingAccountInfo().getCurrency().getValue());
		request.setLevel(details.getProgramDetails().getLevel());
		request.setLevelProgress(details.getProgramDetails().getLevelProgress());
		request.setManagerName(details.getOwner().getUsername());
		request.setIsExchangeProgram(details.getProgramDetails().getType().equals(ProgramType.DAILYPERIOD));
		request.setIsProcessingRealTime(details.getProgramDetails().getDailyPeriodDetails() != null
				&& details.getProgramDetails().getDailyPeriodDetails().isIsProcessingRealTime());

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

	private void handleProgramDetailsSuccess(ProgramFollowDetailsFull programDetails) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.details = programDetails;

		getViewState().setDetails(programDetails);

		if (details.getProgramDetails().getDailyPeriodDetails() != null
				&& details.getProgramDetails().getDailyPeriodDetails().isIsProcessingRealTime()) {
			getViewState().setInvestWithdrawInfo(context.getString(R.string.program_invest_withdraw_info_few_minutes));
		}
		else {
			getWithdrawInfo(details);
		}
	}

	private void handleProgramDetailsError(Throwable throwable) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void getWithdrawInfo(ProgramFollowDetailsFull details) {
		if (programsManager != null && details != null) {
			withdrawInfoSubscription = programsManager.getWithdrawInfo(details.getId())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWithdrawInfoResponse,
							this::handleWithdrawInfoError);
		}
	}

	private void handleWithdrawInfoResponse(ProgramWithdrawInfo response) {
		withdrawInfoSubscription.unsubscribe();

		getViewState().setInvestWithdrawInfo(String.format(Locale.getDefault(),
				context.getString(R.string.program_invest_withdraw_info_template),
				DateTimeUtil.formatRequestInfoDateTime(response.getPeriodEnds())));
	}

	private void handleWithdrawInfoError(Throwable throwable) {
		withdrawInfoSubscription.unsubscribe();

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
		details.getProgramDetails().getPersonalDetails().setIsReinvest(reinvest);

		EventBus.getDefault().post(new OnProgramReinvestChangedEvent(programId, reinvest));
	}

	private void handleReinvestError(Throwable throwable) {
		reinvestSubscription.unsubscribe();
		getViewState().setReinvest(details.getProgramDetails().getPersonalDetails().isIsReinvest());

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void setIgnoreSo(boolean ignoreSo) {
		if (programId != null && programsManager != null) {
			if (ignoreSoSubscription != null) {
				ignoreSoSubscription.unsubscribe();
			}
			ignoreSoSubscription = programsManager.setIgnoreSo(programId, ignoreSo)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleIgnoreSoSuccess(ignoreSo),
							this::handleIgnoreSoError);
		}
	}

	private void handleIgnoreSoSuccess(Boolean ignoreSo) {
		ignoreSoSubscription.unsubscribe();
		details.getProgramDetails().getPersonalDetails().setIsAutoJoin(ignoreSo);
	}

	private void handleIgnoreSoError(Throwable throwable) {
		ignoreSoSubscription.unsubscribe();
		getViewState().setIgnoreSo(details.getProgramDetails().getPersonalDetails().isIsAutoJoin());

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

	@Subscribe
	public void onEventMainThread(OnRequestCancelledEvent event) {
		getProgramDetails();
	}
}
