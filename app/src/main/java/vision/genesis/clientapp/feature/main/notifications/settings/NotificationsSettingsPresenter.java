package vision.genesis.clientapp.feature.main.notifications.settings;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.NotificationSettingList;
import io.swagger.client.model.NotificationSettingViewModel;
import io.swagger.client.model.NotificationType;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.NotificationsManager;
import vision.genesis.clientapp.model.events.ShowFundNotificationsSettingsEvent;
import vision.genesis.clientapp.model.events.ShowProgramNotificationsSettingsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/10/2018.
 */

@InjectViewState
public class NotificationsSettingsPresenter extends MvpPresenter<NotificationsSettingsView>
{
	@Inject
	public NotificationsManager notificationsManager;

	private Subscription settingsSubscription;

	private Subscription settingSwitchSubscription;

	private NotificationSettingList settings;

	private NotificationSettingViewModel newsSetting;

	private NotificationSettingViewModel emergencySetting;

	private NotificationSettingViewModel socialSetting;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		initSettings();

		getViewState().showProgress(true);
		getSettings();
	}

	@Override
	public void onDestroy() {
		if (settingsSubscription != null) {
			settingsSubscription.unsubscribe();
		}
		if (settingSwitchSubscription != null) {
			settingSwitchSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onShow() {
		getSettings();
	}

	private void initSettings() {
		newsSetting = new NotificationSettingViewModel();
		newsSetting.setType(NotificationType.PLATFORMNEWSANDUPDATES);
		newsSetting.setIsEnabled(false);

		emergencySetting = new NotificationSettingViewModel();
		emergencySetting.setType(NotificationType.PLATFORMEMERGENCY);
		emergencySetting.setIsEnabled(false);

		socialSetting = new NotificationSettingViewModel();
		socialSetting.setType(NotificationType.SOCIAL);
		socialSetting.setIsEnabled(false);
	}

	private void getSettings() {
		if (settingsSubscription != null) {
			settingsSubscription.unsubscribe();
		}
		settingsSubscription = notificationsManager.getNotificationsSettings()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::handleGetSettingsResponse,
						this::handleGetSettingsError);
	}

	private void handleGetSettingsResponse(NotificationSettingList response) {
		settingsSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.settings = response;

		for (NotificationSettingViewModel setting : settings.getSettingsGeneral()) {
			switch (setting.getType()) {
				case PLATFORMNEWSANDUPDATES:
					this.newsSetting = setting;
					getViewState().setNewsChecked(setting.isIsEnabled());
					break;
				case PLATFORMEMERGENCY:
					this.emergencySetting = setting;
					getViewState().setEmergencyChecked(setting.isIsEnabled());
					break;
				case SOCIAL:
					this.socialSetting = setting;
					getViewState().setSocialChecked(setting.isIsEnabled());
					break;
			}
		}

		getViewState().setProgramsSettings(response.getSettingsProgram());
		getViewState().setFundsSettings(response.getSettingsFund());
	}

	private void handleGetSettingsError(Throwable throwable) {
		settingsSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	public void onNewsClicked() {
		switchSetting(newsSetting);
	}

	public void onEmergencyClicked() {
		switchSetting(emergencySetting);
	}

	public void onSocialClicked() {
		switchSetting(socialSetting);
	}

	private void switchSetting(NotificationSettingViewModel setting) {
		if (setting != null) {
			if (settingSwitchSubscription != null) {
				settingSwitchSubscription.unsubscribe();
			}
			if (!setting.isIsEnabled()) {
				settingSwitchSubscription = notificationsManager.addNotificationSetting(null, null, setting.getType().getValue(), null, null)
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
		getViewState().setEmergencyChecked(emergencySetting.isIsEnabled());
		getViewState().setSocialChecked(socialSetting.isIsEnabled());

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(ShowProgramNotificationsSettingsEvent event) {
		getViewState().showProgramNotificationsSettings(event.getProgramId(), event.getProgramName());
	}

	@Subscribe
	public void onEventMainThread(ShowFundNotificationsSettingsEvent event) {
		getViewState().showFundNotificationsSettings(event.getFundId(), event.getFundName());
	}
}
