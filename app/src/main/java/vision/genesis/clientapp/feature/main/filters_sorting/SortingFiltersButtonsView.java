package vision.genesis.clientapp.feature.main.filters_sorting;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.filters.sorting.SortingDialogFragment;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/05/2018.
 */
public class SortingFiltersButtonsView extends RelativeLayout
{
	public interface OnFilterUpdatedListener
	{
		void onFilterUpdated(ProgramsFilter filter);
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

	private ProgramsFilter filter;

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
			SortingDialogFragment bottomSheetDialog = new SortingDialogFragment();
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

	private void updateFilter(ProgramsFilter updatedFilter) {
		filter = updatedFilter;

		filtersDot.setVisibility(isFilterReset(filter) ? View.GONE : VISIBLE);

		if (filtersUpdateListener != null) {
			filtersUpdateListener.onFilterUpdated(filter);
		}
	}

	private boolean isFilterReset(ProgramsFilter filter) {
		return filter.getLevelMin() == null
				&& filter.getLevelMax() == null
				&& filter.getProfitAvgMin() == null
				&& filter.getProfitAvgMax() == null;
//				&& filter.isShowActivePrograms() == null;
	}

	@OnClick(R.id.button_up)
	public void onUpClicked() {
		if (buttonUpListener != null) {
			buttonUpListener.onButtonClicked();
		}
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

	public void setFilter(ProgramsFilter filter) {
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

	private void extractSortingFromFilter(SortingEnum sortingEnum) {
		if (sortingEnum == null) {
			return;
		}

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
			case BYBALANCEASC:
				currentSortingName = "balance";
				currentSortingDirection = "asc";
				break;
			case BYBALANCEDESC:
				currentSortingName = "balance";
				currentSortingDirection = "desc";
				break;
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
			case "title":
				if (direction.equals("asc")) {
					sortingEnum = SortingEnum.BYTITLEASC;
				}
				else {
					sortingEnum = SortingEnum.BYTITLEDESC;
				}
				break;
			default:
				sortingEnum = SortingEnum.BYPROFITASC;
				break;
		}

		updateSortingText(option, direction);

		filter.setSorting(sortingEnum);
		extractSortingFromFilter(filter.getSorting());

		if (filtersUpdateListener != null) {
			filtersUpdateListener.onFilterUpdated(filter);
		}
	}

	private void updateSortingText(String option, String direction) {
		String directionSymbol = direction.equals("asc") ? "\u2191" : "\u2193";
		sortingButton.setText(String.format(Locale.getDefault(), "%s %s %s", getContext().getString(R.string.by).toUpperCase(), option, directionSymbol));
	}
}
