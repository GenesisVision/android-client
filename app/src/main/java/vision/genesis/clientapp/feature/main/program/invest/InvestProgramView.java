package vision.genesis.clientapp.feature.main.program.invest;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

interface InvestProgramView extends MvpView
{
	void setInvestButtonEnabled(boolean enabled);

	void setAmount(double amount);

	void setAvailable(double availableFunds);

	void showAvailableProgress(boolean show);

	void setFiatBalance(Double fiatBalance);

	void setFiatAmount(Double fiatAmount);

	void showToastMessage(String message);

	void finishActivity();
}
