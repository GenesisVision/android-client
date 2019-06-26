package vision.genesis.clientapp.feature.main.copytrading.subscription_settings;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import io.swagger.client.model.AttachToSignalProvider;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.managers.SignalsManager;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.model.events.OnSubscribedToProgramEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/06/2019.
 */

@InjectViewState
public class SubscriptionSettingsPresenter extends MvpPresenter<SubscriptionSettingsView> implements
		SelectOptionBottomSheetFragment.OnOptionSelectedListener
{
	@Inject
	public Context context;

	@Inject
	public SignalsManager signalsManager;

	private Subscription signalSubscription;

	private SubscriptionSettingsModel model;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		initTypeOptions();
	}

	@Override
	public void onDestroy() {
		if (signalSubscription != null)
			signalSubscription.unsubscribe();

		super.onDestroy();
	}

	private void initTypeOptions() {
		ArrayList<String> typeOptions = new ArrayList<>();
		typeOptions.add(context.getString(R.string.by_balance));
		typeOptions.add(context.getString(R.string.percentage));
		typeOptions.add(context.getString(R.string.fixed));
		getViewState().setTypeOptions(typeOptions);
		onOptionSelected(0, typeOptions.get(0));
	}

	void setModel(SubscriptionSettingsModel model) {
		this.model = model;
	}


	public void onVolumePercentageMaxClicked() {
		getViewState().setVolumePercentage(StringFormatUtil.formatAmount(SubscriptionSettingsModel.VOLUME_PERCENTAGE_MAX, 0, 0));
	}

	public void onEquivalentMaxClicked() {
		getViewState().setEquivalent(StringFormatUtil.formatCurrencyAmount(SubscriptionSettingsModel.EQUIVALENT_MAX, SubscriptionSettingsModel.EQUIVALENT_CURRENCY));
	}

	public void onTolerancePercentageMaxClicked() {
		getViewState().setTolerancePercentage(StringFormatUtil.formatAmount(SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MAX, 0, 0));
	}

	public void onVolumePercentageChanged(String newValueString) {
		Double newValue;
		try {
			newValue = Double.parseDouble(newValueString);
		} catch (NumberFormatException e) {
			newValue = 0.0;
		}

		if (newValue > SubscriptionSettingsModel.VOLUME_PERCENTAGE_MAX) {
			getViewState().setVolumePercentage(StringFormatUtil.formatAmount(
					SubscriptionSettingsModel.VOLUME_PERCENTAGE_MAX, 0, 0));
			return;
		}

		model.setPercent(newValue);
		updateButtonEnabled();
	}

	public void onEquivalentChanged(String newValueString) {
		Double newValue;
		try {
			newValue = Double.parseDouble(newValueString);
		} catch (NumberFormatException e) {
			newValue = 0.0;
		}

		if (newValue > SubscriptionSettingsModel.EQUIVALENT_MAX) {
			getViewState().setEquivalent(SubscriptionSettingsModel.EQUIVALENT_MAX.toString());
			return;
		}

		model.setFixedVolume(newValue);
		updateButtonEnabled();
	}

	public void onTolerancePercentageChanged(String newValueString) {
		Double newValue;
		try {
			newValue = Double.parseDouble(newValueString);
		} catch (NumberFormatException e) {
			newValue = 0.0;
		}

		if (newValue > SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MAX) {
			getViewState().setTolerancePercentage(StringFormatUtil.formatAmount(
					SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MAX, 0, 0));
			return;
		}

		model.setOpenTolerancePercent(newValue);
		updateButtonEnabled();
	}

	private boolean isDataOk() {
		if (model.getMode().equals(AttachToSignalProvider.ModeEnum.PERCENT.getValue())) {
			if (!(model.getPercent() >= SubscriptionSettingsModel.VOLUME_PERCENTAGE_MIN
					&& model.getPercent() <= SubscriptionSettingsModel.VOLUME_PERCENTAGE_MAX))
				return false;
		}
		if (model.getMode().equals(AttachToSignalProvider.ModeEnum.FIXED.getValue())) {
			if (!(model.getFixedVolume() >= SubscriptionSettingsModel.EQUIVALENT_MIN
					&& model.getFixedVolume() <= SubscriptionSettingsModel.EQUIVALENT_MAX))
				return false;
		}
		return model.getOpenTolerancePercent() >= SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MIN
				&& model.getOpenTolerancePercent() <= SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MAX;
	}

	private void updateButtonEnabled() {
		getViewState().setButtonEnabled(isDataOk());
	}

	void onButtonClicked() {
		subscribeToSignals();
	}

	private void subscribeToSignals() {
		if (signalsManager != null && model != null) {
			if (signalSubscription != null)
				signalSubscription.unsubscribe();
			signalSubscription = signalsManager.subscribeToProgram(model)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleSubscribeSuccess,
							this::handleSubscribeError);
		}
	}

	private void handleSubscribeSuccess(Void response) {
		signalSubscription.unsubscribe();
		EventBus.getDefault().post(new OnSubscribedToProgramEvent());
		getViewState().finishActivity();
	}

	private void handleSubscribeError(Throwable throwable) {
		signalSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}


	@Override
	public void onOptionSelected(Integer position, String text) {
		String typeDescription = "";
		switch (position) {
			case 0:
				this.model.setMode(AttachToSignalProvider.ModeEnum.BYBALANCE.getValue());
				getViewState().showByBalanceFields();
				typeDescription = context.getString(R.string.type_description_by_balance);
				break;
			case 1:
				this.model.setMode(AttachToSignalProvider.ModeEnum.PERCENT.getValue());
				getViewState().showPercentageFields();
				typeDescription = context.getString(R.string.type_description_percentage);
				break;
			case 2:
				this.model.setMode(AttachToSignalProvider.ModeEnum.FIXED.getValue());
				getViewState().showFixedFields();
				typeDescription = context.getString(R.string.type_description_fixed);
				break;
		}
		getViewState().setType(text, position);
		getViewState().setTypeDescription(typeDescription);
		updateButtonEnabled();
	}
}
