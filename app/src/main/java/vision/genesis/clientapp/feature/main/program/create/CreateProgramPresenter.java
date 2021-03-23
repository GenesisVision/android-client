package vision.genesis.clientapp.feature.main.program.create;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AmountWithCurrency;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.Broker;
import io.swagger.client.model.BrokerTradeServerType;
import io.swagger.client.model.Currency;
import io.swagger.client.model.ExchangeInfo;
import io.swagger.client.model.MakeExchangeAccountProgram;
import io.swagger.client.model.MakeExchangeProgram;
import io.swagger.client.model.MakeProgram;
import io.swagger.client.model.MakeSignalProviderProgram;
import io.swagger.client.model.MakeTradingAccountProgram;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.ProgramMinInvestAmount;
import io.swagger.client.model.TradesDelay;
import io.swagger.client.model.TradingAccountCreateResult;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.events.OnAccountBrokerSettingsSelectedEvent;
import vision.genesis.clientapp.model.events.OnBrokerSelectedEvent;
import vision.genesis.clientapp.model.events.OnCreateProgramCreateButtonClickedEvent;
import vision.genesis.clientapp.model.events.OnCreateProgramSuccessEvent;
import vision.genesis.clientapp.model.events.OnExchangeSelectedEvent;
import vision.genesis.clientapp.model.events.OnProgramDepositConfirmEvent;
import vision.genesis.clientapp.model.events.OnProgramSettingsConfirmEvent;
import vision.genesis.clientapp.model.events.OnPublicInfoConfirmButtonClickedEvent;
import vision.genesis.clientapp.model.events.OnSelectBrokerNextClickedEvent;
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

	private ExchangeInfo selectedExchange;

	private Broker selectedBroker;

	private UUID brokerAccountTypeId;

	private Currency accountCurrency;

	private Integer leverage;

	private String title;

	private String description;

	private String logo;

	private Integer periodLength;

	private Double stopOutLevel;

	private Boolean isProcessingRealTime;

	private Integer hourProcessing;

	private String currency;

	private Double investmentLimit;

	private TradesDelay tradesDelay;

	private Double entryFee;

	private Double successFee;

	private boolean needBrokerSelect;

	private boolean needPublicInfo;

	private boolean needDeposit;

	private PlatformInfo platformInfo;

	private Double depositAmount;

	private UUID depositWalletId;

	private BrokerTradeServerType brokerServerType;

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

		this.platformInfo = platformInfo;

		if (model.getAssetId() == null) {
			needBrokerSelect = true;
			needPublicInfo = true;
			needDeposit = true;
			getViewState().initViewPager(needBrokerSelect, needPublicInfo, needDeposit, model);
		}
		else {
			if (model.isExchange()) {
				needPublicInfo = model.getAssetType().equals(AssetType.NONE);
				needDeposit = false;
				getViewState().initViewPager(needBrokerSelect, needPublicInfo, needDeposit, model);
			}
			else {
				for (ProgramMinInvestAmount info : platformInfo.getAssetInfo().getProgramInfo().getMinInvestAmounts()) {
					if (info.getServerType().equals(model.getServerType())) {
						for (AmountWithCurrency amountWithCurrency : info.getMinDepositCreateAsset()) {
							if (amountWithCurrency.getCurrency().getValue().equals(model.getCurrency())) {
								Double minDeposit = amountWithCurrency.getAmount();
								model.setMinDepositInfo(info.getMinDepositCreateAsset());
								needPublicInfo = model.getAssetType().equals(AssetType.NONE);
								needDeposit = model.getCurrentBalance() < minDeposit;
								getViewState().initViewPager(needBrokerSelect, needPublicInfo, needDeposit, model);
								break;
							}
						}
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

		if (model.getAssetId() == null) {
			if (selectedExchange != null) {
				makeExchangeProgram();
			}
			else if (selectedBroker != null) {
				makeProgram();
			}
		}
		else {
			if (model.isExchange()) {
				MakeExchangeAccountProgram accountRequest = new MakeExchangeAccountProgram();

				accountRequest.setId(model.getAssetId());
				accountRequest.setTitle(this.title);
				accountRequest.setDescription(this.description);
				accountRequest.setLogo(this.logo);

				accountRequest.setIsProcessingRealTime(isProcessingRealTime);
				accountRequest.setHourProcessing(hourProcessing);
				accountRequest.setCurrency(Currency.fromValue(currency));
				accountRequest.setInvestmentLimit(investmentLimit);
				accountRequest.setTradesDelay(tradesDelay);
				accountRequest.setManagementFee(entryFee);
				accountRequest.setSuccessFee(successFee);

				apiRequest = assetsManager.createProgramFromExchangeAccount(accountRequest);
			}
			else if (model.getAssetType().equals(AssetType.NONE)) {
				MakeTradingAccountProgram accountRequest = new MakeTradingAccountProgram();

				accountRequest.setId(model.getAssetId());
				accountRequest.setTitle(this.title);
				accountRequest.setDescription(this.description);
				accountRequest.setLogo(this.logo);

				accountRequest.setPeriodLength(periodLength);
				accountRequest.setTradesDelay(tradesDelay);
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

	private void makeProgram() {
		if (assetsManager != null) {
			MakeProgram request = new MakeProgram();
			request.setTitle(this.title);
			request.setDescription(this.description);
			request.setLogo(this.logo);

			request.setBrokerAccountTypeId(brokerAccountTypeId);
			request.setLeverage(leverage);
			request.setCurrency(accountCurrency);

			request.setPeriodLength(periodLength);
			request.setTradesDelay(tradesDelay);
			request.setStopOutLevel(stopOutLevel);
			request.setInvestmentLimit(investmentLimit);
			request.setManagementFee(entryFee);
			request.setSuccessFee(successFee);

			request.setDepositAmount(depositAmount);
			request.setDepositWalletId(depositWalletId);

			createProgramSubscription = assetsManager.createProgram(request)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleMakeProgramSuccess,
							this::handleCreateProgramError);
		}
	}

	private void makeExchangeProgram() {
		if (assetsManager != null) {
			MakeExchangeProgram request = new MakeExchangeProgram();
			request.setTitle(this.title);
			request.setDescription(this.description);
			request.setLogo(this.logo);

			request.setBrokerAccountTypeId(brokerAccountTypeId);

			request.setIsProcessingRealTime(isProcessingRealTime);
			request.setHourProcessing(hourProcessing);
			request.setCurrency(Currency.fromValue(currency));
			request.setInvestmentLimit(investmentLimit);
			request.setTradesDelay(tradesDelay);
			request.setManagementFee(entryFee);
			request.setSuccessFee(successFee);

			request.setDepositAmount(depositAmount);
			request.setDepositWalletId(depositWalletId);

			createProgramSubscription = assetsManager.createExchangeProgram(request)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleMakeProgramSuccess,
							this::handleCreateProgramError);
		}
	}

	private void handleMakeProgramSuccess(TradingAccountCreateResult response) {
		createProgramSubscription.unsubscribe();
		EventBus.getDefault().post(new OnCreateProgramSuccessEvent(response.getId()));
		getViewState().finishActivity();
	}

	@Subscribe
	public void onEventMainThread(OnBrokerSelectedEvent event) {
		this.selectedBroker = event.getSelectedBroker();
		getViewState().setIsExchangeProgram(false);
		getViewState().showAccountSettings(event.getSelectedBroker());
	}

	@Subscribe
	public void onEventMainThread(OnSelectBrokerNextClickedEvent event) {
		getViewState().showAccountSettings(selectedBroker);
	}

	@Subscribe
	public void onEventMainThread(OnExchangeSelectedEvent event) {
		this.selectedExchange = event.getSelectedExchange();
		this.brokerAccountTypeId = event.getSelectedAccountType().getId();
		this.brokerServerType = event.getSelectedAccountType().getType();
		this.accountCurrency = Currency.USDT;

		setMinDeposit(accountCurrency);

		getViewState().setIsExchangeProgram(true);
		getViewState().showPublicInfo();
	}

	@Subscribe
	public void onEventMainThread(OnAccountBrokerSettingsSelectedEvent event) {
		this.brokerAccountTypeId = event.getBrokerAccountType().getId();
		this.brokerServerType = event.getBrokerAccountType().getType();
		this.accountCurrency = event.getCurrency();
		this.leverage = event.getLeverage();

		setMinDeposit(accountCurrency);

		getViewState().showPublicInfo();
	}

	private void setMinDeposit(Currency accountCurrency) {
		Map<String, Double> minDepositInfo = new HashMap<>();
		for (ProgramMinInvestAmount info : platformInfo.getAssetInfo().getProgramInfo().getMinInvestAmounts()) {
			if (info.getServerType().equals(brokerServerType)) {
				for (AmountWithCurrency amountWithCurrency : info.getMinDepositCreateAsset()) {
					minDepositInfo.put(amountWithCurrency.getCurrency().getValue(), amountWithCurrency.getAmount());
				}
			}
		}
		getViewState().setMinDeposit(minDepositInfo, accountCurrency);
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
		this.isProcessingRealTime = event.getModel().isProcessingRealTime();
		this.hourProcessing = event.getModel().getHourProcessing();
		this.currency = event.getModel().getCurrency();
		this.investmentLimit = event.getModel().getInvestmentLimit();
		this.tradesDelay = event.getModel().getTradesDelay();
		this.stopOutLevel = event.getModel().getStopOutLevel();
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
	public void onEventMainThread(OnProgramDepositConfirmEvent event) {
		this.depositAmount = event.getDepositAmount();
		this.depositWalletId = event.getDepositWalletId();

		sendCreateProgramRequest();
	}

	@Subscribe
	public void onEventMainThread(OnCreateProgramCreateButtonClickedEvent event) {
		sendCreateProgramRequest();
	}
}