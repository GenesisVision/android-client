package vision.genesis.clientapp.feature.main.notifications.follow;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FollowNotificationSettingList;
import io.swagger.client.model.NotificationSettingViewModel;
import io.swagger.client.model.NotificationType;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.NotificationsManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/07/2020.
 */

@InjectViewState
public class FollowNotificationsSettingsPresenter extends MvpPresenter<FollowNotificationsSettingsView>
{
	@Inject
	public NotificationsManager notificationsManager;

	private Subscription settingsSubscription;

	private Subscription settingSwitchSubscription;

	private NotificationSettingViewModel newsSetting;

	private UUID followId;

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
		if (settingsSubscription != null) {
			settingsSubscription.unsubscribe();
		}
		if (settingSwitchSubscription != null) {
			settingSwitchSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setFollowId(UUID followId) {
		this.followId = followId;
		getSettings();
	}

	void onShow() {
		getSettings();
	}

	private void initSettings() {
		newsSetting = new NotificationSettingViewModel();
		newsSetting.setType(NotificationType.FOLLOWNEWSANDUPDATES);
		newsSetting.setIsEnabled(false);
	}

	private void getSettings() {
		if (followId != null && notificationsManager != null) {
			if (settingsSubscription != null) {
				settingsSubscription.unsubscribe();
			}
			settingsSubscription = notificationsManager.getFollowNotificationsSettings(followId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetSettingsResponse,
							this::handleGetSettingsError);
		}
	}

	private void handleGetSettingsResponse(FollowNotificationSettingList response) {
		settingsSubscription.unsubscribe();
		getViewState().showProgress(false);

		for (NotificationSettingViewModel setting : response.getSettingsGeneral()) {
			switch (setting.getType()) {
				case FOLLOWNEWSANDUPDATES:
					this.newsSetting = setting;
					getViewState().setNewsChecked(setting.isIsEnabled());
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

	private void switchSetting(NotificationSettingViewModel setting) {
		if (setting != null) {
			if (settingSwitchSubscription != null) {
				settingSwitchSubscription.unsubscribe();
			}
			if (!setting.isIsEnabled()) {
				settingSwitchSubscription = notificationsManager.addNotificationSetting(followId, null, setting.getType().getValue(), null, null)
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

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}
}
