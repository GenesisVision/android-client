package vision.genesis.clientapp.feature.main.traders.filter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramsFilter;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.FilterSortingOption;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

@InjectViewState
public class TradersFiltersPresenter extends MvpPresenter<TradersFiltersView>
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

	void onSortingSelected(FilterSortingOption selectedOption) {
		filter.setSorting(selectedOption.option);
	}

	void onApplyClicked() {
		investManager.setFilter(filter);
		onBackClicked();
	}

	void onClearClicked() {
		filter = new InvestmentProgramsFilter();
		investManager.setFilter(filter);
		onBackClicked();
	}
}
