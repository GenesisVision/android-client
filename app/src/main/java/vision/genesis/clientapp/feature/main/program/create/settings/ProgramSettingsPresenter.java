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

	private double entryFee = 0;

	private double successFee = 0;


	private Double maxStopOutLevel = 100.0;

	private double maxEntryFee = 0;

	private double maxSuccessFee = 0;

	private ProgramSettingsModel model;

	private List<Integer> periods;

	private boolean isInvestmentLimitEnabled;

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
		this.maxStopOutLevel = model.getStopOutLevel() == null ? 100 : model.getStopOutLevel();

		if (model.getStopOutLevel() != null) {
			getViewState().setStopOutLevel(model.getStopOutLevel());
		}
		if (model.getInvestmentLimit() != null) {
			getViewState().setInvestmentLimit(model.getInvestmentLimit());
		}
		if (model.getEntryFee() != null) {
			getViewState().setEntryFee(model.getEntryFee());
		}
		if (model.getSuccessFee() != null) {
			getViewState().setSuccessFee(model.getSuccessFee());
		}
		getPlatformInfo();
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
		if (stopOut > Constants.MAX_STOP_OUT_LEVEL) {
			getViewState().setStopOutLevel(Constants.MAX_STOP_OUT_LEVEL);
			return;
		}
		else if (stopOut > maxStopOutLevel) {
			getViewState().setStopOutLevel(maxStopOutLevel);
			getViewState().showStopOutError(context.getString(R.string.error_stop_out_max));
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

	void onEntryFeeChanged(String entryFeeString) {
		try {
			entryFee = Double.parseDouble(entryFeeString);
		} catch (NumberFormatException e) {
			entryFee = 0;
		}
		if (entryFee > maxEntryFee) {
			getViewState().setEntryFee(maxEntryFee);
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
		newModel.setInvestmentLimit(isInvestmentLimitEnabled ? null : investmentLimit);
		newModel.setStopOutLevel(stopOut);
		newModel.setEntryFee(entryFee);
		newModel.setSuccessFee(successFee);
		EventBus.getDefault().post(new OnProgramSettingsConfirmEvent(newModel));
	}

	private void updateConfirmButtonAvailability() {
		getViewState().setConfirmButtonEnabled(entryFee <= maxEntryFee && successFee <= maxSuccessFee);
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
		ProgramCreateAssetPlatformInfo info = platformInfo.getAssetInfo().getProgramInfo().getCreateProgramInfo();
		maxEntryFee = info.getMaxEntryFee();
		maxSuccessFee = info.getMaxSuccessFee();
		getViewState().updateEntryFeeDescription(maxEntryFee);
		getViewState().updateSuccessFeeDescription(maxSuccessFee);

		periods = platformInfo.getAssetInfo().getProgramInfo().getPeriods();

		ArrayList<String> periodLengthOptions = new ArrayList<>();
		for (Integer period : periods) {
			periodLengthOptions.add(String.format(Locale.getDefault(), "%d %s", period, context.getResources().getQuantityString(R.plurals.days, period)));
		}
		getViewState().setPeriodLengthOptions(periodLengthOptions);

		int pos = 0;
		if (model.getPeriodLength() != null) {
			pos = periods.indexOf(model.getPeriodLength());
		}
		onPeriodLengthOptionSelected(pos, periodLengthOptions.get(pos));
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
}
