package vision.genesis.clientapp.feature.main.filters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.filters.sorting.SortingDialogFragment;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.events.OnAddAssetAssetSelectedEvent;
import vision.genesis.clientapp.model.filter.FilterOption;
import vision.genesis.clientapp.model.filter.UserFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/11/2018.
 */

@InjectViewState
public class FiltersPresenter extends MvpPresenter<FiltersView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, SortingDialogFragment.OnSortingChangedListener, FilterOptionView.ClickListener, SelectOptionBottomSheetFragment.OnOptionSelectedListener
{
	private UserFilter oldFilter;

	private UserFilter filter;

	private FilterOption selectedFilterOption;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setFilter(UserFilter filter) {
		this.oldFilter = filter;
		this.filter = new UserFilter(filter);

		onFilterUpdated();
	}

	private void onFilterUpdated() {
		getViewState().filterUpdated(this.filter);
		getViewState().setApplyButtonEnabled(!oldFilter.equals(filter));
	}

	void onResetClicked() {
		for (FilterOption filterOption : filter.getOptions()) {
			filterOption.resetToDefaultValue();
		}

		filter.setDateRange(DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH));
		filter.setSorting(filter.getType() == UserFilter.TYPE_COINS_LIST_FILTER
				? SortingEnum.BYMARKETCAPDESC : SortingEnum.BYPROFITDESC);
		filter.setAssets(new ArrayList<>());
		filter.setFavorite(false);

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
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYPROFITASC;
				}
				else {
					sortingEnum = SortingEnum.BYPROFITDESC;
				}
				break;
			case "level":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYLEVELASC;
				}
				else {
					sortingEnum = SortingEnum.BYLEVELDESC;
				}
				break;
			case "end of period":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYENDOFPERIODASC;
				}
				else {
					sortingEnum = SortingEnum.BYENDOFPERIODDESC;
				}
				break;
			case "balance":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYBALANCEASC;
				}
				else {
					sortingEnum = SortingEnum.BYBALANCEDESC;
				}
				break;
			case "equity":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYEQUITYASC;
				}
				else {
					sortingEnum = SortingEnum.BYEQUITYDESC;
				}
				break;
			case "size":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYSIZEASC;
				}
				else {
					sortingEnum = SortingEnum.BYSIZEDESC;
				}
				break;
			case "title":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYTITLEASC;
				}
				else {
					sortingEnum = SortingEnum.BYTITLEDESC;
				}
				break;
			case "subscribers":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYSUBSCRIBERSASC;
				}
				else {
					sortingEnum = SortingEnum.BYSUBSCRIBERSDESC;
				}
				break;
			case "investors":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYINVESTORSASC;
				}
				else {
					sortingEnum = SortingEnum.BYINVESTORSDESC;
				}
				break;
			case "drawdown":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYDRAWDOWNASC;
				}
				else {
					sortingEnum = SortingEnum.BYDRAWDOWNDESC;
				}
				break;

			case "name":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYASSETASC;
				}
				else {
					sortingEnum = SortingEnum.BYASSETDESC;
				}
				break;

			case "asset":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYSYMBOLASC;
				}
				else {
					sortingEnum = SortingEnum.BYSYMBOLDESC;
				}
				break;

			case "price":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYPRICEASC;
				}
				else {
					sortingEnum = SortingEnum.BYPRICEDESC;
				}
				break;

			case "change":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYCHANGEASC;
				}
				else {
					sortingEnum = SortingEnum.BYCHANGEDESC;
				}
				break;

			case "market cap":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYMARKETCAPASC;
				}
				else {
					sortingEnum = SortingEnum.BYMARKETCAPDESC;
				}
				break;

			case "volume":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYVOLUMEASC;
				}
				else {
					sortingEnum = SortingEnum.BYVOLUMEDESC;
				}
				break;
			default:
				sortingEnum = SortingEnum.BYPROFITDESC;
				break;
		}

		filter.setSorting(sortingEnum);
		onFilterUpdated();
	}

	@Override
	public void onClick(FilterOption filterOption) {
		switch (filterOption.getType()) {
			case FilterOption.TYPE_SINGLE_VALUE:
				selectedFilterOption = filterOption;
				getViewState().showSingleValueChooser(filterOption);
				break;
			case FilterOption.TYPE_RANGE:
//				getViewState().showRangeValueChooser(filterOption);
				break;
		}
	}

	@Override
	public void onOptionSelected(Integer position, String text) {
		if (selectedFilterOption != null) {
			selectedFilterOption.setSelectedPosition(position);
			onFilterUpdated();
		}
	}

	@Subscribe
	public void onEventMainThread(OnAddAssetAssetSelectedEvent event) {
		String newAsset = event.getAsset().getAsset();
		if (filter.getAssets().contains(newAsset)) {
			getViewState().showAssetAlreadyAdded();
		}
		else {
			filter.addAsset(newAsset);
			getViewState().addAsset(newAsset);
			getViewState().setApplyButtonEnabled(!oldFilter.equals(filter));
		}
	}

	public void onRemoveAssetClicked(String asset) {
		filter.removeAsset(asset);
		getViewState().removeAsset(asset);
		getViewState().setApplyButtonEnabled(!oldFilter.equals(filter));
	}

	public void onFavoriteClicked() {
		filter.setFavorite(!filter.isFavorite());
		onFilterUpdated();
	}
}
