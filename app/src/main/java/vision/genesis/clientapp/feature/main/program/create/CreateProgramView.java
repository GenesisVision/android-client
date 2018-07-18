package vision.genesis.clientapp.feature.main.program.create;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2018.
 */

public interface CreateProgramView extends MvpView
{
	void onBackPressed();

	void showNextStep();

	void showProgress(boolean show);

	void showSnackbar(String text);

	void finishActivity();

	void hideKeyboard();
}
