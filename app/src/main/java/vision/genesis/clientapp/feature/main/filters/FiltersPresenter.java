package vision.genesis.clientapp.feature.main.filters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.filters.sorting.SortingBottomSheetFragment;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.ProgramsFilter;
import vision.genesis.clientapp.model.SortingEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/11/2018.
 */

@InjectViewState
public class FiltersPresenter extends MvpPresenter<FiltersView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, SortingBottomSheetFragment.OnSortingChangedListener
{
	private ProgramsFilter oldFilter;

	private ProgramsFilter filter;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

	}

	void setFilter(ProgramsFilter filter) {
		this.oldFilter = filter;
		this.filter = new ProgramsFilter(filter);

		onFilterUpdated();
	}

	private void onFilterUpdated() {
		getViewState().filterUpdated(this.filter);
		getViewState().setApplyButtonEnabled(!oldFilter.equals(filter));
	}

	void onResetClicked() {
		filter.setLevelMin(null);
		filter.setLevelMax(null);
		filter.setCurrency(null);

		filter.setDateRange(DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK));
		filter.setSorting(SortingEnum.BYPROFITDESC);

		onFilterUpdated();
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		filter.setDateRange(dateRange);

		onFilterUpdated();
	}

	@Override
	public void onSortingChanged(String option, String direction) {
		SortingEnum sortingEnum;
		switch (option) {
			case "profit":
				if (direction.equals("asc"))
					sortingEnum = SortingEnum.BYPROFITASC;
				else
					sortingEnum = SortingEnum.BYPROFITDESC;
				break;
			case "level":
				if (direction.equals("asc"))
					sortingEnum = SortingEnum.BYLEVELASC;
				else
					sortingEnum = SortingEnum.BYLEVELDESC;
				break;
			case "end of period":
				if (direction.equals("asc"))
					sortingEnum = SortingEnum.BYENDOFPERIODASC;
				else
					sortingEnum = SortingEnum.BYENDOFPERIODDESC;
				break;
			case "balance":
				if (direction.equals("asc"))
					sortingEnum = SortingEnum.BYBALANCEASC;
				else
					sortingEnum = SortingEnum.BYBALANCEDESC;
				break;
			case "title":
				if (direction.equals("asc"))
					sortingEnum = SortingEnum.BYTITLEASC;
				else
					sortingEnum = SortingEnum.BYTITLEDESC;
				break;
			default:
				sortingEnum = SortingEnum.BYPROFITDESC;
				break;
		}

		filter.setSorting(sortingEnum);
		onFilterUpdated();
	}
}
