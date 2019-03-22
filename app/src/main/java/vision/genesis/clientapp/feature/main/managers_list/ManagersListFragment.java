package vision.genesis.clientapp.feature.main.managers_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ManagerProfile;
import io.swagger.client.model.ManagersList;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.filters.FiltersActivity;
import vision.genesis.clientapp.model.ProgramsFilter;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/03/2019.
 */

public class ManagersListFragment extends BaseFragment implements ManagersListView
{
	public static String LOCATION_SEARCH = "location_search";

	private static String EXTRA_LOCATION = "extra_location";

	public static ManagersListFragment with(String location) {
		ManagersListFragment programListFragment = new ManagersListFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_LOCATION, location);
		programListFragment.setArguments(arguments);
		return programListFragment;
	}

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

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

	@InjectPresenter
	ManagersListPresenter managersListPresenter;

	private int lastVisible = 0;

	private ManagersListAdapter managersListAdapter;

	private Unbinder unbinder;

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		managersListPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.filters)
	public void onFiltersClicked() {
		managersListPresenter.onFiltersClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_managers_list, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (getArguments() != null) {
			String location = getArguments().getString(EXTRA_LOCATION);

			if (location != null && location.equals(LOCATION_SEARCH)) {
				filters.setVisibility(View.GONE);
			}

			managersListPresenter.setData(location);

			setFonts();
			initRefreshLayout();
			initRecyclerView();
		}
		else {
			Timber.e("Passed empty arguments to ManagersListFragment");
			onBackPressed();
		}
	}

	@Override
	public void onDestroyView() {
		if (recyclerView != null)
			recyclerView.setAdapter(null);

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		filtersText.setTypeface(TypefaceUtil.semibold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> managersListPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		managersListAdapter = new ManagersListAdapter();
		managersListAdapter.setHasStableIds(true);
		recyclerView.setAdapter(managersListAdapter);
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
		lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
		if (lastVisible < 0)
			return;

		boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
		if (totalItemCount > 0 && endHasBeenReached) {
			managersListPresenter.onLastListItemVisible();
		}
	}

	@Override
	public void setManagers(List<ManagerProfile> managers) {
		managersListAdapter.setManagers(managers);
		recyclerView.scrollToPosition(0);
	}

	@Override
	public void addManagers(List<ManagerProfile> managers) {
		managersListAdapter.addManagers(managers);
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
//		toolbar.showRightButtonDot(show);
	}

	@Override
	public void showFiltersActivity(ProgramsFilter filter) {
		FiltersActivity.startFromFragment(this, filter, FiltersActivity.FUND_FILTER);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == FiltersActivity.FUND_FILTER && resultCode == Activity.RESULT_OK) {
			ProgramsFilter newFilter = data.getParcelableExtra("filter");
			if (newFilter != null)
				managersListPresenter.onFilterUpdated(newFilter);
		}
		else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	public void showSearchResults(ManagersList result) {
		if (managersListPresenter != null)
			managersListPresenter.showSearchResults(result);
	}
}
