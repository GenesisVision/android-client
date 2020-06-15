package vision.genesis.clientapp.feature.main.settings.referral_program;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

interface ReferralProgramView extends MvpView
{
	void setReferralFriendsCount(Integer friendsCount);

	void setHistoryCount(Integer historyCount);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();
}