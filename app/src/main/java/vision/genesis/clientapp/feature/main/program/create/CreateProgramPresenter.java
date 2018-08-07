package vision.genesis.clientapp.feature.main.program.create;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import rx.Subscription;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.CreateProgramData;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.CreateProgramConfirmButtonClickedEvent;
import vision.genesis.clientapp.model.events.OnCreateProgramFirstStepPassedEvent;
import vision.genesis.clientapp.model.events.OnCreateProgramSecondStepPassedEvent;
import vision.genesis.clientapp.model.events.OnCreateProgramThirdStepPassedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2018.
 */

@InjectViewState
public class CreateProgramPresenter extends MvpPresenter<CreateProgramView>
{
	@Inject
	public Context context;

	@Inject
	public ProgramsManager programsManager;

	private Subscription dataSubscription;

	private CreateProgramData createProgramData;

//	private NewInvestmentRequest request = new NewInvestmentRequest();

	private Subscription createProgramSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getDataToCreateProgram();
	}

	private void getDataToCreateProgram() {
//		dataSubscription = programsManager.getDataToCreateProgram()
//				.subscribeOn(Schedulers.io())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(this::handleGetDataSuccess,
//						this::handleGetDataError);
	}

	@Override
	public void onDestroy() {
		if (dataSubscription != null)
			dataSubscription.unsubscribe();
		if (createProgramSubscription != null)
			createProgramSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

//	private void handleGetDataSuccess(BrokersViewModel model) {
//		dataSubscription.unsubscribe();
//
//		createProgramData = new CreateProgramData();
//		createProgramData.setBrokers(model.getBrokers());
//
//		EventBus.getDefault().postSticky(new SetCreateProgramDataEvent(createProgramData));
//		getViewState().showProgress(false);
//	}

	private void handleGetDataError(Throwable throwable) {
		dataSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			getViewState().showSnackbar(context.getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null && response.errors != null && response.errors.get(0) != null) {
				getViewState().showSnackbar(response.errors.get(0).message);
			}
		}
	}

	private void createProgram() {
		getViewState().showProgress(true);

//		createProgramSubscription = programsManager.sendCreateProgramRequest(request)
//				.subscribeOn(Schedulers.io())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(this::handleCreateProgramSuccess, this::handleCreateProgramError);
	}

	private void handleCreateProgramSuccess(UUID programId) {
		createProgramSubscription.unsubscribe();
		getViewState().finishActivity();
	}

	private void handleCreateProgramError(Throwable throwable) {
		createProgramSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			getViewState().showSnackbar(context.getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null) {
				for (Error error : response.errors) {
//					if (error.property != null) {
//						switch (error.property.toLowerCase()) {
//							case "title":
//								getViewState().setUserNameError(error.message);
//								break;
//							case "description":
//								getViewState().setEmailError(error.message);
//								break;
//						}
					getViewState().showSnackbar(error.message);
				}
			}
		}
	}

	@Subscribe
	public void onEventMainThread(OnCreateProgramFirstStepPassedEvent event) {
//		request.setLogo(event.getLogo());
//		request.setTitle(event.getTitle());
//		request.setDescription(event.getDescription());
		getViewState().showNextStep();
	}

	@Subscribe
	public void onEventMainThread(OnCreateProgramSecondStepPassedEvent event) {
//		request.setBrokerTradeServerId(event.getBroker().getId());
//		request.setLeverage(event.getLeverage());
//		request.setTradePlatformPassword(event.getPassword());
		getViewState().showNextStep();
	}

	@Subscribe
	public void onEventMainThread(OnCreateProgramThirdStepPassedEvent event) {
//		request.setPeriod(event.getPeriod());
//		request.setDateFrom(event.getStartDate());
//		request.setDepositAmount(event.getDeposit());
//		request.setFeeSuccess(event.getSuccessFee());
//		request.setFeeManagement(event.getManagementFee());
//		request.setTokenSymbol(event.getTokenSymbol());
//		request.setTokenName(event.getTokenName());
		createProgram();
	}

	@Subscribe
	public void onEventMainThread(CreateProgramConfirmButtonClickedEvent event) {
//		confirmTfa(event.getPassword(), event.getCode());
	}
}