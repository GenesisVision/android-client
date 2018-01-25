package vision.genesis.clientapp.feature.main.traders;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.InvestmentProgram;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface TradersView extends MvpView
{
	void setInvestmentPrograms(List<InvestmentProgram> programs);

	void addInvestmentPrograms(List<InvestmentProgram> programs);

	void setRefreshing(boolean refreshing);
}
