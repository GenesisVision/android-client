package vision.genesis.clientapp.feature.main.program.details;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.InvestmentProgramDetails;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

interface ProgramDetailsView extends MvpView
{
	void setProgram(InvestmentProgramDetails programDetails);

	void showInvestDialog();

	void showInvestWithdrawButtons(boolean show);

	void showProgress(boolean show);

	void finishActivity();
}
