package vision.genesis.clientapp.feature.main.traders.filter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentsFilter;
import ru.terrakok.cicerone.Router;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.Screens;
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

	private Router localRouter;

	private InvestmentsFilter filter;

	TradersFiltersPresenter(Router router) {
		this.localRouter = router;
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		filter = investManager.getFilter();
		getViewState().setFilterData(filter);
	}

	void onBackClicked() {
		localRouter.backTo(Screens.TRADERS);
	}

	void onSortingSelected(FilterSortingOption selectedOption) {
		filter.setSorting(selectedOption.option);
	}

	void onApplyClicked() {
		investManager.setFilter(filter);
		onBackClicked();
	}

	void onClearClicked() {
		filter = new InvestmentsFilter();
		investManager.setFilter(filter);
		onBackClicked();
	}
}
