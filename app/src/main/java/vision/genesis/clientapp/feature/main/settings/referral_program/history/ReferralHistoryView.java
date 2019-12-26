package vision.genesis.clientapp.feature.main.settings.referral_program.history;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.RewardDetails;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

interface ReferralHistoryView extends MvpView
{
	void setEvents(List<RewardDetails> newEvents);

	void addEvents(List<RewardDetails> newEvents);

	void showProgress(boolean show);

	void showEmpty(boolean show);

	void showSnackbarMessage(String message);

	void setDateRange(DateRange dateRange);
}
