package vision.genesis.clientapp.feature.main.fund.self_managed.create;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/11/2020.
 */

public interface CreateSelfManagedFundView extends MvpView
{
	void setMinDepositAmount(Double minDepositAmount);

	void showSnackbarMessage(String message);

	void finishActivity();

	void showProgress(boolean show);

	void showNextStep();
}
