package vision.genesis.clientapp.feature.main.filters;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.model.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/11/2018.
 */

interface FiltersView extends MvpView
{
	void filterUpdated(ProgramsFilter filter);

	void setApplyButtonEnabled(boolean enabled);
}
