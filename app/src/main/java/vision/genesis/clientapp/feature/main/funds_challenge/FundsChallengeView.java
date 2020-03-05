package vision.genesis.clientapp.feature.main.funds_challenge;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.FundDetailsListItem;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/03/2020.
 */

interface FundsChallengeView extends MvpView
{
	void setWinner(FundDetailsListItem winner);

	void setRefreshing(boolean refreshing);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
