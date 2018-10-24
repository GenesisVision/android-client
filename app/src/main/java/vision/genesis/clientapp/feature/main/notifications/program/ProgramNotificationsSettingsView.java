package vision.genesis.clientapp.feature.main.notifications.program;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.NotificationSettingViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/10/2018.
 */

interface ProgramNotificationsSettingsView extends MvpView
{
	void setNewsChecked(Boolean checked);

	void setPeriodChecked(Boolean checked);

	void setCustomSettings(List<NotificationSettingViewModel> settings);

	void setCustomSettingEnabled(UUID settingId, Boolean enabled);

	void deleteSetting(UUID settingId);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
