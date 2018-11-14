package vision.genesis.clientapp.feature.main.funds_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.FundDetails;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.filters.FiltersActivity;
import vision.genesis.clientapp.model.ProgramsFilter;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

public class FundsListFragment extends BaseFragment implements FundsListView
{
	private static String EXTRA_MANAGER_ID = "extra_manager_id";

	public static FundsListFragment with(UUID managerId) {
		FundsListFragment programListFragment = new FundsListFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_MANAGER_ID, managerId);
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
	FundsListPresenter fundsListPresenter;

	private int lastVisible = 0;

	private FundsListAdapter fundsListAdapter;

	private Unbinder unbinder;

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		fundsListPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.filters)
	public void onFiltersClicked() {
		fundsListPresenter.onFiltersClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_programs_list, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		UUID managerId = null;
		if (getArguments() != null) {
			managerId = (UUID) getArguments().getSerializable(EXTRA_MANAGER_ID);
		}
		fundsListPresenter.setManagerId(managerId);

		setFonts();

		initRefreshLayout();
		initRecyclerView();
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
		refreshLayout.setOnRefreshListener(() -> fundsListPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		fundsListAdapter = new FundsListAdapter();
		fundsListAdapter.setHasStableIds(true);
		recyclerView.setAdapter(fundsListAdapter);
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
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
			fundsListPresenter.onLastListItemVisible();
		}
	}

	@Override
	public void setFunds(List<FundDetails> funds) {
		fundsListAdapter.setFunds(funds);
		recyclerView.scrollToPosition(0);
	}

	@Override
	public void addFunds(List<FundDetails> funds) {
		fundsListAdapter.addFunds(funds);
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
	public void changeFundIsFavorite(UUID fundId, boolean isFavorite) {
		fundsListAdapter.setFundFavorite(fundId, isFavorite);
	}

	@Override
	public void setFundsCount(String count) {
	}

	@Override
	public void showFiltersActivity(ProgramsFilter filter) {
		FiltersActivity.startFromFragment(this, filter, FiltersActivity.FUND_FILTER);
	}

	@Override
	public void showBottomProgress(boolean show) {

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == FiltersActivity.FUND_FILTER && resultCode == Activity.RESULT_OK) {
			ProgramsFilter newFilter = data.getParcelableExtra("filter");
			if (newFilter != null)
				fundsListPresenter.onFilterUpdated(newFilter);
		}
		else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
}
