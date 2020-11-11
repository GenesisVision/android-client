package vision.genesis.clientapp.feature.main.fund.self_managed.make_public;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/11/2020.
 */

public interface MakePublicFundView extends MvpView
{
	void showTitleError(String errorText);

	void showDescriptionError(String errorText);

	void cleanTitleError();

	void cleanDescriptionError();

	void updateEntryFeeDescription(Double managerMaxEntryFee);

	void updateExitFeeDescription(Double managerMaxExitFee);

	void setEntryFee(Double entryFeeValue);

	void setExitFee(Double exitFeeValue);

	void setButtonEnabled(boolean enabled);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
