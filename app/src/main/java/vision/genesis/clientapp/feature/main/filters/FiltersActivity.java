package vision.genesis.clientapp.feature.main.filters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import vision.genesis.clientapp.feature.main.fund.add_asset.AddAssetActivity;
import vision.genesis.clientapp.model.filter.FilterOption;
import vision.genesis.clientapp.model.filter.UserFilter;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.SocialTagView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypedValueFormatter;
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
		if (fragment.getActivity() != null) {
			fragment.getActivity().overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
		}
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.button_reset)
	public TextView resetButton;

	@BindView(R.id.group_filters)
	public ViewGroup filtersGroup;


	@BindView(R.id.assets)
	public ViewGroup assets;

	@BindView(R.id.assets_all)
	public TextView assetsAll;

	@BindView(R.id.assets_flex_box)
	public FlexboxLayout assetsFlexBox;


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

	@BindView(R.id.group_favorite)
	public ViewGroup favoriteGroup;

	@BindView(R.id.favorite_switch)
	public SwitchCompat favoriteSwitch;

	@BindView(R.id.button_apply)
	public PrimaryButton applyButton;

	@BindInt(R.integer.level_min)
	public int defaultLevelMinLevel;

	@BindInt(R.integer.level_max)
	public int defaultLevelMaxLevel;

	@InjectPresenter
	FiltersPresenter presenter;

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
		presenter.onResetClicked();
	}

	@OnClick(R.id.button_apply)
	public void onApplyClicked() {
		finish(Activity.RESULT_OK);
	}

	@OnClick(R.id.favorite_switch)
	public void onFavoriteSwitchClicked() {
		presenter.onFavoriteClicked();
	}

	@OnClick(R.id.assets)
	public void onAssetsClicked() {
		AddAssetActivity.startWith(this, false);
	}

	@OnClick(R.id.sorting)
	public void onSortingClicked() {
		SortingDialogFragment dialog = new SortingDialogFragment();
		dialog.show(getSupportFragmentManager(), dialog.getTag());
		dialog.setCurrentSorting(currentSortingName, currentSortingDirection);
		dialog.setAssetType(filter.getType());
		dialog.setListener(presenter);
	}

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setDateRange(filter.getDateRange());
		bottomSheetDialog.setListener(presenter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_filters);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			presenter.setFilter(getIntent().getExtras().getParcelable(EXTRA_FILTER));
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
		assets.setVisibility(filter.isAssetsEnabled() ? View.VISIBLE : View.GONE);
		favoriteGroup.setVisibility(filter.isFavoritesEnabled() ? View.VISIBLE : View.GONE);
		this.dateRangeValue.setText(filter.getDateRange().toString());
		updateSortingFromFilter();
	}

	private FilterOptionView getFilterOptionView(FilterOption filterOption) {
		FilterOptionView view = new FilterOptionView(this);
		view.setFilterOption(filterOption);
		view.setClickListener(presenter);
		return view;
	}

	@Override
	public void filterUpdated(UserFilter filter) {
		this.filter = filter;

		for (int i = 0; i < filter.getOptions().size(); i++) {
			filterOptionViewList.get(i).setFilterOption(filter.getOptions().get(i));
		}

		this.dateRangeValue.setText(filter.getDateRange().toString());
		this.favoriteSwitch.setChecked(filter.isFavorite());
		updateSortingFromFilter();
		updateAssets();
	}

	private void updateAssets() {
		assetsFlexBox.removeAllViews();
		for (String asset : filter.getAssets()) {
			addAsset(asset);
		}

		if (filter.getAssets().size() == 0) {
			assetsFlexBox.setVisibility(View.GONE);
			assetsAll.setVisibility(View.VISIBLE);
		}
	}

	private void updateSortingFromFilter() {
		if (filter == null || filter.getSorting() == null) {
			return;
		}

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
			case BYEQUITYASC:
				currentSortingName = "equity";
				currentSortingDirection = "asc";
				break;
			case BYEQUITYDESC:
				currentSortingName = "equity";
				currentSortingDirection = "desc";
				break;
			case BYSIZEASC:
				currentSortingName = "size";
				currentSortingDirection = "asc";
				break;
			case BYSIZEDESC:
				currentSortingName = "size";
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
			case BYSUBSCRIBERSASC:
				currentSortingName = "subscribers";
				currentSortingDirection = "asc";
				break;
			case BYSUBSCRIBERSDESC:
				currentSortingName = "subscribers";
				currentSortingDirection = "desc";
				break;
			case BYINVESTORSASC:
				currentSortingName = "investors";
				currentSortingDirection = "asc";
				break;
			case BYINVESTORSDESC:
				currentSortingName = "investors";
				currentSortingDirection = "desc";
				break;
			case BYDRAWDOWNASC:
				currentSortingName = "drawdown";
				currentSortingDirection = "asc";
				break;
			case BYDRAWDOWNDESC:
				currentSortingName = "drawdown";
				currentSortingDirection = "desc";
				break;

			case BYASSETASC:
				currentSortingName = "asset";
				currentSortingDirection = "asc";
				break;
			case BYASSETDESC:
				currentSortingName = "asset";
				currentSortingDirection = "desc";
				break;
			case BYSYMBOLASC:
				currentSortingName = "name";
				currentSortingDirection = "asc";
				break;
			case BYSYMBOLDESC:
				currentSortingName = "name";
				currentSortingDirection = "desc";
				break;
			case BYPRICEASC:
				currentSortingName = "price";
				currentSortingDirection = "asc";
				break;
			case BYPRICEDESC:
				currentSortingName = "price";
				currentSortingDirection = "desc";
				break;
			case BYCHANGEASC:
				currentSortingName = "change";
				currentSortingDirection = "asc";
				break;
			case BYCHANGEDESC:
				currentSortingName = "change";
				currentSortingDirection = "desc";
				break;
			case BYMARKETCAPASC:
				currentSortingName = "market cap";
				currentSortingDirection = "asc";
				break;
			case BYMARKETCAPDESC:
				currentSortingName = "market cap";
				currentSortingDirection = "desc";
				break;
			case BYVOLUMEASC:
				currentSortingName = "volume";
				currentSortingDirection = "asc";
				break;
			case BYVOLUMEDESC:
				currentSortingName = "volume";
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

	@Override
	public void showSingleValueChooser(FilterOption filterOption) {
		SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
				filterOption.getName(), filterOption.getValues(), filterOption.getSelectedPosition());
		fragment.setListener(presenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@Override
	public void showAssetAlreadyAdded() {
		showSnackbar(getString(R.string.error_asset_added), assets);
	}

	@Override
	public void removeAsset(String asset) {
		for (int i = 0; i < assetsFlexBox.getChildCount(); i++) {
			if (((SocialTagView) assetsFlexBox.getChildAt(i)).getHashTag().equals(asset)) {
				assetsFlexBox.removeViewAt(i);
				break;
			}
		}

		if (assetsFlexBox.getChildCount() == 0) {
			assetsFlexBox.setVisibility(View.GONE);
			assetsAll.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void addAsset(String asset) {
		SocialTagView view = new SocialTagView(this);
		int[] colors = getResources().getIntArray(R.array.assetsColors);
		view.setTag(asset, colors[(new Random()).nextInt(colors.length)], null);
		view.setListener((tag, contentId) -> presenter.onRemoveAssetClicked(tag));
		assetsFlexBox.addView(view);
		FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
		lp.setMargins(0, 0, TypedValueFormatter.toDp(10), TypedValueFormatter.toDp(4));
		view.setLayoutParams(lp);

		if (assetsFlexBox.getChildCount() > 0) {
			assetsFlexBox.setVisibility(View.VISIBLE);
			assetsAll.setVisibility(View.GONE);
		}
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
