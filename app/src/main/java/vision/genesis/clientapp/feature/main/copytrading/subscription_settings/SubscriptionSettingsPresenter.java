package vision.genesis.clientapp.feature.main.copytrading.subscription_settings;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import io.swagger.client.model.SubscriptionMode;
import rx.Observable;
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

	private boolean isEdit;

	private ArrayList<String> typeOptions;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
	}

	@Override
	public void onDestroy() {
		if (signalSubscription != null) {
			signalSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	private void initTypeOptions() {
		typeOptions = new ArrayList<>();
		typeOptions.add(context.getString(R.string.by_balance));
		typeOptions.add(context.getString(R.string.percentage));
		typeOptions.add(context.getString(R.string.fixed));
		getViewState().setTypeOptions(typeOptions);
	}

	void setData(SubscriptionSettingsModel model, boolean isEdit) {
		this.model = model;
		this.isEdit = isEdit;

		GenesisVisionApplication.getComponent().inject(this);

		initTypeOptions();
		updateData();
	}

	private void updateData() {
		if (model != null && typeOptions != null) {
			SubscriptionMode[] modes = SubscriptionMode.values();
			int selectedOptionPosition = 0;
			for (int i = 0; i < modes.length; i++) {
				if (modes[i].getValue().equals(model.getMode())) {
					selectedOptionPosition = i;
					break;
				}
			}
			onOptionSelected(selectedOptionPosition, typeOptions.get(selectedOptionPosition));

//			for (String typeOption : typeOptions) {
//				if (typeOption.equals(model.getMode())) {
//					onOptionSelected(typeOptions.indexOf(typeOption), typeOption);
//					break;
//				}
//			}

			getViewState().setVolumePercentage(StringFormatUtil.formatAmount(model.getPercent(), 0, 2));
			getViewState().setEquivalent(StringFormatUtil.formatAmountWithoutGrouping(model.getFixedVolume()));
			getViewState().setTolerancePercentage(StringFormatUtil.formatAmount(model.getOpenTolerancePercent(), 0, 2));

			updateButtonEnabled();
		}
	}

	public void onLabelVolumePercentageClicked() {
		getViewState().setVolumePercentage(StringFormatUtil.formatAmount(SubscriptionSettingsModel.VOLUME_PERCENTAGE_MIN, 0, 2));
	}

	public void onLabelEquivalentClicked() {
		getViewState().setEquivalent(StringFormatUtil.formatAmountWithoutGrouping(SubscriptionSettingsModel.EQUIVALENT_MIN));
	}

	public void onLabelTolerancePercentageClicked() {
		getViewState().setTolerancePercentage(StringFormatUtil.formatAmount(SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MIN, 0, 2));
	}

	public void onVolumePercentageMaxClicked() {
		getViewState().setVolumePercentage(StringFormatUtil.formatAmount(SubscriptionSettingsModel.VOLUME_PERCENTAGE_MAX, 0, 2));
	}

	public void onEquivalentMaxClicked() {
		getViewState().setEquivalent(StringFormatUtil.formatAmountWithoutGrouping(SubscriptionSettingsModel.EQUIVALENT_MAX));
	}

	public void onTolerancePercentageMaxClicked() {
		getViewState().setTolerancePercentage(StringFormatUtil.formatAmount(SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MAX, 0, 2));
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
			getViewState().setEquivalent(StringFormatUtil.formatAmountWithoutGrouping(SubscriptionSettingsModel.EQUIVALENT_MAX));
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
		if (model.getMode().equals(SubscriptionMode.PERCENT.getValue())) {
			if (!(model.getPercent() >= SubscriptionSettingsModel.VOLUME_PERCENTAGE_MIN
					&& model.getPercent() <= SubscriptionSettingsModel.VOLUME_PERCENTAGE_MAX)) {
				return false;
			}
		}
		if (model.getMode().equals(SubscriptionMode.FIXED.getValue())) {
			if (!(model.getFixedVolume() >= SubscriptionSettingsModel.EQUIVALENT_MIN
					&& model.getFixedVolume() <= SubscriptionSettingsModel.EQUIVALENT_MAX)) {
				return false;
			}
		}
		return model.getOpenTolerancePercent() >= SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MIN
				&& model.getOpenTolerancePercent() <= SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MAX;
	}

	private void updateButtonEnabled() {
		getViewState().setButtonEnabled(isDataOk());
	}

	void onButtonClicked() {
		if (isEdit) {
			updateSubscription();
		}
		else {
			subscribeToSignals();
		}
	}

	private void updateSubscription() {
		performRequest(signalsManager.updateSubscription(model));
	}

	private void subscribeToSignals() {
		performRequest(signalsManager.subscribeToProgram(model));
	}

	private void performRequest(Observable<Void> request) {
		if (signalsManager != null && model != null) {
			if (signalSubscription != null) {
				signalSubscription.unsubscribe();
			}
			getViewState().showProgress(true);
			signalSubscription = request
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleSubscriptionSuccess,
							this::handleSubscriptionError);
		}
	}

	private void handleSubscriptionSuccess(Void response) {
		signalSubscription.unsubscribe();
		EventBus.getDefault().post(new OnSubscribedToProgramEvent());
		getViewState().finishActivity();
	}

	private void handleSubscriptionError(Throwable throwable) {
		signalSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}


	@Override
	public void onOptionSelected(Integer position, String text) {
		String typeDescription = "";
		switch (position) {
			case 0:
				this.model.setMode(SubscriptionMode.BYBALANCE.getValue());
				getViewState().showByBalanceFields();
				typeDescription = context.getString(R.string.type_description_subscribe_by_balance);
				break;
			case 1:
				this.model.setMode(SubscriptionMode.PERCENT.getValue());
				getViewState().showPercentageFields();
				typeDescription = context.getString(R.string.type_description_subscribe_percentage);
				break;
			case 2:
				this.model.setMode(SubscriptionMode.FIXED.getValue());
				getViewState().showFixedFields();
				typeDescription = context.getString(R.string.type_description_subscribe_fixed);
				break;
		}
		getViewState().setType(text, position);
		getViewState().setTypeDescription(typeDescription);
		updateButtonEnabled();
	}
}
