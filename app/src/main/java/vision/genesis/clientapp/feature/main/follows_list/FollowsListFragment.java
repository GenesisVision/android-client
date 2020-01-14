package vision.genesis.clientapp.feature.main.follows_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.AssetFacet;
import io.swagger.client.model.FollowDetailsListItem;
import io.swagger.client.model.ItemsViewModelFollowDetailsListItem;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.filters.FiltersActivity;
import vision.genesis.clientapp.model.RatingInfo;
import vision.genesis.clientapp.model.filter.ProgramsFilter;
import vision.genesis.clientapp.model.filter.UserFilter;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/11/2019.
 */

public class FollowsListFragment extends BaseFragment implements FollowsListView
{
	public static final String LOCATION_ASSETS = "location_assets";

	public static final String LOCATION_SEARCH = "location_search";

	public static final String LOCATION_MANAGER = "location_manager";

	public static final String LOCATION_FACET = "location_facet";

	public static final String LOCATION_RATING = "location_rating";

	public static final String EXTRA_FILTER = "extra_filter";

	public static final String EXTRA_RATING_INFO = "extra_rating_info";

	private static final String EXTRA_LOCATION = "extra_location";

	private static final String EXTRA_DATA = "extra_data";

	public static FollowsListFragment with(@NonNull String location, Bundle data) {
		FollowsListFragment programListFragment = new FollowsListFragment();
		Bundle arguments = new Bundle(2);
		arguments.putString(EXTRA_LOCATION, location);
		arguments.putBundle(EXTRA_DATA, data);
		programListFragment.setArguments(arguments);
		return programListFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.group_rating_info)
	public ViewGroup ratingInfoGroup;

	@BindView(R.id.rating_total)
	public TextView ratingTotal;

	@BindView(R.id.rating_quota)
	public TextView ratingQuota;

	@BindView(R.id.rating_target_profit)
	public TextView ratingTargetProfit;

	@BindView(R.id.filters)
	public ViewGroup filters;

	@BindView(R.id.text_filters)
	public TextView filtersText;

	@BindView(R.id.filters_dot)
	public View filtersDot;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@BindView(R.id.button_try_again)
	public View tryAgainButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindDimen(R.dimen.assets_filters_margin_bottom)
	public int assetsFiltersMarginBottom;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@InjectPresenter
	FollowsListPresenter followsListPresenter;

	private int filtersMarginBottom;

	private FollowsListAdapter followsListAdapter;

	private Unbinder unbinder;

	private List<AssetFacet> facets = new ArrayList<>();

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		followsListPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.filters)
	public void onFiltersClicked() {
		followsListPresenter.onFiltersClicked();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_programs_list, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (getArguments() != null) {
			Bundle data = getArguments().getBundle(EXTRA_DATA);
			String location = getArguments().getString(EXTRA_LOCATION);

			ProgramsFilter filter = null;
			RatingInfo ratingInfo;

			setFonts();
			initRefreshLayout();
			initRecyclerView();

			if (location != null) {
				switch (location) {
					case LOCATION_ASSETS:
						filtersMarginBottom = assetsFiltersMarginBottom;
						break;
					case LOCATION_MANAGER:
						filtersMarginBottom = dateRangeMarginBottom;
						filter = data != null ? data.getParcelable(EXTRA_FILTER) : null;
						break;
					case LOCATION_SEARCH:
						filters.setVisibility(View.GONE);
						refreshLayout.setEnabled(false);
						break;
					case LOCATION_FACET:
						filters.setVisibility(View.GONE);
						filter = data != null ? data.getParcelable(EXTRA_FILTER) : null;
						break;
					case LOCATION_RATING:
						filters.setVisibility(View.GONE);
						filter = data != null ? data.getParcelable(EXTRA_FILTER) : null;
						ratingInfo = data != null ? data.getParcelable(EXTRA_RATING_INFO) : null;
						if (ratingInfo != null) {
							this.ratingInfoGroup.setVisibility(View.VISIBLE);
							updateRatingInfo(ratingInfo);
						}
						break;
					default:
						filtersMarginBottom = assetsFiltersMarginBottom;
						break;
				}
			}

			followsListPresenter.setData(location, filter);
		}
		else {
			Timber.e("Passed empty arguments to ProgramsListFragment");
			onBackPressed();
		}
	}

	@Override
	public void onDestroyView() {
		if (recyclerView != null) {
			recyclerView.setAdapter(null);
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		filtersText.setTypeface(TypefaceUtil.semibold());
		ratingTotal.setTypeface(TypefaceUtil.semibold());
		ratingQuota.setTypeface(TypefaceUtil.semibold());
		ratingTargetProfit.setTypeface(TypefaceUtil.semibold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> followsListPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		followsListAdapter = new FollowsListAdapter();
		followsListAdapter.setHasStableIds(true);
		followsListAdapter.setFacets(facets);
		recyclerView.setAdapter(followsListAdapter);
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
				checkIfLastItemVisible();
			}
		});
	}

	private void checkIfLastItemVisible() {
		LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
		int totalItemCount = layoutManager.getItemCount();
		int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
		if (lastVisible < 0) {
			return;
		}

		boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
		if (totalItemCount > 0 && endHasBeenReached) {
			followsListPresenter.onLastListItemVisible();
		}
	}

	public void setFacets(List<AssetFacet> facets) {
		this.facets = facets;
		if (followsListAdapter != null) {
			followsListAdapter.setFacets(facets);
		}
	}

	private void updateRatingInfo(RatingInfo ratingInfo) {
		this.ratingTotal.setText(String.valueOf(ratingInfo.getTotal()));
		this.ratingQuota.setText(String.valueOf(ratingInfo.getQuota()));
		this.ratingTargetProfit.setText(String.format(Locale.getDefault(), "%s%%",
				StringFormatUtil.formatAmount(ratingInfo.getTargetProfit(), 0, 2)));
	}

	@Override
	public void setFollows(List<FollowDetailsListItem> follows) {
		followsListAdapter.setFollows(follows);
		recyclerView.scrollToPosition(0);
	}

	@Override
	public void addFollows(List<FollowDetailsListItem> follows) {
		followsListAdapter.addFollows(follows);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void showNoInternet(boolean show) {
		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		tryAgainButton.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showEmptyList(boolean show) {
		emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showFiltersActive(boolean show) {
		filtersDot.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void changeFollowIsFavorite(UUID followId, boolean isFavorite) {
		followsListAdapter.changeFollowIsFavorite(followId, isFavorite);
	}

	@Override
	public void showFiltersActivity(ProgramsFilter filter) {
		FiltersActivity.startFromFragment(this, filter.getUserFilter(UserFilter.TYPE_FOLLOWS_LIST_FILTER));
	}

	@Override
	public void showBottomProgress(boolean show) {

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == UserFilter.TYPE_FOLLOWS_LIST_FILTER && resultCode == Activity.RESULT_OK) {
			UserFilter userFilter = data.getParcelableExtra("filter");
			if (userFilter != null) {
				followsListPresenter.onFilterUpdated(userFilter);
			}
		}
		else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	public void showSearchResults(ItemsViewModelFollowDetailsListItem result) {
		if (followsListPresenter != null) {
			followsListPresenter.showSearchResults(result);
		}
	}

	public void onOffsetChanged(int verticalOffset) {
		if (filters != null) {
			filters.setY(root.getHeight() - verticalOffset - filters.getHeight() - filtersMarginBottom);
		}
	}
}
