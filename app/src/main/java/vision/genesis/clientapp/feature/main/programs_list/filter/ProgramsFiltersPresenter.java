package vision.genesis.clientapp.feature.main.programs_list.filter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramsFilter;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.events.ProgramsListFiltersAppliedEvent;
import vision.genesis.clientapp.model.events.ProgramsListFiltersClearedEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

@InjectViewState
public class ProgramsFiltersPresenter extends MvpPresenter<ProgramsFiltersView>
{
	@Inject
	public Context context;

	@Inject
	public InvestManager investManager;

	private InvestmentProgramsFilter filter;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		filter = investManager.getFilter();
		getViewState().setFilterData(filter);
	}

	void onBackClicked() {
		getViewState().finishActivity();
	}

	void setLevel(String minLevel, String maxLevel) {
		filter.setLevelMin(Integer.parseInt(minLevel));
		filter.setLevelMax(Integer.parseInt(maxLevel));
	}

	void setAvgProfit(String minLevel, String maxLevel) {
		filter.setProfitAvgPercentMin(Integer.parseInt(minLevel));
		filter.setProfitAvgPercentMax(Integer.parseInt(maxLevel));
	}

	void onActiveProgramsOnlyChanged(boolean checked) {
		filter.setShowActivePrograms(checked);
	}

	void onFavoriteProgramsOnlyChanged(boolean checked) {
		filter.setShowMyFavorites(checked);
	}

	void onApplyClicked() {
		investManager.setFilter(filter);
		EventBus.getDefault().post(new ProgramsListFiltersAppliedEvent());
		onBackClicked();
	}

	void onClearClicked() {
		String name = filter.getName();
		filter = new InvestmentProgramsFilter();
		filter.setName(name);
		investManager.setFilter(filter);
		EventBus.getDefault().post(new ProgramsListFiltersClearedEvent());
		onBackClicked();
	}
}
