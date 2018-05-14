package vision.genesis.clientapp.feature.main.programs_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.InvestmentProgram;
import io.swagger.client.model.InvestmentProgramsFilter;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.filters_sorting.SortingFiltersButtonsView;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class ProgramsListFragment extends BaseFragment implements ProgramsListView
{
	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.view_sorting_filters_buttons)
	public SortingFiltersButtonsView sortingFiltersButtonsView;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@BindView(R.id.button_try_again)
	public View tryAgainButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	ProgramsListPresenter programsListPresenter;

	private boolean sortingFiltersInAnim = false;

	private int lastVisible = 0;

	private ProgramsListAdapter programsListAdapter;

	private Unbinder unbinder;

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		programsListPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.fab)
	public void onFabClicked() {
		if (lastVisible < 20)
			recyclerView.smoothScrollToPosition(0);
		else
			recyclerView.scrollToPosition(0);
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

		initRefreshLayout();
		initRecyclerView();
		initSortingFiltersButtonsView();
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

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimary),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> programsListPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		programsListAdapter = new ProgramsListAdapter();
		programsListAdapter.setHasStableIds(true);
		recyclerView.setAdapter(programsListAdapter);
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				checkIfLastItemVisible();
				updateSortingFiltersVisibility(dy);
			}
		});
	}

	private void initSortingFiltersButtonsView() {
		sortingFiltersButtonsView.setActivity(getActivity());
		sortingFiltersButtonsView.setFiltersUpdateListener(programsListPresenter);
		sortingFiltersButtonsView.setButtonUpListener(() -> {
			if (lastVisible < 20)
				recyclerView.smoothScrollToPosition(0);
			else
				recyclerView.scrollToPosition(0);
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
			programsListPresenter.onLastListItemVisible();
		}
	}

	private void updateSortingFiltersVisibility(int dy) {
		if (!sortingFiltersInAnim && sortingFiltersButtonsView.getVisibility() != View.VISIBLE && dy > 10)
			showSortingFilters();
		else if (!sortingFiltersInAnim && sortingFiltersButtonsView.getVisibility() == View.VISIBLE && dy < -10)
			hideSortingFilters();
	}

	private void showSortingFilters() {
		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_from_bottom);
		animation.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation) {
				sortingFiltersInAnim = true;
				sortingFiltersButtonsView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				sortingFiltersInAnim = false;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		sortingFiltersButtonsView.startAnimation(animation);
	}

	private void hideSortingFilters() {
		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_to_bottom);
		animation.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation) {
				sortingFiltersInAnim = true;
				sortingFiltersButtonsView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				sortingFiltersInAnim = false;
				sortingFiltersButtonsView.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		sortingFiltersButtonsView.startAnimation(animation);
	}

	@Override
	public void setInvestmentPrograms(List<InvestmentProgram> programs) {
		programsListAdapter.setInvestmentPrograms(programs);
		recyclerView.scrollToPosition(0);
	}

	@Override
	public void addInvestmentPrograms(List<InvestmentProgram> programs) {
		programsListAdapter.addInvestmentPrograms(programs);
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
	public void changeProgramIsFavorite(UUID programId, boolean isFavorite) {
		programsListAdapter.changeProgramIsFavorite(programId, isFavorite);
	}

	@Override
	public void updateFilter(InvestmentProgramsFilter filter) {
		sortingFiltersButtonsView.setFilter(filter);
	}

	@Override
	public void setProgramsCount(String count) {
		sortingFiltersButtonsView.setCount(count);
	}

	@Override
	public boolean onBackPressed() {
		return false;
	}
}
