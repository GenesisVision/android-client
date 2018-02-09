package vision.genesis.clientapp.feature.tournament.leaderboard;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVision
 * Created by Vitaly on 2/9/18.
 */

interface LeaderboardView extends MvpView
{
	void showLoading(boolean show);

	void showCannotLoad(boolean show);
}
