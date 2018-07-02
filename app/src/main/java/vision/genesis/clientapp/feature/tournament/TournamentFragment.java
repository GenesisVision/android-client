package vision.genesis.clientapp.feature.tournament;

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
import io.swagger.client.model.InvestmentProgramsFilter;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.filters_sorting.SortingFiltersButtonsView;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListAdapter;
import vision.genesis.clientapp.model.InvestmentProgramExtended;
import vision.genesis.clientapp.ui.TournamentRoundsButtonBar;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/05/2018.
 */

public class TournamentFragment extends BaseFragment implements TournamentView
{
	@BindView(R.id.rounds_button_bar)
	public TournamentRoundsButtonBar roundsButtonBar;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.view_sorting_filters_buttons)
	public SortingFiltersButtonsView sortingFiltersButtonsView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.button_try_again)
	public View tryAgainButton;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@InjectPresenter
	public TournamentPresenter tournamentPresenter;

	private ProgramsListAdapter programsAdapter;

	private Unbinder unbinder;

	private int tournamentCurrentRound;

	private int tournamentTotalRounds;

	private boolean sortingFiltersInAnim = false;

	private int lastVisible = 0;

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		tournamentPresenter.onTryAgainClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tournament_programs, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		unbinder = ButterKnife.bind(this, view);

		initRefreshLayout();
		initRecyclerView();
		initSortingFiltersButtonsView();

		roundsButtonBar.setOnRoundSelectedListener(roundNumber -> tournamentPresenter.setCurrentRound(roundNumber));

		if (tournamentTotalRounds > 0)
			roundsButtonBar.setRounds(tournamentCurrentRound, tournamentTotalRounds);
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
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> tournamentPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		programsAdapter = new ProgramsListAdapter();
		programsAdapter.setHasStableIds(true);
		recyclerView.setAdapter(programsAdapter);
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
		sortingFiltersButtonsView.setFiltersUpdateListener(tournamentPresenter);
		sortingFiltersButtonsView.setButtonUpListener(() -> {
			if (lastVisible < 20)
				recyclerView.smoothScrollToPosition(0);
			else
				recyclerView.scrollToPosition(0);
		});
		sortingFiltersButtonsView.disableSorting(getString(R.string.sort_by_place));
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

	private void checkIfLastItemVisible() {
		LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
		int totalItemCount = layoutManager.getItemCount();
		lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
		if (lastVisible < 0)
			return;

		boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
		if (totalItemCount > 0 && endHasBeenReached) {
			tournamentPresenter.onLastListItemVisible();
		}
	}

	@Override
	public void changeProgramIsFavorite(UUID programId, boolean isFavorite) {
		programsAdapter.changeProgramIsFavorite(programId, isFavorite);
	}

	@Override
	public void setTournamentPrograms(List<InvestmentProgramExtended> programs) {
		programsAdapter.setInvestmentPrograms(programs);
		recyclerView.scrollToPosition(0);
	}

	@Override
	public void addTournamentPrograms(List<InvestmentProgramExtended> programs) {
		programsAdapter.addInvestmentPrograms(programs);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, roundsButtonBar);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		if (refreshLayout != null)
			refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showEmptyList(boolean show) {
		emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		tryAgainButton.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showNoInternet(boolean show) {
		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void updateFilter(InvestmentProgramsFilter filter) {
		sortingFiltersButtonsView.setFilter(filter);
	}

	@Override
	public void setProgramsCount(String count) {
		sortingFiltersButtonsView.setCount(count);
	}

	public void setTournamentData(Integer tournamentCurrentRound, Integer tournamentTotalRounds) {
		this.tournamentCurrentRound = tournamentCurrentRound;
		this.tournamentTotalRounds = tournamentTotalRounds;
		if (roundsButtonBar != null)
			roundsButtonBar.setRounds(tournamentCurrentRound, tournamentTotalRounds);
	}
}