package vision.genesis.clientapp.feature.auth.registration;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface RegistrationView extends MvpView
{
	void setUserNameError(String message);

	void setEmailError(String message);

	void setPasswordError(String message);

	void setConfirmPasswordError(String message);

	void clearErrors();

	void setSignUpButtonEnabled(boolean enabled);

	void showProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showLoginActivity();

	void showMessageDialog(int imageResourceId, String title, String message, boolean mustRead, MessageBottomSheetDialog.OnButtonClickListener listener);

	void finishActivity();
}
