package vision.genesis.clientapp.feature.main.program.details;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.NewInvestmentSuccessEvent;
import vision.genesis.clientapp.model.events.OnPeriodLeftEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

@InjectViewState
public class ProgramDetailsPresenter extends MvpPresenter<ProgramDetailsView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public InvestManager investManager;

	private Subscription userSubscription;

	private Subscription programDetailsSubscription;

	private UUID programId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
		getViewState().showProgress(true);
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null)
			userSubscription.unsubscribe();
		if (programDetailsSubscription != null)
			programDetailsSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	void setProgramId(UUID programId) {
		this.programId = programId;
	}

	void onResume() {
		if (programId != null)
			getProgramDetails();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		if (programId != null)
			getProgramDetails();
	}

	private void getProgramDetails() {
		programDetailsSubscription = investManager.getInvestmentProgramDetails(programId)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleInvestmentProgramDetailsSuccess,
						this::handleInvestmentProgramDetailsError);
	}

	private void handleInvestmentProgramDetailsSuccess(InvestmentProgramViewModel model) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		getViewState().setProgram(model.getInvestmentProgram());
	}

	private void handleInvestmentProgramDetailsError(Throwable throwable) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated, this::handleUserError);
	}

	private void userUpdated(User user) {
		if (user == null)
			userLoggedOff();
		else
			userLoggedOn();
	}

	private void userLoggedOn() {
		getViewState().showInvestWithdrawButtons(true);
	}

	private void userLoggedOff() {
		getViewState().showInvestWithdrawButtons(false);
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

	@Subscribe
	public void onEventMainThread(NewInvestmentSuccessEvent event) {
		getViewState().finishActivity();
	}

	@Subscribe
	public void onEventMainThread(OnPeriodLeftEvent event) {
		getProgramDetails();
	}
}
