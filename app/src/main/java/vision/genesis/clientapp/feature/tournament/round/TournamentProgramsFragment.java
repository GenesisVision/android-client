package vision.genesis.clientapp.feature.tournament.round;

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
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.InvestmentProgramDashboardInvestor;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.programs.DashboardPagerAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/05/2018.
 */

public class TournamentProgramsFragment extends BaseFragment implements TournamentProgramsView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_ROUND = "extra_round";

	public static TournamentProgramsFragment with(int round) {
		TournamentProgramsFragment tournamentProgramsFragment = new TournamentProgramsFragment();
		Bundle arguments = new Bundle(1);
		arguments.putInt(EXTRA_ROUND, round);
		tournamentProgramsFragment.setArguments(arguments);
		return tournamentProgramsFragment;
	}

	@BindView(R.id.text_round)
	public TextView roundText;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.button_try_again)
	public View tryAgainButton;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@InjectPresenter
	public TournamentProgramsPresenter tournamentProgramsPresenter;

	private TournamentProgramsAdapter tournamentProgramsAdapter;

	private Unbinder unbinder;

	private int round;

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		tournamentProgramsPresenter.onTryAgainClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tournament_programs, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		if (getArguments() != null) {
			round = getArguments().getInt(EXTRA_ROUND);

			unbinder = ButterKnife.bind(this, view);

			roundText.setText(String.format(Locale.getDefault(), "Round %d", round));

			initRefreshLayout();
			initRecyclerView();

			tournamentProgramsPresenter.setRound(round);
		}
		else {
			Timber.e("Passed empty round to TournamentProgramsFragment");
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

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimary),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> tournamentProgramsPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		tournamentProgramsAdapter = new TournamentProgramsAdapter();
		tournamentProgramsAdapter.setHasStableIds(true);
		recyclerView.setAdapter(tournamentProgramsAdapter);
	}

	@Override
	public void setPrograms(List<InvestmentProgramDashboardInvestor> programs) {
		tournamentProgramsAdapter.setInvestorPrograms(programs);

		showEmpty(programs.size() == 0);
	}

	@Override
	public void changeProgramIsFavorite(UUID programId, boolean isFavorite) {
		tournamentProgramsAdapter.changeProgramIsFavorite(programId, isFavorite);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		if (refreshLayout != null)
			refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showEmpty(boolean show) {
		emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		tryAgainButton.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showNoInternet(boolean show) {
		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void pagerShow() {
		if (tournamentProgramsPresenter != null)
			tournamentProgramsPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}
}