package vision.genesis.clientapp.feature.main.program.withdraw;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

interface WithdrawProgramView extends MvpView
{
	void setWithdrawButtonEnabled(boolean enabled);

	void setAmount(double amount);

	void setAvailable(double availableFunds);

	void showToastMessage(String message);

	void setFiatBalance(Double fiatBalance);

	void setFiatAmount(Double fiatAmount);

	void showAmountHint(boolean show);

	void setKeyboardKeysEnabled(boolean enabled);

	void finishActivity();
}
