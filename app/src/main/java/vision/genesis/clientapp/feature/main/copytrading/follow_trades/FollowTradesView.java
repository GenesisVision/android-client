package vision.genesis.clientapp.feature.main.copytrading.follow_trades;

import com.arellomobile.mvp.MvpView;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */

public interface FollowTradesView extends MvpView
{
	void initViewPager(UUID followId);

	void showSettings();

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
