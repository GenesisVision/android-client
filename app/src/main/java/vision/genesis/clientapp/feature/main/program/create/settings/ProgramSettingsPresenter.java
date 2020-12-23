package vision.genesis.clientapp.feature.main.program.create.settings;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.ProgramCreateAssetPlatformInfo;
import io.swagger.client.model.TradesDelay;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.ProgramSettingsModel;
import vision.genesis.clientapp.model.events.OnProgramSettingsConfirmEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

@InjectViewState
public class ProgramSettingsPresenter extends MvpPresenter<ProgramSettingsView>
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private int periodLength;

	private double investmentLimit;

	private double stopOut = 0;

	private double managementFee = 0;

	private double successFee = 0;


	private Double maxStopOutLevel = 100.0;

	private double maxManagementFee = 0;

	private double maxSuccessFee = 0;


	private ProgramSettingsModel model;

	private List<Integer> periods;

	private boolean isRealTimeEnabled;

	private int hourProcessing;

	private ArrayList<String> currencies;

	private String currency;

	private boolean isInvestmentLimitEnabled;

	private TradesDelay tradesDelay;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getPlatformInfo();
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setModel(ProgramSettingsModel model) {
		this.model = model;

		getPlatformInfo();
	}

	void onRealTimeCheckedChanged(boolean checked) {
		isRealTimeEnabled = checked;
	}

	void onInvestmentLimitCheckedChanged(boolean checked) {
		isInvestmentLimitEnabled = checked;
	}

	void onInvestmentLimitChanged(String investmentLimitString) {
		try {
			investmentLimit = Double.parseDouble(investmentLimitString);
		} catch (NumberFormatException e) {
			investmentLimit = 0;
		}

		updateConfirmButtonAvailability();
	}

	void onStopOutChanged(String stopOutString) {
		try {
			stopOut = Double.parseDouble(stopOutString);
		} catch (NumberFormatException e) {
			stopOut = 0;
		}
		if (stopOut > maxStopOutLevel) {
			getViewState().setStopOutLevel(maxStopOutLevel);
			if (maxStopOutLevel != Constants.MAX_STOP_OUT_LEVEL) {
				getViewState().showStopOutError(context.getString(R.string.error_stop_out_max));
			}
			return;
		}
		else if (stopOut < Constants.MIN_STOP_OUT_LEVEL) {
			getViewState().showStopOutError(String.format(Locale.getDefault(),
					context.getString(R.string.error_stop_out_min),
					StringFormatUtil.formatAmount(Constants.MIN_STOP_OUT_LEVEL, 0, 2)));
			return;
		}
		getViewState().hideStopOutError();

		updateConfirmButtonAvailability();
	}

	void onManagementFeeChanged(String managementFeeString) {
		try {
			managementFee = Double.parseDouble(managementFeeString);
		} catch (NumberFormatException e) {
			managementFee = 0;
		}
		if (managementFee > maxManagementFee) {
			getViewState().setManagementFee(maxManagementFee);
			return;
		}

		updateConfirmButtonAvailability();
	}

	void onSuccessFeeChanged(String exitFeeString) {
		try {
			successFee = Double.parseDouble(exitFeeString);
		} catch (NumberFormatException e) {
			successFee = 0;
		}
		if (successFee > maxSuccessFee) {
			getViewState().setSuccessFee(maxSuccessFee);
			return;
		}

		updateConfirmButtonAvailability();
	}

	void onConfirmClicked() {
		ProgramSettingsModel newModel = new ProgramSettingsModel();
		newModel.setPeriodLength(periodLength);
		newModel.setInvestmentLimit(!isInvestmentLimitEnabled ? null : investmentLimit);
		if (model.isExchange()) {
			newModel.setIsProcessingRealTime(isRealTimeEnabled);
			newModel.setHourProcessing(hourProcessing);
		}
		newModel.setCurrency(currency);
		newModel.setStopOutLevel(stopOut);
		newModel.setEntryFee(managementFee);
		newModel.setSuccessFee(successFee);
		newModel.setTradesDelay(TradesDelay.fromValue(tradesDelay.getValue()));
		EventBus.getDefault().post(new OnProgramSettingsConfirmEvent(newModel));
	}

	private void updateConfirmButtonAvailability() {
		getViewState().setConfirmButtonEnabled(managementFee <= maxManagementFee && successFee <= maxSuccessFee);
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

		initPeriods(platformInfo);
		initProcessing();
		initCurrencies();
		initInvestmentLimit();
		initTradesDelay();
		initStopOut();
		initFees(platformInfo);
	}

	private void initPeriods(PlatformInfo platformInfo) {
		periods = platformInfo.getAssetInfo().getProgramInfo().getPeriods();

		ArrayList<String> periodLengthOptions = new ArrayList<>();
		for (Integer period : periods) {
			periodLengthOptions.add(String.format(Locale.getDefault(), "%d %s", period, context.getResources().getQuantityString(R.plurals.days, period)));
		}
		getViewState().setPeriodLengthOptions(periodLengthOptions);

		int periodPos = 0;
		if (model.getPeriodLength() != null) {
			periodPos = periods.indexOf(model.getPeriodLength());
		}
		onPeriodLengthOptionSelected(periodPos, periodLengthOptions.get(periodPos));
	}

	private void initProcessing() {
		getViewState().setProcessingRealTimeChecked(model.isProcessingRealTime() != null ? model.isProcessingRealTime() : false);

		ArrayList<String> processingHours = new ArrayList<>();

		for (int i = 0; i < 24; i++) {
			processingHours.add((i < 10 ? "0" : "").concat(String.valueOf(i).concat(":00")));
		}
		getViewState().setProcessingTimeOptions(processingHours);

		int hourProcessing = 0;
		if (model.getHourProcessing() != null) {
			hourProcessing = model.getHourProcessing();
		}
		onProcessingTimeOptionSelected(hourProcessing, processingHours.get(hourProcessing));
	}

	private void initCurrencies() {
		currencies = new ArrayList<>();
		currencies.add("BTC");
		currencies.add("USDT");
		getViewState().setCurrencyOptions(currencies);

		int currencyPos = 0;
		if (model.getCurrency() != null) {
			for (String currency : currencies) {
				if (currency.equals(model.getCurrency())) {
					this.currency = currency;
					break;
				}
				currencyPos++;
			}
		}
		if (currencyPos < currencies.size()) {
			onCurrencyOptionSelected(currencyPos, currencies.get(currencyPos));
		}
	}

	private void initInvestmentLimit() {
		if (model.getInvestmentLimit() != null) {
			getViewState().setInvestmentLimit(model.getInvestmentLimit());
		}
	}

	private void initTradesDelay() {
		getViewState().setTradesDelayOptions(StringFormatUtil.getTradesDelayOptions());
		int tradesDelayPos = 0;
		if (model.getTradesDelay() != null) {
			for (TradesDelay value : TradesDelay.values()) {
				if (value.getValue().equals(model.getTradesDelay().getValue())) {
					this.tradesDelay = value;

					break;
				}
				tradesDelayPos++;
			}
		}
		onTradesDelayOptionSelected(tradesDelayPos, StringFormatUtil.getTradesDelayString(TradesDelay.values()[tradesDelayPos]));

	}

	private void initStopOut() {
		this.maxStopOutLevel = model.getStopOutLevel() == null ? Constants.MAX_STOP_OUT_LEVEL : model.getStopOutLevel();
		if (model.getStopOutLevel() != null) {
			getViewState().setStopOutLevel(model.getStopOutLevel());
		}
	}

	private void initFees(PlatformInfo platformInfo) {
		ProgramCreateAssetPlatformInfo info = platformInfo.getAssetInfo().getProgramInfo().getCreateProgramInfo();
		maxManagementFee = info.getMaxManagementFee();
		maxSuccessFee = info.getMaxSuccessFee();
		getViewState().updateManagementFeeDescription(maxManagementFee);
		getViewState().updateSuccessFeeDescription(maxSuccessFee);

		if (model.getEntryFee() != null) {
			getViewState().setManagementFee(model.getEntryFee());
		}
		if (model.getSuccessFee() != null) {
			getViewState().setSuccessFee(model.getSuccessFee());
		}
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	void onPeriodLengthOptionSelected(Integer position, String text) {
		this.periodLength = periods.get(position);
		getViewState().setPeriodLength(text, position);

		updateConfirmButtonAvailability();
	}

	void onProcessingTimeOptionSelected(Integer position, String text) {
		this.hourProcessing = position;
		getViewState().setProcessingTime(text, position);

		updateConfirmButtonAvailability();
	}

	void onCurrencyOptionSelected(Integer position, String text) {
		this.currency = currencies.get(position);
		getViewState().setCurrency(text, position);

		updateConfirmButtonAvailability();
	}

	void onTradesDelayOptionSelected(Integer position, String text) {
		this.tradesDelay = TradesDelay.values()[position];
		getViewState().setTradesDelay(text, position);

		updateConfirmButtonAvailability();
	}
}
