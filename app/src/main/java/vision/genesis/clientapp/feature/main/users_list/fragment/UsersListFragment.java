package vision.genesis.clientapp.feature.main.users_list.fragment;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.PublicProfile;
import io.swagger.client.model.PublicProfileItemsViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.users_list.UsersListAdapter;
import vision.genesis.clientapp.model.filter.ProgramsFilter;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/03/2019.
 */

public class UsersListFragment extends BaseFragment implements UsersListView
{
	public static final String LOCATION_SEARCH = "location_search";

	private static final String EXTRA_LOCATION = "extra_location";

	public static UsersListFragment with(String location) {
		UsersListFragment programListFragment = new UsersListFragment();
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
	UsersListPresenter usersListPresenter;

	private int lastVisible = 0;

	private UsersListAdapter usersListAdapter;

	private Unbinder unbinder;

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		usersListPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.filters)
	public void onFiltersClicked() {
		usersListPresenter.onFiltersClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_users_list, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (getArguments() != null) {
			String location = getArguments().getString(EXTRA_LOCATION);

			if (location != null && location.equals(LOCATION_SEARCH)) {
				filters.setVisibility(View.GONE);
				refreshLayout.setEnabled(false);
			}

			usersListPresenter.setData(location);

			setFonts();
			initRefreshLayout();
			initRecyclerView();
		}
		else {
			Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
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
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> usersListPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		usersListAdapter = new UsersListAdapter();
		usersListAdapter.setHasStableIds(true);
		recyclerView.setAdapter(usersListAdapter);
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
		if (lastVisible < 0) {
			return;
		}

		boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
		if (totalItemCount > 0 && endHasBeenReached) {
			usersListPresenter.onLastListItemVisible();
		}
	}

	@Override
	public void setUsers(List<PublicProfile> users) {
		usersListAdapter.setUsers(users);
		recyclerView.scrollToPosition(0);
	}

	@Override
	public void addUsers(List<PublicProfile> users) {
		usersListAdapter.addUsers(users);
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
//		FiltersActivity.startFromFragment(this, filter.getUserFilter(), FiltersActivity.FUNDS_LIST_FILTER);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	}

	public void showSearchResults(PublicProfileItemsViewModel result) {
		if (usersListPresenter != null) {
			usersListPresenter.showSearchResults(result);
		}
	}
}
