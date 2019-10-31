package vision.genesis.clientapp.feature.main.notifications.program;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.NotificationSettingViewModel;
import io.swagger.client.model.NotificationType;
import io.swagger.client.model.ProgramNotificationSettingList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.NotificationsManager;
import vision.genesis.clientapp.model.events.OnCustomNotificationSettingSetEnabledEvent;
import vision.genesis.clientapp.model.events.OnDeleteCustomNotificationSettingClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/10/2018.
 */

@InjectViewState
public class ProgramNotificationsSettingsPresenter extends MvpPresenter<ProgramNotificationsSettingsView>
{
	@Inject
	public NotificationsManager notificationsManager;

	private Subscription settingsSubscription;

	private Subscription settingSwitchSubscription;

	private Subscription deleteSettingSubscription;

	private Subscription setEnabledSettingSubscription;

	private NotificationSettingViewModel newsSetting;

	private NotificationSettingViewModel periodSetting;

	private UUID programId;

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
		if (deleteSettingSubscription != null) {
			deleteSettingSubscription.unsubscribe();
		}
		if (setEnabledSettingSubscription != null) {
			setEnabledSettingSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setProgramId(UUID programId) {
		this.programId = programId;
		getSettings();
	}

	void onShow() {
		getSettings();
	}

	private void initSettings() {
		newsSetting = new NotificationSettingViewModel();
		newsSetting.setType(NotificationType.PROGRAMNEWSANDUPDATES);
		newsSetting.setIsEnabled(false);

		periodSetting = new NotificationSettingViewModel();
		periodSetting.setType(NotificationType.PROGRAMENDOFPERIOD);
		periodSetting.setIsEnabled(false);
	}

	private void getSettings() {
		if (programId != null && notificationsManager != null) {
			if (settingsSubscription != null) {
				settingsSubscription.unsubscribe();
			}
			settingsSubscription = notificationsManager.getProgramNotificationsSettings(programId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetSettingsResponse,
							this::handleGetSettingsError);
		}
	}

	private void handleGetSettingsResponse(ProgramNotificationSettingList response) {
		settingsSubscription.unsubscribe();
		getViewState().showProgress(false);

		for (NotificationSettingViewModel setting : response.getSettingsGeneral()) {
			switch (setting.getType()) {
				case PROGRAMNEWSANDUPDATES:
					this.newsSetting = setting;
					getViewState().setNewsChecked(setting.isIsEnabled());
					break;
				case PROGRAMENDOFPERIOD:
					this.periodSetting = setting;
					getViewState().setPeriodChecked(setting.isIsEnabled());
					break;
			}
		}

		getViewState().setCustomSettings(response.getSettingsCustom());
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

	public void onPeriodClicked() {
		switchSetting(periodSetting);
	}

	private void switchSetting(NotificationSettingViewModel setting) {
		if (setting != null) {
			if (settingSwitchSubscription != null) {
				settingSwitchSubscription.unsubscribe();
			}
			if (!setting.isIsEnabled()) {
				settingSwitchSubscription = notificationsManager.addNotificationSetting(programId, null, setting.getType().getValue(), null, null)
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
		getViewState().setPeriodChecked(periodSetting.isIsEnabled());

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void deleteCustomNotificationSetting(UUID settingId) {
		if (deleteSettingSubscription != null) {
			deleteSettingSubscription.unsubscribe();
		}
		deleteSettingSubscription = notificationsManager.removeNotificationSetting(settingId)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(response -> handleDeleteCustomNotificationSuccess(settingId),
						this::handleDeleteCustomNotificationError);
	}

	private void handleDeleteCustomNotificationSuccess(UUID settingId) {
		deleteSettingSubscription.unsubscribe();

		getViewState().deleteSetting(settingId);
	}

	private void handleDeleteCustomNotificationError(Throwable throwable) {
		deleteSettingSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void setCustomNotificationSettingEnabled(UUID settingId, Boolean enabled) {
		if (setEnabledSettingSubscription != null) {
			setEnabledSettingSubscription.unsubscribe();
		}
		setEnabledSettingSubscription = notificationsManager.setEnabledNotificationSetting(settingId, enabled)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(response -> handleSetEnabledCustomNotificationSuccess(settingId),
						throwable -> handleSetEnabledCustomNotificationError(throwable, settingId, enabled));
	}

	private void handleSetEnabledCustomNotificationSuccess(UUID settingId) {
		setEnabledSettingSubscription.unsubscribe();

		getViewState().deleteSetting(settingId);
	}

	private void handleSetEnabledCustomNotificationError(Throwable throwable, UUID settingId, Boolean enabled) {
		setEnabledSettingSubscription.unsubscribe();
	}

	@Subscribe
	public void onEventMainThread(OnDeleteCustomNotificationSettingClickedEvent event) {
		deleteCustomNotificationSetting(event.getSettingId());
	}

	@Subscribe
	public void onEventMainThread(OnCustomNotificationSettingSetEnabledEvent event) {
		setCustomNotificationSettingEnabled(event.getSettingId(), event.isEnabled());
	}
}
