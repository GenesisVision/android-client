package vision.genesis.clientapp.feature.main.programs_list.filter;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.InvestmentProgramsFilter;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

interface ProgramsFiltersView extends MvpView
{
	void setFilterData(InvestmentProgramsFilter filter);

	void finishActivity();
}
