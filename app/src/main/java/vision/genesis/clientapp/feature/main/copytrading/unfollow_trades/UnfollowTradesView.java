package vision.genesis.clientapp.feature.main.copytrading.unfollow_trades;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/06/2019.
 */

interface UnfollowTradesView extends MvpView
{
	void setTypeOptions(ArrayList<String> typeOptions);

	void setType(String type, Integer position);

	void setTypeDescription(String typeDescription);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
