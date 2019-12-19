package vision.genesis.clientapp.feature.main.copytrading.subscription_settings;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import io.swagger.client.model.Currency;
import io.swagger.client.model.SubscriptionMode;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.model.events.OnSubscriptionSettingsConfirmEvent;
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

	private SubscriptionSettingsModel model;

	private ArrayList<String> typeOptions;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		updateData();
	}

	void setModel(SubscriptionSettingsModel model) {
		this.model = model;

		updateData();
	}

	private void updateData() {
		if (typeOptions == null && context != null) {
			typeOptions = new ArrayList<>();
			typeOptions.add(context.getString(R.string.by_balance));
			typeOptions.add(context.getString(R.string.percentage));
			typeOptions.add(context.getString(R.string.fixed));
			getViewState().setTypeOptions(typeOptions);
		}
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

			getViewState().setVolumePercentage(StringFormatUtil.formatAmount(model.getPercent(), 0, 2));
			getViewState().setEquivalent(StringFormatUtil.formatAmountWithoutGrouping(model.getFixedVolume()));
			getViewState().setTolerancePercentage(StringFormatUtil.formatAmount(model.getTolerancePercent(), 0, 2));

			updateConfirmButtonEnabled();
		}
	}

	void onLabelVolumePercentageClicked() {
		getViewState().setVolumePercentage(StringFormatUtil.formatAmount(SubscriptionSettingsModel.VOLUME_PERCENTAGE_MIN, 0, 2));
	}

	void onLabelEquivalentClicked() {
		getViewState().setEquivalent(StringFormatUtil.formatAmountWithoutGrouping(SubscriptionSettingsModel.EQUIVALENT_MIN));
	}

	void onLabelTolerancePercentageClicked() {
		getViewState().setTolerancePercentage(StringFormatUtil.formatAmount(SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MIN, 0, 2));
	}

	void onVolumePercentageMaxClicked() {
		getViewState().setVolumePercentage(StringFormatUtil.formatAmount(SubscriptionSettingsModel.VOLUME_PERCENTAGE_MAX, 0, 2));
	}

	void onEquivalentMaxClicked() {
		getViewState().setEquivalent(StringFormatUtil.formatAmountWithoutGrouping(SubscriptionSettingsModel.EQUIVALENT_MAX));
	}

	void onTolerancePercentageMaxClicked() {
		getViewState().setTolerancePercentage(StringFormatUtil.formatAmount(SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MAX, 0, 2));
	}

	void onVolumePercentageChanged(String newValueString) {
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
		updateConfirmButtonEnabled();
	}

	void onEquivalentChanged(String newValueString) {
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
		model.setFixedCurrency(Currency.USD.getValue());
		updateConfirmButtonEnabled();
	}

	void onTolerancePercentageChanged(String newValueString) {
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

		model.setTolerancePercent(newValue);
		updateConfirmButtonEnabled();
	}

	private boolean isDataOk() {
		if (model == null || model.getMode() == null) {
			return false;
		}
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
		return model.getTolerancePercent() >= SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MIN
				&& model.getTolerancePercent() <= SubscriptionSettingsModel.TOLERANCE_PERCENTAGE_MAX;
	}

	private void updateConfirmButtonEnabled() {
		getViewState().setConfirmButtonEnabled(isDataOk());
	}

	void onConfirmButtonClicked() {
		if (model != null) {
			EventBus.getDefault().post(new OnSubscriptionSettingsConfirmEvent(model.getApiModel()));
		}
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
		updateConfirmButtonEnabled();
	}
}
