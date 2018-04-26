package vision.genesis.clientapp.feature.main.dashboard.programs;

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

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.InvestmentProgramDashboardInvestor;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;

/**
 * GenesisVision
 * Created by Vitaly on 3/13/18.
 */

public class DashboardProgramsFragment extends BaseFragment implements DashboardProgramsView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	public static DashboardProgramsFragment with() {
		DashboardProgramsFragment dashboardProgramsFragment = new DashboardProgramsFragment();
		Bundle arguments = new Bundle(1);
		dashboardProgramsFragment.setArguments(arguments);
		return dashboardProgramsFragment;
	}

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
	public DashboardProgramsPresenter dashboardProgramsPresenter;

	private DashboardProgramsAdapter dashboardProgramsAdapter;

	private Unbinder unbinder;

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		dashboardProgramsPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.button_browse_programs)
	public void onStartInvestingClicked() {
		dashboardProgramsPresenter.onStartInvestingClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dashboard_programs, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

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

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimary),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> dashboardProgramsPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		dashboardProgramsAdapter = new DashboardProgramsAdapter();
		dashboardProgramsAdapter.setHasStableIds(true);
		recyclerView.setAdapter(dashboardProgramsAdapter);
	}

	@Override
	public void setPrograms(List<InvestmentProgramDashboardInvestor> programs) {
		dashboardProgramsAdapter.setInvestorPrograms(programs);

		showEmpty(programs.size() == 0);
	}

	@Override
	public void changeProgramIsFavorite(UUID programId, boolean isFavorite) {
		dashboardProgramsAdapter.changeProgramIsFavorite(programId, isFavorite);
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
		if (dashboardProgramsPresenter != null)
			dashboardProgramsPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}
}