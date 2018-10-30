package vision.genesis.clientapp.feature.main.notifications.fund;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FundNotificationSettingList;
import io.swagger.client.model.NotificationSettingViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.NotificationsManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/10/2018.
 */

@InjectViewState
public class FundNotificationsSettingsPresenter extends MvpPresenter<FundNotificationsSettingsView>
{
	@Inject
	public NotificationsManager notificationsManager;

	private Subscription settingsSubscription;

	private Subscription settingSwitchSubscription;

	private NotificationSettingViewModel newsSetting;

	private NotificationSettingViewModel structureSetting;

	private UUID fundId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		initSettings();

		getViewState().showProgress(true);
		getSettings();
	}

	@Override
	public void onDestroy() {
		if (settingsSubscription != null)
			settingsSubscription.unsubscribe();
		if (settingSwitchSubscription != null)
			settingSwitchSubscription.unsubscribe();

		super.onDestroy();
	}

	void setFundId(UUID programId) {
		this.fundId = programId;
		getSettings();
	}

	void onShow() {
		getSettings();
	}

	private void initSettings() {
		newsSetting = new NotificationSettingViewModel();
		newsSetting.setType(NotificationSettingViewModel.TypeEnum.FUNDNEWSANDUPDATES);
		newsSetting.setIsEnabled(false);

		structureSetting = new NotificationSettingViewModel();
		structureSetting.setType(NotificationSettingViewModel.TypeEnum.FUNDREBALANCING);
		structureSetting.setIsEnabled(false);
	}

	private void getSettings() {
		if (fundId != null && notificationsManager != null) {
			if (settingsSubscription != null)
				settingsSubscription.unsubscribe();
			settingsSubscription = notificationsManager.getFundNotificationsSettings(fundId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetSettingsResponse,
							this::handleGetSettingsError);
		}
	}

	private void handleGetSettingsResponse(FundNotificationSettingList response) {
		settingsSubscription.unsubscribe();
		getViewState().showProgress(false);

		for (NotificationSettingViewModel setting : response.getSettingsGeneral()) {
			switch (setting.getType()) {
				case FUNDNEWSANDUPDATES:
					this.newsSetting = setting;
					getViewState().setNewsChecked(setting.isIsEnabled());
					break;
				case FUNDREBALANCING:
					this.structureSetting = setting;
					getViewState().setStructureChecked(setting.isIsEnabled());
					break;
			}
		}
	}

	private void handleGetSettingsError(Throwable throwable) {
		settingsSubscription.unsubscribe();
//		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	public void onNewsClicked() {
		switchSetting(newsSetting);
	}

	public void onStructureClicked() {
		switchSetting(structureSetting);
	}

	private void switchSetting(NotificationSettingViewModel setting) {
		if (setting != null) {
			if (settingSwitchSubscription != null)
				settingSwitchSubscription.unsubscribe();
			if (!setting.isIsEnabled()) {
				settingSwitchSubscription = notificationsManager.addNotificationSetting(fundId, null, setting.getType().getValue(), null, null)
						.observeOn(AndroidSchedulers.mainThread())
						.subscribeOn(Schedulers.newThread())
						.subscribe(newId -> handleAddSettingResponse(setting, newId), this::handleSwitchNewsError);
			}
			else {
				settingSwitchSubscription = notificationsManager.removeNotificationSetting(setting.getId())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribeOn(Schedulers.newThread())
						.subscribe(response -> handleRemoveSettingResponse(setting), this::handleSwitchNewsError);
			}
		}
	}

	private void handleAddSettingResponse(NotificationSettingViewModel setting, UUID newId) {
		settingSwitchSubscription.unsubscribe();

		setting.setIsEnabled(true);
		setting.setId(newId);
	}

	private void handleRemoveSettingResponse(NotificationSettingViewModel setting) {
		settingSwitchSubscription.unsubscribe();

		setting.setIsEnabled(false);
	}

	private void handleSwitchNewsError(Throwable throwable) {
		settingSwitchSubscription.unsubscribe();

		getViewState().setNewsChecked(newsSetting.isIsEnabled());
		getViewState().setStructureChecked(structureSetting.isIsEnabled());

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}
}
