package vision.genesis.clientapp.feature.main.fund.create;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

public interface CreateFundView extends MvpView
{
	void setMinDepositAmount(Double minDepositAmount);

	void showSnackbarMessage(String message);

	void finishActivity();

	void showProgress(boolean show);

	void showNextStep();
}
