package vision.genesis.clientapp.feature.main.notifications.settings;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.FundNotificationSettingList;
import io.swagger.client.model.ProgramNotificationSettingList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/10/2018.
 */

interface NotificationsSettingsView extends MvpView
{
	void setNewsChecked(Boolean checked);

	void setEmergencyChecked(Boolean checked);

	void setSocialChecked(Boolean checked);

	void setProgramsSettings(List<ProgramNotificationSettingList> settings);

	void setFundsSettings(List<FundNotificationSettingList> settings);

	void showProgramNotificationsSettings(UUID programId, String programName);

	void showFundNotificationsSettings(UUID fundId, String fundName);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
