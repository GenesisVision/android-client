package vision.genesis.clientapp.feature.main.traders.filter;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.InvestmentProgramsFilter;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

interface TradersFiltersView extends MvpView
{
	void setFilterData(InvestmentProgramsFilter filter);

	void finishActivity();
}
