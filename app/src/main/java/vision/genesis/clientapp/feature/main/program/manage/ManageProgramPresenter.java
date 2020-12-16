package vision.genesis.clientapp.feature.main.program.manage;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramType;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.TradesDelay;
import io.swagger.client.model.TwoFactorCodeModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.model.events.OnProgramSettingsChangedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

@InjectViewState
public class ManageProgramPresenter extends MvpPresenter<ManageProgramView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	private Subscription closePeriodSubscription;

	private Subscription closeProgramSubscription;

	private ProgramFollowDetailsFull details;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (closePeriodSubscription != null) {
			closePeriodSubscription.unsubscribe();
		}
		if (closeProgramSubscription != null) {
			closeProgramSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(ProgramFollowDetailsFull details) {
		this.details = details;
	}

	void onChangeSettingsClicked() {
		ProgramUpdate model = new ProgramUpdate();
		model.setTitle(details.getPublicInfo().getTitle());
		model.setDescription(details.getPublicInfo().getDescription());
		model.setLogo(details.getPublicInfo().getLogo());
		model.setEntryFee(details.getProgramDetails().getManagementFeeSelected());
		model.setSuccessFee(details.getProgramDetails().getSuccessFeeSelected());
		model.setInvestmentLimit(details.getProgramDetails().getAvailableInvestmentLimit());
		model.setStopOutLevel(details.getProgramDetails().getStopOutLevelSelected());
		model.setTradesDelay(TradesDelay.fromValue(details.getProgramDetails().getTradesDelay().getValue()));
		if (details.getProgramDetails().getDailyPeriodDetails() != null) {
			model.setIsProcessingRealTime(details.getProgramDetails().getDailyPeriodDetails().isIsProcessingRealTime());
			model.setHourProcessing(details.getProgramDetails().getDailyPeriodDetails().getHourProcessing());
		}

		getViewState().showChangeSettingsActivity(details.getId(), details.getTradingAccountInfo().getCurrency().getValue(), model, details.getProgramDetails().getType().equals(ProgramType.DAILYPERIOD));
	}

	void onClosePeriodClicked() {
		closePeriod();
	}

	void onCloseProgramClicked() {
		closeProgram();
	}

	private void closePeriod() {
		if (details != null && assetsManager != null) {
			getViewState().showProgress(true);
			closePeriodSubscription = assetsManager.closePeriod(details.getId())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleClosePeriodSuccess,
							this::handleClosePeriodError);
		}
	}

	private void handleClosePeriodSuccess(Void response) {
		closePeriodSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void handleClosePeriodError(Throwable throwable) {
		closePeriodSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void closeProgram() {
		if (details != null && assetsManager != null) {
			getViewState().showProgress(true);
			TwoFactorCodeModel model = new TwoFactorCodeModel();
			closeProgramSubscription = assetsManager.closeProgram(details.getId(), model)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleCloseProgramSuccess,
							this::handleCloseProgramError);
		}
	}

	private void handleCloseProgramSuccess(Void response) {
		closeProgramSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void handleCloseProgramError(Throwable throwable) {
		closeProgramSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnProgramSettingsChangedEvent event) {
		details.getProgramDetails().setAvailableInvestmentLimit(event.getModel().getInvestmentLimit());
		details.getProgramDetails().setTradesDelay(TradesDelay.fromValue(event.getModel().getTradesDelay().getValue()));
		details.getProgramDetails().setStopOutLevelSelected(event.getModel().getStopOutLevel());
		details.getProgramDetails().setManagementFeeSelected(event.getModel().getEntryFee());
		details.getProgramDetails().setSuccessFeeSelected(event.getModel().getSuccessFee());
		if (details.getProgramDetails().getDailyPeriodDetails() != null) {
			details.getProgramDetails().getDailyPeriodDetails().isProcessingRealTime(event.getModel().isIsProcessingRealTime());
			details.getProgramDetails().getDailyPeriodDetails().setHourProcessing(event.getModel().getHourProcessing());
		}
		getViewState().updateView(details.getProgramDetails());
	}
}
