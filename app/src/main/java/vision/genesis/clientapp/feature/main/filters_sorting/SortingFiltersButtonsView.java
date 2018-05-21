package vision.genesis.clientapp.feature.main.filters_sorting;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentProgramsFilter;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/05/2018.
 */
public class SortingFiltersButtonsView extends RelativeLayout
{
	public interface OnFilterUpdatedListener
	{
		void onFilterUpdated(InvestmentProgramsFilter filter);
	}

	public interface OnButtonUpListener
	{
		void onButtonClicked();
	}

	@BindView(R.id.button_sorting)
	public TextView sortingButton;

	@BindView(R.id.button_filters)
	public ViewGroup filtersButton;

	@BindView(R.id.filters_dot)
	public View filtersDot;

	@BindView(R.id.text_count)
	public TextView count;

	private FragmentActivity activity;

	private String currentSortingName = "";

	private String currentSortingDirection = "";

	private InvestmentProgramsFilter filter;

	private OnFilterUpdatedListener filtersUpdateListener;

	private OnButtonUpListener buttonUpListener;

	public SortingFiltersButtonsView(Context context) {
		super(context);
		initView();
	}

	public SortingFiltersButtonsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SortingFiltersButtonsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.button_sorting)
	public void onSortingClicked() {
		if (activity != null) {
			SortingBottomSheetFragment bottomSheetDialog = new SortingBottomSheetFragment();
			bottomSheetDialog.show(activity.getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setCurrentSorting(currentSortingName, currentSortingDirection);
			bottomSheetDialog.setListener(this::updateSorting);
		}
	}

	@OnClick(R.id.button_filters)
	public void onFiltersClicked() {
		if (activity != null) {
			FiltersBottomSheetFragment bottomSheetDialog = new FiltersBottomSheetFragment();
			bottomSheetDialog.show(activity.getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setCurrentFilter(filter);
			bottomSheetDialog.setListener(this::updateFilter);
			bottomSheetDialog.setActivity(activity);
		}
	}

	private void updateFilter(InvestmentProgramsFilter updatedFilter) {
		filter = updatedFilter;

		filtersDot.setVisibility(isFilterReset(filter) ? View.GONE : VISIBLE);

		if (filtersUpdateListener != null)
			filtersUpdateListener.onFilterUpdated(filter);
	}

	private boolean isFilterReset(InvestmentProgramsFilter filter) {
		return filter.getLevelMin() == null
				&& filter.getLevelMax() == null
				&& filter.getProfitAvgPercentMin() == null
				&& filter.getProfitAvgPercentMax() == null
				&& filter.isShowActivePrograms() == null;
	}

	@OnClick(R.id.button_up)
	public void onUpClicked() {
		if (buttonUpListener != null)
			buttonUpListener.onButtonClicked();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_filters_sorting_buttons, this);

		ButterKnife.bind(this);
	}

	public void setActivity(FragmentActivity activity) {
		this.activity = activity;
	}

	public void setFiltersUpdateListener(OnFilterUpdatedListener listener) {
		this.filtersUpdateListener = listener;
	}

	public void setButtonUpListener(OnButtonUpListener listener) {
		this.buttonUpListener = listener;
	}

	public void setFilter(InvestmentProgramsFilter filter) {
		this.filter = filter;
		extractSortingFromFilter(filter.getSorting());
	}

	public void setCount(String count) {
		this.count.setText(count);
	}

	public void disableSorting(String sortingString) {
		sortingButton.setEnabled(false);
		sortingButton.setText(sortingString);
	}

	private void extractSortingFromFilter(InvestmentProgramsFilter.SortingEnum sortingEnum) {
		if (sortingEnum == null)
			return;

		switch (sortingEnum) {
			case BYPROFITASC:
				currentSortingName = "profit";
				currentSortingDirection = "asc";
				break;
			case BYPROFITDESC:
				currentSortingName = "profit";
				currentSortingDirection = "desc";
				break;
			case BYLEVELASC:
				currentSortingName = "level";
				currentSortingDirection = "asc";
				break;
			case BYLEVELDESC:
				currentSortingName = "level";
				currentSortingDirection = "desc";
				break;
			case BYENDOFPERIODASC:
				currentSortingName = "end of period";
				currentSortingDirection = "asc";
				break;
			case BYENDOFPERIODDESC:
				currentSortingName = "end of period";
				currentSortingDirection = "desc";
				break;
//			case BYBALANCEASC:
//				currentSortingName = "balance";
//				currentSortingDirection = "asc";
//				break;
//			case BYBALANCEDESC:
//				currentSortingName = "balance";
//				currentSortingDirection = "desc";
//				break;
			case BYTITLEASC:
				currentSortingName = "title";
				currentSortingDirection = "asc";
				break;
			case BYTITLEDESC:
				currentSortingName = "title";
				currentSortingDirection = "desc";
				break;
			default:
				currentSortingName = "";
				currentSortingDirection = "";
				break;
		}
	}

	private void updateSorting(String option, String direction) {
		InvestmentProgramsFilter.SortingEnum sortingEnum;
		switch (option) {
			case "profit":
				if (direction.equals("asc"))
					sortingEnum = InvestmentProgramsFilter.SortingEnum.BYPROFITASC;
				else
					sortingEnum = InvestmentProgramsFilter.SortingEnum.BYPROFITDESC;
				break;
			case "level":
				if (direction.equals("asc"))
					sortingEnum = InvestmentProgramsFilter.SortingEnum.BYLEVELASC;
				else
					sortingEnum = InvestmentProgramsFilter.SortingEnum.BYLEVELDESC;
				break;
			case "end of period":
				if (direction.equals("asc"))
					sortingEnum = InvestmentProgramsFilter.SortingEnum.BYENDOFPERIODASC;
				else
					sortingEnum = InvestmentProgramsFilter.SortingEnum.BYENDOFPERIODDESC;
				break;
//			case "balance":
//				if (direction.equals("asc"))
//					sortingEnum = InvestmentProgramsFilter.SortingEnum.BYBALANCEASC;
//				else
//					sortingEnum = InvestmentProgramsFilter.SortingEnum.BYBALANCEDESC;
//				break;
			case "title":
				if (direction.equals("asc"))
					sortingEnum = InvestmentProgramsFilter.SortingEnum.BYTITLEASC;
				else
					sortingEnum = InvestmentProgramsFilter.SortingEnum.BYTITLEDESC;
				break;
			default:
				sortingEnum = InvestmentProgramsFilter.SortingEnum.BYPROFITASC;
				break;
		}

		updateSortingText(option, direction);

		filter.setSorting(sortingEnum);
		extractSortingFromFilter(filter.getSorting());

		if (filtersUpdateListener != null)
			filtersUpdateListener.onFilterUpdated(filter);
	}

	private void updateSortingText(String option, String direction) {
		String directionSymbol = direction.equals("asc") ? "\u2191" : "\u2193";
		sortingButton.setText(String.format(Locale.getDefault(), "%s %s %s", getContext().getString(R.string.by).toUpperCase(), option, directionSymbol));
	}
}
