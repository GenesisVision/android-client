package vision.genesis.clientapp.feature.main.program.create;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.AmountWithCurrency;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.MakeSignalProviderProgram;
import io.swagger.client.model.MakeTradingAccountProgram;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.ProgramMinInvestAmount;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.events.OnCreateProgramCreateButtonClickedEvent;
import vision.genesis.clientapp.model.events.OnCreateProgramSuccessEvent;
import vision.genesis.clientapp.model.events.OnProgramSettingsConfirmEvent;
import vision.genesis.clientapp.model.events.OnPublicInfoConfirmButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

@InjectViewState
public class CreateProgramPresenter extends MvpPresenter<CreateProgramView>
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public AssetsManager assetsManager;

	private Subscription platformInfoSubscription;

	private Subscription createProgramSubscription;

	private CreateProgramModel model;

	private String title;

	private String description;

	private String logo;

	private Integer periodLength;

	private Double stopOutLevel;

	private Double investmentLimit;

	private Double entryFee;

	private Double successFee;

	private boolean needPublicInfo;

	private boolean needDeposit;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getPlatformInfo();
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}
		if (createProgramSubscription != null) {
			createProgramSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(CreateProgramModel model) {
		this.model = model;

		getPlatformInfo();
	}

	private void getPlatformInfo() {
		if (settingsManager != null && model != null) {
			platformInfoSubscription = settingsManager.getPlatformInfo()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPlatformInfoSuccess,
							this::handleGetPlatformInfoError);
		}
	}

	private void handleGetPlatformInfoSuccess(PlatformInfo platformInfo) {
		platformInfoSubscription.unsubscribe();

		for (ProgramMinInvestAmount info : platformInfo.getAssetInfo().getProgramInfo().getMinInvestAmounts()) {
			if (info.getServerType().equals(model.getServerType())) {
				for (AmountWithCurrency amountWithCurrency : info.getMinDepositCreateAsset()) {
					if (amountWithCurrency.getCurrency().getValue().equals(model.getCurrency())) {
						Double minDeposit = amountWithCurrency.getAmount();
						model.setMinDeposit(minDeposit);
						needPublicInfo = model.getAssetType().equals(AssetType.NONE);
						needDeposit = model.getCurrentBalance() < minDeposit;
						getViewState().initViewPager(needPublicInfo, needDeposit, model);
						break;
					}
				}
			}
		}
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void sendCreateProgramRequest() {
		getViewState().showProgress(true);

		Observable<Void> apiRequest = null;

		if (model.getAssetType().equals(AssetType.NONE)) {
			MakeTradingAccountProgram accountRequest = new MakeTradingAccountProgram();

			accountRequest.setId(model.getAssetId());
			accountRequest.setTitle(this.title);
			accountRequest.setDescription(this.description);
			accountRequest.setLogo(this.logo);

			accountRequest.setPeriodLength(periodLength);
			accountRequest.setStopOutLevel(stopOutLevel);
			accountRequest.setInvestmentLimit(investmentLimit);
			accountRequest.setManagementFee(entryFee);
			accountRequest.setSuccessFee(successFee);

			apiRequest = assetsManager.createProgramFromTradingAccount(accountRequest);
		}
		else if (model.getAssetType().equals(AssetType.FOLLOW)) {
			MakeSignalProviderProgram followRequest = new MakeSignalProviderProgram();

			followRequest.setId(model.getAssetId());

			followRequest.setPeriodLength(periodLength);
			followRequest.setStopOutLevel(stopOutLevel);
			followRequest.setInvestmentLimit(investmentLimit);
			followRequest.setManagementFee(entryFee);
			followRequest.setSuccessFee(successFee);

			apiRequest = assetsManager.createProgramFromSignalProvider(followRequest);
		}

		if (apiRequest != null) {
			createProgramSubscription = apiRequest
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleCreateProgramSuccess,
							this::handleCreateProgramError);
		}
	}

	private void handleCreateProgramSuccess(Void response) {
		createProgramSubscription.unsubscribe();
		EventBus.getDefault().post(new OnCreateProgramSuccessEvent(model.getAssetId()));
		getViewState().finishActivity();
	}

	private void handleCreateProgramError(Throwable throwable) {
		createProgramSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnPublicInfoConfirmButtonClickedEvent event) {
		this.title = event.getTitle();
		this.description = event.getDescription();
		this.logo = event.getLogo();

		getViewState().showSettings();
	}

	@Subscribe
	public void onEventMainThread(OnProgramSettingsConfirmEvent event) {
		this.periodLength = event.getModel().getPeriodLength();
		this.stopOutLevel = event.getModel().getStopOutLevel();
		this.investmentLimit = event.getModel().getInvestmentLimit();
		this.entryFee = event.getModel().getEntryFee();
		this.successFee = event.getModel().getSuccessFee();

		if (needDeposit) {
			getViewState().showDeposit();
		}
		else {
			sendCreateProgramRequest();
		}
	}

	@Subscribe
	public void onEventMainThread(OnCreateProgramCreateButtonClickedEvent event) {
		sendCreateProgramRequest();
	}
}