package vision.genesis.clientapp.feature.main.filters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.content.res.AppCompatResources;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.filters.sorting.SortingBottomSheetFragment;
import vision.genesis.clientapp.model.ProgramsFilter;
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
	public static final int PROGRAM_FILTER = 100;

	public static final int FUND_FILTER = 101;

	private static final String EXTRA_FILTER = "extra_filter";

	private static final String EXTRA_REQUEST_CODE = "extra_request_code";

	public static void startFromFragment(Fragment fragment, ProgramsFilter filter, int requestCode) {
		Intent intent = new Intent(fragment.getContext(), FiltersActivity.class);
		intent.putExtra(EXTRA_FILTER, filter);
		intent.putExtra(EXTRA_REQUEST_CODE, requestCode);
		fragment.startActivityForResult(intent, requestCode);
		if (fragment.getActivity() != null)
			fragment.getActivity().overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.button_reset)
	public TextView resetButton;

	@BindView(R.id.levels_value)
	public TextView levelsValue;

	@BindView(R.id.currency_value)
	public TextView currencyValue;

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

	private ProgramsFilter filter;

	private String currentSortingName = "";

	private String currentSortingDirection = "";

	private int requestCode;

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

	@OnClick(R.id.button_sorting)
	public void onSortingClicked() {
		SortingBottomSheetFragment bottomSheetDialog = new SortingBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setCurrentSorting(currentSortingName, currentSortingDirection);
		bottomSheetDialog.setAssetType(requestCode == PROGRAM_FILTER ? "program" : "fund");
		bottomSheetDialog.setListener(filtersPresenter);
	}

	@OnClick(R.id.button_date_range)
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
			requestCode = getIntent().getExtras().getInt(EXTRA_REQUEST_CODE);
		}
		else {
			Timber.e("Passed empty filter to FiltersActivity");
			onBackPressed();
		}
	}

	@Override
	public void filterUpdated(ProgramsFilter filter) {
		this.filter = filter;

		setRangeFilterValue(levelsValue, filter.getLevelMin(), filter.getLevelMax());
		setSingleFilterValue(currencyValue, filter.getCurrency() != null ? filter.getCurrency().getValue() : null);

		this.dateRangeValue.setText(filter.getDateRange().toString());
		updateSortingFromFilter();
	}

	private void setRangeFilterValue(TextView filterValueView, Integer minValue, Integer maxValue) {
		if (minValue == null && maxValue == null) {
			filterValueView.setAlpha(0.4f);
			filterValueView.setTextColor(ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary));

			filterValueView.setText(getString(R.string.all));
		}
		else {
			filterValueView.setAlpha(1f);
			filterValueView.setTextColor(ThemeUtil.getColorByAttrId(this, R.attr.colorAccent));

			if (minValue == null)
				minValue = defaultLevelMinLevel;
			if (maxValue == null)
				maxValue = defaultLevelMaxLevel;

			if (maxValue - minValue == 1)
				filterValueView.setText(String.format(Locale.getDefault(), "%d, %d", minValue, maxValue));
			else if (minValue.equals(maxValue))
				filterValueView.setText(String.format(Locale.getDefault(), "%d", minValue));
			else
				filterValueView.setText(String.format(Locale.getDefault(), "%d-%d", minValue, maxValue));
		}
	}

	private void setSingleFilterValue(TextView filterValueView, String value) {
		if (value == null) {
			filterValueView.setAlpha(0.4f);
			filterValueView.setTextColor(ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary));

			filterValueView.setText(getString(R.string.all));
		}
		else {
			filterValueView.setAlpha(1f);
			filterValueView.setTextColor(ThemeUtil.getColorByAttrId(this, R.attr.colorAccent));

			filterValueView.setText(value);
		}
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
