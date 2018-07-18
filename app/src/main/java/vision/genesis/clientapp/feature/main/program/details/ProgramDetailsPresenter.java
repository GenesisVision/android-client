package vision.genesis.clientapp.feature.main.program.details;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramViewModel;
import io.swagger.client.model.TradesChartViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

@InjectViewState
public class ProgramDetailsPresenter extends MvpPresenter<ProgramDetailsView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public ProgramsManager programsManager;

	private Subscription userSubscription;

	private Subscription programDetailsSubscription;

	private Subscription chartDataSubscription;

	private UUID programId;

	private String chartTimeFrame = "All";

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToUser();
		getViewState().showProgress(true);
		getProgramDetails();
		getChartData();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null)
			userSubscription.unsubscribe();

		if (programDetailsSubscription != null)
			programDetailsSubscription.unsubscribe();

		if (chartDataSubscription != null)
			chartDataSubscription.unsubscribe();

		super.onDestroy();
	}

	void setProgramId(UUID programId) {
		this.programId = programId;
		getProgramDetails();
		getChartData();
	}

	void onShow() {
		getProgramDetails();
		getChartData();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getProgramDetails();
		getChartData();
	}

	void onChartTimeFrameChanged(String newTimeFrame) {
		chartTimeFrame = newTimeFrame;
		getChartData();
	}

	private void getProgramDetails() {
		if (programId != null && programsManager != null) {
			if (programDetailsSubscription != null)
				programDetailsSubscription.unsubscribe();
			programDetailsSubscription = programsManager.getInvestmentProgramDetails(programId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleInvestmentProgramDetailsSuccess,
							this::handleInvestmentProgramDetailsError);
		}
	}

	private void handleInvestmentProgramDetailsSuccess(InvestmentProgramViewModel model) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		getViewState().setProgramDetails(model.getInvestmentProgram());
	}

	private void handleInvestmentProgramDetailsError(Throwable throwable) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);
	}

	private void getChartData() {
		if (programId != null && programsManager != null) {
			if (chartDataSubscription != null)
				chartDataSubscription.unsubscribe();
			chartDataSubscription = programsManager.getEquityChart(programId, chartTimeFrame)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetChartDataSuccess,
							this::handleGetChartDataError);
		}
	}

	private void handleGetChartDataSuccess(TradesChartViewModel model) {
		chartDataSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		getViewState().setChartData(model.getChart());
	}

	private void handleGetChartDataError(Throwable throwable) {
		chartDataSubscription.unsubscribe();
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
		getViewState().showInvestWithdrawButtons(Constants.IS_INVESTOR);
	}

	private void userLoggedOff() {
		getViewState().showInvestWithdrawButtons(false);
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}
}
