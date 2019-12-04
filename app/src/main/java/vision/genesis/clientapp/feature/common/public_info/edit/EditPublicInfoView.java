package vision.genesis.clientapp.feature.common.public_info.edit;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/11/2019.
 */

public interface EditPublicInfoView extends MvpView
{
	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
