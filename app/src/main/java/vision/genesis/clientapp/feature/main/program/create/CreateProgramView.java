package vision.genesis.clientapp.feature.main.program.create;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.model.CreateProgramModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public interface CreateProgramView extends MvpView
{
	void initViewPager(Boolean needPublicInfo, Boolean needDeposit, CreateProgramModel model);

	void showSettings();

	void showDeposit();

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
