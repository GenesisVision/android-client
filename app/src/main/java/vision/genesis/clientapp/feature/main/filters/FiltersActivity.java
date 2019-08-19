package vision.genesis.clientapp.feature.main.filters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.filters.sorting.SortingDialogFragment;
import vision.genesis.clientapp.model.filter.FilterOption;
import vision.genesis.clientapp.model.filter.UserFilter;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/11/2018.
 */

public class FiltersActivity extends BaseSwipeBackActivity implements FiltersView
{
	private static final String EXTRA_FILTER = "extra_filter";

	public static void startFromFragment(Fragment fragment, UserFilter filter) {
		Intent intent = new Intent(fragment.getContext(), FiltersActivity.class);
		intent.putExtra(EXTRA_FILTER, filter);
		fragment.startActivityForResult(intent, filter.getType());
		if (fragment.getActivity() != null)
			fragment.getActivity().overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.button_reset)
	public TextView resetButton;

	@BindView(R.id.group_filters)
	public ViewGroup filtersGroup;

	@BindView(R.id.date_range)
	public ViewGroup dateRange;

	@BindView(R.id.sorting)
	public ViewGroup sorting;

	@BindView(R.id.date_range_value)
	public TextView dateRangeValue;

	@BindView(R.id.sorting_icon)
	public ImageView sortingIcon;

	@BindView(R.id.sorting_value)
	public TextView sortingValue;

	@BindView(R.id.button_apply)
	public PrimaryButton applyButton;

	@BindInt(R.integer.level_min)
	public int defaultLevelMinLevel;

	@BindInt(R.integer.level_max)
	public int defaultLevelMaxLevel;

	@InjectPresenter
	FiltersPresenter filtersPresenter;

	private UserFilter filter;

	private String currentSortingName = "";

	private String currentSortingDirection = "";

	private List<FilterOptionView> filterOptionViewList = new ArrayList<>();

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finish(Activity.RESULT_CANCELED);
	}

	@OnClick(R.id.button_reset)
	public void onResetClicked() {
		filtersPresenter.onResetClicked();
	}

	@OnClick(R.id.button_apply)
	public void onApplyClicked() {
		finish(Activity.RESULT_OK);
	}

	@OnClick(R.id.sorting)
	public void onSortingClicked() {
		SortingDialogFragment bottomSheetDialog = new SortingDialogFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setCurrentSorting(currentSortingName, currentSortingDirection);
		bottomSheetDialog.setAssetType(filter.getType());
		bottomSheetDialog.setListener(filtersPresenter);
	}

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setDateRange(filter.getDateRange());
		bottomSheetDialog.setListener(filtersPresenter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_filters);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			filtersPresenter.setFilter(getIntent().getExtras().getParcelable(EXTRA_FILTER));
			filter = getIntent().getExtras().getParcelable(EXTRA_FILTER);

			setupFilterOptions();
		}
		else {
			Timber.e("Passed empty filter to %s", getClass().getSimpleName());
			onBackPressed();
		}
	}

	private void setupFilterOptions() {
		filterOptionViewList.clear();
		filtersGroup.removeAllViews();
		for (FilterOption filterOption : filter.getOptions()) {
			FilterOptionView view = getFilterOptionView(filterOption);
			filterOptionViewList.add(view);
			filtersGroup.addView(view);
		}

		dateRange.setVisibility(filter.isDateRangeEnabled() ? View.VISIBLE : View.GONE);
		sorting.setVisibility(filter.isSortingEnabled() ? View.VISIBLE : View.GONE);
		this.dateRangeValue.setText(filter.getDateRange().toString());
		updateSortingFromFilter();
	}

	private FilterOptionView getFilterOptionView(FilterOption filterOption) {
		FilterOptionView view = new FilterOptionView(this);
		view.setFilterOption(filterOption);
		view.setClickListener(filtersPresenter);
		return view;
	}

	@Override
	public void filterUpdated(UserFilter filter) {
		this.filter = filter;

		for (int i = 0; i < filter.getOptions().size(); i++) {
			filterOptionViewList.get(i).setFilterOption(filter.getOptions().get(i));
		}

		this.dateRangeValue.setText(filter.getDateRange().toString());
		updateSortingFromFilter();
	}

	private void updateSortingFromFilter() {
		if (filter == null || filter.getSorting() == null)
			return;

		switch (filter.getSorting()) {
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
				currentSortingName = "profit";
				currentSortingDirection = "desc";
				break;
		}

		sortingIcon.setImageDrawable(AppCompatResources.getDrawable(this,
				currentSortingDirection.equals("asc")
						? R.drawable.icon_sorting_low_to_high
						: R.drawable.icon_sorting_high_to_low));

		sortingValue.setText(StringFormatUtil.capitalize(currentSortingName));
	}

	@Override
	public void setApplyButtonEnabled(boolean enabled) {
		applyButton.setEnabled(enabled);
		if (enabled)
			Timber.d("stop");
	}

	@Override
	public void showSingleValueChooser(FilterOption filterOption) {
		SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
				filterOption.getName(), filterOption.getValues(), filterOption.getSelectedPosition());
		fragment.setListener(filtersPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		resetButton.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void onBackPressed() {
		finish(Activity.RESULT_CANCELED);
	}

	private void finish(int resultCode) {
		Intent data = new Intent();
		data.putExtra("filter", filter);
		setResult(resultCode, data);
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_bottom);
	}
}
