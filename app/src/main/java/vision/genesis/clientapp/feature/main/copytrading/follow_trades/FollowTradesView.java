package vision.genesis.clientapp.feature.main.copytrading.follow_trades;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.ProgramFollowDetailsFull;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */

public interface FollowTradesView extends MvpView
{
	void initViewPager(ProgramFollowDetailsFull details);

	void showSettings();

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
